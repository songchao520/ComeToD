package com.changuang.web.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changuang.domain.entity.OrderProblem;
import com.changuang.domain.entity.OrderSheet;
import com.changuang.domain.service.OrderService;

import net.sf.json.JSONObject;

/**
* @author songc
* @version 创建时间：2017年12月14日 下午4:02:25
* @ClassName OrderDaoController
* @Description 类描述
*/
@Controller
public class OrderDaoController {
	@Autowired
	OrderService orderService;
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取订单总量
	 */
	@ResponseBody 
	@RequestMapping("/getOrderSheetsCount")
	public JSONObject getOrdersCount(String pagesize, String currpage, String cxtj,OrderSheet orderSheet){
		Integer lis = orderService.getOrdersCount(pagesize, currpage, cxtj, orderSheet);
		JSONObject jso = new JSONObject();
		if(lis != null){
			jso.put("msg", "获取成功");			
			jso.put("result", "success");
			jso.put("data", lis);
		}else{
			jso.put("msg", "获取失败");			
			jso.put("result", "error");
			jso.put("data", null);
		}
		return jso;
	}
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取订单
	 */
	@ResponseBody 
	@RequestMapping("/getOrderSheets")
	@SuppressWarnings("rawtypes")
	public JSONObject getOrderSheets(String pagesize, String currpage, String cxtj,OrderSheet orderSheet){
		List lis = orderService.getOrders(pagesize, currpage, cxtj, orderSheet);
		JSONObject jso = new JSONObject();
		if(lis != null){
			jso.put("msg", "获取成功");			
			jso.put("result", "success");
			jso.put("data", lis);
		}else{
			jso.put("msg", "获取失败");			
			jso.put("result", "error");
			jso.put("data", null);
		}
		return jso;
	}
	/**
	 * @desc 添加订单
	 * @param anchorSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/saveOrderSheet") 
	public JSONObject saveOrderSheet(OrderSheet orderSheet){
		Serializable sl = orderService.saveOrder(orderSheet);
		JSONObject jso = new JSONObject();
		if(sl != null){
			jso.put("msg", "保存成功");			
			jso.put("result", "success");
			jso.put("data", sl);
		}else{
			jso.put("msg", "保存失败");			
			jso.put("result", "error");
			jso.put("data", sl);
		}
		return jso;
	}
	/**
	 * @desc 修改订单信息
	 * @param anchorSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/UpdateOrderSheet")  
	public JSONObject UpdateOrderSheet(OrderSheet orderSheet){
		JSONObject jso = new JSONObject();
		if(orderSheet.getRecid()==null){
			jso.put("msg", "没有接收到主键");			
			jso.put("result", "error");
			jso.put("data", "false");
			return jso;
		}
		boolean flag = orderService.UpdateOrder(orderSheet);	
		if(flag){
			jso.put("msg", "更新成功");			
			jso.put("result", "success");
			jso.put("data", "true");
		}else{
			jso.put("msg", "更新失败");			
			jso.put("result", "error");
			jso.put("data", "false");
		}
		return jso;
	}
	
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取订单问题
	 */
	@ResponseBody 
	@RequestMapping("/getOrderProblems")
	@SuppressWarnings("rawtypes")
	public JSONObject getOrderProblems(String pagesize, String currpage, String cxtj,OrderProblem orderProblem){
		List lis = orderService.getOrderProblems(pagesize, currpage, cxtj, orderProblem);
		JSONObject jso = new JSONObject();
		if(lis != null){
			jso.put("msg", "获取成功");			
			jso.put("result", "success");
			jso.put("data", lis);
		}else{
			jso.put("msg", "获取失败");			
			jso.put("result", "error");
			jso.put("data", null);
		}
		return jso;
	}
	/**
	 * @desc 添加订单问题
	 * @param anchorSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/saveOrderProblem") 
	public JSONObject saveOrderProblem(OrderProblem orderProblem){
		Serializable sl = orderService.saveOrderProblem(orderProblem);
		JSONObject jso = new JSONObject();
		if(sl != null){
			jso.put("msg", "保存成功");			
			jso.put("result", "success");
			jso.put("data", sl);
		}else{
			jso.put("msg", "保存失败");			
			jso.put("result", "error");
			jso.put("data", sl);
		}
		return jso;
	}
	/**
	 * @desc 修改订单问题信息
	 * @param anchorSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/UpdateOrderProblem")  
	public JSONObject UpdateOrderProblem(OrderProblem orderProblem){
		JSONObject jso = new JSONObject();
		if(orderProblem.getRecid()==null){
			jso.put("msg", "没有接收到主键");			
			jso.put("result", "error");
			jso.put("data", "false");
			return jso;
		}
		boolean flag = orderService.updateOrderProblems(orderProblem);	
		if(flag){
			jso.put("msg", "更新成功");			
			jso.put("result", "success");
			jso.put("data", "true");
		}else{
			jso.put("msg", "更新失败");			
			jso.put("result", "error");
			jso.put("data", "false");
		}
		return jso;
	}
	
}
