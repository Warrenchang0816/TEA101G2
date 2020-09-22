package com.orderDetail.model;

import java.util.List;

public class OrderDetailService {
	private OrderDetailDAOInterface dao;
	
	public OrderDetailService() {
		dao = new OrderDetailDAO();
	}
	
	
	public OrderDetailVO addOrderDetail(OrderDetailVO orderDetailVO) {
		dao.insert(orderDetailVO);
		return orderDetailVO;
	}
	
	
	public OrderDetailVO updateOrderDetail(OrderDetailVO orderDetailVO) {		
		dao.update(orderDetailVO);
		return orderDetailVO;
	}
	
	public void deleteOrderDetail(String orderDetailId) {
		dao.delete(orderDetailId);
	}
	
	
	public OrderDetailVO selectOneOrderDetail(String spaceDetailId) {
		return dao.selectOne(spaceDetailId);
	}
	
	public List<OrderDetailVO> selectAllOrderDetail() {
		return dao.selectAll();
	}
}
