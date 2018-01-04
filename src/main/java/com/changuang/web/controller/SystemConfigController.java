package com.changuang.web.controller;


import java.io.Serializable;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.changuang.domain.entity.PageConfig;
import com.changuang.domain.entity.PageManage;
import com.changuang.domain.service.SystemConfigService;

/**
 * @author songc
 *
 */
@Controller
public class SystemConfigController {
	@Autowired  
	SystemConfigService systemConfigService;
	
	/**
	 * @desc 保存权限控制管理页面
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/savePageManage")
	public Serializable savePageManage(PageManage pmg){
		return systemConfigService.savePageManage(pmg);
	}
	/**
	 * @desc 删除权限控制管理页面
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/deletePageManage")
	public boolean deletePageManage(PageManage pmg){
		return systemConfigService.deletePageManage(pmg);
	}
	
	/**
	 * @desc 根据用户权限获取页面配置
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody 
	@RequestMapping("/getPagesByUser")
	public List<PageConfig> getPagesByUser(Integer usertyperecid){
		return systemConfigService.getPagesByUser(usertyperecid);
	}
	
	/**
	 * @desc 获取页面配置，显示哪些页面，后期可拓展为权限控制
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/getPageConfig")
	public List<PageConfig> getPageConfig(){
		return systemConfigService.getPageConfig();
	}

}
