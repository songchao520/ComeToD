package com.changuang.domain.service;

import java.io.Serializable;
import java.util.List;
import com.changuang.domain.entity.PageConfig;
import com.changuang.domain.entity.PageManage;

public interface SystemConfigService {
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
	 * @desc 获取配置页面集合
	 * @return
	 */
	public List<PageConfig> getPageConfig();
}
