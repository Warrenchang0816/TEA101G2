package com.orderMaster.model;

import java.util.List;

import com.orderDetail.model.OrderDetailVO;

public interface OrderMasterDAOInterface {
	public void insert(OrderMasterVO orderMasterVO);
	public void delete(String orderMasterId);
	public void update(OrderMasterVO orderMasterVO);
	public void updateStatus(OrderMasterVO orderMasterVO);
	public OrderMasterVO selectOne(String orderMasterId);
	public List<OrderMasterVO> selectAll();
	public void insertwithOrderDetail(OrderMasterVO orderMasterVO, List<OrderDetailVO> odlist);
//	public List<OrderMasterVO> selectAllByMember(String memberId);
	
}