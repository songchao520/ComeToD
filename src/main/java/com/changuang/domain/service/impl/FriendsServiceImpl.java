package com.changuang.domain.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.changuang.domain.dao.FriendsDao;
import com.changuang.domain.entity.FriendSheet;
import com.changuang.domain.service.FriendsService;

/**
* @author songc
* @version 创建时间：2017年12月6日 上午11:12:27
* @ClassName 类名称
* @Description 类描述
*/
@Service
@Transactional
public class FriendsServiceImpl implements FriendsService {
	@Autowired
	FriendsDao friendsDao;

	@SuppressWarnings("rawtypes")
	@Override
	public List getFriendSheets(String pagesize, String currpage, String cxtj, FriendSheet friendSheet) {
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist = friendsDao.getFriendSheets(pagesize, currpage, cxtj, friendSheet);
		 if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("recid",object[0] );
				 map.put("userRecid",object[1]!=null ?object[1]:"" );
				 map.put("friendRecid",object[2]!=null ?object[2]:"" );
				 map.put("friendStatus",object[3]!=null ?object[3]:"" );
				 if(object[4]!=null){
					 map.put("loginStatus",1 );
				 }else{
					 map.put("loginStatus",0 );
				 }
				 
				 map.put("friendShowname",object[5]!=null ?object[5]:"" );
				 map.put("friendHeadimg",object[6]!=null ?object[6]:"" );
				 amp.add(map);
			 }
		 }
		return amp;
	}

	@Override
	public Serializable saveFriendSheet(FriendSheet friendSheet) {
		return friendsDao.saveFriendSheet(friendSheet);
	}

	@Override
	public boolean UpdateFriendSheet(FriendSheet friendSheet) {
		return friendsDao.UpdateFriendSheet(friendSheet);
	}
	

}
