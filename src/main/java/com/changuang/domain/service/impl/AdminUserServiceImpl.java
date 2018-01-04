package com.changuang.domain.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.changuang.domain.dao.AdminUserDao;
import com.changuang.domain.entity.AdminUser;
import com.changuang.domain.service.AdminUserService;

/**
* @author songc
* @version 创建时间：2017年12月19日 上午11:30:09
* @ClassName AdminUserServiceImpl
* @Description 类描述
*/
@Service
@Transactional
public class AdminUserServiceImpl implements AdminUserService {
	@Autowired
	AdminUserDao adminUserDao;
	
	@Override
	public Serializable saveAdminUser(AdminUser adminUser) {
		return adminUserDao.saveAdminUser(adminUser);
	}
	@Override
	public Integer getAdminUsersCount(String pagesize, String currpage, String cxtj,AdminUser adminUser){
		return adminUserDao.getAdminUsersCount(pagesize, currpage, cxtj, adminUser);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getAdminUsers(String pagesize, String currpage, String cxtj, AdminUser adminUser) {
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist =  adminUserDao.getAdminUsers(pagesize, currpage, cxtj, adminUser);
		 if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("recid",object[0] );
				 map.put("utypeRecid",object[1]!=null ?object[1]:"" );
				 map.put("adminLoginname",object[2]!=null ?object[2]:"" );
				 map.put("adminShowname",object[3]!=null ?object[3]:"" );
				 map.put("adminPhone",object[5]!=null ?object[5]:"" );
				 map.put("adminStatus",object[6]!=null ?object[6]:"" );
				 if(object[7]!=null){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String createtime = formatter.format(object[7]);
					 map.put("createtime",createtime);
				 }else{
					 map.put("createtime","");
				 }
				
				
				 map.put("adminTencent",object[8]!=null ?object[8]:"" );
				 map.put("adminUsername",object[9]!=null ?object[9]:"" );
				 map.put("adminId",object[10]!=null ?object[10]:"" );
				 map.put("utypeName",object[11]!=null ?object[11]:"" );
				 amp.add(map);
			 }
		 }
		return amp;
	}

	@Override
	public boolean UpdateAdminUser(AdminUser adminUser) {
		return adminUserDao.UpdateAdminUser(adminUser);
	}

	@Override
	public boolean LoginNameVerification(String loginname) {
		return adminUserDao.LoginNameVerification(loginname);
	}

	@Override
	public String adminloginVerification(AdminUser adminUser) {
		return adminUserDao.adminloginVerification(adminUser);
	}

}
