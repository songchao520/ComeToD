package com.changuang.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changuang.domain.entity.PushActivity;
import com.changuang.domain.service.PushActivityService;

import net.sf.json.JSONObject;

/**
* @author songc
* @version 创建时间：2017年12月19日 下午2:36:17
* @ClassName 类名称
* @Description 类描述
*/
@Controller
public class PushActivityController {
	@Autowired
	PushActivityService pushActivityService;
	/**
	 * @desc 保存活动
	 * @param pagesize currpage cxtj PushActivity
	 * @return 活动ID
	 */
	@ResponseBody 
	@RequestMapping("/savePushActivity")  
	public JSONObject savePushActivity(HttpServletRequest request,PushActivity PushActivity){
		JSONObject jso = new JSONObject();		
		Serializable sl = pushActivityService.savePushActivity(PushActivity);
		if(sl != null){
			jso.put("msg", "注册成功");			
			jso.put("result", "success");
			jso.put("data", sl);
		}else{
			jso.put("msg", "注册失败，请联系管理员");			
			jso.put("result", "error");
			jso.put("data", sl);
		}
 		return jso;
	}
	/**
	 * @desc 获取活动总数
	 * @param usertype
	 * @return 活动集合
	 */
	@ResponseBody 
	@RequestMapping("/getPushActivitysCount")  
	public JSONObject getPushActivitysCount(String pagesize, String currpage, String cxtj, PushActivity PushActivity){
		Integer lis = pushActivityService.getPushActivitysCount(pagesize,currpage,cxtj,PushActivity);
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
	 * @desc 获取活动
	 * @param usertype
	 * @return 活动集合
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody 
	@RequestMapping("/getPushActivitys")  
	public JSONObject getPushActivitys(String pagesize, String currpage, String cxtj, PushActivity PushActivity){
		List lis = pushActivityService.getPushActivitys(pagesize,currpage,cxtj,PushActivity);
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
	 * @desc 获取活动
	 * @param usertype
	 * @return 活动集合
	 */
	@ResponseBody 
	@RequestMapping("/UpdatePushActivity")  
	public JSONObject UpdatePushActivity(PushActivity PushActivity){
		JSONObject jso = new JSONObject();
		if(PushActivity.getRecid()==null){
			jso.put("msg", "没有接收到主键");			
			jso.put("result", "error");
			jso.put("data", "false");
			return jso;
		}
		boolean flag = pushActivityService.UpdatePushActivity(PushActivity);	
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
