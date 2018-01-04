package com.changuang.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.changuang.domain.entity.AdminUser;
import com.changuang.domain.service.AdminUserService;

import net.sf.json.JSONObject;

/**
* @author songc
* @version 创建时间：2017年12月19日 上午11:50:19
* @ClassName 类名称
* @Description 类描述
*/
@Controller
public class AdminUserController {
	@Autowired  
	AdminUserService adminUserService;
	/**
	 * @desc 保存管理用户
	 * @param pagesize currpage cxtj AdminUser
	 * @return 管理用户ID
	 */
	@ResponseBody 
	@RequestMapping("/saveAdminUser")  
	public JSONObject saveAdminUser(HttpServletRequest request,AdminUser adminUser){
		
		JSONObject jso = new JSONObject();		
		Serializable sl = adminUserService.saveAdminUser(adminUser);
		if(sl != null){
			jso.put("msg", "保存成功");			
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
	 * @desc 获取管理用户
	 * @param usertype
	 * @return 管理用户集合
	 */
	@ResponseBody 
	@RequestMapping("/getAdminUsersCount")  
	public JSONObject getAdminUsersCount(String pagesize, String currpage, String cxtj, AdminUser adminUser){
		Integer lis = adminUserService.getAdminUsersCount(pagesize,currpage,cxtj,adminUser);
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
	 * @desc 获取管理用户
	 * @param usertype
	 * @return 管理用户集合
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody 
	@RequestMapping("/getAdminUsers")  
	public JSONObject getAdminUsers(String pagesize, String currpage, String cxtj, AdminUser adminUser){
		List lis = adminUserService.getAdminUsers(pagesize,currpage,cxtj,adminUser);
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
	 * @desc 更改管理用户
	 * @param usertype
	 */
	@ResponseBody 
	@RequestMapping("/UpdateAdminUser")  
	public JSONObject UpdateAdminUser(AdminUser adminUser){
		JSONObject jso = new JSONObject();
		if(adminUser.getRecid()==null){
			jso.put("msg", "没有接收到主键");			
			jso.put("result", "error");
			jso.put("data", "false");
			return jso;
		}
		boolean flag = adminUserService.UpdateAdminUser(adminUser);	
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
