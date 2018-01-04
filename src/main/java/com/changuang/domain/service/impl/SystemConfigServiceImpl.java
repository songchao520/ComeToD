package com.changuang.domain.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.changuang.domain.dao.SystemConfigDao;
import com.changuang.domain.entity.PageConfig;
import com.changuang.domain.entity.PageManage;
import com.changuang.domain.service.SystemConfigService;

@Service
@Transactional
public class SystemConfigServiceImpl implements SystemConfigService {
	@Autowired  
	SystemConfigDao systemConfigDao;
	
	/**
	 * @desc 保存权限控制管理页面
	 * @return
	 */
	public Serializable savePageManage(PageManage pmg){
		return systemConfigDao.savePageManage(pmg);
	}
	/**
	 * @desc 删除权限控制管理页面
	 * @return
	 */
	public boolean deletePageManage(PageManage pmg){
		return systemConfigDao.deletePageManage(pmg);
	}
	@SuppressWarnings("rawtypes")
	public List getPagesByUser(Integer usertyperecid){
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist =  systemConfigDao.getPagesByUser(usertyperecid);
		if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("id",object[0]!=null ?object[0]:"" );
				 map.put("title",object[1]!=null ?object[1]:"");
				 map.put("icon",object[2]!=null ?object[2]:"");
				 map.put("href",object[3]!=null ?object[3]:"");
				 map.put("spread",object[4]!=null ?object[4]:"");
				 map.put("children",object[5]!=null ?object[5]:"");
				 map.put("level", object[6]!=null ?object[6]:"");
				 map.put("status", object[7]!=null ?object[7]:"");
				 map.put("parent", object[8]!=null ?object[8]:"");
				 amp.add(map);
			 }
		 }
		return amp;
	}
	public List<PageConfig> getPageConfig(){
		return systemConfigDao.getPageConfig();
	}

}
