package com.changuang.domain.service;

import java.io.Serializable;
import java.util.List;

import com.changuang.domain.entity.PushActivity;

/**
* @author songc
* @version 创建时间：2017年12月19日 下午2:20:21
* @ClassName 类名称
* @Description 类描述
*/
public interface PushActivityService {
	/**
	 * @desc 保存登录活动数据
	 * @return
	 */
	public Serializable savePushActivity(PushActivity pushActivity);
	
	/**
	 * @desc 获取活动数据
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @param PushActivity
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getPushActivitys(String pagesize, String currpage, String cxtj,PushActivity pushActivity);
	
	/***
	 * @desc 修改管理员用户用户
	 * @param PushActivity
	 * @return
	 */
	public boolean UpdatePushActivity(PushActivity pushActivity);
}
