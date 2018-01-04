package com.changuang.domain.service;

import java.io.Serializable;
import java.util.List;

import com.changuang.domain.entity.AdminUser;

/**
* @author songc
* @version 创建时间：2017年12月19日 上午11:28:12
* @ClassName 类名称
* @Description 类描述
*/
public interface AdminUserService {
	/**
	 * @desc 保存登录后台用户数据
	 * @return
	 */
	public Serializable saveAdminUser(AdminUser adminUser);
	
	/**
	 * @desc 获取后台用户数据
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @param AdminUser
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getAdminUsers(String pagesize, String currpage, String cxtj,AdminUser adminUser);
	/**
	 * @desc 获取后台用户数据总数
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @param AdminUser
	 * @return
	 */
	public Integer getAdminUsersCount(String pagesize, String currpage, String cxtj,AdminUser adminUser);
	
	/***
	 * @desc 修改管理员用户用户
	 * @param AdminUser
	 * @return
	 */
	public boolean UpdateAdminUser(AdminUser adminUser);
	/**
	 * @desc 校验管理登录名是否重复
	 * @param loginname
	 * @return
	 */
	public boolean LoginNameVerification(String loginname);
	/**
	 * @desc 校验登录
	 * @param adminUser
	 * @return
	 */
	public String adminloginVerification(AdminUser adminUser);
}
