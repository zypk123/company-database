package com.huntkey.rx.hr.provider.service;
import com.huntkey.rx.commons.utils.rest.Pagination;
public interface OrderListService {
	/***
	 * 查询单据列表
	 * @param orderNo 单据号
	 * @param orderType 单据类型
	 * @param applicant 申请人
	 * @param orderStatus 单据状态
	 * @param startPage 当前页
	 * @param rows 每页记录数
	 * @return
	 */
	Pagination orderList(String orderNo,String orderType,String applicant,String orderStatus,
			int startPage,int rows);
	int deleteOrder(String orderTable,String orderId);
	
	int deleteHrTempOrderByCre(String creuser);
}
