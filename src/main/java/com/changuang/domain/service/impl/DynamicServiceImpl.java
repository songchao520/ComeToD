package com.changuang.domain.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.changuang.domain.dao.DynamicDao;
import com.changuang.domain.entity.DynamicLikes;
import com.changuang.domain.entity.UserDynamic;
import com.changuang.domain.service.DynamicService;

/**
* @author songc
* @version 创建时间：2017年12月6日 下午4:35:24
* @ClassName 类名称
* @Description 类描述
*/
@Service
@Transactional
public class DynamicServiceImpl implements DynamicService {
	@Autowired
	DynamicDao dynamicDao;
	
	@Override
	public Integer getUserDynamicsCount(String pagesize, String currpage, String cxtj, UserDynamic userDynamic){
		return dynamicDao.getUserDynamicsCount(pagesize, currpage, cxtj, userDynamic);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getUserDynamics(String pagesize, String currpage, String cxtj, UserDynamic userDynamic) {
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist = dynamicDao.getUserDynamics(pagesize, currpage, cxtj, userDynamic);
		 if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("recid",object[0] );
				 map.put("dynamicContent",object[1]!=null ?object[1]:"" );
				 if(object[2]!=null){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String createtime = formatter.format(object[2]);
					 map.put("createTime" ,createtime );
				 }else{
					 map.put("createTime" ,"" );
				 }
				 map.put("userRecid",object[3]!=null ?object[3]:"" );
				 List<String> imgs = new ArrayList<String>();
				 if(object[4]!=null ){
					 if(object[4].toString().indexOf(",")!=-1){
						 String[] imgArray = object[4].toString().split(",");
						 for(int j=0;j<imgArray.length;j++){
							 imgs.add(imgArray[j]);
						 }
						
					 }else{
						 imgs.add(object[4].toString());
					 }
					 
				 }
				 map.put("dynamicImages",imgs );
				 map.put("dynamicAddress",object[5]!=null ?object[5]:"" );
				 map.put("userLoginname",object[6]!=null ?object[6]:"" );
				 map.put("userShowname",object[7]!=null ?object[7]:"" );
				 amp.add(map);
			 }
		 }
		return amp;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getUserDynamics(String pagesize, String currpage, String cxtj,UserDynamic userDynamic,Integer thisRecid){
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist = dynamicDao.getUserDynamics(pagesize, currpage, cxtj, userDynamic,thisRecid);
		 if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("recid",object[0] );
				 map.put("dynamicContent",object[1]!=null ?object[1]:"" );
				 if(object[2]!=null){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String createtime = formatter.format(object[2]);
					 map.put("createTime" ,createtime );
				 }else{
					 map.put("createTime" ,"" );
				 }
				 map.put("userRecid",object[3]!=null ?object[3]:"" );
				 List<String> imgs = new ArrayList<String>();
				 List<String> slts = new ArrayList<String>();
				 if(object[4]!=null ){
					 if(object[4].toString().indexOf(",")!=-1){
						 String[] imgArray = object[4].toString().split(",");
						 for(int j=0;j<imgArray.length;j++){
							 imgs.add(imgArray[j]);
							 slts.add(imgArray[j].replace("dynamic", "dynamicslt"));
						 }
						
					 }else{
						 imgs.add(object[4].toString());
						 slts.add(object[4].toString().replace("dynamic", "dynamicslt"));
					 }
					 
				 }
				 map.put("dynamicImages",imgs );
				 map.put("dynamicslts",slts );
				 map.put("dynamicAddress",object[5]!=null ?object[5]:"" );
				 map.put("userShowname",object[6]!=null ?object[6]:"" );
				 map.put("userHeadimg",object[7]!=null ?object[7]:"" );
				 map.put("DzCount",object[8]!=null ?object[8]:0 );
				 map.put("plCount",object[9]!=null ?object[9]:0 );
				 map.put("isDz",object[10]!=null ?object[10]:0 );
				 amp.add(map);
			 }
		 }
		return amp;
	}
	@Override
	public Serializable saveUserDynamic(UserDynamic userDynamic) {
		return dynamicDao.saveUserDynamic(userDynamic);
	}

	@Override
	public boolean UpdateUserDynamic(UserDynamic userDynamic) {
		return dynamicDao.UpdateUserDynamic(userDynamic);
	}

	@Override
	public boolean DeleteUserDynamic(UserDynamic userDynamic) {
		return dynamicDao.DeleteUserDynamic(userDynamic);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getDynamicLikes(String pagesize, String currpage, String cxtj, DynamicLikes dynamicLikes) {
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist = dynamicDao.getDynamicLikes(pagesize, currpage, cxtj, dynamicLikes);
		 if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("recid",object[0] );
				 map.put("userRecid",object[1]!=null ?object[1]:"" );
				 map.put("dynamicRecid",object[2]!=null ?object[2]:"" );
				 if(object[3]!=null){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String createtime = formatter.format(object[3]);
					 map.put("createTime" ,createtime );
				 }else{
					 map.put("createTime" ,"" );
				 }
				
				 map.put("userShowname",object[4]!=null ?object[4]:"" );
				 amp.add(map);
			 }
		 }
		return amp;
	}

	@Override
	public Serializable saveDynamicLikes(DynamicLikes dynamicLikes) {
		return dynamicDao.saveDynamicLikes(dynamicLikes);
	}

	@Override
	public boolean DeleteDynamicLikes(DynamicLikes dynamicLikes) {
		return dynamicDao.DeleteDynamicLikes(dynamicLikes);
	}

}
