package com.changuang.web.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changuang.domain.entity.AnchorOnline;
import com.changuang.domain.entity.HomeUser;
import com.changuang.domain.entity.PushActivity;
import com.changuang.domain.service.AnchorService;
import com.changuang.domain.service.HomeUserService;
import com.changuang.domain.service.PushActivityService;

import net.sf.json.JSONObject;

/**
* @author songc
* @version 创建时间：2018年1月29日 下午4:41:51
* @ClassName 类名称
* @Description 类描述
*/
@Controller
public class HomeUserController {
	@Autowired
	HomeUserService homeUserService;
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
	 * @desc 获取房间用户总数
	 */
	@ResponseBody 
	@RequestMapping("/getHomeUsersCount")
	public JSONObject getHomeUsersCount(String pagesize, String currpage, String cxtj,HomeUser homeUser){
		Integer lis = homeUserService.getHomeUsersCount(pagesize,currpage,cxtj,homeUser);
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
	 * @desc 比对房间人数是否满员
	 */
	@ResponseBody 
	@RequestMapping("/compareHomeUser")
	public JSONObject compareHomeUser(Integer userNum,HomeUser homeUser){
		Integer userCount = homeUserService.getHomeUsersCount(null,null,null,homeUser);
		
		JSONObject jso = new JSONObject();
		if(userCount != null){
			if(userCount<userNum){
				jso.put("msg", "获取成功");			
				jso.put("result", "success");
				jso.put("data", true);
			}else{
				jso.put("msg", "人数已达到最大限制");			
				jso.put("result", "false");
				jso.put("data", false);
			}
			
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
	 * @desc 获取房间用户
	 */
	@ResponseBody 
	@RequestMapping("/getHomeUsers")
	@SuppressWarnings("rawtypes")
	public JSONObject getHomeUsers(String pagesize, String currpage, String cxtj,HomeUser homeUser){
		List lis = homeUserService.getHomeUsers(pagesize,currpage,cxtj,homeUser);
		JSONObject jso = new JSONObject();
		if(lis != null){
			jso.put("msg", "获取成功");			
			jso.put("result", "success");
			jso.put("count", homeUserService.getHomeUsersCount(pagesize, currpage, cxtj, homeUser));
			jso.put("data", lis);
		}else{
			jso.put("msg", "获取失败");			
			jso.put("result", "error");
			jso.put("data", null);
		}
		return jso;
	}
	/**
	 * @desc 添加房间用户
	 * @param HomeUser
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody 
	@RequestMapping("/saveHomeUser") 
	public JSONObject saveHomeUser(HomeUser homeUser){
		Serializable sl = homeUserService.saveHomeUser(homeUser);
		JSONObject jso = new JSONObject();
		if(sl != null){
			jso.put("msg", "保存成功");			
			jso.put("result", "success");
			AnchorOnline anchorOnline = new AnchorOnline();
			anchorOnline.setRecid(homeUser.getHomeRecid());
			List lis =  anchorService.getAnchorOnlines(null, null, null, anchorOnline, homeUser.getUserRecid());
			jso.put("data", lis);
			PushActivity pushActivity = new PushActivity();
			pushActivity.setSource("6");
			List liss = pushActivityService.getPushActivitys(null, null, null, pushActivity);
			jso.put("notice", liss);
		}else{
			jso.put("msg", "保存失败");			
			jso.put("result", "error");
			jso.put("data", sl);
		}
		return jso;
	}
	/**
	 * @desc 删除在线房间用户
	 * @param anchorSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/DeleteHomeUser")
	public Serializable DeleteHomeUser(HomeUser homeUser){
		JSONObject jso = new JSONObject();
		if(homeUser.getRecid()==null && homeUser.getHomeRecid()==null  && homeUser.getUserRecid()==null){
			jso.put("msg", "没有接收到主键");			
			jso.put("result", "error");
			jso.put("data", "false");
			return jso;
		}
		boolean flag = homeUserService.DeleteHomeUser(homeUser);
		
		if(flag){
			jso.put("msg", "删除成功");			
			jso.put("result", "success");
			jso.put("data", "true");
		}else{
			jso.put("msg", "删除成功");			
			jso.put("result", "error");
			jso.put("data", "false");
		}
		return jso;
	}
}
