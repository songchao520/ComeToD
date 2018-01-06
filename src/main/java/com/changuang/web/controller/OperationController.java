package com.changuang.web.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changuang.domain.entity.CommentSheet;
import com.changuang.domain.entity.FollowSheet;
import com.changuang.domain.entity.ReplySheet;
import com.changuang.domain.service.OperationService;

import net.sf.json.JSONObject;

/**
* @author songc
* @version 创建时间：2017年12月7日 下午2:42:12
* @ClassName OperationController
* @Description 用户关注，评论回复vie层
*/
@Controller
public class OperationController {
	@Autowired
	OperationService operationService;
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取用户关注
	 */
	@ResponseBody 
	@RequestMapping("/getFollowSheets")
	@SuppressWarnings("rawtypes")
	public JSONObject getFollowSheets(String pagesize, String currpage, String cxtj,FollowSheet followSheet){
		List lis = operationService.getFollowSheets(pagesize,currpage,cxtj,followSheet);
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
	 * @desc 添加用户关注
	 * @param followSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/saveFollowSheet")
	public Serializable saveFollowSheet(FollowSheet followSheet){
		Serializable sl = operationService.saveFollowSheet(followSheet);
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
	 * @desc 删除用户关注
	 * @param followSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/DeleteFollowSheet")
	public Serializable DeleteFollowSheet(FollowSheet followSheet){
		JSONObject jso = new JSONObject();
		if(followSheet.getRecid()==null && followSheet.getUserRecid() == null && followSheet.getAnchorRecid()==null){
			jso.put("msg", "没有接收到主键");			
			jso.put("result", "error");
			jso.put("data", "false");
			return jso;
		}
		boolean flag = operationService.DeleteFollowSheet(followSheet);
		
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
	
	@ResponseBody 
	@RequestMapping("/getCommentSheetsCount")
	public JSONObject getCommentSheetsCount(String pagesize, String currpage, String cxtj,CommentSheet commentSheet){
		Integer lis = operationService.getCommentSheetsCount(pagesize,currpage,cxtj,commentSheet);
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
	 * @desc 获取动态评论
	 */
	@ResponseBody 
	@RequestMapping("/getCommentSheets")
	@SuppressWarnings("rawtypes")
	public JSONObject getCommentSheets(String pagesize, String currpage, String cxtj,CommentSheet commentSheet,String getReply){
		List  lis = null;
		if(getReply!=null && getReply.equals("replytrue")){
			lis=operationService.getCommentSheetsAndReply(pagesize,currpage,cxtj,commentSheet);
		}else{
			lis = operationService.getCommentSheets(pagesize,currpage,cxtj,commentSheet);
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
	 * @desc 添加动态评论
	 * @param commentSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/saveCommentSheet")
	public Serializable saveCommentSheet(CommentSheet commentSheet){
		Serializable sl = operationService.saveCommentSheet(commentSheet);
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
	 * @desc 删除动态评论
	 * @param commentSheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/DeleteCommentSheet")
	public Serializable DeleteCommentSheet(CommentSheet commentSheet){
		JSONObject jso = new JSONObject();
		if(commentSheet.getRecid()==null && commentSheet.getUserRecid() == null && commentSheet.getDynamicRecid()==null){
			jso.put("msg", "没有接收到主键");			
			jso.put("result", "error");
			jso.put("data", "false");
			return jso;
		}
		boolean flag = operationService.DeleteCommentSheet(commentSheet);
		
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
	 * @desc 获取评论下回复
	 */
	@ResponseBody 
	@RequestMapping("/getReplySheetsCount")
	public JSONObject getReplySheetsCount(String pagesize, String currpage, String cxtj,ReplySheet replySheet){
		Integer lis = operationService.getReplySheetsCount(pagesize,currpage,cxtj,replySheet);
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
	 * @desc 获取评论下回复
	 */
	@ResponseBody 
	@RequestMapping("/getReplySheets")
	@SuppressWarnings("rawtypes")
	public JSONObject getReplySheets(String pagesize, String currpage, String cxtj,ReplySheet replySheet){
		List lis = operationService.getReplySheets(pagesize,currpage,cxtj,replySheet);
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
	 * @desc 添加评论下回复
	 * @param replySheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/saveReplySheet")
	public Serializable saveReplySheet(ReplySheet replySheet){
		Serializable sl = operationService.saveReplySheet(replySheet);
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
	 * @desc 删除评论下回复
	 * @param replySheet
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/DeleteReplySheet")
	public Serializable DeleteReplySheet(ReplySheet replySheet){
		JSONObject jso = new JSONObject();
		if(replySheet.getRecid()==null && replySheet.getUserRecid() == null && replySheet.getCommentRecid()==null){
			jso.put("msg", "没有接收到主键");			
			jso.put("result", "error");
			jso.put("data", "false");
			return jso;
		}
		boolean flag = operationService.DeleteReplySheet(replySheet);
		
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
