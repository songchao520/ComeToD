package com.changuang.domain.dao;

import java.io.Serializable;
import java.util.List;

import com.changuang.domain.entity.OrderProblem;
import com.changuang.domain.entity.OrderSheet;

/**
* @author songc
* @version 创建时间：2017年12月7日 下午5:09:07
* @ClassName OrderDao
* @Description 订单及订单投诉的接口
*/
public interface OrderDao {
	/**
	 * @desc 插入订单(客服，客户，不包括司机)
	 * @param userlogin
	 * @return
	 */
	Serializable saveOrder(OrderSheet orderSheet);
	/**
	 * @desc根据一定条件获取订单数据总量
	 * @param orderSheet
	 * @return
	 */
	public Integer getOrdersCount(String pagesize, String currpage, String cxtj, OrderSheet orderSheet);
	
	/**
	 * @desc根据一定条件获取订单数据
	 * @param orderSheet
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getOrders(String pagesize,String currpage,String cxtj,OrderSheet orderSheet);
	
	/**
	 * @desc 根据某些条件改变订单
	 * @param orderSheet
	 * @return
	 */
	public boolean UpdateOrder(OrderSheet orderSheet);
	/**
	 * @desc 保存订单问题
	 * @param opinionsheet
	 * @return
	 */
	public Serializable saveOrderProblem(OrderProblem orderproblem);
	/**
	 * @desc根据一定条件获取订单问题数据
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getOrderProblems(String pagesize,String currpage,String cxtj,OrderProblem orderproblem);
	/**
	 * @desc根据一定条件修改订单数据
	 * @return
	 */
	public boolean updateOrderProblems(OrderProblem orderproblem);
}
