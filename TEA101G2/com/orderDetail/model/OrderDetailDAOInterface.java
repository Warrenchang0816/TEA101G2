package com.orderDetail.model;

import java.util.List;

public interface OrderDetailDAOInterface {
	public void insert(OrderDetailVO orderDetailVO);
	//用接到的OM來新增OrderDetail
	public void insertwithOrderMaster(OrderDetailVO orderDetailVO, java.sql.Connection con);
	public void delete(String orderDetailId);
	public void update(OrderDetailVO orderDetailVO);
	public OrderDetailVO selectOne(String orderDetailId);
	public List<OrderDetailVO> selectAll();
	//取所有相同spaceDetailId的OrderDetailVO
	public List<OrderDetailVO> selectAllBySD(String spaceDetailId);
	//取所有相同orderMasterId的OrderDetailVO
	public List<OrderDetailVO> selectAllByMasterId(String orderMasterId);
	//遠征用
	public String selectOneSPId(String orderMasterId);

}