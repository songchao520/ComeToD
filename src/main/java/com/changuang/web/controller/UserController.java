package com.changuang.web.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.changuang.domain.entity.UserSheet;
import com.changuang.domain.entity.UserType;
import com.changuang.domain.service.UserService;
import com.changuang.domain.util.SmsSends;

import net.sf.json.JSONObject;

/**
* @author songc
* @version 创建时间：2017年12月1日 下午3:16:20
* @ClassName UserController
* @Description 用户表，用户类型表控制层对接view
*/
@Controller
public class UserController {
	public static Map<String,String> maps = new HashMap<String,String>();
	@Autowired  
	UserService userService; 
	
	/**
	 * @desc 保存用户类型
	 * @param usertype
	 * @return 用户类型ID
	 */
	@ResponseBody 
	@RequestMapping("/saveUserType")  
	public JSONObject saveUserType(UserType usertype){
		Serializable sl = userService.saveUserType(usertype);
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
	 * @desc 获取短信验证码
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws ClientException 
	 */
	@ResponseBody 
	@RequestMapping("/SmsAccepted")  
	public JSONObject SmsAccepted(HttpServletRequest request) throws IOException, ClientException{
		String loginname = request.getParameter("userMobilephone");
		String forgetpassword = request.getParameter("forgetpassword");
		boolean flag = true;
		JSONObject jso = new JSONObject();
		if(forgetpassword!=null && forgetpassword.equals("forgetpassword")){
			boolean fg = userService.LoginNameVerification(loginname);
			if(fg){
				jso.put("msg", "没有此手机号");			
				jso.put("result", "error");
				jso.put("data", "");
				return jso;
			}
			flag = true;
		}else{
			flag = userService.LoginNameVerification(loginname);
		}
		
		
		if(!flag){
			jso.put("msg", "手机号码已被注册");			
			jso.put("result", "error");
			jso.put("data", "");
			return jso;
		}
		Integer s = (int)((Math.random()*9+1)*100000);
		SendSmsResponse response = SmsSends.sendSms(loginname, s.toString());
		maps.put(loginname, s.toString());
		
		if(response.getCode().toString().equals("OK")){
			jso.put("msg", "短信发送成功");			
			jso.put("result", "success");
			jso.put("data", response.getCode());
		}else{
			jso.put("msg", "短信发送失败");			
			jso.put("result", "error");
			jso.put("data", s);
		}	
		return jso;
	}
	/**
	 * @desc 忘记密码
	 * @param pagesize currpage cxtj userSheet
	 * @return 用户ID
	 */
	@ResponseBody 
	@RequestMapping("/forgetUserPassword")  
	public JSONObject forgetUserPassword(HttpServletRequest request,UserSheet userSheet){
		JSONObject jso = new JSONObject();		
		String loginname = userSheet.getUserMobilephone();
		String smsYzm = request.getParameter("smsYzm");
 		if(!maps.containsKey(loginname)){
 			jso.put("msg", "请先发送短信验证码");			
			jso.put("result", "error");
			jso.put("data", "");
		}else{
			if(maps.get(loginname).indexOf(smsYzm)==-1){
				jso.put("msg", "短信验证码不正确");			
				jso.put("result", "error");
				jso.put("data", "");
			}else{
				boolean sl = userService.UpdateUserSheet(userSheet);
				if(sl){
					jso.put("msg", "修改成功");			
					jso.put("result", "success");
					jso.put("data", sl);
				}else{
					jso.put("msg", "修改失败，请联系管理员");			
					jso.put("result", "error");
					jso.put("data", sl);
				}
				maps.remove(loginname);
			}
		}
 		return jso;
	}
	/**
	 * @desc 获取用户类型
	 * @param usertype
	 * @return 用户类型集合
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody 
	@RequestMapping("/getUserTypes")  
	public JSONObject getUserTypes(UserType usertype){
		List lis = userService.getUserTypes(usertype);
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
	 * @desc 保存用户
	 * @param pagesize currpage cxtj userSheet
	 * @return 用户ID
	 */
	@ResponseBody 
	@RequestMapping("/saveUserSheet")  
	public JSONObject saveUserSheet(HttpServletRequest request,UserSheet userSheet){
		JSONObject jso = new JSONObject();		
		String loginname = userSheet.getUserMobilephone();
		String smsYzm = request.getParameter("smsYzm");
 		if(!maps.containsKey(loginname)){
 			jso.put("msg", "请先发送短信验证码");			
			jso.put("result", "error");
			jso.put("data", "");
		}else{
			if(maps.get(loginname).indexOf(smsYzm)==-1){
				jso.put("msg", "短信验证码不正确");			
				jso.put("result", "error");
				jso.put("data", "");
			}else{
				Serializable sl = userService.saveUserSheet(userSheet);
				if(sl != null){
					jso.put("msg", "注册成功");			
					jso.put("result", "success");
					jso.put("data", sl);
				}else{
					jso.put("msg", "注册失败，请联系管理员");			
					jso.put("result", "error");
					jso.put("data", sl);
				}
				maps.remove(loginname);
			}
		}
 		return jso;
	}
	/**
	 * @desc 获取用户
	 * @param usertype
	 * @return 用户集合
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody 
	@RequestMapping("/getUserSheets")  
	public JSONObject getUserSheets(String pagesize, String currpage, String cxtj, UserSheet userSheet){
		List lis = userService.getUserSheets(pagesize,currpage,cxtj,userSheet);
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
	 * @desc 获取用户总数
	 * @param usertype
	 * @return 用户集合
	 */
	@ResponseBody 
	@RequestMapping("/getUserSheetsCount")  
	public JSONObject getUserSheetsCount(String pagesize, String currpage, String cxtj, UserSheet userSheet){
		Integer ss = userService.getUserSheetsCount(pagesize,currpage,cxtj,userSheet);
		JSONObject jso = new JSONObject();
		if(ss != null){
			jso.put("msg", "获取成功");			
			jso.put("result", "success");
			jso.put("data", ss);
		}else{
			jso.put("msg", "获取失败");			
			jso.put("result", "error");
			jso.put("data", null);
		}
		return jso;
	}
	/**
	 * @desc 获取用户
	 * @param usertype
	 * @return 用户集合
	 */
	@ResponseBody 
	@RequestMapping("/UpdateUserSheet")  
	public JSONObject UpdateUserSheet(HttpServletRequest request,UserSheet userSheet){
		JSONObject jso = new JSONObject();
		String bindPhone = request.getParameter("bindPhone");
		if(bindPhone!=null && bindPhone.equals("bindPhone")){
			String smsYzm = request.getParameter("smsYzm");
			String loginname = userSheet.getUserMobilephone();
			if(!maps.containsKey(loginname)){
	 			jso.put("msg", "请先发送短信验证码");			
				jso.put("result", "error");
				jso.put("data", "");
			}else{
				if(maps.get(loginname).indexOf(smsYzm)==-1){
					jso.put("msg", "短信验证码不正确");			
					jso.put("result", "error");
					jso.put("data", "");
				}else{
					boolean sl = userService.UpdateUserSheet(userSheet);
					if(sl){
						jso.put("msg", "修改成功");			
						jso.put("result", "success");
						jso.put("data", sl);
					}else{
						jso.put("msg", "修改失败，请联系管理员");			
						jso.put("result", "error");
						jso.put("data", sl);
					}
					maps.remove(loginname);
				}
			}
		}
		
		if(userSheet.getRecid()==null){
			jso.put("msg", "没有接收到主键");			
			jso.put("result", "error");
			jso.put("data", "false");
			return jso;
		}
		boolean flag = userService.UpdateUserSheet(userSheet);	
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
	 * @desc 第三方授权登录
	 * @param 
	 * @return request
	 */
	@SuppressWarnings({ "unchecked" })
	@ResponseBody 
	@RequestMapping("/ThirdLogin")  
	public JSONObject ThirdLogin(HttpServletRequest request){
		JSONObject jso = new JSONObject();		
		String from = request.getParameter("from");
		String openid = request.getParameter("openid");
		if(openid==null){
			jso.put("msg", "微信主键为空");			
			jso.put("result", "error");
			jso.put("data","");
			return jso;
		}
		UserSheet userSheet = new UserSheet();
		userSheet.setUserLoginname(openid);
		if(userService.LoginUserVerification(openid)){
			String sex = request.getParameter("sex");
			String name  = request.getParameter("name");
			String headimg = request.getParameter("headImg");
			
			userSheet.setUserSource(from);
			userSheet.setUserShowname(name);
			userSheet.setUserHeadimg(headimg);
			Integer sexs = Integer.parseInt(sex);
			userSheet.setUserSex(sexs);
			Serializable sl = userService.saveUserSheet(userSheet);
			if(sl== null){
				jso.put("msg", "系统保存第三方用户失败，请联系管理员");			
				jso.put("result", "error");
				jso.put("data", sl);
				return jso;
			}
		}
		
		String bl = userService.UserloginVerification(userSheet);
		if(bl.equals("0")){
			jso.put("msg", "此用户已被禁用");			
			jso.put("result", "error");
			jso.put("data", bl);
		}else{
			
			HttpSession session = request.getSession();
			if(LoginController.sessionids.containsKey(bl)){
				//TODO 这里是在其他地点登录，给上一个手机登录的发送信息。
			}
			LoginController.sessionids.put(bl, session.getId());
		    jso.put("msg", "登录成功");			
			jso.put("result", "success");
			Map<String, Object> ms = new HashMap<String, Object>();
			List<Map<String, Object>>  us = null;
			
			if(bl.equals("-1")){
				us= userService.getUserSheets("1","1", null, userSheet);
				bl = us.get(0).get("recid").toString();
				
			}else{

				us = userService.getUserSheets("1", "1", null, userSheet);
			}
			ms.put("recid", bl);
			ms.put("userSheet", us);
			ms.put("loginId", session.getId());
			jso.put("data", us);
			jso.put("recid", bl);
			jso.put("loginId", session.getId());
			
		}
		
		
		
 		return jso;
	}
}
