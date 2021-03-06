package com.changuang.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.changuang.domain.entity.AnchorOnline;
import com.changuang.domain.entity.AnchorSheet;
import com.changuang.domain.entity.AnchorStatusSheet;
import com.changuang.domain.entity.PushActivity;
import com.changuang.domain.entity.UserSheet;
import com.changuang.domain.service.AnchorService;
import com.changuang.domain.service.PushActivityService;
import com.changuang.domain.service.UserService;

import net.sf.json.JSONObject;

/**
* @author songc
* @version 创建时间：2017年12月4日 上午11:18:17
* @ClassName AnchorController
* @Description 主播表及主播审核表控制层
*/
@Controller
public class AnchorController {
	@Autowired
	AnchorService anchorService;
	@Autowired
	UserService userService;
	@Autowired
	PushActivityService pushActivityService;
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取主播总数
	 */
	@ResponseBody 
	@RequestMapping("/getAnchorSheetsCount")
	public JSONObject getAnchorSheetsCount(String pagesize, String currpage, String cxtj,AnchorSheet anchorSheet,String sortZiduan){
		Integer lis = anchorService.getAnchorSheetsCount(pagesize,currpage,cxtj,anchorSheet,sortZiduan);
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
	 * @desc 获取主播
	 */
	@ResponseBody 
	@RequestMapping("/getAnchorSheets")
	@SuppressWarnings("rawtypes")
	public JSONObject getAnchorSheets(String pagesize, String currpage, String cxtj,AnchorSheet anchorSheet,String sortZiduan){
		List lis = anchorService.getAnchorSheets(pagesize,currpage,cxtj,anchorSheet,sortZiduan);
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
	 * @desc 添加主播
	 * @param anchorSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/saveAnchorSheet") 
	public JSONObject saveAnchorSheet(AnchorSheet anchorSheet){
		Serializable sl = anchorService.saveAnchorSheet(anchorSheet);
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
	 * @desc 修改主播信息
	 * @param anchorSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/UpdateAnchorSheet")  
	public JSONObject UpdateAnchorSheet(AnchorSheet anchorSheet){
		JSONObject jso = new JSONObject();
		if(anchorSheet.getRecid()==null && anchorSheet.getUserRecid()==null){
			jso.put("msg", "没有接收到主键或用户主键");			
			jso.put("result", "error");
			jso.put("data", "false");
			return jso;
		}
		boolean flag = anchorService.UpdateAnchorSheet(anchorSheet);	
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
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取审核主播表
	 */
	@ResponseBody 
	@RequestMapping("/getAnchorStatusSheetsCount")
	public JSONObject getAnchorStatusSheetsCount(String pagesize, String currpage, String cxtj,AnchorStatusSheet anchorStatusSheet){
		Integer lis = anchorService.getAnchorStatusSheetsCount(pagesize,currpage,cxtj,anchorStatusSheet);
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
	 * @desc 获取审核主播表
	 */
	@ResponseBody 
	@RequestMapping("/getAnchorStatusSheets")
	@SuppressWarnings("rawtypes")
	public JSONObject getAnchorStatusSheets(String pagesize, String currpage, String cxtj,AnchorStatusSheet anchorStatusSheet){
		List lis = anchorService.getAnchorStatusSheets(pagesize,currpage,cxtj,anchorStatusSheet);
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
	 * @desc 添加审核主播
	 * @param anchorSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/saveAnchorStatusSheet")
	public Serializable saveAnchorStatusSheet(@RequestParam("file") MultipartFile[] uploadFiles,HttpServletRequest request,AnchorStatusSheet anchorStatusSheet){
		JSONObject jso = new JSONObject();
		String showname = System.currentTimeMillis()+"";
		String topath = "images";
		topath = topath+"/examineImg/";
		for(int i =0;i<uploadFiles.length;i++){
			MultipartFile uploadFile = uploadFiles[i];
			String type = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().indexOf("."));
			String fileName = uploadFile.getOriginalFilename();
			String filename  = showname+i+type;
			if(fileName.indexOf("bigScreen")!=-1){
				anchorStatusSheet.setMyPhoto(topath+filename);
			}else if(fileName.indexOf("smallScreen")!=-1){
				anchorStatusSheet.setSmallPhoto(topath+filename);
			}else if(fileName.indexOf("cardScreen")!=-1){			
				anchorStatusSheet.setExaminePhoto(topath+filename);
			}		
			
			System.out.println(filename);
			
			String realPath = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/resources/"+topath);
			try {
				if(i==1){
					
				}
				File file = new File(realPath,filename);
				try {
					uploadFile.transferTo(file);
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
		
	
		if(anchorStatusSheet.getUserRecid()!=null){
			String userLabel = request.getParameter("userLabel");
			String userLabelt = request.getParameter("userLabelt");
			String userLabels = request.getParameter("userLabels");
			UserSheet us = new UserSheet();
			us.setRecid(anchorStatusSheet.getUserRecid());
			us.setUserLabel(userLabel);
			us.setUserLabelt(userLabelt);
			us.setUserLabels(userLabels);
			userService.UpdateUserSheet(us);
		}else{
			jso.put("msg", "没有用户主键");			
			jso.put("result", "error");
			jso.put("data", "");
			return jso;
		}
		Serializable sl = anchorService.saveAnchorStatusSheet(anchorStatusSheet);
		
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
	 * @desc 修改审核主播信息
	 * @param anchorSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/UpdateAnchorStatusSheet")
	public JSONObject UpdateAnchorStatusSheet(AnchorStatusSheet anchorStatusSheet){
		JSONObject jso = new JSONObject();
		if(anchorStatusSheet.getRecid()==null && anchorStatusSheet.getUserRecid()==null){
			jso.put("msg", "没有接收到主键或用户主键");			
			jso.put("result", "error");
			jso.put("data", "false");
			return jso;
		}
		boolean flag = anchorService.UpdateAnchorStatusSheet(anchorStatusSheet);	
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
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取在线主播表
	 */
	@ResponseBody 
	@RequestMapping("/getAnchorOnlines")
	@SuppressWarnings("rawtypes")
	public JSONObject getAnchorOnlines(String pagesize, String currpage, String cxtj,AnchorOnline anchorOnline,Integer thisRecid){
		List lis = anchorService.getAnchorOnlines(pagesize,currpage,cxtj,anchorOnline,thisRecid);
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
	 * @desc 添加在线主播
	 * @param anchorSheet
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody 
	@RequestMapping("/saveAnchorOnline")
	public Serializable saveAnchorOnline(AnchorOnline anchorOnline){
		AnchorSheet anchorSheet = new AnchorSheet();
		anchorSheet.setUserRecid(anchorOnline.getUserRecid());
		List<Map<String, Object>>  lis = anchorService.getAnchorSheets(null,null,null,anchorSheet,null);
		
		Integer anchorRecid = (Integer) lis.get(0).get("recid");
		anchorOnline.setAnchorRecid(anchorRecid);
		Serializable sl = anchorService.saveAnchorOnline(anchorOnline);
		JSONObject jso = new JSONObject();
		if(sl != null){
			anchorOnline.setRecid(Integer.parseInt(sl.toString()));
			jso.put("msg", "保存成功");			
			jso.put("result", "success");
			PushActivity pushActivity = new PushActivity();
			pushActivity.setSource("6");
			List liss = pushActivityService.getPushActivitys(null, null, null, pushActivity);
			jso.put("notice", liss);
			jso.put("data", anchorService.getAnchorOnlines(null, null, null, anchorOnline,null));
		}else{
			jso.put("msg", "保存失败");			
			jso.put("result", "error");
			jso.put("data", sl);
		}
		return jso;
	}
	/**
	 * @desc 修改在线主播信息
	 * @param anchorSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/UpdateAnchorOnline")
	public JSONObject UpdateAnchorOnline(AnchorOnline anchorOnline){
		JSONObject jso = new JSONObject();
		if(anchorOnline.getRecid()==null && anchorOnline.getAnchorRecid()==null){
			jso.put("msg", "没有接收到主键或用户主键");			
			jso.put("result", "error");
			jso.put("data", "false");
			return jso;
		}
		boolean flag = anchorService.UpdateAnchorOnline(anchorOnline);	
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
	 * @desc 删除在线主播
	 * @param anchorSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/DeleteAnchorOnline")
	public Serializable DeleteAnchorOnline(AnchorOnline anchorOnline){
		JSONObject jso = new JSONObject();
		if(anchorOnline.getRecid()==null && anchorOnline.getAnchorRecid()==null){
			jso.put("msg", "没有接收到主键或主播主键");			
			jso.put("result", "error");
			jso.put("data", "false");
			return jso;
		}
		boolean flag = anchorService.DeleteAnchorOnline(anchorOnline);
		
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
