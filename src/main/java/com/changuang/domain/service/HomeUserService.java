package com.changuang.domain.service;

import java.io.Serializable;
import java.util.List;

import com.changuang.domain.entity.HomeUser;

/**
* @author songc
* @version 创建时间：2018年1月29日 下午4:36:33
* @ClassName 类名称
* @Description 类描述
*/
public interface HomeUserService {
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取房间用户总数
	 */
	public Integer getHomeUsersCount(String pagesize, String currpage, String cxtj,HomeUser homeUser);
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取房间用户
	 */
	@SuppressWarnings("rawtypes")
	public List getHomeUsers(String pagesize, String currpage, String cxtj,HomeUser homeUser);
	/**
	 * @desc 添加房间用户
	 * @param anchorSheet
	 * @return
	 */
	public Serializable saveHomeUser(HomeUser homeUser);
	/**
	 * @desn 删除房间用户
	 * @param HomeUser
	 * @return
	 */
	public boolean DeleteHomeUser(HomeUser homeUser);
}
