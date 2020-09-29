package com.orderMaster.model;

import java.util.List;

public class TestJDBC {
	public static void main(String[] args) {
	
		OrderMasterDAOInterface dao = new OrderMasterDAO();
		
		//All
		List<OrderMasterVO> list = dao.selectAll();
		for(OrderMasterVO orderMasterVO : list) {
			System.out.println(orderMasterVO.getOrderMasterId());
		}		
		
		//One
//		OrderMasterVO one = dao.selectOne("20");
//		System.out.println(one.getOrderMasterId());
		
		//insert
//		OrderMasterVO add = new OrderMasterVO();
//		add.setMemberId("10");
//		add.setOrderCreatDate(java.sql.Date.valueOf("2020-09-01"));
//		add.setOrderStatus("Y");
//		dao.insert(add);
//		System.out.println("FUCK");
		
		//delete
//		dao.delete("20");
//		System.out.println("FUCK");
		
		//update
//		OrderMasterVO update = new OrderMasterVO();
//		update.setOrderMasterId("20");
//		update.setMemberId("10");
//		update.setOrderCreatDate(java.sql.Date.valueOf("2020-09-01"));
//		update.setOrderStatus("N");
//		dao.update(update);
//		System.out.println("FUCK");
		
	}

}
