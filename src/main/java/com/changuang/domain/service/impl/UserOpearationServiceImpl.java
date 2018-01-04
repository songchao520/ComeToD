package com.changuang.domain.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.changuang.domain.dao.UserOpearationDao;
import com.changuang.domain.entity.MonthlyMembers;
import com.changuang.domain.entity.WithDrawals;
import com.changuang.domain.service.UserOpearationService;

/**
* @author songc
* @version 创建时间：2017年12月8日 下午2:32:55
* @ClassName UserOpearationServiceImpl
* @Description 实现类
*/
@Service
@Transactional
public class UserOpearationServiceImpl implements UserOpearationService {
	@Autowired 
	UserOpearationDao userOpearationDao;

	@Override
	public Serializable saveMonthlyMembers(MonthlyMembers monthlyMembers) {
		return userOpearationDao.saveMonthlyMembers(monthlyMembers);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getMonthlyMemberss(String pagesize, String currpage, String cxtj, MonthlyMembers monthlyMembers) {
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist =  userOpearationDao.getMonthlyMemberss(pagesize, currpage, cxtj, monthlyMembers);
		 if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("recid",object[0]!=null ?object[0]:"" );
				 map.put("userRecid",object[1]!=null ?object[1]:"" );
				 if(object[2]!=null){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String createtime = formatter.format(object[2]);
					 map.put("createTime",createtime );
				 }else{
					 map.put("createTime","");
				 }
				 if(object[3]!=null){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String openTime = formatter.format(object[3]);
					 map.put("openTime",openTime );
				 }else{
					 map.put("openTime","");
				 }
				 if(object[4]!=null){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String endTime = formatter.format(object[4]);
					 map.put("endTime",endTime );
				 }else{
					 map.put("endTime","");
				 }
				
				 map.put("availableTime",object[5]!=null ?object[5]:""  );
				 map.put("isAvailable",object[6]!=null ?object[6]:""  );
				 amp.add(map);
			 }
		 }
		return amp;
	}

	@Override
	public boolean UpdateMonthlyMembers(MonthlyMembers monthlyMembers) {
		return userOpearationDao.UpdateMonthlyMembers(monthlyMembers);
	}

	@Override
	public Serializable saveWithDrawals(WithDrawals withDrawals) {
		return userOpearationDao.saveWithDrawals(withDrawals);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getWithDrawalss(String pagesize, String currpage, String cxtj, WithDrawals withDrawals) {
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist =  userOpearationDao.getWithDrawalss(pagesize, currpage, cxtj, withDrawals);
		 if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("anchorShowname",object[0]!=null ?object[0]:""  );
				 map.put("anchorLoginname",object[1]!=null ?object[1]:"" );
				 map.put("recid",object[2]!=null ?object[2]:"" );
				 map.put("moneyAmount",object[3]!=null ?object[3]:"" );
				 map.put("userRecid",object[4]!=null ?object[4]:""  );
				
				 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 String createtime = formatter.format(object[5]);
				 map.put("createTime",createtime );
				 if(object[6]!=null){
					 formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String transfertime = formatter.format(object[6]);
					 map.put("transfertime",transfertime );
				 }else{
					 map.put("transfertime","");
				 }
				
				 map.put("transferboolean",object[7]!=null ?object[7]:""  );
				 map.put("transferpeople",object[8]!=null ?object[8]:""  );
				 map.put("alipaynumber",object[9]!=null ?object[9]:""  );
				 map.put("wtihdpeople",object[10]!=null ?object[10]:""  );
				 map.put("withdway",object[11]!=null ?object[11]:""  );
				 map.put("withdbank",object[12]!=null ?object[12]:""  );
				 amp.add(map);
			 }
		 }
		return amp;
	}

	@Override
	public boolean UpdateWithDrawals(WithDrawals withDrawals) {
		return userOpearationDao.UpdateWithDrawals(withDrawals);
	}

}
