package com.emp.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;


@WebServlet("/EmpServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class EmpServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
       
    public EmpServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("backend_AddEmp".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			EmpService empServ = new EmpService();
			
//			try {
				String empAccount = req.getParameter("empAccount").trim();
				String empAccountRegEx1 = "^(?=.*\\p{Upper}).{6,16}$";
				String empAccountRegEx2 = "^\\w{4,16}$";
				if(empAccount == null || empAccount.isEmpty()) {
					errorMsgs.add("請輸入帳號");
				}else if(!empAccount.trim().matches(empAccountRegEx1)) {
					errorMsgs.add("帳號至少包含一個大寫英文字母");
				}else if(!empAccount.trim().matches(empAccountRegEx2)) {
					errorMsgs.add("帳號格式不符");
				}else if(empServ.isEmpAccountLogin(empAccount)) {
					errorMsgs.add("此帳號已被使用");
				}
				req.setAttribute("empAccount", empAccount); 
				
				String empPassword = req.getParameter("empPassword").trim();
				String empPasswordRegEx = "^\\w{6,16}$";
				if(empPassword == null || empPassword.isEmpty()) {
					errorMsgs.add("請輸入密碼");
				}else if(!empPassword.trim().matches(empPasswordRegEx)) {
					errorMsgs.add("密碼格式不符");
				}
				
				String empName = req.getParameter("empName").trim();
				String empNameRegEx = "^[\\p{Alpha}\\u4E00-\\u9FA5]{0,20}$";
				if(empName == null || empName.isEmpty()) {
					errorMsgs.add("請輸入姓名");
				}else if(!empName.trim().matches(empNameRegEx)) {
					errorMsgs.add("姓名只能為中文或英文");
				}
				
				String empSex = req.getParameter("empSex").trim();
				if(empSex == null || empSex.equals("")) {
					errorMsgs.add("請選擇性別");
				}
				System.out.println(empSex);
				
//				String empNickname = req.getParameter("empNickname").trim();
//				if(empNickname == null || empNickname.isEmpty()) {
//					errorMsgs.add("員工名稱: 請勿空白");
//				}
				String empNickname = "新鮮的肝";
				
				String empPhone = req.getParameter("empPhone").trim();
				String empPhoneRegEx = "09\\d{2}\\d{6}";
				if(empPhone == null || empPhone.isEmpty()) {
					errorMsgs.add("請輸入電話");
				}else if(!empPhone.trim().matches(empPhoneRegEx)) {
					errorMsgs.add("電話格式不符");
				}
				
				String empEmail = req.getParameter("empEmail").trim();
				String empEmailRegEx = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
				if(empEmail == null || empEmail.isEmpty()) {
					errorMsgs.add("請輸入email");
				}else if(!empEmail.trim().matches(empEmailRegEx)) {
					errorMsgs.add("email格式不符");
				}
				
				Part part = req.getPart("empPhoto");
				System.out.println(part);
				InputStream in = null;
				byte[] empPhoto = null;
				String filename = getFileNameFromPart(part);
				if (filename == null || filename.isEmpty()) {
//				if (part == null) {
//					errorMsgs.add("員工圖片: 請勿空白");
					File file = new File(getServletContext().getRealPath("/") + "/backend/img/BlobTest3.jpg");
					FileInputStream fis = new FileInputStream(file);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();  
					byte[] buffer = new byte[8192];
					int i;
					while ((i = fis.read(buffer)) != -1) {
						baos.write(buffer, 0, i);
					}
					empPhoto = baos.toByteArray();
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
					empPhoto = baos.toByteArray();
					baos.close();
					in.close();
				}
				
				
				String empAddress = req.getParameter("empAddress").trim();
				if(empAddress == null || empAddress.isEmpty()) {
					errorMsgs.add("請輸入地址");
				}
				
				Date empBirth = null;
				try {
					empBirth = java.sql.Date.valueOf(req.getParameter("empBirth").trim());
				} catch (IllegalArgumentException e) {
					empBirth = new java.sql.Date(System.currentTimeMillis());
				}
				
				
//				String empCountry = req.getParameter("empCountry").trim();
//				if(empCountry == null || empCountry.isEmpty()) {
//					errorMsgs.add("員工國籍: 請勿空白");
//				}
				String empCountry = "鬼島";
				
				Date empHireDate = null;
				try {
					empHireDate = java.sql.Date.valueOf(req.getParameter("empHireDate").trim());
				} catch (IllegalArgumentException e) {
					empHireDate = new java.sql.Date(System.currentTimeMillis());
				}
				
				String empJob = req.getParameter("empJob").trim();
				if(empJob == null || empJob.isEmpty()) {
					errorMsgs.add("請輸入職稱");
				}
				
//				Integer empAuth = null;
//				try {
//					empAuth = Integer.parseInt(req.getParameter("empAuth").trim());
//					if(empAuth <= 0 || empAuth > 5 ) errorMsgs.add("員工權限: 請確認權限範圍(1~5)");
//				} catch (NumberFormatException e) {
//					empAuth = 0;
//					errorMsgs.add("員工權限: 請選擇 1~5");
//				}

				Integer empAuth = null;
				try {
					empAuth = Integer.parseInt(req.getParameter("empAuth").trim());
					if(empAuth == 0 ) errorMsgs.add("請選擇員工權限");
				} catch (NumberFormatException e) {
					empAuth = 0;
					errorMsgs.add("員工權限格式有問題");
				}

				String empStatus = req.getParameter("empStatus").trim();

				EmpVO addEmp = new EmpVO();
				addEmp.setEmpAccount(empAccount);
				addEmp.setEmpPassword(empPassword);
				addEmp.setEmpName(empName);
				addEmp.setEmpNickname(empNickname);
				addEmp.setEmpEmail(empEmail);
				addEmp.setEmpPhoto(empPhoto);
				addEmp.setEmpPhone(empPhone);
				addEmp.setEmpAddress(empAddress);
				addEmp.setEmpBirth(empBirth);
				addEmp.setEmpSex(empSex);
				addEmp.setEmpCountry(empCountry);
				addEmp.setEmpHireDate(empHireDate);
				addEmp.setEmpJob(empJob);
				addEmp.setEmpAuth(empAuth);
				addEmp.setEmpStatus(empStatus);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addEmp", addEmp); 
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}

				empServ = new EmpService();
				addEmp = empServ.addEmp(addEmp);
				req.setAttribute("selectOneEmp", addEmp); 
				
				String url = "/backend/emp/empProfile.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
//			} catch (Exception e) {
//				e.printStackTrace();
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
//				exceptionView.forward(req, res);
//			}
		}		
		
		if ("backend_SelectOneEmp".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String empId = req.getParameter("empId").trim();
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/emp/selectEmp.jsp");
					failureView.forward(req, res);
					return;
				}

				EmpService empServ = new EmpService();
				EmpVO selectOneEmp = new EmpVO();
				selectOneEmp = empServ.selectOneEmp(empId);
				if (selectOneEmp == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/emp/selectEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOneEmp", selectOneEmp);
				
				String url = "/backend/emp/empProfile.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_DeleteEmp".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String empId = req.getParameter("empId").trim();

				EmpService empServ = new EmpService();
				EmpVO deleteEmp = new EmpVO();
				empServ.deleteEmp(empId);

				String url = "/backend/emp/selectAllEmp.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_SelectOneAuth".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String empId = req.getParameter("empId").trim();

				EmpService empServ = new EmpService();
				EmpVO selectOneUpdate = new EmpVO();
				selectOneUpdate = empServ.selectOneEmp(empId);
				req.setAttribute("selectOneUpdate", selectOneUpdate);

				String url = "/backend/emp/updateEmp.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_UpdateEmp".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				
				String empId = req.getParameter("empId").trim();
				EmpService empService = new EmpService();
				EmpVO Emp = empService.selectOneEmp(empId);
				
				
				String empJob = req.getParameter("empJob").trim();
				if(empJob == null || empJob.isEmpty()) {
					errorMsgs.add("員工職稱: 請勿空白");
				}
				
				Integer empAuth = null;
				try {
					empAuth = Integer.parseInt(req.getParameter("empAuth").trim());
					if(empAuth <= 0 && empAuth > 5 ) errorMsgs.add("員工權限: 請確認權限範圍(1~5)");
				} catch (NumberFormatException e) {
					empAuth = 0;
					errorMsgs.add("員工權限: 請選擇 1~5");
				}
				
				String empStatus = req.getParameter("empStatus").trim();
				if(empStatus == null || empStatus.isEmpty()) {
					errorMsgs.add("員工在職狀態: 請勿空白");
				}
				
				EmpVO updateEmp = new EmpVO();
				updateEmp.setEmpId(empId);
				updateEmp.setEmpAccount(Emp.getEmpAccount());
				updateEmp.setEmpPassword(Emp.getEmpPassword());
				updateEmp.setEmpName(Emp.getEmpName());
				updateEmp.setEmpNickname(Emp.getEmpNickname());
				updateEmp.setEmpEmail(Emp.getEmpEmail());
				updateEmp.setEmpPhoto(Emp.getEmpPhoto());
				updateEmp.setEmpPhone(Emp.getEmpPhone());
				updateEmp.setEmpAddress(Emp.getEmpAddress());
				updateEmp.setEmpBirth(Emp.getEmpBirth());
				updateEmp.setEmpSex(Emp.getEmpSex());
				updateEmp.setEmpCountry(Emp.getEmpCountry());
				updateEmp.setEmpHireDate(Emp.getEmpHireDate());
				updateEmp.setEmpJob(empJob);
				updateEmp.setEmpAuth(empAuth);
				updateEmp.setEmpStatus(empStatus);
				updateEmp.setEmpOnline(Emp.getEmpOnline());
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateEmp);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/emp/updateEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				

				empService = new EmpService();
				updateEmp = empService.updateEmp(updateEmp);
				req.setAttribute("selectOneEmp", updateEmp);

				String url = "/backend/emp/empProfile.jsp";
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
				String empId = req.getParameter("empId").trim();

				EmpService empServ = new EmpService();
				EmpVO selectOneUpdate = new EmpVO();
				selectOneUpdate = empServ.selectOneEmp(empId);
				req.setAttribute("selectOneUpdate", selectOneUpdate);

				String url = "/backend/emp/updateEmpBasic.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_UpdateEmpBasic".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String empId = req.getParameter("empId").trim();
				
				String empName = req.getParameter("empName").trim();
				if(empName == null || empName.isEmpty()) {
					errorMsgs.add("員工姓名: 請勿空白");
				}
				
				String empNickname = req.getParameter("empNickname").trim();
				if(empNickname == null || empNickname.isEmpty()) {
					errorMsgs.add("員工名稱: 請勿空白");
				}
				
				String empEmail = req.getParameter("empEmail").trim();
				if(empEmail == null || empEmail.isEmpty()) {
					errorMsgs.add("員工email: 請勿空白");
				}
				
				Part part = req.getPart("empPhoto");
				InputStream in = null;
				byte[] empPhoto = null;
				String filename = getFileNameFromPart(part);
				if (filename == null || filename.isEmpty()) {
//				if (part == null) {
					EmpService empService = new EmpService();
					EmpVO empOriginPhoto = empService.selectOneEmp(empId);
					empPhoto = empOriginPhoto.getEmpPhoto();
				} else {
					in = part.getInputStream();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte[] buffer = new byte[8192];
					int i;
					while ((i = in.read(buffer)) != -1) {
						baos.write(buffer, 0, i);
					}
					empPhoto = baos.toByteArray();
					baos.close();
					in.close();
				}
				
				String empPhone = req.getParameter("empPhone").trim();
				if(empPhone == null || empPhone.isEmpty()) {
					errorMsgs.add("員工電話: 請勿空白");
				}
				
				String empAddress = req.getParameter("empAddress").trim();
				if(empAddress == null || empAddress.isEmpty()) {
					errorMsgs.add("員工聯絡地址: 請勿空白");
				}
				
				Date empBirth = null;
				try {
					empBirth = java.sql.Date.valueOf(req.getParameter("empBirth").trim());
				} catch (IllegalArgumentException e) {
					empBirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("員工生日: 請勿空白");
				}
				
				String empSex = req.getParameter("empSex").trim();
				if(empSex == null || empSex.isEmpty()) {
					errorMsgs.add("員工性別: 請勿空白");
				}
				
				String empCountry = req.getParameter("empCountry").trim();
				if(empCountry == null || empCountry.isEmpty()) {
					errorMsgs.add("員工國籍: 請勿空白");
				}
				
				EmpService empService = new EmpService();
				EmpVO emp = empService.selectOneEmp(empId);
				
				
				EmpVO updateEmp = new EmpVO();
				updateEmp.setEmpId(empId);
				updateEmp.setEmpAccount(emp.getEmpAccount());
				updateEmp.setEmpPassword(emp.getEmpPassword());
				updateEmp.setEmpName(empName);
				updateEmp.setEmpNickname(empNickname);
				updateEmp.setEmpEmail(empEmail);
				updateEmp.setEmpPhoto(empPhoto);
				updateEmp.setEmpPhone(empPhone);
				updateEmp.setEmpAddress(empAddress);
				updateEmp.setEmpBirth(empBirth);
				updateEmp.setEmpSex(empSex);
				updateEmp.setEmpCountry(empCountry);
				updateEmp.setEmpHireDate(emp.getEmpHireDate());
				updateEmp.setEmpJob(emp.getEmpJob());
				updateEmp.setEmpAuth(emp.getEmpAuth());
				updateEmp.setEmpStatus(emp.getEmpStatus());
				updateEmp.setEmpOnline(emp.getEmpOnline());
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateEmp);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/updateEmpBasic.jsp");
					failureView.forward(req, res);
					return;
				}
				

				updateEmp = empService.updateEmp(updateEmp);
				req.setAttribute("selectOneEmp", updateEmp);

				String url = "/backend/emp/emp.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}	
		
		
		if ("backend_ChangePassword".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String empId = req.getParameter("empId").trim();
				
				EmpService empService = new EmpService();
				EmpVO emp = empService.selectOneEmp(empId);
				
				String empKeyinPassword = req.getParameter("empPassword").trim();
				String empPassword = emp.getEmpPassword();
				String newEmpPassword = req.getParameter("newEmpPassword").trim();
				String empPasswordConfirm = req.getParameter("empPasswordConfirm").trim();
				String empPasswordRegEx = "^\\w{6,16}$";
				
				if(!empKeyinPassword.equals(empPassword)) {
					errorMsgs.add("密碼錯誤");
					newEmpPassword = empKeyinPassword;
				}else if(!newEmpPassword.matches(empPasswordRegEx)) {
					errorMsgs.add("格式錯誤");
				}else if(!empPasswordConfirm.equals(newEmpPassword)) {
					errorMsgs.add("密碼確認不一致");
				}
				
				EmpVO changePassword = new EmpVO();
				changePassword.setEmpId(empId);
				changePassword.setEmpAccount(emp.getEmpAccount());
				changePassword.setEmpPassword(newEmpPassword);
				changePassword.setEmpName(emp.getEmpName());
				changePassword.setEmpNickname(emp.getEmpNickname());
				changePassword.setEmpEmail(emp.getEmpEmail());
				changePassword.setEmpPhoto(emp.getEmpPhoto());
				changePassword.setEmpPhone(emp.getEmpPhone());
				changePassword.setEmpAddress(emp.getEmpAddress());
				changePassword.setEmpBirth(emp.getEmpBirth());
				changePassword.setEmpSex(emp.getEmpSex());
				changePassword.setEmpCountry(emp.getEmpCountry());
				changePassword.setEmpHireDate(emp.getEmpHireDate());
				changePassword.setEmpJob(emp.getEmpJob());
				changePassword.setEmpAuth(emp.getEmpAuth());
				changePassword.setEmpStatus(emp.getEmpStatus());
				changePassword.setEmpOnline(emp.getEmpOnline());
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("changePassword", changePassword);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/emp/changePassword.jsp");
					failureView.forward(req, res);
					return;
				}
				

				changePassword = empService.updateEmp(changePassword);
				req.setAttribute("selectOneEmp", changePassword);

				String url = "/backend/emp/emp.jsp";
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
		
		
		if ("addEmp".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String empAccount = req.getParameter("empAccount").trim();
//				String memberIdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(empAccount == null || empAccount.isEmpty()) {
					errorMsgs.add("員工帳號: 請勿空白");
				}
				
				String empPassword = req.getParameter("empPassword").trim();
				if(empPassword == null || empPassword.isEmpty()) {
					errorMsgs.add("員工密碼: 請勿空白");
				}
				
				String empName = req.getParameter("empName").trim();
				if(empName == null || empName.isEmpty()) {
					errorMsgs.add("員工姓名: 請勿空白");
				}
				
				String empNickname = req.getParameter("empNickname").trim();
				if(empNickname == null || empNickname.isEmpty()) {
					errorMsgs.add("員工名稱: 請勿空白");
				}
				
				String empEmail = req.getParameter("empEmail").trim();
				if(empEmail == null || empEmail.isEmpty()) {
					errorMsgs.add("員工email: 請勿空白");
				}
				
				Part part = req.getPart("empPhoto");
				InputStream in = null;
				byte[] empPhoto = null;
				String filename = getFileNameFromPart(part);
				if (filename == null || filename.isEmpty()) {
//				if (part == null) {
//					errorMsgs.add("員工圖片: 請勿空白");
					File file = new File("/frontend/img/BlobTest3.jpg");
					FileInputStream fis = new FileInputStream(file);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();  
					byte[] buffer = new byte[8192];
					int i;
					while ((i = fis.read(buffer)) != -1) {
						baos.write(buffer, 0, i);
					}
					empPhoto = baos.toByteArray();
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
					empPhoto = baos.toByteArray();
					baos.close();
					in.close();
				}
				
				String empPhone = req.getParameter("empPhone").trim();
				if(empPhone == null || empPhone.isEmpty()) {
					errorMsgs.add("員工電話: 請勿空白");
				}
				
				String empAddress = req.getParameter("empAddress").trim();
				if(empAddress == null || empAddress.isEmpty()) {
					errorMsgs.add("員工聯絡地址: 請勿空白");
				}
				
				Date empBirth = null;
				try {
					empBirth = java.sql.Date.valueOf(req.getParameter("empBirth").trim());
				} catch (IllegalArgumentException e) {
					empBirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("員工生日: 請勿空白");
				}
				
				String empSex = req.getParameter("empSex").trim();
				if(empSex == null || empSex.isEmpty()) {
					errorMsgs.add("員工性別: 請勿空白");
				}
				
				String empCountry = req.getParameter("empCountry").trim();
				if(empCountry == null || empCountry.isEmpty()) {
					errorMsgs.add("員工國籍: 請勿空白");
				}
				
				Date empHireDate = null;
				try {
					empHireDate = java.sql.Date.valueOf(req.getParameter("empHireDate").trim());
				} catch (IllegalArgumentException e) {
					empHireDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("員工到職日: 請勿空白");
				}
				
				String empJob = req.getParameter("empJob").trim();
				if(empJob == null || empJob.isEmpty()) {
					errorMsgs.add("員工職稱: 請勿空白");
				}
				
				Integer empAuth = null;
				try {
					empAuth = Integer.parseInt(req.getParameter("empAuth").trim());
					if(empAuth <= 0 || empAuth > 5 ) errorMsgs.add("員工權限: 請確認權限範圍(1~5)");
				} catch (NumberFormatException e) {
					empAuth = 0;
					errorMsgs.add("員工權限: 請選擇 1~5");
				}
				
				String empStatus = req.getParameter("empStatus").trim();
				if(empStatus == null || empStatus.isEmpty()) {
					errorMsgs.add("員工在職狀態: 請勿空白");
				}

				EmpVO addEmp = new EmpVO();
				addEmp.setEmpAccount(empAccount);
				addEmp.setEmpPassword(empPassword);
				addEmp.setEmpName(empName);
				addEmp.setEmpNickname(empNickname);
				addEmp.setEmpEmail(empEmail);
				addEmp.setEmpPhoto(empPhoto);
				addEmp.setEmpPhone(empPhone);
				addEmp.setEmpAddress(empAddress);
				addEmp.setEmpBirth(empBirth);
				addEmp.setEmpSex(empSex);
				addEmp.setEmpCountry(empCountry);
				addEmp.setEmpHireDate(empHireDate);
				addEmp.setEmpJob(empJob);
				addEmp.setEmpAuth(empAuth);
				addEmp.setEmpStatus(empStatus);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addEmp", addEmp); 
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}

				EmpService empServ = new EmpService();
				addEmp = empServ.addEmp(addEmp);

				String url = "/frontend/emp/selectAllEmp.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("selectOneEmp".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String empId = req.getParameter("empId").trim();
				if (empId == null || (empId.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/emp/emp.jsp");
					failureView.forward(req, res);
					return;
				}

				EmpService empServ = new EmpService();
				EmpVO selectOneEmp = new EmpVO();
				selectOneEmp = empServ.selectOneEmp(empId);
				if (selectOneEmp == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/emp/emp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("selectOneEmp", selectOneEmp);
				
				String url = "/frontend/emp/selectOneEmp.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("deleteEmp".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String empId = req.getParameter("empId").trim();

				EmpService empServ = new EmpService();
				EmpVO deleteEmp = new EmpVO();
				empServ.deleteEmp(empId);

				String url = "/frontend/emp/selectAllEmp.jsp";
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
				String empId = req.getParameter("empId").trim();

				EmpService empServ = new EmpService();
				EmpVO selectOneUpdate = new EmpVO();
				selectOneUpdate = empServ.selectOneEmp(empId);
				req.setAttribute("selectOneUpdate", selectOneUpdate);

				String url = "/frontend/emp/updateEmp.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("updateEmp".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String empId = req.getParameter("empId").trim();
				
				String empAccount = req.getParameter("empAccount").trim();
//				String memberIdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(empAccount == null || empAccount.isEmpty()) {
					errorMsgs.add("員工帳號: 請勿空白");
				}
				
				String empPassword = req.getParameter("empPassword").trim();
				if(empPassword == null || empPassword.isEmpty()) {
					errorMsgs.add("員工密碼: 請勿空白");
				}
				
				String empName = req.getParameter("empName").trim();
				if(empName == null || empName.isEmpty()) {
					errorMsgs.add("員工姓名: 請勿空白");
				}
				
				String empNickname = req.getParameter("empNickname").trim();
				if(empNickname == null || empNickname.isEmpty()) {
					errorMsgs.add("員工名稱: 請勿空白");
				}
				
				String empEmail = req.getParameter("empEmail").trim();
				if(empEmail == null || empEmail.isEmpty()) {
					errorMsgs.add("員工email: 請勿空白");
				}
				
				Part part = req.getPart("empPhoto");
				InputStream in = null;
				byte[] empPhoto = null;
				String filename = getFileNameFromPart(part);
				if (filename == null || filename.isEmpty()) {
//				if (part == null) {
//					errorMsgs.add("員工圖片: 請勿空白");
					EmpService empService = new EmpService();
					EmpVO empOriginPhoto = empService.selectOneEmp(empId);
					empPhoto = empOriginPhoto.getEmpPhoto();
				} else {
					in = part.getInputStream();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte[] buffer = new byte[8192];
					int i;
					while ((i = in.read(buffer)) != -1) {
						baos.write(buffer, 0, i);
					}
					empPhoto = baos.toByteArray();
					baos.close();
					in.close();
				}
				
				String empPhone = req.getParameter("empPhone").trim();
				if(empPhone == null || empPhone.isEmpty()) {
					errorMsgs.add("員工電話: 請勿空白");
				}
				
				String empAddress = req.getParameter("empAddress").trim();
				if(empAddress == null || empAddress.isEmpty()) {
					errorMsgs.add("員工聯絡地址: 請勿空白");
				}
				
				Date empBirth = null;
				try {
					empBirth = java.sql.Date.valueOf(req.getParameter("empBirth").trim());
				} catch (IllegalArgumentException e) {
					empBirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("員工生日: 請勿空白");
				}
				
				String empSex = req.getParameter("empSex").trim();
				if(empSex == null || empSex.isEmpty()) {
					errorMsgs.add("員工性別: 請勿空白");
				}
				
				String empCountry = req.getParameter("empCountry").trim();
				if(empCountry == null || empCountry.isEmpty()) {
					errorMsgs.add("員工國籍: 請勿空白");
				}
				
				Date empHireDate = null;
				try {
					empHireDate = java.sql.Date.valueOf(req.getParameter("empHireDate").trim());
				} catch (IllegalArgumentException e) {
					empHireDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("員工到職日: 請勿空白");
				}
				
				String empJob = req.getParameter("empJob").trim();
				if(empJob == null || empJob.isEmpty()) {
					errorMsgs.add("員工職稱: 請勿空白");
				}
				
				Integer empAuth = null;
				try {
					empAuth = Integer.parseInt(req.getParameter("empAuth").trim());
					if(empAuth <= 0 && empAuth > 5 ) errorMsgs.add("員工權限: 請確認權限範圍(1~5)");
				} catch (NumberFormatException e) {
					empAuth = 0;
					errorMsgs.add("員工權限: 請選擇 1~5");
				}
				
				String empStatus = req.getParameter("empStatus").trim();
				if(empStatus == null || empStatus.isEmpty()) {
					errorMsgs.add("員工在職狀態: 請勿空白");
				}
				
				EmpVO updateEmp = new EmpVO();
				updateEmp.setEmpId(empId);
				updateEmp.setEmpAccount(empAccount);
				updateEmp.setEmpPassword(empPassword);
				updateEmp.setEmpName(empName);
				updateEmp.setEmpNickname(empNickname);
				updateEmp.setEmpEmail(empEmail);
				updateEmp.setEmpPhoto(empPhoto);
				updateEmp.setEmpPhone(empPhone);
				updateEmp.setEmpAddress(empAddress);
				updateEmp.setEmpBirth(empBirth);
				updateEmp.setEmpSex(empSex);
				updateEmp.setEmpCountry(empCountry);
				updateEmp.setEmpHireDate(empHireDate);
				updateEmp.setEmpJob(empJob);
				updateEmp.setEmpAuth(empAuth);
				updateEmp.setEmpStatus(empStatus);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateEmp);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/emp/updateEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				

				EmpService empService = new EmpService();
				updateEmp = empService.updateEmp(updateEmp);
//				req.setAttribute("updateEmp", updateEmp);

				String url = "/frontend/emp/selectAllEmp.jsp";
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
