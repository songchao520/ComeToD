package com.changuang.web.controller;

import java.io.File;
import java.io.IOException;
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

import com.changuang.domain.entity.UserSheet;
import com.changuang.domain.service.UserService;

import net.sf.json.JSONObject;


/**
* @author songc
* @version 创建时间：2017年12月20日 下午5:51:55
* @ClassName 类名称
* @Description 类描述
*/
@Controller
public class UploadController {
	@Autowired  
	UserService userService; 
	
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
}
