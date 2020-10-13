package com.orderDetail.model;

import java.util.List;

public class TestJDBC {
	public static void main(String[] args) {
	
		OrderDetailDAOInterfaceB dao = new OrderDetailDAOB();
		
		//All
//		List<OrderDetailVO> list = dao.selectAll();
//		for(OrderDetailVO orderDetailVO : list) {
//			System.out.println(orderDetailVO.getOrderMasterId());
//		}		
		
		//AllByMaster
		OrderDetailServiceB ods = new OrderDetailServiceB();
		List<OrderDetailVO> list = ods.selectAllOrderDetailByMaster("202009190001");
		for(OrderDetailVO orderDetailVO : list) {
			System.out.println(orderDetailVO.getOrderDetailId());
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
