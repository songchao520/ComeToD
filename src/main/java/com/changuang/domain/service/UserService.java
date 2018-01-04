package com.changuang.domain.service;

import java.io.Serializable;
import java.util.List;

import com.changuang.domain.entity.UserSheet;
import com.changuang.domain.entity.UserType;

/**
* @author songc
* @version 创建时间：2017年12月1日 下午3:09:40
* @ClassName UserService
* @Description 用户表，用户类型表的业务逻辑层
*/
public interface UserService {
	
	/**
	 * @desc 保存用户类型
	 * @param usertype
	 * @return 用户类型ID
	 */
	public Serializable saveUserType(UserType usertype);
	/**
	 * @desc 获取用户类型
	 * @param usertype
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getUserTypes(UserType usertype);
	/**
	 * @desc 保存用户数据
	 * @param userSheet
	 * @return
	 */
	public Serializable saveUserSheet(UserSheet userSheet);
	/**
	 * @desc 获取用户数据表总数
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @param userSheet
	 * @return
	 */
	public Integer getUserSheetsCount(String pagesize, String currpage, String cxtj, UserSheet userSheet);
	
	/**
	 * @desc 获取用户数据表
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @param userSheet
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getUserSheets(String pagesize, String currpage, String cxtj,UserSheet userSheet);
	/***
	 * @desc 修改用户类型
	 * @param userSheet
	 * @return
	 */
	public boolean UpdateUserSheet(UserSheet userSheet);
	/**
	 * @desc 返回登录校验
	 * @param userSheet
	 * @return
	 */
	public String UserloginVerification(UserSheet userSheet);
	/**
	 * @desc 校验手机号码是否重复
	 * @param userSheet
	 * @return
	 */
	public boolean LoginNameVerification(String loginname);
	/**
	 * @desc 校验登录名是否重复
	 * @param userSheet
	 * @return
	 */
	public boolean LoginUserVerification(String loginname);
}
