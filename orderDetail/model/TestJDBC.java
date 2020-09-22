package com.orderDetail.model;

import java.util.List;

public class TestJDBC {
	public static void main(String[] args) {
	
		OrderDetailDAOInterface dao = new OrderDetailDAO();
		
		//All
		List<OrderDetailVO> list = dao.selectAll();
		for(OrderDetailVO orderDetailVO : list) {
			System.out.println(orderDetailVO.getOrderMasterId());
		}		
		
		//One
//		OrderDetailVO one = dao.selectOne("30");
//		System.out.println(one.getOrderMasterId());
		
		//insert
//		OrderDetailVO add = new OrderDetailVO();
//		add.setOrderMasterId("30");
//		add.setSpaceDetailId("30");
//		dao.insert(add);
//		System.out.println("FUCK");
		
		//delete
//		dao.delete("20");
//		System.out.println("FUCK");
		
		//update
//		OrderDetailVO update = new OrderDetailVO();
//		update.setOrderDetailId("20");
//		update.setOrderMasterId("30");
//		update.setSpaceDetailId("30");
//		dao.update(update);
//		System.out.println("FUCK");
		
	}

}
