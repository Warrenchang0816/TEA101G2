package com.spacePhoto.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
@WebServlet("/space/showmempicture")
public class ShowMemPicture extends HttpServlet {
	
	//用DataSource連線
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TEA101G2");
		}
		catch(NamingException e){
			e.printStackTrace();
		}
	}
	
	//用JDBC連線	
//	private static final String driver = "oracle.jdbc.driver.OracleDriver";
//	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	private static final String userid = "TEA101G2";
//	private static final String passwd = "123456";
	private static final String GET_MEMPHOTO = "SELECT MEMBER_PHOTO FROM MEMBER WHERE MEMBER_ID = ?";


	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			PreparedStatement pstmt = con.prepareStatement(GET_MEMPHOTO);
			String memberId = req.getParameter("memberId").trim();
			pstmt.setString(1, memberId);
			ResultSet rs = pstmt.executeQuery();
			

			if (rs.next()) {
//				InputStream in = rs.getBinaryStream("PIC");
//				InputStream in = new BufferedInputStream(rs.getBinaryStream("PIC"));
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("MEMBER_PHOTO"));
				byte[] buf = new byte[8 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(req.getParameter("memberId").trim());
		}
	}

	public void init() throws ServletException {
//		try {
			
			
//			Context ctx = new javax.naming.InitialContext();
//			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
//			con = ds.getConnection();
//		} catch (NamingException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

	public void destroy() {
//		try {
//			if (con != null) con.close();
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
	}
}

