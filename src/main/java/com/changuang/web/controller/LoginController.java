package com.changuang.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changuang.domain.entity.AdminUser;
import com.changuang.domain.entity.AnchorOnline;
import com.changuang.domain.entity.UserSheet;
import com.changuang.domain.service.AdminUserService;
import com.changuang.domain.service.UserService;

import net.sf.json.JSONObject;
/**
 * @author songc
 *
 */
@Controller
public class LoginController {
	@Autowired  
	UserService userService; 
	@Autowired  
	AdminUserService adminUserService;
	//保存已登录的cookie标识，有新的cookie且登录用户名相同时，干掉上一个cookie
	public static Map<String, String> sessionids = new HashMap<String, String>();
	public static Map<String,AnchorOnline> anos =  new HashMap<String, AnchorOnline>();
	public static Map<String, String> adminsessionids = new HashMap<String, String>();
	/**
	 * @desc 用户登录验证
	 * @param request
	 * @param userlogin
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody 
	@RequestMapping("/loginVerification")  
    public JSONObject loginVerification(HttpServletRequest request,UserSheet userSheet){  
		JSONObject jso = new JSONObject();
		try {
			String bl = userService.UserloginVerification(userSheet);
			if(bl.equals("0")){
				jso.put("msg", "此用户已被禁用");			
				jso.put("result", "error");
				jso.put("data", bl);
			}else if(bl.equals("-1")){
				jso.put("msg", "用户名密码不正确");			
				jso.put("result", "error");
				jso.put("data", bl);
			}else{
				HttpSession session = request.getSession();
				if(sessionids.containsKey(bl)){
					//TODO 这里是在其他地点登录，给上一个手机登录的发送信息。
				}
				sessionids.put(bl, session.getId());
			    jso.put("msg", "登录成功");			
				jso.put("result", "success");
				Map<String, Object> ms = new HashMap<String, Object>();
				ms.put("recid", bl);
				userSheet = new UserSheet();
				userSheet.setRecid(Integer.parseInt(bl));
				List us = userService.getUserSheets("1", "1", null, userSheet);
				ms.put("userSheet", us);
				ms.put("loginId", session.getId());
				jso.put("data", us);
				jso.put("recid", bl);
				jso.put("loginId", session.getId());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return	jso;  
          
    }  
	
	
	/**
	 * @desc 登录验证后台用户
	 * @param request
	 * @param adminUser
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody 
	@RequestMapping("/loginAdmin")  
    public JSONObject loginAdmin(HttpServletRequest request,AdminUser adminUser){  
		JSONObject jso = new JSONObject();
		try {
			String bl = adminUserService.adminloginVerification(adminUser);
			if(bl.equals("0")){
				jso.put("msg", "此用户已被禁用");			
				jso.put("result", "error");
				jso.put("data", bl);
			}else if(bl.equals("-1")){
				jso.put("msg", "用户名密码不正确");			
				jso.put("result", "error");
				jso.put("data", bl);
			}else{
				HttpSession session = request.getSession();
				if(sessionids.containsKey(bl)){
					//TODO 这里是在其他地点登录，给上一个手机登录的发送信息。
				}
				sessionids.put(bl, session.getId());
			    jso.put("msg", "登录成功");			
				jso.put("result", "success");
				Map<String, Object> ms = new HashMap<String, Object>();
				ms.put("recid", bl);
				adminUser.setRecid(Integer.parseInt(bl));
				List us = adminUserService.getAdminUsers("1", "1", null, adminUser);
				ms.put("userSheet", us);
				ms.put("loginId", session.getId());
				jso.put("data", us);
				jso.put("recid", bl);
				jso.put("loginId", session.getId());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return	jso;  
    }  
	
	/**
	 * @desc jsp模式下，登录错误或过期指向登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/loginPc")  
    public String loginPc(Model model){  
		model.addAttribute("result", "请先登录或已失效");  
        return "/login";  
          
    } 
	
	/**
	 * @desc 登录失效或登录被顶必走方法，告诉前端已登录
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/loginMp")  
    public JSONObject loginMp(){  
		List<String> ls = new ArrayList<String>();
		JSONObject jso = new JSONObject();
		jso.put("msg", "请先登录！");			
		jso.put("result", "error");
		jso.put("data", ls);
        return jso;  
          
    } 

}
