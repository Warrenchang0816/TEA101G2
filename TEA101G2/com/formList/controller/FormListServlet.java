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
import com.member.model.MemberService;
import com.member.model.MemberServiceB;
import com.member.model.MemberVO;


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
				String memberId = req.getParameter("memberId").trim();
				if(memberId == null || memberId.isEmpty()) errorMsgs.add("會員編號: 請勿空白");
				
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
				
				String formListStatus = req.getParameter("formListStatus").trim();
				if(formListStatus == null || formListStatus.isEmpty()) errorMsgs.add("表單狀態: 請勿空白");

				FormListVO addFormList = new FormListVO();
				addFormList.setMemberId(memberId);
				addFormList.setEmpId(empId);
				addFormList.setFormListCreateDate(formListCreateDate);
				addFormList.setFormListType(formListType);
				addFormList.setFormListTitle(formListTitle);
				addFormList.setFormListContext(formListContext);
				addFormList.setFormListFile(formListFile);
				addFormList.setFormListStatus(formListStatus);
				
				
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

				String url = "/backend/formList/formListProfile.jsp";
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
				
				String empId = req.getParameter("empId").trim();
				if(empId == null || empId.isEmpty()) errorMsgs.add("員工編號: 請勿空白");
				
				FormListService formListServ = new FormListService();
				FormListVO updateFormList = formListServ.selectOneFormList(formListId);
				
				String memberId = updateFormList.getMemberId();
				java.sql.Date formListCreateDate = updateFormList.getFormListCreateDate();
				String formListType = updateFormList.getFormListType();
				String formListTitle = updateFormList.getFormListTitle();
				String formListContext = updateFormList.getFormListContext();
				byte[] formListFile = updateFormList.getFormListFile();
				
				String formListStatus = req.getParameter("formListStatus").trim();
				if(formListStatus == null || formListStatus.isEmpty()) errorMsgs.add("表單狀態: 請勿空白");
				
				String formListSolu = req.getParameter("formListSolu").trim();
				if(formListSolu == null || formListSolu.isEmpty()) errorMsgs.add("表單結案: 請勿空白");
				
				java.sql.Date formListSoluDate = null;
				try {
					formListSoluDate = java.sql.Date.valueOf(req.getParameter("formListSoluDate").trim());
				} catch (IllegalArgumentException e) {
					formListSoluDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("表單申請日期: 格式錯誤");
				}
				updateFormList = new FormListVO();
				updateFormList.setFormListId(formListId);
				updateFormList.setMemberId(memberId);
				updateFormList.setEmpId(empId);
				updateFormList.setFormListCreateDate(formListCreateDate);
				updateFormList.setFormListType(formListType);
				updateFormList.setFormListTitle(formListTitle);
				updateFormList.setFormListContext(formListContext);
				updateFormList.setFormListFile(formListFile);
				updateFormList.setFormListStatus(formListStatus);
				updateFormList.setFormListSolu(formListSolu);
				updateFormList.setFormListSoluDate(formListSoluDate);
				
				MemberServiceB msb = new MemberServiceB();
				MemberVO m = msb.selectOneMember(memberId);
				String mName = m.getMemberName();
				FormListVO addMessage = new FormListVO();
				//*MESSAGE
//				MEMBER_ID----MEM00001
//				EMP_ID----EMP00001
//				FORM_LIST_TYPE----message
//				FORM_SOLU----收件會員
//				FORM_STATUS----'R':已讀,'M':未讀
				if("done".equals(formListStatus)) {
					addMessage.setMemberId("MEM00001");
					addMessage.setEmpId("EMP00001");
					addMessage.setFormListCreateDate(new java.sql.Date(System.currentTimeMillis()));
					addMessage.setFormListType("message");
					addMessage.setFormListTitle("客服表單["+formListType+"]: "+formListTitle+"結案通知");
					addMessage.setFormListContext("親愛的會員"+mName+"，您好<br />您日前申請的客服表單已由客服人員處理結案，感謝您的耐心等候，有任何問題都可以聯絡我們，感謝。<br />處理流程: " + formListSolu);
					addMessage.setFormListFile(formListFile);
					addMessage.setFormListStatus("M");
					addMessage.setFormListSolu(memberId);
				}
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateFormList);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/formList/updateFormList.jsp");
					failureView.forward(req, res);
					return;
				}

				formListServ = new FormListService();
				updateFormList = formListServ.updateFormList(updateFormList);
				req.setAttribute("selectOneFormList", updateFormList);
				formListServ.addFormList(addMessage);

				String url = "/backend/formList/formListProfile.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}	
		
		
		if ("backend_SelectOneMail".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String formListId = req.getParameter("formListId").trim();
				String loginEmpId = req.getParameter("loginEmpId").trim();
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/mail/mailBox.jsp");
					failureView.forward(req, res);
					return;
				}

				FormListService formListServ = new FormListService();
				FormListVO selectOneMail = new FormListVO();
				selectOneMail = formListServ.selectOneFormList(formListId);
				if(selectOneMail.getEmpId().equals(loginEmpId)) {
					formListServ.readMail(formListId);
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/mail/mailBox.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOneMail", selectOneMail);

				String url = "/backend/mail/mail.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		
		if ("backend_SendMail".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String formListStatus = req.getParameter("formListStatus").trim(); //M:會員, E:員工
				
				String formListSolu = req.getParameter("formListSolu").trim(); //寄件者
				
				String empId = req.getParameter("empId").trim(); //收件者
				if(empId == null || empId.isEmpty()) errorMsgs.add("收件者請勿空白");
				
				String formListTitle = req.getParameter("formListTitle").trim();
				if(formListTitle == null || formListTitle.isEmpty()) errorMsgs.add("請填mail主旨");
				
				String formListContext = req.getParameter("formListContext").trim();
				
				Part part = req.getPart("formListFile");
				InputStream in = null;
				byte[] formListFile = null;
				String filename = getFileNameFromPart(part);
				if (filename == null || filename.isEmpty()) {
					formListFile = null;
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
				
				FormListVO sendMail = new FormListVO();
				sendMail.setMemberId("MEM00001"); //固定
				sendMail.setEmpId(empId);
				sendMail.setFormListCreateDate(new java.sql.Date(System.currentTimeMillis()));
				sendMail.setFormListType("mail");
				sendMail.setFormListTitle(formListTitle);
				sendMail.setFormListContext(formListContext);
				sendMail.setFormListFile(formListFile);
				sendMail.setFormListStatus("M");
				sendMail.setFormListSolu(formListSolu);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sendMail", sendMail);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/mail/sendMail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				if(empId.contains(";")) {
					String[] idArray = empId.split(";");
					for(String id: idArray) {
						sendMail = new FormListVO();
						sendMail.setMemberId("MEM00001"); //固定
						sendMail.setEmpId(id);
						sendMail.setFormListCreateDate(new java.sql.Date(System.currentTimeMillis()));
						sendMail.setFormListType("mail");
						sendMail.setFormListTitle(formListTitle);
						sendMail.setFormListContext(formListContext);
						sendMail.setFormListFile(formListFile);
						sendMail.setFormListStatus("M");
						sendMail.setFormListSolu(formListSolu);
						
						FormListService formListServ = new FormListService();
						sendMail = formListServ.addFormList(sendMail);
					}
				}else {
					sendMail = new FormListVO();
					sendMail.setMemberId("MEM00001"); //固定
					sendMail.setEmpId(empId);
					sendMail.setFormListCreateDate(new java.sql.Date(System.currentTimeMillis()));
					sendMail.setFormListType("mail");
					sendMail.setFormListTitle(formListTitle);
					sendMail.setFormListContext(formListContext);
					sendMail.setFormListFile(formListFile);
					sendMail.setFormListStatus("M");
					sendMail.setFormListSolu(formListSolu);
					
					FormListService formListServ = new FormListService();
					sendMail = formListServ.addFormList(sendMail);
				}

				String url = "/backend/mail/sendBox.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		
		if ("backend_MailToTrash".equals(action)) {
			try {
				//String formListStatus = req.getParameter("formListStatus").trim(); //M:會員, E:員工
				
				String[] checked = req.getParameterValues("checked");
				System.out.println(checked.toString());
				for(String formListId: checked) {
					System.out.println(formListId);
					FormListService formListServ = new FormListService();
					FormListVO formListVO = formListServ.selectOneFormList(formListId);
					FormListVO updateFormList = new FormListVO();
					updateFormList.setFormListId(formListId);
					updateFormList.setMemberId(formListVO.getMemberId());
					updateFormList.setEmpId(formListVO.getEmpId());
					updateFormList.setFormListCreateDate(formListVO.getFormListCreateDate());
					updateFormList.setFormListType(formListVO.getFormListType());
					updateFormList.setFormListTitle(formListVO.getFormListTitle());
					updateFormList.setFormListContext(formListVO.getFormListContext());
					updateFormList.setFormListFile(formListVO.getFormListFile());
					updateFormList.setFormListStatus("trash");
					updateFormList.setFormListSolu(formListVO.getFormListSolu());
					updateFormList.setFormListSoluDate(formListVO.getFormListSoluDate());
					formListServ.updateFormList(updateFormList);
				}
				

				String url = "/backend/mail/mailBox.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}
		
		
		if ("backend_DeleteMail".equals(action)) {
			try {
				String[] checked = req.getParameterValues("checked");
				for(String formListId: checked) {
					FormListService formListServ = new FormListService();
					formListServ.deleteFormList(formListId);
				}

				String url = "/backend/mail/trashBox.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}
		
		if ("backend_ReadMail".equals(action)) {
			try {
				String formListId = req.getParameter("formListId");
				
				FormListService fls = new FormListService();
				fls.readMail(formListId);
				
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		

		
//======================================================================================================================================

		
		if ("addFormList".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberId = req.getParameter("memberId").trim();
				
				String formListType = req.getParameter("formListType").trim();
				
				String contactPhone = req.getParameter("contactPhone").trim();
				String phoneRegEx = "09\\d{2}\\d{6}";
				if(!contactPhone.equals("")) {
					if(contactPhone.trim().matches(phoneRegEx)) {
						contactPhone = "contactPhone:" + req.getParameter("contactPhone").trim();
					}else {
						errorMsgs.add("電話格式錯誤");
					}
					contactPhone = req.getParameter("contactPhone").trim();
				}
				
				String contactEmail = req.getParameter("contactEmail").trim();
				String empEmailRegEx = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
				if(!contactEmail.equals("")) {
					if(contactEmail.trim().matches(empEmailRegEx)) {
						contactEmail = "contactEmail:" + req.getParameter("contactEmail").trim();
					}else {
						errorMsgs.add("Mail格式錯誤");
					}
					contactEmail = req.getParameter("contactEmail").trim();
				}
				
				String formListTitle = req.getParameter("formListTitle").trim();
				if(formListTitle == null || formListTitle.isEmpty()) errorMsgs.add("客服表單主旨: 請勿空白");
				
				String formListContextforMessage = req.getParameter("formListContext").trim();
				String formListContext = req.getParameter("formListContext").trim();
				if(formListContext == null || formListContext.isEmpty()) errorMsgs.add("客服表單內容: 請勿空白");
				 else formListContext = contactPhone + ";" + contactEmail + ";" + req.getParameter("formListContext").trim(); 
				
				System.out.println(formListContext);
				
				Part part = req.getPart("formListFile");
				InputStream in = null;
				byte[] formListFile = null;
				String filename = getFileNameFromPart(part);
				if (filename == null || filename.isEmpty()) {
//					File file = new File(getServletContext().getRealPath("/") + "/backend/img/BlobTest3.jpg");
//					FileInputStream fis = new FileInputStream(file);
//					ByteArrayOutputStream baos = new ByteArrayOutputStream();  
//					byte[] buffer = new byte[8192];
//					int i;
//					while ((i = fis.read(buffer)) != -1) {
//						baos.write(buffer, 0, i);
//					}
//					formListFile = baos.toByteArray();
//					baos.close();
//					fis.close();
					formListFile = null;
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
				addFormList.setMemberId(memberId);
				addFormList.setEmpId("");
				addFormList.setFormListCreateDate(new java.sql.Date(System.currentTimeMillis()));
				addFormList.setFormListType(formListType);
				addFormList.setFormListTitle(formListTitle);
				addFormList.setFormListContext(formListContext);
				addFormList.setFormListStatus("undo");
				addFormList.setFormListFile(formListFile);
				
				MemberServiceB msb = new MemberServiceB();
				MemberVO m = msb.selectOneMember(memberId);
				String mName = m.getMemberName();
				
				//*MESSAGE
//				MEMBER_ID----MEM00001
//				EMP_ID----EMP00001
//				FORM_LIST_TYPE----message
//				FORM_SOLU----收件會員
//				FORM_STATUS----'R':已讀,'M':未讀
				
				FormListVO addMessage = new FormListVO();
				addMessage.setMemberId("MEM00001");
				addMessage.setEmpId("EMP00001");
				addMessage.setFormListCreateDate(new java.sql.Date(System.currentTimeMillis()));
				addMessage.setFormListType("message");
				addMessage.setFormListTitle("客服表單["+formListType+"]: "+formListTitle+"提交成功");
				addMessage.setFormListContext("親愛的會員"+mName+"，您好<br />您申請的客服表單已成功提交，目前客服人員處理中，請耐心等候回覆，感謝。<br />表單內容: " + formListContextforMessage);
				addMessage.setFormListFile(formListFile);
				addMessage.setFormListStatus("M");
				addMessage.setFormListSolu(memberId);
				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addFormList", addFormList);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/formList/addFormList.jsp");
					failureView.forward(req, res);
					return;
				}

				FormListService formListServ = new FormListService();
				addFormList = formListServ.addFormList(addFormList);
				formListServ.addFormList(addMessage);
				
				String url = "/frontend/formList/faq.jsp";
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
