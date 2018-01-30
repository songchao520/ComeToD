package com.changuang.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changuang.domain.entity.AnchorOnline;
import com.changuang.domain.entity.FollowSheet;
import com.changuang.domain.entity.FriendSheet;
import com.changuang.domain.entity.PushActivity;
import com.changuang.domain.entity.UserSheet;
import com.changuang.domain.service.AnchorService;
import com.changuang.domain.service.FriendsService;
import com.changuang.domain.service.OperationService;
import com.changuang.domain.service.PushActivityService;
import com.changuang.domain.service.UserService;

import net.sf.json.JSONObject;

/**
* @author songc
* @version 创建时间：2018年1月9日 下午5:19:25
* @ClassName ComprehensiveQueryController
* @Description 综合查询
*/
@Controller
public class ComprehensiveQueryController {
	@Autowired  
	UserService userService; 
	@Autowired
	OperationService operationService;
	@Autowired
	FriendsService friendsService;
	@Autowired
	AnchorService anchorService;
	@Autowired
	PushActivityService pushActivityService;
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取用户个人信息的所有
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody 
	@RequestMapping("/getUserInfoPage") 
	public JSONObject getUserInfoPage(Integer userRecid,Integer thisRecid){
		JSONObject jso = new JSONObject();		
		FollowSheet followSheet = new FollowSheet();
		followSheet.setUserRecid(userRecid);
		Integer guanz = operationService.getFollowSheetsCount(null, null, null, followSheet);
		if(guanz != null){
			jso.put("guanz", guanz);
		}else{
			jso.put("guanz", 0);
		}
		followSheet = new FollowSheet();
		followSheet.setAnchorRecid(userRecid);
		Integer fens = operationService.getFollowSheetsCount(null, null, null, followSheet);
		if(fens != null){
			jso.put("fens", fens);
		}else{
			jso.put("fens", 0);
		}
		followSheet = new FollowSheet();
		followSheet.setUserRecid(thisRecid);
		followSheet.setAnchorRecid(userRecid);
		Integer isGuan  = operationService.getFollowSheetsCount(null, null, null, followSheet);
		if(isGuan != 0){
			jso.put("isGuan", 1);
		}else{
			jso.put("isGuan", 0);
		}
		FriendSheet fs = new FriendSheet();
		fs.setUserRecidone(thisRecid);
		fs.setUserRecidtwo(userRecid);
		List ls = friendsService.getFriendSheets(null, null, null, fs);
		if(ls!=null && ls.size()!=0){
			jso.put("isFriend", ((Map<String, Object>)ls.get(0)).get("friendStatus"));
		}else{
			jso.put("isFriend", 0);
		}
		UserSheet userSheet = new UserSheet();
		userSheet.setRecid(userRecid);
		List lis = userService.getUserSheets(null,null,null,userSheet);
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
	@SuppressWarnings("rawtypes")
	@ResponseBody 
	@RequestMapping("/getFriendStatus") 
	public JSONObject getFriendStatus(Integer userRecid,Integer friendStatus){
		JSONObject jso = new JSONObject();
		FriendSheet fs = new FriendSheet();
		fs.setUserRecidone(userRecid);
		fs.setFriendStatus(friendStatus);
		List lis =  friendsService.getFriendSheets(null, null, null, fs);
		if(lis != null){
			jso.put("yanz", lis);
		}else{
			jso.put("msg", "获取失败");			
			jso.put("result", "error");
			jso.put("data", null);
			return jso;
		}
		fs = new FriendSheet();
		fs.setUserRecidtwo(userRecid);
		fs.setFriendStatus(friendStatus);
		lis =  friendsService.getFriendSheets(null, null, null, fs);
		if(lis != null){
			jso.put("shenq", lis);
			jso.put("msg", "获取成功");			
			jso.put("result", "success");
		}else{
			jso.put("msg", "获取失败");			
			jso.put("result", "error");
			jso.put("data", null);
			return jso;
		}
		return jso;
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody 
	@RequestMapping("/getHotAnchor") 
	public JSONObject getHotAnchor(AnchorOnline anchorOnline){
		JSONObject jso = new JSONObject();
		PushActivity pushA = new PushActivity();
		pushA.setSource(5+"");
		List liss = pushActivityService.getPushActivitys("10",null,null,pushA);
		anchorOnline.setIsHot(1);
		anchorOnline.setIsFree(1);
		anchorOnline.setAnchorClass(1);
		List lis = anchorService.getAnchorOnlines(null,null,null, anchorOnline);
		anchorOnline.setIsHot(1);
		anchorOnline.setIsFree(1);
		anchorOnline.setAnchorClass(2);
		List lisss = anchorService.getAnchorOnlines(null,null,null, anchorOnline);
		if(lis != null && liss!=null){
			jso.put("relentimg",liss);
			jso.put("anchorVoice", lis);
			jso.put("anchorVideo", lisss);
			jso.put("msg", "获取成功");			
			jso.put("result", "success");
			
		}else{
			jso.put("msg", "获取失败");			
			jso.put("result", "error");
			jso.put("data", null);
			return jso;
		}
		return jso;
	}
}
