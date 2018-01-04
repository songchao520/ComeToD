package com.changuang.web.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.changuang.domain.entity.GiftSheet;
import com.changuang.domain.entity.RewardSheet;
import com.changuang.domain.service.GiftService;

import net.sf.json.JSONObject;

/**
* @author songc
* @version 创建时间：2017年12月5日 下午5:17:27
* @ClassName 类名称
* @Description 类描述
*/
@Controller
public class GiftController {
	@Autowired
	GiftService giftService;
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取礼物
	 */
	@ResponseBody 
	@RequestMapping("/getGiftSheetsCount")
	public JSONObject getGiftSheetsCount(String pagesize, String currpage, String cxtj,GiftSheet giftSheet){
		Integer lis = giftService.getGiftSheetsCount(pagesize,currpage,cxtj,giftSheet);
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
	 * @desc 获取礼物
	 */
	@ResponseBody 
	@RequestMapping("/getGiftSheets")
	@SuppressWarnings("rawtypes")
	public JSONObject getGiftSheets(String pagesize, String currpage, String cxtj,GiftSheet giftSheet){
		List lis = giftService.getGiftSheets(pagesize,currpage,cxtj,giftSheet);
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
	 * @desc 添加礼物
	 * @param anchorSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/saveGiftSheet") 
	public JSONObject saveGiftSheet(GiftSheet giftSheet){
		Serializable sl = giftService.saveGiftSheet(giftSheet);
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
	 * @desc 修改礼物信息
	 * @param anchorSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/UpdateGiftSheet")  
	public JSONObject UpdateGiftSheet(GiftSheet giftSheet){
		JSONObject jso = new JSONObject();
		if(giftSheet.getRecid()==null){
			jso.put("msg", "没有接收到主键");			
			jso.put("result", "error");
			jso.put("data", "false");
			return jso;
		}
		boolean flag = giftService.UpdateGiftSheet(giftSheet);	
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
	 * @desc 获取打赏记录
	 */
	@ResponseBody 
	@RequestMapping("/getRewardSheetsCount")
	public JSONObject getRewardSheetsCount(String pagesize, String currpage, String cxtj,RewardSheet rewardSheet){
		Integer lis = giftService.getRewardSheetsCount(pagesize,currpage,cxtj,rewardSheet);
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
	 * @desc 获取打赏记录
	 */
	@ResponseBody 
	@RequestMapping("/getRewardSheets")
	@SuppressWarnings("rawtypes")
	public JSONObject getRewardSheets(String pagesize, String currpage, String cxtj,RewardSheet rewardSheet){
		List lis = giftService.getRewardSheets(pagesize,currpage,cxtj,rewardSheet);
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
	 * @desc 添加打赏记录
	 * @param anchorSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/saveRewardSheet") 
	public JSONObject saveRewardSheet(RewardSheet rewardSheet){
		Serializable sl = giftService.saveRewardSheet(rewardSheet);
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
}
