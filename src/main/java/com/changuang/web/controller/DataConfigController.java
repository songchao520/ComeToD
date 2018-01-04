package com.changuang.web.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changuang.domain.entity.DataFixed;
import com.changuang.domain.entity.LabelSheet;
import com.changuang.domain.service.DataConfigService;

import net.sf.json.JSONObject;

/**
* @author songc
* @version 创建时间：2017年12月22日 下午2:25:34
* @ClassName 类名称
* @Description 默认数据修改接口
*/
@Controller
public class DataConfigController {
	@Autowired
	DataConfigService dataConfigService;
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取标签
	 */
	@ResponseBody 
	@RequestMapping("/getLabelSheetsCount")
	public JSONObject getLabelSheetsCount(String pagesize, String currpage, String cxtj,LabelSheet LabelSheet){
		Integer lis = dataConfigService.getLabelSheetsCount(pagesize,currpage,cxtj,LabelSheet);
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
	 * @desc 获取标签
	 */
	@ResponseBody 
	@RequestMapping("/getLabelSheets")
	@SuppressWarnings("rawtypes")
	public JSONObject getLabelSheets(String pagesize, String currpage, String cxtj,LabelSheet LabelSheet){
		List lis = dataConfigService.getLabelSheets(pagesize,currpage,cxtj,LabelSheet);
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
	 * @desc 添加标签
	 * @param anchorSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/saveLabelSheet") 
	public JSONObject saveLabelSheet(LabelSheet LabelSheet){
		Serializable sl = dataConfigService.saveLabelSheet(LabelSheet);
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
	 * @desc 修改标签信息
	 * @param anchorSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/UpdateLabelSheet")  
	public JSONObject UpdateLabelSheet(LabelSheet LabelSheet){
		JSONObject jso = new JSONObject();
		if(LabelSheet.getRecid()==null){
			jso.put("msg", "没有接收到主键");			
			jso.put("result", "error");
			jso.put("data", "false");
			return jso;
		}
		boolean flag = dataConfigService.UpdateLabelSheet(LabelSheet);	
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
	 * @desc 获取金额兑换
	 */
	@ResponseBody 
	@RequestMapping("/getDataFixeds")
	@SuppressWarnings("rawtypes")
	public JSONObject getDataFixeds(String pagesize, String currpage, String cxtj,DataFixed dataFixed){
		List lis = dataConfigService.getDataFixeds(pagesize,currpage,cxtj,dataFixed);
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
	 * @desc 修改金额兑换
	 * @param DataFixed
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/UpdateDataFixed")  
	public JSONObject UpdateDataFixed(DataFixed dataFixed){
		JSONObject jso = new JSONObject();
		if(dataFixed.getRecid()==null){
			jso.put("msg", "没有接收到主键");			
			jso.put("result", "error");
			jso.put("data", "false");
			return jso;
		}
		boolean flag = dataConfigService.UpdateDataFixed(dataFixed);	
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
