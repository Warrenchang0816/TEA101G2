package com.member.controller;

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

import com.mail.service.MailService;
import com.member.model.MemberServiceB;
import com.member.model.MemberVO;


@WebServlet("/MemberServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemberServletB extends HttpServlet {
//	private static final long serialVersionUID = 1L;
       
    public MemberServletB() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("backend_AddMember".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberAccount = req.getParameter("memberAccount").trim();
//				String memberIdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(memberAccount == null || memberAccount.isEmpty()) {
					errorMsgs.add("會員帳號: 請勿空白");
				}
				
				String memberPassword = req.getParameter("memberPassword").trim();
				if(memberPassword == null || memberPassword.isEmpty()) {
					errorMsgs.add("會員密碼: 請勿空白");
				}
				
				String memberName = req.getParameter("memberName").trim();
				if(memberName == null || memberName.isEmpty()) {
					errorMsgs.add("會員姓名: 請勿空白");
				}
				
				String memberNickname = req.getParameter("memberNickname").trim();
				if(memberNickname == null || memberNickname.isEmpty()) {
					errorMsgs.add("會員名稱: 請勿空白");
				}
				
				String memberEmail = req.getParameter("memberEmail").trim();
				if(memberEmail == null || memberEmail.isEmpty()) {
					errorMsgs.add("會員email: 請勿空白");
				}
				
				Part part = req.getPart("memberPhoto");
				InputStream in = null;
				byte[] memberPhoto = null;
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
					memberPhoto = baos.toByteArray();
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
					memberPhoto = baos.toByteArray();
					baos.close();
					in.close();
				}
				
				String memberPhone = req.getParameter("memberPhone").trim();
				if(memberPhone == null || memberPhone.isEmpty()) {
					errorMsgs.add("會員電話: 請勿空白");
				}
				
				String memberAddress = req.getParameter("memberAddress").trim();
				if(memberAddress == null || memberAddress.isEmpty()) {
					errorMsgs.add("會員聯絡地址: 請勿空白");
				}
				
				Date memberBirth = null;
				try {
					memberBirth = java.sql.Date.valueOf(req.getParameter("memberBirth").trim());
				} catch (IllegalArgumentException e) {
					memberBirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("會員生日: 請勿空白");
				}
				
				String memberSex = req.getParameter("memberSex").trim();
				if(memberSex == null || memberSex.isEmpty()) {
					errorMsgs.add("會員性別: 請勿空白");
				}
				
				String memberCountry = req.getParameter("memberCountry").trim();
				if(memberCountry == null || memberCountry.isEmpty()) {
					errorMsgs.add("會員國籍: 請勿空白");
				}
				
				Date memberSignupDate = null;
				try {
					memberSignupDate = java.sql.Date.valueOf(req.getParameter("memberSignupDate").trim());
				} catch (IllegalArgumentException e) {
					memberSignupDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("會員註冊日: 請勿空白");
				}
				
				Integer memberAuth = null;
				try {
					memberAuth = Integer.parseInt(req.getParameter("memberAuth").trim());
					if(memberAuth <= 0 || memberAuth > 5 ) errorMsgs.add("員工權限: 請確認權限範圍(1~5)");
				} catch (NumberFormatException e) {
					memberAuth = 0;
					errorMsgs.add("會員權限: 請選擇 1~5");
				}
				
				String memberStatus = req.getParameter("memberStatus").trim();
				if(memberStatus == null || memberStatus.isEmpty()) {
					errorMsgs.add("會員帳號狀態: 請勿空白");
				}

				MemberVO addMember = new MemberVO();
				addMember.setMemberAccount(memberAccount);
				addMember.setMemberPassword(memberPassword);
				addMember.setMemberName(memberName);
				addMember.setMemberNickName(memberNickname);
				addMember.setMemberEmail(memberEmail);
				addMember.setMemberPhoto(memberPhoto);
				addMember.setMemberPhone(memberPhone);
				addMember.setMemberAddress(memberAddress);
				addMember.setMemberBirth(memberBirth);
				addMember.setMemberSex(memberSex);
				addMember.setMemberCountry(memberCountry);
				addMember.setMemberSignupDate(memberSignupDate);
				addMember.setMemberAuth(memberAuth);
				addMember.setMemberStatus(memberStatus);
				addMember.setMemberOnline("N");
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("addMember", addMember); 
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/member/addMember.jsp");
					failureView.forward(req, res);
					return;
				}

				MemberServiceB memberService = new MemberServiceB();
				addMember = memberService.addMember(addMember);

				String url = "/backend/member/selectAllMember.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_SelectOneMember".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				String memberId = req.getParameter("memberId").trim();
				if (memberId == null || (memberId.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}

				MemberServiceB memberService = new MemberServiceB();
				MemberVO selectOneMember = new MemberVO();
				selectOneMember = memberService.selectOneMember(memberId);
				if (selectOneMember == null) {
					errorMsgs.add("查無資料");
				}
				
				req.setAttribute("selectOneMember", selectOneMember);
				
				String url = "/backend/member/memberProfile.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
//			} catch (Exception e) {
//				e.printStackTrace();
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
//				exceptionView.forward(req, res);
//			}
		}		
		
		if ("backend_DeleteMember".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memberId = req.getParameter("memberId").trim();

				MemberServiceB memberService = new MemberServiceB();
				MemberVO deleteMember = new MemberVO();
				memberService.deleteMember(memberId);

				String url = "/backend/member/selectAllMember.jsp";
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
				String memberId = req.getParameter("memberId").trim();

				MemberServiceB memberService = new MemberServiceB();
				MemberVO selectOneUpdate = memberService.selectOneMember(memberId);
				req.setAttribute("selectOneUpdate", selectOneUpdate);

				String url = "/backend/member/updateMember.jsp";
				RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
				sucessVeiw.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher exceptionView = req.getRequestDispatcher("/backend/error.jsp");
				exceptionView.forward(req, res);
			}
		}		
		
		if ("backend_UpdateMember".equals(action)) {
			Queue<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				MemberServiceB memberService = new MemberServiceB();
				
				String memberId = req.getParameter("memberId").trim();
				
				MemberVO member = memberService.selectOneMember(memberId);
				
				String memberAccount = req.getParameter("memberAccount").trim();
				
				String memberPassword = req.getParameter("memberPassword").trim();
				
				String memberName = req.getParameter("memberName").trim();
				
				String memberNickname = req.getParameter("memberNickname").trim();
				
				String memberEmail = req.getParameter("memberEmail").trim();
				
				byte[] memberPhoto = memberService.selectOneMember(memberId).getMemberPhoto();
				
				String memberPhone = req.getParameter("memberPhone").trim();
				
				String memberAddress = req.getParameter("memberAddress").trim();
				
				Date memberBirth = java.sql.Date.valueOf(req.getParameter("memberBirth").trim());
				
				String memberSex = req.getParameter("memberSex").trim();
				
				String memberCountry = req.getParameter("memberCountry").trim();
				
				Date memberSignupDate = java.sql.Date.valueOf(req.getParameter("memberSignupDate").trim());
				
				Integer memberAuth = null;
				try {
					memberAuth = Integer.parseInt(req.getParameter("memberAuth").trim());
					if(memberAuth <= 0 || memberAuth > 5 ) errorMsgs.add("員工權限: 請確認權限範圍(1~5)");
				} catch (NumberFormatException e) {
					memberAuth = 0;
					errorMsgs.add("會員權限: 請選擇 1~5");
				}
				
				String memberStatus = req.getParameter("memberStatus").trim();
				if(memberStatus == null || memberStatus.isEmpty()) {
					errorMsgs.add("會員帳號狀態: 請勿空白");
				}
				
				String memberStatusEmp = req.getParameter("memberStatusEmp").trim();
				
				String memberStatusComm = req.getParameter("memberStatusComm").trim();
				if(!memberStatus.equals("O")) {
					if(memberStatusComm == null || memberStatusComm.isEmpty()) 
					errorMsgs.add("停權原因請勿空白");
				}

				MemberVO updateMember = new MemberVO();
				updateMember.setMemberId(memberId);
				updateMember.setMemberAccount(memberAccount);
				updateMember.setMemberPassword(memberPassword);
				updateMember.setMemberName(memberName);
				updateMember.setMemberNickName(memberNickname);
				updateMember.setMemberEmail(memberEmail);
				updateMember.setMemberPhoto(memberPhoto);
				updateMember.setMemberPhone(memberPhone);
				updateMember.setMemberAddress(memberAddress);
				updateMember.setMemberBirth(memberBirth);
				updateMember.setMemberSex(memberSex);
				updateMember.setMemberCountry(memberCountry);
				updateMember.setMemberSignupDate(memberSignupDate);
				updateMember.setMemberAuth(memberAuth);
				updateMember.setMemberStatus(memberStatus);
				updateMember.setMemberStatusEmp(memberStatusEmp);
				updateMember.setMemberStatusComm(memberStatusComm);
				updateMember.setMemberOnline(member.getMemberOnline());
				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("selectOneUpdate", updateMember);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/member/updateMember.jsp");
					failureView.forward(req, res);
					return;
				}
				

				memberService = new MemberServiceB();
				updateMember = memberService.updateMember(updateMember);
				req.setAttribute("selectOneMember", updateMember);
				
				if(memberStatus.equals("O")) {
					MailService mailService = new MailService();
					String messageText = "親愛的會員: "+ memberName + "您好，" + "\n" + "經由管理員審查後，回復您的帳號權限。";
					mailService.sendMail(memberEmail, "會員帳號權限通知", messageText);
				}else {
					MailService mailService = new MailService();
					String messageText = "親愛的會員: "+ memberName + "您好，" + "\n" + "經由管理員審查後，將您的帳號停權。" + "\n" + "停權原因:" + memberStatusComm;
					mailService.sendMail(memberEmail, "會員帳號停權通知", messageText);
				}

				String url = "/backend/member/memberProfile.jsp";
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

	
	if ("addMember".equals(action)) {
//		List<String> errorMsgs = new LinkedList<String>();
		Queue<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			String memberAccount = req.getParameter("memberAccount").trim();
//			String memberIdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if(memberAccount == null || memberAccount.isEmpty()) {
				errorMsgs.add("會員帳號: 請勿空白");
			}
			
			String memberPassword = req.getParameter("memberPassword").trim();
			if(memberPassword == null || memberPassword.isEmpty()) {
				errorMsgs.add("會員密碼: 請勿空白");
			}
			
			String memberName = req.getParameter("memberName").trim();
			if(memberName == null || memberName.isEmpty()) {
				errorMsgs.add("會員姓名: 請勿空白");
			}
			
			String memberNickname = req.getParameter("memberNickname").trim();
			if(memberNickname == null || memberNickname.isEmpty()) {
				errorMsgs.add("會員名稱: 請勿空白");
			}
			
			String memberEmail = req.getParameter("memberEmail").trim();
			if(memberEmail == null || memberEmail.isEmpty()) {
				errorMsgs.add("會員email: 請勿空白");
			}
			
			Part part = req.getPart("memberPhoto");
			InputStream in = null;
			byte[] memberPhoto = null;
			String filename = getFileNameFromPart(part);
			if (filename == null || filename.isEmpty()) {
//				if (part == null) {
				File file = new File(getServletContext().getRealPath("/") + "/frontend/img/BlobTest3.jpg");
				FileInputStream fis = new FileInputStream(file);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();  
				byte[] buffer = new byte[8192];
				int i;
				while ((i = fis.read(buffer)) != -1) {
					baos.write(buffer, 0, i);
				}
				memberPhoto = baos.toByteArray();
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
				memberPhoto = baos.toByteArray();
				baos.close();
				in.close();
			}
			
			String memberPhone = req.getParameter("memberPhone").trim();
			if(memberPhone == null || memberPhone.isEmpty()) {
				errorMsgs.add("會員電話: 請勿空白");
			}
			
			String memberAddress = req.getParameter("memberAddress").trim();
			if(memberAddress == null || memberAddress.isEmpty()) {
				errorMsgs.add("會員聯絡地址: 請勿空白");
			}
			
			Date memberBirth = null;
			try {
				memberBirth = java.sql.Date.valueOf(req.getParameter("memberBirth").trim());
			} catch (IllegalArgumentException e) {
				memberBirth = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("會員生日: 請勿空白");
			}
			
			String memberSex = req.getParameter("memberSex").trim();
			if(memberSex == null || memberSex.isEmpty()) {
				errorMsgs.add("會員性別: 請勿空白");
			}
			
			String memberCountry = req.getParameter("memberCountry").trim();
			if(memberCountry == null || memberCountry.isEmpty()) {
				errorMsgs.add("會員國籍: 請勿空白");
			}
			
			Date memberSignupDate = null;
			try {
				memberSignupDate = java.sql.Date.valueOf(req.getParameter("memberSignupDate").trim());
			} catch (IllegalArgumentException e) {
				memberSignupDate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("會員註冊日: 請勿空白");
			}
			
			Integer memberAuth = null;
			try {
				memberAuth = Integer.parseInt(req.getParameter("memberAuth").trim());
				if(memberAuth <= 0 || memberAuth > 5 ) errorMsgs.add("員工權限: 請確認權限範圍(1~5)");
			} catch (NumberFormatException e) {
				memberAuth = 0;
				errorMsgs.add("會員權限: 請選擇 1~5");
			}
			
			String memberStatus = req.getParameter("memberStatus").trim();
			if(memberStatus == null || memberStatus.isEmpty()) {
				errorMsgs.add("會員帳號狀態: 請勿空白");
			}

			MemberVO addMember = new MemberVO();
			addMember.setMemberAccount(memberAccount);
			addMember.setMemberPassword(memberPassword);
			addMember.setMemberName(memberName);
			addMember.setMemberNickName(memberNickname);
			addMember.setMemberEmail(memberEmail);
			addMember.setMemberPhoto(memberPhoto);
			addMember.setMemberPhone(memberPhone);
			addMember.setMemberAddress(memberAddress);
			addMember.setMemberBirth(memberBirth);
			addMember.setMemberSex(memberSex);
			addMember.setMemberCountry(memberCountry);
			addMember.setMemberSignupDate(memberSignupDate);
			addMember.setMemberAuth(memberAuth);
			addMember.setMemberStatus(memberStatus);
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("addMember", addMember); 
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/member/addMember.jsp");
				failureView.forward(req, res);
				return;
			}

			MemberServiceB memberService = new MemberServiceB();
			addMember = memberService.addMember(addMember);

			String url = "/frontend/member/selectAllMember.jsp";
			RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
			sucessVeiw.forward(req, res);
			
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add(e.getMessage());
			RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
			exceptionView.forward(req, res);
		}
	}		
	
	if ("selectOneMember".equals(action)) {
		Queue<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			String memberId = req.getParameter("memberId").trim();
			if (memberId == null || (memberId.trim()).length() == 0) {
				errorMsgs.add("請輸入會員編號");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/member/member.jsp");
				failureView.forward(req, res);
				return;
			}

			MemberServiceB memberService = new MemberServiceB();
			MemberVO selectOneMember = new MemberVO();
			selectOneMember = memberService.selectOneMember(memberId);
			if (selectOneMember == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/member/member.jsp");
				failureView.forward(req, res);
				return;
			}
			
			req.setAttribute("selectOneMember", selectOneMember);
			
			String url = "/frontend/member/selectOneMember.jsp";
			RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
			sucessVeiw.forward(req, res);
			
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add(e.getMessage());
			RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
			exceptionView.forward(req, res);
		}
	}		
	
	if ("deleteMember".equals(action)) {
		Queue<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			String memberId = req.getParameter("memberId").trim();

			MemberServiceB memberService = new MemberServiceB();
			MemberVO deleteMember = new MemberVO();
			memberService.deleteMember(memberId);

			String url = "/frontend/member/selectAllMember.jsp";
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
			String memberId = req.getParameter("memberId").trim();

			MemberServiceB memberService = new MemberServiceB();
			MemberVO selectOneUpdate = new MemberVO();
			selectOneUpdate = memberService.selectOneMember(memberId);
			req.setAttribute("selectOneUpdate", selectOneUpdate);

			String url = "/frontend/member/updateMember.jsp";
			RequestDispatcher sucessVeiw = req.getRequestDispatcher(url);
			sucessVeiw.forward(req, res);
			
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add(e.getMessage());
			RequestDispatcher exceptionView = req.getRequestDispatcher("/frontend/error.jsp");
			exceptionView.forward(req, res);
		}
	}		
	
	if ("updateMember".equals(action)) {
		Queue<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			String memberId = req.getParameter("memberId").trim();
			
			String memberAccount = req.getParameter("memberAccount").trim();
//			String memberIdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if(memberAccount == null || memberAccount.isEmpty()) {
				errorMsgs.add("會員帳號: 請勿空白");
			}
			
			String memberPassword = req.getParameter("memberPassword").trim();
			if(memberPassword == null || memberPassword.isEmpty()) {
				errorMsgs.add("會員密碼: 請勿空白");
			}
			
			String memberName = req.getParameter("memberName").trim();
			if(memberName == null || memberName.isEmpty()) {
				errorMsgs.add("會員姓名: 請勿空白");
			}
			
			String memberNickname = req.getParameter("memberNickname").trim();
			if(memberNickname == null || memberNickname.isEmpty()) {
				errorMsgs.add("會員名稱: 請勿空白");
			}
			
			String memberEmail = req.getParameter("memberEmail").trim();
			if(memberEmail == null || memberEmail.isEmpty()) {
				errorMsgs.add("會員email: 請勿空白");
			}
			
			Part part = req.getPart("memberPhoto");
			InputStream in = null;
			byte[] memberPhoto = null;
			String filename = getFileNameFromPart(part);
			if (filename == null || filename.isEmpty()) {
//			if (part == null) {
				MemberServiceB memberService = new MemberServiceB();
				MemberVO memberOriginPhoto = memberService.selectOneMember(memberId);
				memberPhoto = memberOriginPhoto.getMemberPhoto();
			} else {
				in = part.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[8192];
				int i;
				while ((i = in.read(buffer)) != -1) {
					baos.write(buffer, 0, i);
				}
				memberPhoto = baos.toByteArray();
				baos.close();
				in.close();
			}
			
			String memberPhone = req.getParameter("memberPhone").trim();
			if(memberPhone == null || memberPhone.isEmpty()) {
				errorMsgs.add("會員電話: 請勿空白");
			}
			
			String memberAddress = req.getParameter("memberAddress").trim();
			if(memberAddress == null || memberAddress.isEmpty()) {
				errorMsgs.add("會員聯絡地址: 請勿空白");
			}
			
			Date memberBirth = null;
			try {
				memberBirth = java.sql.Date.valueOf(req.getParameter("memberBirth").trim());
			} catch (IllegalArgumentException e) {
				memberBirth = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("會員生日: 請勿空白");
			}
			
			String memberSex = req.getParameter("memberSex").trim();
			if(memberSex == null || memberSex.isEmpty()) {
				errorMsgs.add("會員性別: 請勿空白");
			}
			
			String memberCountry = req.getParameter("memberCountry").trim();
			if(memberCountry == null || memberCountry.isEmpty()) {
				errorMsgs.add("會員國籍: 請勿空白");
			}
			
			Date memberSignupDate = null;
			try {
				memberSignupDate = java.sql.Date.valueOf(req.getParameter("memberSignupDate").trim());
			} catch (IllegalArgumentException e) {
				memberSignupDate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("會員註冊日: 請勿空白");
			}
			
			Integer memberAuth = null;
			try {
				memberAuth = Integer.parseInt(req.getParameter("memberAuth").trim());
				if(memberAuth <= 0 || memberAuth > 5 ) errorMsgs.add("員工權限: 請確認權限範圍(1~5)");
			} catch (NumberFormatException e) {
				memberAuth = 0;
				errorMsgs.add("會員權限: 請選擇 1~5");
			}
			
			String memberStatus = req.getParameter("memberStatus").trim();
			if(memberStatus == null || memberStatus.isEmpty()) {
				errorMsgs.add("會員帳號狀態: 請勿空白");
			}
			
			MemberVO updateMember = new MemberVO();
			updateMember.setMemberId(memberId);
			updateMember.setMemberAccount(memberAccount);
			updateMember.setMemberPassword(memberPassword);
			updateMember.setMemberName(memberName);
			updateMember.setMemberNickName(memberNickname);
			updateMember.setMemberEmail(memberEmail);
			updateMember.setMemberPhoto(memberPhoto);
			updateMember.setMemberPhone(memberPhone);
			updateMember.setMemberAddress(memberAddress);
			updateMember.setMemberBirth(memberBirth);
			updateMember.setMemberSex(memberSex);
			updateMember.setMemberCountry(memberCountry);
			updateMember.setMemberSignupDate(memberSignupDate);
			updateMember.setMemberAuth(memberAuth);
			updateMember.setMemberStatus(memberStatus);
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("selectOneUpdate", updateMember);
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/member/updateMember.jsp");
				failureView.forward(req, res);
				return;
			}
			

			MemberServiceB memberService = new MemberServiceB();
			updateMember = memberService.updateMember(updateMember);
//			req.setAttribute("updateEmp", updateEmp);

			String url = "/frontend/member/selectAllMember.jsp";
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
