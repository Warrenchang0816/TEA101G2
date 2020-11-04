package com.orderMaster.model;

import java.util.List;

import com.orderDetail.model.OrderDetailVO;

public interface OrderMasterDAOInterface {
	public void insert(OrderMasterVO orderMasterVO);
	public void delete(String orderMasterId);
	public void update(OrderMasterVO orderMasterVO);
	public OrderMasterVO selectOne(String orderMasterId);
	public List<OrderMasterVO> selectAll();
	//同時新增orderMaster以及orderDetail用，並回傳OrderMasterId
	public String insertwithOrderDetail(OrderMasterVO orderMasterVO, List<OrderDetailVO> odlist);
	//付款完畢用
	public void purchaseDone(String orderMasterId);
//	public List<OrderMasterVO> selectAllByMember(String memberId);
	
}