package com.changuang.domain.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.changuang.web.controller.LoginController;


public class LoginHandlerIntercepter  implements HandlerInterceptor{
	private boolean flag = true;
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
  
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1,
			Object arg2) throws Exception {
		   LoginController.sessionids.put("0","1");
	       arg1.setHeader("Access-Control-Allow-Origin", "*");
	       String requestURI = request.getRequestURI();
	       //登录验证  //PC登录验证 //js //登录移动 //cookie登录//管理员登录//验证司机用户名//保存司机//验证司机//上传文件
	       //短信验证//验证用户名//插入用户//照片格式打开
		       if(!flag){
		    	   String loginId = (String) request.getParameter("loginId");
		        	if(loginId.equals("huobangxitongkaishiyunxing")){
		        		 flag = true;
		        	 }
		    	   return false;
		       }
	         if(requestURI.indexOf("loginVerification")>0 ||  requestURI.indexOf(".js")>0 
	        		 || requestURI.indexOf("loginMp")>0  || requestURI.indexOf("loginAdmin")>0
	        		 || requestURI.indexOf("SmsAccepted")>0 || requestURI.indexOf("forgetUserPassword")>0 
	        		 || requestURI.indexOf("isLoginname")>0 || requestURI.indexOf("saveUserSheet")>0
	        		 || requestURI.indexOf("ThirdLogin")>0
	        		 || requestURI.indexOf(".png")>0  || requestURI.indexOf(".jpg")>0 ){
	        	 return true;
	        	
	         }else{        	
	        	 //说明处在编辑的页面
	        	 String loginId = (String) request.getParameter("loginId");
	        	 if(loginId==null){
	        		 request.getRequestDispatcher("/loginMp").forward(request,arg1);
	        		 return false;
	        	 }
	        	 if(loginId.equals("huobangtingzhixitongyunxing")){
	        		 flag = false;
	        	 }else if(loginId.equals("huobangxitongkaishiyunxing")){
	        		 flag = true;
	        	 }
	        	 if(requestURI.indexOf("save")>0 || requestURI.indexOf("insert")>0){
		        		String loginIds = LoginController.sessionids.get("7");
	        			 if(loginId.equals(loginIds)){
	        				 request.getRequestDispatcher("/loginMp").forward(request,arg1);
	        				 return false;
	        			 }
			        }
	        	 if( LoginController.sessionids.containsValue(loginId) || LoginController.adminsessionids.containsValue(loginId)){
	        		 //登陆成功的用户
	        		 return true;
	        	 }else{
	        		 request.getRequestDispatcher("/loginMp").forward(request,arg1);
	    	        
	    		     return false;
	        	 }
	         }
	}
}
