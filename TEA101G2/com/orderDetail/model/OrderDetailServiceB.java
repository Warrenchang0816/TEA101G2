package com.orderDetail.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDetailServiceB {
	private OrderDetailDAOInterfaceB dao;
	
	public OrderDetailServiceB() {
		dao = new OrderDetailDAOB();
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
	
//	public List<OrderDetailVO> selectAllOrderDetailByMaster(String orderMasterId) {
//		return dao.selectAllByMasterId(orderMasterId);
//	}
	
	public List<OrderDetailVO> selectAllOrderDetailByMaster(String orderMasterId) {
		List<OrderDetailVO> all = dao.selectAll();
		List<OrderDetailVO> allByMaster = new ArrayList<OrderDetailVO>();
		
		allByMaster = all.stream()
				.filter(od -> orderMasterId.equals(od.getOrderMasterId()))
				.collect(Collectors.toList());
		
		return allByMaster;
	}
	
	public List<OrderDetailVO> selectAllOrderDetailBySpaceDetail(String spaceDetailId) {
		List<OrderDetailVO> all = dao.selectAll();
		List<OrderDetailVO> allBySpaceDetail = new ArrayList<OrderDetailVO>();
		
		allBySpaceDetail = all.stream()
				.filter(od -> spaceDetailId.equals(od.getOrderDetailId()))
				.collect(Collectors.toList());
		
		return allBySpaceDetail;
	}
}
