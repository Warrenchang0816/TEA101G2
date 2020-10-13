package com.member.model;

import java.util.List;

import javax.servlet.http.Part;

import com.google.gson.Gson;

public class MemberService {
	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}

	public MemberVO addMember(MemberVO memberVO) {
		dao.insert(memberVO);
		return memberVO;
	}

	public MemberVO updateMember(MemberVO memberVO) {
		dao.update(memberVO);
		return memberVO;
	}

	public void deleteMember(String memberId) {
		dao.delete(memberId);
	}

	public MemberVO getOneMember(String memberId) {
		return dao.findByPrimaryKey(memberId);
	}
	
	public MemberVO getOneMemberByAccount(String Account) {
		return dao.findByAccount(Account);
	}

	public List<MemberVO> getAllMember() {
		return dao.getAll();
	}

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		String filename = header.substring(header.lastIndexOf("=") + 2, header.length() - 1);
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

	public boolean AccountVerify(String account) {
		List<MemberVO> existAccount = dao.getAll();
		if (existAccount.stream().anyMatch(m -> account.equals(m.getMemberAccount()))) {
			return true;
		}
		return false;
	}
	
	public boolean OldPasswordVerify(String oldPassword,String memberId) {
		String password = dao.findByPrimaryKey(memberId).getMemberPassword();
		System.out.println(password);
		if(oldPassword.equals(password)) {
			return true;
		}
		return false;
	}

	public MemberVO LoginAuthenticate(String account, String password) {
		return dao.LoginAuthenticate(account, password);
	}

	public String fromJsonObject(MemberVO memberVO) {
		Gson gson = new Gson();
		String jsonObj = gson.toJson(memberVO);
		System.out.println(jsonObj);
		return jsonObj;
	}

	public String fromJsonObjectList(List<MemberVO> list) {
		Gson gson = new Gson();
		String jsonObj = gson.toJson(list);
		System.out.println(jsonObj);
		return jsonObj;
	}
}
