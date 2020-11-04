package com.orderMaster.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.orderDetail.model.*;
import com.spaceDetail.model.*;
import com.spacePhoto.model.*;

public class OrderMasterService {
	
	private OrderMasterDAOInterface dao;
	private OrderDetailDAO oddao;
	private SpaceDetailDAO sddao;
	
	public OrderMasterService() {
		dao = new OrderMasterDAO();
	}
	
	
	public OrderMasterVO addOrderMaster(OrderMasterVO orderMasterVO) {
		dao.insert(orderMasterVO);
		return orderMasterVO;
	}
	

	public String insertwithOrderDetail(OrderMasterVO orderMasterVO, List<OrderDetailVO> odlist) {
		return dao.insertwithOrderDetail(orderMasterVO, odlist);
	}
	
	public OrderMasterVO updateOrderMaster(OrderMasterVO orderMasterVO) {		
		dao.update(orderMasterVO);
		return orderMasterVO;
	}
	//付款完成，設定訂單狀態
	public void purchaseDone(String orderMasterId) {
		dao.purchaseDone(orderMasterId);
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
	
	//超長遠征取SpaceId以生成場地照片
	public String runforSPId(String orderMasterId) {
		OrderMasterVO omVO = dao.selectOne(orderMasterId);
		System.out.println(omVO.getOrderMasterId());
		String spaceDetailId = oddao.selectOneSPId(omVO.getOrderMasterId());
		String spaceId = sddao.selectOneSpaceId(spaceDetailId);
		return spaceId;
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