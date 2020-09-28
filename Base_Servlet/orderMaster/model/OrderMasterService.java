package com.orderMaster.model;

import java.util.List;

public class OrderMasterService {
	
	private OrderMasterDAOInterface dao;
	
	public OrderMasterService() {
		dao = new OrderMasterDAO();
	}
	
	
	public OrderMasterVO addOrderMaster(OrderMasterVO orderMasterVO) {
		dao.insert(orderMasterVO);
		return orderMasterVO;
	}
	
	
	public OrderMasterVO updateOrderMaster(OrderMasterVO orderMasterVO) {		
		dao.update(orderMasterVO);
		return orderMasterVO;
	}
	
	public void deleteOrderMaster(String orderMasterId) {
		dao.delete(orderMasterId);
	}
	
	
	public OrderMasterVO selectOneOrderMaster(String spaceDetailId) {
		return dao.selectOne(spaceDetailId);
	}
	
	public List<OrderMasterVO> selectAllOrderMaster() {
		return dao.selectAll();
	}

}
