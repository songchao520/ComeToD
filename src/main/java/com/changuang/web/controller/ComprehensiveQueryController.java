package com.changuang.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changuang.domain.entity.FollowSheet;
import com.changuang.domain.entity.UserSheet;
import com.changuang.domain.service.OperationService;
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
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取用户个人信息的所有
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody 
	@RequestMapping("/getUserInfoPage") 
	public JSONObject getUserInfoPage(Integer userRecid){
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
}
