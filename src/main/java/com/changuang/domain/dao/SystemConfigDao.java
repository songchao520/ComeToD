/**
 * 
 */
package com.changuang.domain.dao;

import java.io.Serializable;
import java.util.List;

import com.changuang.domain.entity.PageConfig;
import com.changuang.domain.entity.PageManage;

/**
 * @author Administrator
 *
 */
public interface SystemConfigDao {
	
	/**
	 * @desc 保存权限控制管理页面
	 * @return
	 */
	public Serializable savePageManage(PageManage pmg);
	/**
	 * @desc 删除权限控制管理页面
	 * @return
	 */
	public boolean deletePageManage(PageManage pmg);
	/**
	 * @desc 根据用户权限获取页面集合
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getPagesByUser(Integer usertyperecid);
	/**
	 * @desc 获取页面配置集合
	 * @return
	 */
	public List<PageConfig> getPageConfig();
	

	


}


