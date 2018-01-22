package com.changuang.domain.dao;

import java.io.Serializable;
import java.util.List;

import com.changuang.domain.entity.FriendSheet;

/**
* @author songc
* @version 创建时间：2017年12月6日 上午10:57:52
* @ClassName 类名称
* @Description 类描述
*/
public interface FriendsDao {
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取朋友列表
	 */
	@SuppressWarnings("rawtypes")
	public List getFriendSheets(String pagesize, String currpage, String cxtj,FriendSheet friendSheet);
	/**
	 * @desc 添加朋友
	 * @param anchorSheet
	 * @return
	 */
	public Serializable saveFriendSheet(FriendSheet friendSheet);
	/**
	 * @desc 修改朋友状态
	 * @param anchorSheet
	 * @return
	 */
	public boolean UpdateFriendSheet(FriendSheet friendSheet);
	public Integer getFriendSheetsCount(String pagesize, String currpage, String cxtj, FriendSheet friendSheet);

}
