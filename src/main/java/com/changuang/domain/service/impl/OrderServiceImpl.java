package com.changuang.domain.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.changuang.domain.dao.OrderDao;
import com.changuang.domain.entity.OrderProblem;
import com.changuang.domain.entity.OrderSheet;
import com.changuang.domain.service.OrderService;

/**
* @author songc
* @version 创建时间：2017年12月14日 上午11:33:44
* @ClassName OrderServiceImpl
* @Description 类描述
*/
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDao orderDao;
	
	@Override
	public Serializable saveOrder(OrderSheet orderSheet) {
		return orderDao.saveOrder(orderSheet);
	}
	public Integer getOrdersCount(String pagesize, String currpage, String cxtj, OrderSheet orderSheet){
		return orderDao.getOrdersCount(pagesize,currpage,cxtj,orderSheet);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getOrders(String pagesize, String currpage, String cxtj, OrderSheet orderSheet) {
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist =  orderDao.getOrders(pagesize,currpage,cxtj,orderSheet);
		if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("recid",object[0]!=null ?object[0]:"" );
				 map.put("userRecid",object[1]!=null ?object[1]:"");
				 map.put("anchorUser",object[2]!=null ?object[2]:"");
				 map.put("anchorRecid",object[3]!=null ?object[3]:"");
				 map.put("orderStatus",object[4]!=null ?object[4]:"");
				 if(object[5]!=null ){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String startTime = formatter.format(object[5]);
					 map.put("startTime",startTime);
				 }else{
					 map.put("startTime","");
				 }
				 if(object[6]!=null ){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String endTime = formatter.format(object[6]);
					 map.put("endTime",endTime);
				 }else{
					 map.put("endTime","");
				 }
				 if(object[7]!=null ){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String createTime = formatter.format(object[7]);
					 map.put("createTime", createTime);
				 }else{
					 map.put("createTime", "");
				 }
				 
				
				 map.put("purchaseTime", object[8]!=null ?object[8]:"");
				 map.put("useTime", object[9]!=null ?object[9]:"");
				 map.put("orderEvaluate", object[10]!=null ?object[10]:"");
				 map.put("orderScore",object[11] !=null ?object[11]:"");
				 map.put("tipAnchor",object[12]!=null ?object[12]:"" );
				 map.put("orderStatusname",object[13]!=null ?object[13]:"" );
				 map.put("anchorShowname",object[14]!=null ?object[14]:"" );
				 map.put("anchorHeadimg",object[15]!=null ?object[15]:"" );
				 map.put("userLoginname",object[16]!=null ?object[16]:"" );
				 map.put("anchorLoginname",object[17]!=null ?object[17]:"" );
				 amp.add(map);
			 }
		 }
		return amp;
	}

	@Override
	public boolean UpdateOrder(OrderSheet orderSheet) {
		return orderDao.UpdateOrder(orderSheet);
	}

	@Override
	public Serializable saveOrderProblem(OrderProblem orderproblem) {
		return orderDao.saveOrderProblem(orderproblem);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getOrderProblems(String pagesize, String currpage, String cxtj, OrderProblem orderproblem) {
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist =  orderDao.getOrderProblems(pagesize,currpage,cxtj,orderproblem);
		if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("recid",object[0]!=null ?object[0]:"" );
				 map.put("orderRecid",object[1]!=null ?object[1]:"");
				 map.put("userRecid",object[2]!=null ?object[2]:"");
				 map.put("feedbackContent",object[3]!=null ?object[3]:"");
				 if(object[4]!=null ){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String createTime = formatter.format(object[4]);
					 map.put("createTime", createTime);
				 }else{
					 map.put("createTime", "");
				 }
				 map.put("isreading",object[5]!=null ?object[5]:"");
				 map.put("reader",object[6]!=null ?object[6]:"");
				 map.put("replyContent",object[7]!=null ?object[7]:"");
				 if(object[8]!=null ){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String replyTime = formatter.format(object[8]);
					 map.put("replyTime", replyTime);
				 }else{
					 map.put("replyTime", "");
				 }
				 
				 map.put("tipAnchor", object[9]!=null ?object[9]:"");
				 map.put("userShowname", object[10]!=null ?object[10]:"");
				 map.put("anchorUser",object[11] !=null ?object[11]:"");
				 map.put("anchorShowname",object[12]!=null ?object[12]:"" );
				 map.put("anchorHeadimg",object[13]!=null ?object[13]:"" );
				 map.put("readerShowname",object[14]!=null ?object[14]:"" );
				 amp.add(map);
			 }
		 }
		return amp;
	}

	@Override
	public boolean updateOrderProblems(OrderProblem orderproblem) {
		return orderDao.updateOrderProblems(orderproblem);
	}

}
