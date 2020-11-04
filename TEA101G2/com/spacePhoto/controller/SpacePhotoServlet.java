package com.spacePhoto.controller;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.space.model.SpaceService;
import com.space.model.SpaceVO;
import com.spaceDetail.model.SpaceDetailService;
import com.spaceDetail.model.SpaceDetailVO;
import com.spacePhoto.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/spacephoto/spacephoto.do")
public class SpacePhotoServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自SpaceDetail_Home.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("spacePhotoId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入場地圖片ID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacephoto/spacePhotoHome.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				SpacePhotoService spacePhotoSvc = new SpacePhotoService();
				SpacePhotoVO spacePhotoVO = spacePhotoSvc.selectOneSpacePhoto(str);
				if (spacePhotoVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacephoto/spacePhotoHome.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("spacePhotoVO", spacePhotoVO); 
				String url = "/frontend/spacephoto/listOneSpacePhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacephoto/spacePhotoHome.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllSpaceDetail.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String spacePhotoId = req.getParameter("spacePhotoId");

				/*************************** 2.開始查詢資料 ****************************************/
				SpacePhotoService spacePhotoSvc = new SpacePhotoService();
				SpacePhotoVO spacePhotoVO = spacePhotoSvc.selectOneSpacePhoto(spacePhotoId);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("spacePhotoVO", spacePhotoVO);
				String url = "/frontend/spacephoto/updateSpacePhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacephoto/listAllSpacePhoto.jsp");
				failureView.forward(req, res);
			}
		}
		/***************************** 場主按下"編輯場地照片"按鈕後執行 *****************************/
		if ("listAllSpacePhotoBySpaceForEdit".equals(action)) {
			Queue<String> errorMessages = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMessages);
			
			try {
				/********************************* 設定spaceId **************************************/
				String spaceId = req.getParameter("spaceId");
				if (spaceId == null || (spaceId.trim()).length() == 0) {
					errorMessages.add("請輸入場地ID");
				}
				// Send the use back to the form, if there were errors
				if (!errorMessages.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/space/spaceHome.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
//				/************************************* 開始撈資料 *************************************************/
				SpaceService spaceSvc = new SpaceService();
				SpaceVO spaceVO = spaceSvc.selectOneSpace(spaceId);
				if (spaceVO == null) {
					errorMessages.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMessages.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacedetail/spaceDetailHome");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 查詢完成,準備轉交(Send the Success view) ********************************/
				req.setAttribute("spaceVO", spaceVO);
				String url = "/frontend/spacephoto/listAllSpacePhotoBySpaceForEdit.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMessages.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { 
			List<String> errorMessages = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMessages);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String spacePhotoId = new String(req.getParameter("spacePhotoId").trim());
				if (spacePhotoId == null || spacePhotoId.trim().length() == 0) {
					errorMessages.add("場地圖片ID請勿空白");
				}

				String spaceId = req.getParameter("spaceId");
				if (spaceId == null || spaceId.trim().length() == 0) {
					errorMessages.add("場地ID請勿空白");
				}

//				修改照片，若無檔案則預設塞入原本的照片
				byte[] spacePhoto = null;
				Part part = req.getPart("spacePhoto");
				InputStream in = part.getInputStream();
				String filename = getFileNameFromPart(part);
				if(filename == null || filename.isEmpty()) {
					SpacePhotoDAO_interface dao = new SpacePhotoDAO();
					SpacePhotoVO spacePhotoVO = dao.selectOne(req.getParameter("spacePhotoId"));
					spacePhoto = spacePhotoVO.getSpacePhoto();
				}else {
					spacePhoto = new byte[in.available()];
					in.read(spacePhoto);
					in.close();
				}
				
				SpacePhotoVO spacePhotoVO = new SpacePhotoVO();
				spacePhotoVO.setSpacePhotoId(spacePhotoId);
				spacePhotoVO.setSpaceId(spaceId);
				spacePhotoVO.setSpacePhoto(spacePhoto);
				
				// Send the use back to the form, if there were errors
				if (!errorMessages.isEmpty()) {
					req.setAttribute("spacePhotoVO", spacePhotoVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacephoto/updateSpacePhoto.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				SpacePhotoService spacePhotoSvc = new SpacePhotoService();
				spacePhotoVO = spacePhotoSvc.updateSpacePhoto(spacePhotoVO);
				System.out.println(spacePhotoVO);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("spacePhotoVO", spacePhotoVO); 
				String url = "/frontend/spacephoto/listOneSpacePhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneSpacePhoto.jsp
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMessages.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacephoto/updateSpacePhoto.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { 
			List<String> errorMessages = new LinkedList<String>();
			 //Store this set in the request scope, in case we need to
			 //send the ErrorPage view.
			req.setAttribute("errorMessages", errorMessages);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String spaceId = req.getParameter("spaceId");
				
				if (spaceId == null || spaceId.trim().length() == 0) {
					errorMessages.add("場地ID請勿空白");
				}
				
				List<SpacePhotoVO> spacePhotoFileList = new ArrayList<SpacePhotoVO>();
				Part partCheck = req.getPart("spacePhoto");
//				Part spacePhoto = req.getPart("spacePhoto");
//				SpacePhotoVO sp = new SpacePhotoVO();
				String filename = getFileNameFromPart(partCheck);
				if (filename == null || filename.isEmpty()) {
					File file = new File(getServletContext().getRealPath("/") + "/frontend/spacephoto/images/tomcat.png");
					FileInputStream fis = new FileInputStream(file);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();  
					byte[] buffer = new byte[8192];
					int i;
					while ((i = fis.read(buffer)) != -1) {
						baos.write(buffer, 0, i);
					}
					byte[] spacePhoto = baos.toByteArray();
					baos.close();
					fis.close();
					
					SpacePhotoService spacePhotoSvc = new SpacePhotoService();
					SpacePhotoVO sp = new SpacePhotoVO();
					sp.setSpaceId(spaceId);
					sp.setSpacePhoto(spacePhoto);
					spacePhotoSvc.addSpacePhoto(sp);
				}else {
					Collection<Part> parts = req.getParts();
					List<Part> allPartlist = new ArrayList<Part>();
					Part[] allPartArray = parts.toArray(new Part[parts.size()]);
					for(Part p: allPartArray) {
						System.out.println("allPartArrayname: " + p.getName());
						System.out.println("allPartArraysubmittedFileName: " + p.getSubmittedFileName());
						allPartlist.add(p);
					}
					
					List<Part> photoList = allPartlist.stream().filter(ap -> ap.getSubmittedFileName() != null).collect(Collectors.toList());
					
					for(Part photo: photoList) {
						System.out.println("fileListname: " + photo.getName());
						System.out.println("fileListsubmittedFileName: " + photo.getSubmittedFileName());
						
						InputStream in = null;
						SpacePhotoVO sp = new SpacePhotoVO();
						in = photo.getInputStream();
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] buffer = new byte[8192];
						int i;
						while ((i = in.read(buffer)) != -1) {
							baos.write(buffer, 0, i);
						}

						
						baos.close();
						in.close();
						
						SpacePhotoService spacePhotoSvc = new SpacePhotoService();
						byte[] spacePhotoByte = baos.toByteArray();
						sp.setSpaceId(spaceId);
						sp.setSpacePhoto(spacePhotoByte);
						spacePhotoSvc.addSpacePhoto(sp);
//						spacePhotoFileList.add(sp);
					}
				}
				

////				新增一個含有照片資料的spacePhoto
//				byte[] spacePhoto = null;
//				Part part = req.getPart("spacePhoto");
//				InputStream in = part.getInputStream();
//				String filename = getFileNameFromPart(part);
////				若無上傳照片則塞入預設照片
//				if(filename == null || filename.isEmpty()) {
//					File file = new File(getServletContext().getRealPath("/") + "/frontend/spacephoto/images/tomcat.png");
//					FileInputStream fis = new FileInputStream(file);
//					ByteArrayOutputStream baos = new ByteArrayOutputStream();  
//					byte[] buffer = new byte[8192];
//					int i;
//					while ((i = fis.read(buffer)) != -1) {
//						baos.write(buffer, 0, i);
//					}
//					spacePhoto = baos.toByteArray();
//					baos.close();
//					fis.close();
//				}else {
//					spacePhoto = new byte[in.available()];
//					in.read(spacePhoto);
//					in.close();
//				}
				
//				SpacePhotoVO spacePhotoVO = new SpacePhotoVO();
//				spacePhotoVO.setSpaceId(spaceId);
//				spacePhotoVO.setSpacePhoto(spacePhoto);

				
//				 Send the use back to the form, if there were errors
//				if (!errorMessages.isEmpty()) {
//					req.setAttribute("spacePhotoVO", spacePhotoVO);
//					RequestDispatcher failureView = req.getRequestDispatcher("/spacephoto/updateSpacePhoto.jsp");
//					failureView.forward(req, res);
//					return; // 程式中斷
//				}
				/*************************** 2.開始新增資料 ***************************************/
//				SpacePhotoService spacePhotoSvc = new SpacePhotoService();
//				spacePhotoVO = spacePhotoSvc.addSpacePhoto(spacePhotoVO);
				/***************************** 開始新增SpaceVO資料，後續頁面要接************************************/
				SpaceService spaceSvc = new SpaceService();
				SpaceVO spaceVO = new SpaceVO();
				spaceVO = spaceSvc.selectOneSpace(spaceId);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				req.setAttribute("spacePhotoFileList", spacePhotoFileList);
				req.setAttribute("spaceVO", spaceVO);
				String url = "/frontend/space/listOneSpace.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMessages.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacephoto/listAllSpacePhotoBySpaceForEdit.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String spacePhotoId = new String(req.getParameter("spacePhotoId"));
				String spaceId = new String(req.getParameter("spaceId"));

				/*************************** 2.開始刪除資料 ***************************************/
				SpacePhotoService spacePhotoSvc = new SpacePhotoService();
				spacePhotoSvc.deleteSpacePhoto(spacePhotoId);
				SpaceService ss = new SpaceService();
				SpaceVO spaceVO = ss.selectOneSpace(spaceId);
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("spaceVO", spaceVO);
				String url = "/frontend/space/listOneSpace.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacephoto/listAllSpacePhotoBySpaceForEdit.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
//		System.out.println(header);
		String filename = header.substring(header.lastIndexOf("=") + 2, header.length() - 1);
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
