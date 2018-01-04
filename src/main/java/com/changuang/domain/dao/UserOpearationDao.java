package com.changuang.domain.dao;

import java.io.Serializable;
import java.util.List;

import com.changuang.domain.entity.MonthlyMembers;
import com.changuang.domain.entity.WithDrawals;

/**
* @author songc
* @version 创建时间：2017年12月7日 下午6:38:40
* @ClassName UserOpearation
* @Description 提现，充值会员
*/
public interface UserOpearationDao {
	/**
	 * @desc 保存包月会员数据
	 * @param MonthlyMembers
	 * @return
	 */
	public Serializable saveMonthlyMembers(MonthlyMembers monthlyMembers);
	
	/**
	 * @desc 获取包月会员数据表 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @param MonthlyMembers
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getMonthlyMemberss(String pagesize, String currpage, String cxtj,MonthlyMembers monthlyMembers);
	
	/***
	 * @desc 修改包月会员类型
	 * @param MonthlyMembers
	 * @return
	 */
	public boolean UpdateMonthlyMembers(MonthlyMembers monthlyMembers);
	/**
	 * @desc 保存提现数据
	 * @param MonthlyMembers
	 * @return
	 */
	public Serializable saveWithDrawals(WithDrawals withDrawals);
	
	/**
	 * @desc 获取提现数据表 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @param MonthlyMembers
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getWithDrawalss(String pagesize, String currpage, String cxtj,WithDrawals withDrawals);
	
	/***
	 * @desc 修改提现
	 * @param MonthlyMembers
	 * @return
	 */
	public boolean UpdateWithDrawals(WithDrawals withDrawals);
}
