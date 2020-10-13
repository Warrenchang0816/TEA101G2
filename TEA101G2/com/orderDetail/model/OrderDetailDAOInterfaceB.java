package com.orderDetail.model;

import java.util.List;

public interface OrderDetailDAOInterfaceB {
	public void insert(OrderDetailVO orderDetailVO);
	public void delete(String orderDetailId);
	public void update(OrderDetailVO orderDetailVO);
	public OrderDetailVO selectOne(String orderDetailId);
	public List<OrderDetailVO> selectAll();
//	public List<OrderDetailVO> selectAllByMasterId(String orderMasterId);
	

}
