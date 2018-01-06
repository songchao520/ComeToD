package com.changuang.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.changuang.domain.entity.PushActivity;
import com.changuang.domain.entity.UserSheet;
import com.changuang.domain.service.PushActivityService;
import com.changuang.domain.service.UserService;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;


/**
* @author songc
* @version 创建时间：2017年12月20日 下午5:51:55
* @ClassName 类名称
* @Description 类描述
*/
@SuppressWarnings("restriction")
@Controller
public class UploadController {
	@Autowired  
	UserService userService; 
	@Autowired
	PushActivityService pushActivityService;
	
	@SuppressWarnings("unchecked")
	@ResponseBody 
	@RequestMapping("/UploadFile")  
	public JSONObject UploadFile(@RequestParam("file") MultipartFile[] uploadFiles,HttpServletRequest request){
		JSONObject jso = new JSONObject();
		Map<String,Object> maps = new HashMap<String,Object>();
		String fileClass = request.getParameter("fileClass");
		String recid = request.getParameter("recid");
		String topath = "images";
		if(fileClass.equals("headImg")){
			topath = topath+"/headImg/";
		}
		for(int i =0;i<uploadFiles.length;i++){
			MultipartFile uploadFile = uploadFiles[i];
			String type = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().indexOf("."));
			String showname = System.currentTimeMillis()+""+i;
			String filename  = showname+type;
			System.out.println(filename);
			String realPath = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/resources/"+topath);
			
			Map<String,Object> maplist = new HashMap<String,Object>();
			try {
				File file = new File(realPath,filename);
				uploadFile.transferTo(file);
				maplist.put("code","0");
				maplist.put("msg","success");
				Map<String,String> newmaplist = new HashMap<String,String>();
				newmaplist.put("src",topath+showname+type);
				
				if(fileClass.equals("headImg")){
					UserSheet userSheet = new UserSheet();
					userSheet.setRecid(Integer.parseInt(recid));
					List<Map<String, Object>>  us = null;
					us = userService.getUserSheets("1", "1", null, userSheet);
					if(us.size()<1){
						jso.put("msg", "你传入的用户主键不正确");
						jso.put("data",recid);
						jso.put("result","error");
						return jso;
					}
					
					if(us.get(0).get("userHeadimg").toString().length() >1 && us.get(0).get("userHeadimg").toString().indexOf("http")==-1){
						String f = us.get(0).get("userHeadimg").toString();
						int idx = f.lastIndexOf("/");	 
						String filena = f.substring(idx + 1, f.length());
						File files = new File(realPath,filena);
						files.delete();
					}
					userSheet.setUserHeadimg(topath+showname+type);
					userService.UpdateUserSheet(userSheet);
				}
				
				maplist.put("data", newmaplist);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				maplist.put("code","1");
				maplist.put("msg","error");
				maplist.put("data", null);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				maplist.put("code","1");
				maplist.put("msg","error");
				maplist.put("data", null);
				
			}
			maps.put(showname, maplist);
		}
		jso.put("msg", "修改成功");
		jso.put("data",maps);
		jso.put("result","success");
		return jso;
		
	}
	/**
	 * @desc 64位上传照片
	 * @param request
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/UploadImages")  
	public JSONObject UploadImages(HttpServletRequest request){
		JSONObject jso = new JSONObject();
		String images = request.getParameter("images");
		String index = request.getParameter("index");
		String recid = request.getParameter("recid");
		//System.out.println(images);
		String getpath = "/WEB-INF/resources/images";
		if(index.equals("activityImg")){
			getpath = getpath+"/activity";
		}
		String realPath = request.getSession().getServletContext()
				.getRealPath(getpath);
		
		System.out.println(realPath);
		String jpgname = System.currentTimeMillis()+".jpg";
		System.out.println(jpgname);
		boolean flag = generateImage(images,realPath+"\\"+jpgname);
		
		if(index.equals("activityImg")){
			PushActivity pay = new PushActivity();
			pay.setRecid(Integer.parseInt(recid));
			pay.setRelevantImg("images/activity/"+jpgname);
			flag = pushActivityService.UpdatePushActivity(pay);
		}
		if(flag){
			
			jso.put("msg", "修改成功");
			jso.put("data",jpgname);
			jso.put("result","success");
		}else{
			jso.put("msg", "修改成功");
			jso.put("data",jpgname);
			jso.put("result","error");
		}
		return jso;
	}
	/***
	 * 上传照片方法
	 * @param imgStr
	 * @param path
	 * @return
	 */
	public static boolean generateImage(String imgStr, String path) {
		if(imgStr == null){
			return false;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(imgStr);
			for(int i=0;i<b.length;i++){
				if(b[i]<0){
					b[i]+=256;
				}
			}
			OutputStream out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
}
