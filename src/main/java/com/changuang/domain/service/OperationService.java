package com.changuang.domain.service;

import java.io.Serializable;
import java.util.List;

import com.changuang.domain.entity.CommentSheet;
import com.changuang.domain.entity.FollowSheet;
import com.changuang.domain.entity.ReplySheet;

/**
* @author songc
* @version 创建时间：2017年12月7日 下午2:14:33
* @ClassName OperationService
* @Description 用户关注，评论回复的业务逻辑接口。
*/
public interface OperationService {
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取用户关注列表
	 */
	@SuppressWarnings("rawtypes")
	public List getFollowSheets(String pagesize, String currpage, String cxtj,FollowSheet followSheet);
	/**
	 * @desc 添加用户关注
	 * @param FollowSheet
	 * @return
	 */
	public Serializable saveFollowSheet(FollowSheet followSheet);
	/**
	 * @desc 删除用户关注
	 * @param FollowSheet
	 * @return
	 */
	public boolean DeleteFollowSheet(FollowSheet followSheet);
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取评论列表
	 */
	@SuppressWarnings("rawtypes")
	public List getCommentSheets(String pagesize, String currpage, String cxtj,CommentSheet commentSheet);
	public Integer getCommentSheetsCount(String pagesize, String currpage, String cxtj,CommentSheet commentSheet);
	/**
	 * @desc 添加评论
	 * @param CommentSheet
	 * @return
	 */
	public Serializable saveCommentSheet(CommentSheet commentSheet);

	/**
	 * @desc 删除评论
	 * @param CommentSheet
	 * @return
	 */
	public boolean DeleteCommentSheet(CommentSheet commentSheet);
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取评论回复列表
	 */
	@SuppressWarnings("rawtypes")
	public List getReplySheets(String pagesize, String currpage, String cxtj,ReplySheet replySheet);
	public Integer getReplySheetsCount(String pagesize, String currpage, String cxtj,ReplySheet replySheet);
	/**
	 * @desc 添加回复内容
	 * @param ReplySheet
	 * @return
	 */
	public Serializable saveReplySheet(ReplySheet replySheet);

	/**
	 * @desc 删除回复内容
	 * @param ReplySheet
	 * @return
	 */
	public boolean DeleteReplySheet(ReplySheet replySheet);
}
