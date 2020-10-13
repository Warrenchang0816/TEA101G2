package com.orderMaster.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.orderDetail.model.*;

public class OrderMasterService {
	
	private OrderMasterDAOInterface dao;
	
	public OrderMasterService() {
		dao = new OrderMasterDAO();
	}
	
	
	public OrderMasterVO addOrderMaster(OrderMasterVO orderMasterVO) {
		dao.insert(orderMasterVO);
		return orderMasterVO;
	}
	

	public void insertwithOrderDetail(OrderMasterVO orderMasterVO, List<OrderDetailVO> odlist) {
		dao.insertwithOrderDetail(orderMasterVO, odlist);
	}
	
	public OrderMasterVO updateOrderMaster(OrderMasterVO orderMasterVO) {		
		dao.update(orderMasterVO);
		return orderMasterVO;
	}
	
	public void deleteOrderMaster(String orderMasterId) {
		dao.delete(orderMasterId);
	}
	
	
	public OrderMasterVO selectOneOrderMaster(String orderMasterId) {
		return dao.selectOne(orderMasterId);
	}
	
	public List<OrderMasterVO> selectAllOrderMaster() {
		return dao.selectAll();
	}
	
//	public List<OrderMasterVO> selectAllOrderMasterByMember(String memberId) {
//		return dao.selectAllByMember(memberId);
//	}
	
	public List<OrderMasterVO> selectAllOrderMasterByMember(String memberId) {
		List<OrderMasterVO> all = dao.selectAll();
		List<OrderMasterVO> allBySpace = new ArrayList<OrderMasterVO>();
		
		allBySpace = all.stream()
				.filter(om -> memberId.equals(om.getMemberId()))
//				.filter(om -> om.getMemberId().equals(memberId))
				.collect(Collectors.toList());
		
		return allBySpace;
	}
	
	public List<OrderMasterVO> selectAllOrderMasterByStatus(String memberStatus) {
		List<OrderMasterVO> all = dao.selectAll();
		List<OrderMasterVO> allByStatus = new ArrayList<OrderMasterVO>();
		
		allByStatus = all.stream()
				.filter(om -> memberStatus.equals(om.getOrderStatus()))
				.collect(Collectors.toList());
		
		return allByStatus;
	}

}