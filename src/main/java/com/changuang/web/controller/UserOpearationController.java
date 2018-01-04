package com.changuang.web.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changuang.domain.entity.MonthlyMembers;
import com.changuang.domain.entity.WithDrawals;
import com.changuang.domain.service.UserOpearationService;

import net.sf.json.JSONObject;

/**
* @author songc
* @version 创建时间：2017年12月8日 下午3:28:22
* @ClassName MonthlyMembersController
* @Description 类描述
*/
@Controller
public class UserOpearationController {
	@Autowired  
	UserOpearationService userOpearationService;
	
	/**
	 * @desc 保存包月会员
	 * @param pagesize currpage cxtj MonthlyMembers
	 * @return 包月会员ID
	 */
	@ResponseBody 
	@RequestMapping("/saveMonthlyMembers")  
	public JSONObject saveMonthlyMembers(MonthlyMembers monthlyMembers){
		Serializable sl = userOpearationService.saveMonthlyMembers(monthlyMembers);
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
	 * @desc 获取包月会员
	 * @param usertype
	 * @return 包月会员集合
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody 
	@RequestMapping("/getMonthlyMemberss")  
	public JSONObject getMonthlyMemberss(String pagesize, String currpage, String cxtj, MonthlyMembers monthlyMembers){
		List lis = userOpearationService.getMonthlyMemberss(pagesize,currpage,cxtj,monthlyMembers);
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
	 * @desc 更改包月会员
	 * @param usertype
	 * @return 包月会员集合
	 */
	@ResponseBody 
	@RequestMapping("/UpdateMonthlyMembers")  
	public JSONObject UpdateMonthlyMembers(MonthlyMembers monthlyMembers){
		JSONObject jso = new JSONObject();
		if(monthlyMembers.getRecid()==null){
			jso.put("msg", "没有接收到主键");			
			jso.put("result", "error");
			jso.put("data", "false");
			return jso;
		}
		boolean flag = userOpearationService.UpdateMonthlyMembers(monthlyMembers);	
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
	 * @desc 保存提现表
	 * @param pagesize currpage cxtj WithDrawals
	 * @return 提现表ID
	 */
	@ResponseBody 
	@RequestMapping("/saveWithDrawals")  
	public JSONObject saveWithDrawals(WithDrawals withDrawals){
		Serializable sl = userOpearationService.saveWithDrawals(withDrawals);
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
	 * @desc 获取提现表
	 * @param usertype
	 * @return 提现表集合
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody 
	@RequestMapping("/getWithDrawalss")  
	public JSONObject getWithDrawalss(String pagesize, String currpage, String cxtj, WithDrawals withDrawals){
		List lis = userOpearationService.getWithDrawalss(pagesize,currpage,cxtj,withDrawals);
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
	 * @desc 更改提现表
	 * @param usertype
	 * @return 提现表集合
	 */
	@ResponseBody 
	@RequestMapping("/UpdateWithDrawals")  
	public JSONObject UpdateWithDrawals(WithDrawals withDrawals){
		JSONObject jso = new JSONObject();
		if(withDrawals.getRecid()==null){
			jso.put("msg", "没有接收到主键");			
			jso.put("result", "error");
			jso.put("data", "false");
			return jso;
		}
		boolean flag = userOpearationService.UpdateWithDrawals(withDrawals);	
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
