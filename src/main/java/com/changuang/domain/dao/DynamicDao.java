package com.changuang.domain.dao;

import java.io.Serializable;
import java.util.List;
import com.changuang.domain.entity.DynamicLikes;
import com.changuang.domain.entity.UserDynamic;

/**
* @author songc
* @version 创建时间：2017年12月6日 下午3:50:41
* @ClassName DynamicDao
* @Description 用户动态接口
*/
public interface DynamicDao {
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取动态列表总数
	 */
	public Integer getUserDynamicsCount(String pagesize, String currpage, String cxtj, UserDynamic userDynamic);
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取动态列表
	 */
	@SuppressWarnings("rawtypes")
	public List getUserDynamics(String pagesize, String currpage, String cxtj,UserDynamic userDynamic);
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取动态列表
	 */
	@SuppressWarnings("rawtypes")
	public List getUserDynamics(String pagesize, String currpage, String cxtj,UserDynamic userDynamic,Integer thisRecid);
	/**
	 * @desc 添加动态
	 * @param userDynamic
	 * @return
	 */
	public Serializable saveUserDynamic(UserDynamic userDynamic);
	/**
	 * @desc 修改用户动态
	 * @param userDynamic
	 * @return
	 */
	public boolean UpdateUserDynamic(UserDynamic userDynamic);
	/**
	 * @desc 删除动态
	 * @param userDynamic
	 * @return
	 */
	public boolean DeleteUserDynamic(UserDynamic userDynamic);
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取动态列表
	 */
	@SuppressWarnings("rawtypes")
	public List getDynamicLikes(String pagesize, String currpage, String cxtj,DynamicLikes dynamicLikes);
	/**
	 * @desc 添加动态
	 * @param userDynamic
	 * @return
	 */
	public Serializable saveDynamicLikes(DynamicLikes dynamicLikes);
	/**
	 * @desc 删除动态
	 * @param userDynamic
	 * @return
	 */
	public boolean DeleteDynamicLikes(DynamicLikes dynamicLikes);

}
