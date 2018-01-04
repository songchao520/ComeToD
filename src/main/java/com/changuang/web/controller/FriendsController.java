package com.changuang.web.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changuang.domain.entity.FriendSheet;
import com.changuang.domain.service.FriendsService;

import net.sf.json.JSONObject;

/**
* @author songc
* @version 创建时间：2017年12月6日 上午11:13:06
* @ClassName 类名称
* @Description 类描述
*/
@Controller
public class FriendsController {
	@Autowired
	FriendsService friendsService;
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取礼物
	 */
	@ResponseBody 
	@RequestMapping("/getFriendSheets")
	@SuppressWarnings("rawtypes")
	public JSONObject getFriendSheets(String pagesize, String currpage, String cxtj,FriendSheet friendSheet){
		List lis = friendsService.getFriendSheets(pagesize, currpage, cxtj, friendSheet);
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
	 * @desc 添加礼物
	 * @param anchorSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/saveFriendSheet") 
	public JSONObject saveFriendSheet(FriendSheet friendSheet){
		Serializable sl = friendsService.saveFriendSheet(friendSheet);
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
	 * @desc 修改礼物信息
	 * @param anchorSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/UpdateFriendSheet")  
	public JSONObject UpdateFriendSheet(FriendSheet friendSheet){
		JSONObject jso = new JSONObject();
		if(friendSheet.getRecid()==null && friendSheet.getUserRecidone() == null && friendSheet.getUserRecidtwo() == null){
			jso.put("msg", "没有接收到主键");			
			jso.put("result", "error");
			jso.put("data", "false");
			return jso;
		}
		boolean flag = friendsService.UpdateFriendSheet(friendSheet);	
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
