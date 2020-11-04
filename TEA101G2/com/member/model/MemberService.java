package com.member.model;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

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

	public void updateMemberOnline(MemberVO memberVO, String memberOnline) {
		MemberVO updateMemberOnline = dao.findByPrimaryKey(memberVO.getMemberId());
		updateMemberOnline.setMemberOnline(memberOnline);
		dao.update(updateMemberOnline);
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

	public MemberVO getOneMemberByEmail(String Email) {
		return dao.findByEmail(Email);
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
	
	public boolean EmailVerify(String email) {
		List<MemberVO> existEmail = dao.getAll();
		if (existEmail.stream().anyMatch(m -> email.equals(m.getMemberEmail()))) {
			return true;
		}
		return false;
	}

	public boolean OldPasswordVerify(String oldPassword, String memberId) {
		String password = dao.findByPrimaryKey(memberId).getMemberPassword();
		if (oldPassword.equals(password)) {
			return true;
		}
		return false;
	}
	
	public String UpdateMemberPassword(String password, String email) {
		dao.updateMemberPassword(password, email);
		return password;
	}

	public MemberVO LoginAuthenticate(String account, String password) {
		return dao.LoginAuthenticate(account, password);
	}

	public String fromJsonObject(MemberVO memberVO) {
		Gson gson = new Gson();
		String jsonObj = gson.toJson(memberVO);
		return jsonObj;
	}

	public String fromJsonObjectList(List<MemberVO> list) {
		Gson gson = new Gson();
		String jsonObj = gson.toJson(list);
		return jsonObj;
	}

	public List<MemberVO> selectAllMemberOnline() {
		List<MemberVO> all = dao.getAll();
		List<MemberVO> allMemberOnline = all.stream().filter(m -> m.getMemberOnline().equals("Y"))
				.collect(Collectors.toList());

		return allMemberOnline;
	}

	public Map<String, String> selectAllMemberIdName() {
		List<MemberVO> all = dao.getAll();
		return all.stream().collect(Collectors.toMap(MemberVO::getMemberId, MemberVO::getMemberName));
	}

	public Map<String, String> selectAllEmpIdNameR() {
		List<MemberVO> all = dao.getAll();
		return all.stream().collect(Collectors.toMap(MemberVO::getMemberName, MemberVO::getMemberId));
	}

	public String randomPassword() {
		Random r = new Random();
		StringBuilder sb = new StringBuilder();
		int[] array = new int[62];
		
		for (int i = 0; i < array.length; i++)
			if (i < 10)
				array[i] = 48 + i;
			else if (i < 36)
				array[i] = 55 + i;
			else
				array[i] = 61 + i;

		int passwordSize = 12;
		int[] password = new int[passwordSize];
		
		for (int i = 0; i < passwordSize; i++) {
			password[i] = array[r.nextInt(62)];
		}
		for (int i = 0; i < passwordSize; i++) {
			sb.append((char) password[i]);
		}
		return sb.toString();
	}
}
