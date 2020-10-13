package com.orderMaster.model;

import java.util.List;

public interface OrderMasterDAOInterfaceB {
	public void insert(OrderMasterVO orderMasterVO);
	public void delete(String orderMasterId);
	public void update(OrderMasterVO orderMasterVO);
	public OrderMasterVO selectOne(String orderMasterId);
	public List<OrderMasterVO> selectAll();
//	public List<OrderMasterVO> selectAllByMember(String memberId);
	
}
