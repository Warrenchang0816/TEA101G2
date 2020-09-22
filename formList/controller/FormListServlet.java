package com.formList.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import com.formList.model.FormListService;
import com.formList.model.FormListVO;


@WebServlet("/FormListServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class FormListServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
       
    public FormListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("backend_AddFormList".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String membrId = req.getParameter("membrId").trim();
				if(membrId == null || membrId.isEmpty()) errorMsgs.add("會員編號: 請勿空白");
				
				String empId = req.getParameter("empId").trim();
				if(empId == null || empId.isEmpty()) errorMsgs.add("員工編號: 請勿空白");
				
				java.sql.Date formListCreateDate = null;
				try {
					formListCreateDate = java.sql.Date.valueOf(req.getParameter("formListCreateDate").trim());
				} catch (IllegalArgumentException e) {
					formListCreateDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("表單申請日期: 格式錯誤");
				}
				
				String formListType = req.getParameter("formListType").trim();
				if(formListType == null || formListType.isEmpty()) errorMsgs.add("客服表單類型: 請勿空白");
				
				String formListTitle = req.getParameter("formListTitle").trim();
				if(formListTitle == null || formListTitle.isEmpty()) errorMsgs.add("客服表單主旨: 請勿空白");
				
				String formListContext = req.getParameter("formListContext").trim();
				if(formListContext == null || formListContext.isEmpty()) errorMsgs.add("客服表單內容: 請勿空白");
				
				Part part = req.getPart("formListFile");
				InputStream in = null;
				byte[] formListFile = null;
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
					formListFile = baos.toByteArray();
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
					formListFile = baos.toByteArray();
					baos.close();
					in.close();
				}

				FormListVO addFormList = new FormListVO();
				addFormList.setMembrId(membrId);
				addFormList.setEmpId(empId);
				addFormList.setFormListCreateDate(formListCreateDate);
				addFormList.setFormListType(formListType);
				addFormList.setFormListTitle(formListTitle);
				addFormList.setFormListContext(formListContext);
				addFormList.setFormListFile(formListFile);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addFormList", addFormList);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/formList/addFormList.jsp");
					failureView.forward(req, res);
					return;
				}

				FormListService formListServ = new FormListService();
				addFormList = formListServ.addFormList(addFormList);

				String url = "/backend/formList/selectAllFormList.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_SelectOneFormList".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String formListId = req.getParameter("formListId").trim();
				if(formListId == null || formListId.isEmpty()) errorMsgs.add("客服表單編號: 請勿空白");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/formList/formList.jsp");
					failureView.forward(req, res);
					return;
				}

				FormListService formListServ = new FormListService();
				FormListVO selectOneFormList = new FormListVO();
				selectOneFormList = formListServ.selectOneFormList(formListId);
				
				if (selectOneFormList == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/formList/formList.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOneFormList", selectOneFormList);

				String url = "/backend/formList/selectOneFormList.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_DeleteFormList".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String formListId = req.getParameter("formListId").trim();

				FormListService formListServ = new FormListService();
//				SupplyListVO deleteSupplyList = new SupplyListVO();
				formListServ.deleteFormList(formListId);

				String url = "/backend/formList/selectAllFormList.jsp";
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
				String formListId = req.getParameter("formListId").trim();

				FormListService formListServ = new FormListService();
				FormListVO selectOneUpdate = new FormListVO();
				selectOneUpdate = formListServ.selectOneFormList(formListId);
				req.setAttribute("selectOneUpdate", selectOneUpdate);

				String url = "/backend/formList/updateFormList.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_UpdateFormList".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String formListId = req.getParameter("formListId").trim();
				
				String membrId = req.getParameter("membrId").trim();
				if(membrId == null || membrId.isEmpty()) errorMsgs.add("會員編號: 請勿空白");
				
				String empId = req.getParameter("empId").trim();
				if(empId == null || empId.isEmpty()) errorMsgs.add("員工編號: 請勿空白");
				
				java.sql.Date formListCreateDate = null;
				try {
					formListCreateDate = java.sql.Date.valueOf(req.getParameter("formListCreateDate").trim());
				} catch (IllegalArgumentException e) {
					formListCreateDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("表單申請日期: 格式錯誤");
				}
				
				String formListType = req.getParameter("formListType").trim();
				if(formListType == null || formListType.isEmpty()) errorMsgs.add("客服表單類型: 請勿空白");
				
				String formListTitle = req.getParameter("formListTitle").trim();
				if(formListTitle == null || formListTitle.isEmpty()) errorMsgs.add("客服表單主旨: 請勿空白");
				
				String formListContext = req.getParameter("formListContext").trim();
				if(formListContext == null || formListContext.isEmpty()) errorMsgs.add("客服表單內容: 請勿空白");
				
				Part part = req.getPart("formListFile");
				InputStream in = null;
				byte[] formListFile = null;
				String filename = getFileNameFromPart(part);
				if (filename == null || filename.isEmpty()) {
//				if (part == null) {
					FormListService spacePhotoServ = new FormListService();
					FormListVO spaceOriginPhoto = spacePhotoServ.selectOneFormList(formListId);
					formListFile = spaceOriginPhoto.getFormListFile();
				} else {
					in = part.getInputStream();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte[] buffer = new byte[8192];
					int i;
					while ((i = in.read(buffer)) != -1) {
						baos.write(buffer, 0, i);
					}
					formListFile = baos.toByteArray();
					baos.close();
					in.close();
				}

				FormListVO updateFormList = new FormListVO();
				updateFormList.setFormListId(formListId);
				updateFormList.setMembrId(membrId);
				updateFormList.setEmpId(empId);
				updateFormList.setFormListCreateDate(formListCreateDate);
				updateFormList.setFormListType(formListType);
				updateFormList.setFormListTitle(formListTitle);
				updateFormList.setFormListContext(formListContext);
				updateFormList.setFormListFile(formListFile);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateFormList);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/formList/updateFormList.jsp");
					failureView.forward(req, res);
					return;
				}

				FormListService formListServ = new FormListService();
				updateFormList = formListServ.updateFormList(updateFormList);
//				req.setAttribute("updateSpacePhoto", updateSpacePhoto);

				String url = "/backend/formList/selectAllFormList.jsp";
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
