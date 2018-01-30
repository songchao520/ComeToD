package com.changuang.domain.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.changuang.domain.dao.HomeUserDao;
import com.changuang.domain.entity.HomeUser;
import com.changuang.domain.service.HomeUserService;

/**
* @author songc
* @version 创建时间：2018年1月29日 下午4:37:43
* @ClassName 类名称
* @Description 类描述
*/
@Service
@Transactional
public class HomeUserServiceImpl implements HomeUserService {
	@Autowired
	HomeUserDao homeUserDao;
	
	@Override
	public Integer getHomeUsersCount(String pagesize, String currpage, String cxtj, HomeUser homeUser) {
		return homeUserDao.getHomeUsersCount(pagesize, currpage, cxtj, homeUser);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getHomeUsers(String pagesize, String currpage, String cxtj, HomeUser homeUser) {
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist = homeUserDao.getHomeUsers(pagesize, currpage, cxtj, homeUser);
		 if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("recid",object[0] );
				 map.put("userRecid",object[1]!=null ?object[1]:"" );
				 map.put("userHeadimg",object[2]!=null ?object[2]:"" );
				 map.put("userShowname",object[3]!=null ?object[3]:"" );
				 map.put("userLoginname",object[4]!=null ?object[4]:"" );
				 map.put("wealthGrade",object[5]!=null ?object[5]:"" );
				 amp.add(map);
			 }
		 }
		return amp;
	}

	@Override
	public Serializable saveHomeUser(HomeUser homeUser) {
		return homeUserDao.saveHomeUser(homeUser);
	}

	@Override
	public boolean DeleteHomeUser(HomeUser homeUser) {
		return homeUserDao.DeleteHomeUser(homeUser);
	}

}
