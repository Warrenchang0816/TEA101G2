package com.spacePhoto.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.spacePhoto.model.SpacePhotoService;
import com.spacePhoto.model.SpacePhotoVO;


@WebServlet("/SpacePhotoServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class SpacePhotoServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
       
    public SpacePhotoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("backend_AddSpacePhoto".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceId = req.getParameter("spaceId").trim();
				if(spaceId == null || spaceId.isEmpty()) errorMsgs.add("場地編號: 請勿空白");
				
				Part part = req.getPart("spacePhoto");
				InputStream in = null;
				byte[] spacePhoto = null;
				String filename = getFileNameFromPart(part);
				if (filename == null || filename.isEmpty()) {
//					if (part == null) {
					File file = new File(getServletContext().getRealPath("/") + "/backend/img/BlobTest3.jpg");
					FileInputStream fis = new FileInputStream(file);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();  
					byte[] buffer = new byte[8192];
					int i;
					while ((i = fis.read(buffer)) != -1) {
						baos.write(buffer, 0, i);
					}
					spacePhoto = baos.toByteArray();
					baos.close();
					fis.close();
				} else {
					in = part.getInputStream();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte[] buffer = new byte[8192];
					int i;
					while ((i = in.read(buffer)) != -1) {
						baos.write(buffer, 0, i);
					}
					spacePhoto = baos.toByteArray();
					baos.close();
					in.close();
				}

				SpacePhotoVO addSpacePhoto = new SpacePhotoVO();
				addSpacePhoto.setSpaceId(spaceId);
				addSpacePhoto.setSpacePhoto(spacePhoto);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addSpacePhoto", addSpacePhoto);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spacePhoto/addSpacePhoto.jsp");
					failureView.forward(req, res);
					return;
				}

				SpacePhotoService spacePhotoServ = new SpacePhotoService();
				addSpacePhoto = spacePhotoServ.addSpacePhoto(addSpacePhoto);

				String url = "/backend/spacePhoto/selectAllSpacePhoto.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_SelectOneSpacePhoto".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spacePhotoId = req.getParameter("spacePhotoId").trim();
				if(spacePhotoId == null || spacePhotoId.isEmpty()) errorMsgs.add("場地評價編號: 請勿空白");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spacePhoto/spacePhoto.jsp");
					failureView.forward(req, res);
					return;
				}

				SpacePhotoService spacePhotoServ = new SpacePhotoService();
				SpacePhotoVO selectOneSpacePhoto = new SpacePhotoVO();
				selectOneSpacePhoto = spacePhotoServ.selectOneSpacePhoto(spacePhotoId);
				
				if (selectOneSpacePhoto == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spacePhoto/spacePhoto.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOneSpacePhoto", selectOneSpacePhoto);

				String url = "/backend/spacePhoto/selectOneSpacePhoto.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_DeleteSpacePhoto".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spacePhotoId = req.getParameter("spacePhotoId").trim();

				SpacePhotoService spacePhotoServ = new SpacePhotoService();
				SpacePhotoVO deleteSpace = new SpacePhotoVO();
				spacePhotoServ.deleteSpacePhoto(spacePhotoId);

				String url = "/backend/spacePhoto/selectAllSpacePhoto.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_SelectOneUpdate".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spacePhotoId = req.getParameter("spacePhotoId").trim();

				SpacePhotoService spacePhotoServ = new SpacePhotoService();
				SpacePhotoVO selectOneUpdate = new SpacePhotoVO();
				selectOneUpdate = spacePhotoServ.selectOneSpacePhoto(spacePhotoId);
				req.setAttribute("selectOneUpdate", selectOneUpdate);

				String url = "/backend/spacePhoto/updateSpacePhoto.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_UpdateSpacePhoto".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spacePhotoId = req.getParameter("spacePhotoId").trim();
				String spaceId = req.getParameter("spaceId").trim();
				if (spaceId == null || spaceId.isEmpty()) errorMsgs.add("場地編號: 請勿空白");

				Part part = req.getPart("spacePhoto");
				InputStream in = null;
				byte[] spacePhoto = null;
				String filename = getFileNameFromPart(part);
				if (filename == null || filename.isEmpty()) {
//				if (part == null) {
//					errorMsgs.add("場地圖片: 請勿空白");
					SpacePhotoService spacePhotoServ = new SpacePhotoService();
					SpacePhotoVO spaceOriginPhoto = spacePhotoServ.selectOneSpacePhoto(spacePhotoId);
					spacePhoto = spaceOriginPhoto.getSpacePhoto();
				} else {
					in = part.getInputStream();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte[] buffer = new byte[8192];
					int i;
					while ((i = in.read(buffer)) != -1) {
						baos.write(buffer, 0, i);
					}
					spacePhoto = baos.toByteArray();
					baos.close();
					in.close();
				}

				SpacePhotoVO updateSpacePhoto = new SpacePhotoVO();
				updateSpacePhoto.setSpacePhotoId(spacePhotoId);
				updateSpacePhoto.setSpaceId(spaceId);
				updateSpacePhoto.setSpacePhoto(spacePhoto);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateSpacePhoto);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spacePhoto/updateSpacePhoto.jsp");
					failureView.forward(req, res);
					return;
				}

				SpacePhotoService spacePhotoServ = new SpacePhotoService();
				updateSpacePhoto = spacePhotoServ.updateSpacePhoto(updateSpacePhoto);
//				req.setAttribute("updateSpacePhoto", updateSpacePhoto);

				String url = "/backend/spacePhoto/selectAllSpacePhoto.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		

		
//======================================================================================================================================
		

		
		
		if ("addSpacePhoto".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spaceId = req.getParameter("spaceId").trim();
				if(spaceId == null || spaceId.isEmpty()) errorMsgs.add("場地編號: 請勿空白");
				
				Part part = req.getPart("spacePhoto");
				InputStream in = null;
				byte[] spacePhoto = null;
				String filename = getFileNameFromPart(part);
				if (filename == null || filename.isEmpty()) {
//					if (part == null) {
//					errorMsgs.add("員工圖片: 請勿空白");
					File file = new File(getServletContext().getRealPath("/") + "/backend/img/BlobTest3.jpg");
					FileInputStream fis = new FileInputStream(file);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();  
					byte[] buffer = new byte[8192];
					int i;
					while ((i = fis.read(buffer)) != -1) {
						baos.write(buffer, 0, i);
					}
					spacePhoto = baos.toByteArray();
					baos.close();
					fis.close();
				} else {
					in = part.getInputStream();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte[] buffer = new byte[8192];
					int i;
					while ((i = in.read(buffer)) != -1) {
						baos.write(buffer, 0, i);
					}
					spacePhoto = baos.toByteArray();
					baos.close();
					in.close();
				}

				SpacePhotoVO addSpacePhoto = new SpacePhotoVO();
				addSpacePhoto.setSpaceId(spaceId);
				addSpacePhoto.setSpacePhoto(spacePhoto);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addSpacePhoto", addSpacePhoto);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacePhoto/addSpacePhoto.jsp");
					failureView.forward(req, res);
					return;
				}

				SpacePhotoService spacePhotoServ = new SpacePhotoService();
				addSpacePhoto = spacePhotoServ.addSpacePhoto(addSpacePhoto);

				String url = "/frontend/spacePhoto/selectAllSpacePhoto.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("selectOneSpacePhoto".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spacePhotoId = req.getParameter("spacePhotoId").trim();
				if(spacePhotoId == null || spacePhotoId.isEmpty()) errorMsgs.add("場地評價編號: 請勿空白");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacePhoto/spacePhoto.jsp");
					failureView.forward(req, res);
					return;
				}

				SpacePhotoService spacePhotoServ = new SpacePhotoService();
				SpacePhotoVO selectOneSpacePhoto = new SpacePhotoVO();
				selectOneSpacePhoto = spacePhotoServ.selectOneSpacePhoto(spacePhotoId);
				
				if (selectOneSpacePhoto == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacePhoto/spacePhoto.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOneSpacePhoto", selectOneSpacePhoto);

				String url = "/frontend/spacePhoto/selectOneSpacePhoto.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("deleteSpacePhoto".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spacePhotoId = req.getParameter("spacePhotoId").trim();

				SpacePhotoService spacePhotoServ = new SpacePhotoService();
				SpacePhotoVO deleteSpace = new SpacePhotoVO();
				spacePhotoServ.deleteSpacePhoto(spacePhotoId);

				String url = "/frontend/spacePhoto/selectAllSpacePhoto.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("selectOneUpdate".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spacePhotoId = req.getParameter("spacePhotoId").trim();

				SpacePhotoService spacePhotoServ = new SpacePhotoService();
				SpacePhotoVO selectOneUpdate = new SpacePhotoVO();
				selectOneUpdate = spacePhotoServ.selectOneSpacePhoto(spacePhotoId);
				req.setAttribute("selectOneUpdate", selectOneUpdate);

				String url = "/frontend/spacePhoto/updateSpacePhoto.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("updateSpacePhoto".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String spacePhotoId = req.getParameter("spacePhotoId").trim();
				String spaceId = req.getParameter("spaceId").trim();
				if (spaceId == null || spaceId.isEmpty()) errorMsgs.add("場地編號: 請勿空白");

				Part part = req.getPart("spacePhoto");
				InputStream in = null;
				byte[] spacePhoto = null;
				String filename = getFileNameFromPart(part);
				if (filename == null || filename.isEmpty()) {
//				if (part == null) {
//					errorMsgs.add("場地圖片: 請勿空白");
					SpacePhotoService spacePhotoServ = new SpacePhotoService();
					SpacePhotoVO spaceOriginPhoto = spacePhotoServ.selectOneSpacePhoto(spacePhotoId);
					spacePhoto = spaceOriginPhoto.getSpacePhoto();
				} else {
					in = part.getInputStream();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte[] buffer = new byte[8192];
					int i;
					while ((i = in.read(buffer)) != -1) {
						baos.write(buffer, 0, i);
					}
					spacePhoto = baos.toByteArray();
					baos.close();
					in.close();
				}

				SpacePhotoVO updateSpacePhoto = new SpacePhotoVO();
				updateSpacePhoto.setSpacePhotoId(spacePhotoId);
				updateSpacePhoto.setSpaceId(spaceId);
				updateSpacePhoto.setSpacePhoto(spacePhoto);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateSpacePhoto);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spacePhoto/updateSpacePhoto.jsp");
					failureView.forward(req, res);
					return;
				}

				SpacePhotoService spacePhotoServ = new SpacePhotoService();
				updateSpacePhoto = spacePhotoServ.updateSpacePhoto(updateSpacePhoto);
//				req.setAttribute("updateSpacePhoto", updateSpacePhoto);

				String url = "/frontend/spacePhoto/selectAllSpacePhoto.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
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
