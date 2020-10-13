package com.orderDetail.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	
	public OrderDetailVO selectOneOrderDetail(String orderDetailId) {
		return dao.selectOne(orderDetailId);
	}
	
	public List<OrderDetailVO> selectAllOrderDetailBySD(String spaceDetailId) {
		return dao.selectAllBySD(spaceDetailId);
	}
	
	public List<OrderDetailVO> selectAllOrderDetail() {
		return dao.selectAll();
	}
	
//	public List<OrderDetailVO> selectAllOrderDetailByMaster(String orderMasterId) {
//		return dao.selectAllByMasterId(orderMasterId);
//	}
	
	public List<OrderDetailVO> selectAllOrderDetailBySpaceDetail(String spaceDetailId) {
		List<OrderDetailVO> all = dao.selectAll();
		List<OrderDetailVO> allBySpaceDetail = new ArrayList<OrderDetailVO>();
		
		allBySpaceDetail = all.stream()
				.filter(od -> spaceDetailId.equals(od.getSpaceDetailId()))
				.collect(Collectors.toList());
		
		return allBySpaceDetail;
	}
	
	
}