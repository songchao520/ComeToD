package com.changuang.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.changuang.domain.entity.DynamicLikes;
import com.changuang.domain.entity.UserDynamic;
import com.changuang.domain.service.DynamicService;
import com.changuang.domain.util.ImgCompress;

import net.sf.json.JSONObject;

/**
* @author songc
* @version 创建时间：2017年12月6日 下午4:58:30
* @ClassName DynamicController
* @Description 用户动态及点赞的view类
*/
@Controller
public class DynamicController {
	@Autowired
	DynamicService dynamicService;
	
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取用户动态总数
	 */
	@ResponseBody 
	@RequestMapping("/getUserDynamicsCount")
	public JSONObject getUserDynamicsCount(String pagesize, String currpage, String cxtj,UserDynamic userDynamic,Integer thisRecid){
		Integer lis  = dynamicService.getUserDynamicsCount(pagesize,currpage,cxtj,userDynamic);
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
	 * @desc 获取用户动态
	 */
	@ResponseBody 
	@RequestMapping("/getUserDynamics")
	@SuppressWarnings("rawtypes")
	public JSONObject getUserDynamics(String pagesize, String currpage, String cxtj,UserDynamic userDynamic,Integer thisRecid){
		List lis;
		if(thisRecid!=null){
			 lis = dynamicService.getUserDynamics(pagesize,currpage,cxtj,userDynamic,thisRecid);
		}else{
			 lis = dynamicService.getUserDynamics(pagesize,currpage,cxtj,userDynamic);
		}
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
	 * @desc 保存动态
	 * @param anchorSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/saveUserDynamic")
	public Serializable saveUserDynamic(@RequestParam("file") MultipartFile[] uploadFiles,HttpServletRequest request,UserDynamic userDynamic){
		
		JSONObject jso = new JSONObject();
		String showname = System.currentTimeMillis()+"";
		String topath = "images";
		topath = topath+"/dynamic/";
		String imgstring = "";
		for(int i =0;i<uploadFiles.length-1;i++){
			MultipartFile uploadFile = uploadFiles[i];
			String type = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().indexOf("."));
			String filename  = showname+i+type;		
			System.out.println(filename);
			String realPath = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/resources/"+topath);
			try {
				
				File file = new File(realPath,filename);
				imgstring =topath+ filename+","+imgstring;
				
				try {
					
					uploadFile.transferTo(file);
					ImgCompress impC = new ImgCompress(realPath+"/"+filename);
					impC.resizeByHeight(300);
				} catch (IOException e) {
					jso.put("msg", "图片提交失败");			
					jso.put("result", "error");
					jso.put("data", "");
					return jso;
				}		
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				jso.put("msg", "图片提交失败");			
				jso.put("result", "error");
				jso.put("data", "");
				return jso;
			}
		}
		if(imgstring.length()>1){
			imgstring = imgstring.substring(0,imgstring.length()-1);
			userDynamic.setDynamicImages(imgstring);
		}
		
		Serializable sl = dynamicService.saveUserDynamic(userDynamic);
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
	 * @desc 修改用户动态
	 * @param anchorSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/UpdateUserDynamic")
	public JSONObject UpdateUserDynamic(UserDynamic userDynamic){
		JSONObject jso = new JSONObject();
		if(userDynamic.getRecid()==null){
			jso.put("msg", "没有接收到主键");			
			jso.put("result", "error");
			jso.put("data", "false");
			return jso;
		}
		boolean flag = dynamicService.UpdateUserDynamic(userDynamic);	
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
	 * @desc 删除动态
	 * @param anchorSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/DeleteUserDynamic")
	public Serializable DeleteUserDynamic(UserDynamic userDynamic){
		JSONObject jso = new JSONObject();
		if(userDynamic.getRecid()==null){
			jso.put("msg", "没有接收到主键");			
			jso.put("result", "error");
			jso.put("data", "false");
			return jso;
		}
		boolean flag = dynamicService.DeleteUserDynamic(userDynamic);
		
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
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取动态点赞
	 */
	@ResponseBody 
	@RequestMapping("/getDynamicLikes")
	@SuppressWarnings("rawtypes")
	public JSONObject getDynamicLikes(String pagesize, String currpage, String cxtj,DynamicLikes dynamicLikes){
		List lis = dynamicService.getDynamicLikes(pagesize,currpage,cxtj,dynamicLikes);
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
	 * @desc 添加动态点赞
	 * @param anchorSheet
	 * @return
	 */
	@ResponseBody  
	@RequestMapping("/saveDynamicLikes")
	public Serializable saveDynamicLikes(DynamicLikes dynamicLikes){
		Serializable sl = dynamicService.saveDynamicLikes(dynamicLikes);
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
	 * @desc 删除点赞
	 * @param userDynamic
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/DeleteDynamicLikes")
	public Serializable DeleteDynamicLikes(DynamicLikes dynamicLikes){
		JSONObject jso = new JSONObject();
		if(dynamicLikes.getRecid()==null && dynamicLikes.getUserRecid() == null && dynamicLikes.getDynamicRecid()==null){
			jso.put("msg", "没有接收到主键");			
			jso.put("result", "error");
			jso.put("data", "false");
			return jso;
		}
		boolean flag = dynamicService.DeleteDynamicLikes(dynamicLikes);
		
		if(flag){
			jso.put("msg", "删除成功");			
			jso.put("result", "success");
			jso.put("data", "true");
		}else{
			jso.put("msg", "删除失败");			
			jso.put("result", "error");
			jso.put("data", "false");
		}
		return jso;
	}
}
