package com.changuang.domain.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.changuang.domain.dao.UserDao;
import com.changuang.domain.entity.UserSheet;
import com.changuang.domain.entity.UserType;
import com.changuang.domain.service.UserService;

/**
* @author songc
* @version 创建时间：2017年12月1日 下午3:11:39
* @ClassName UserServiceImpl
* @Description UserService实现类
*/
@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired  
	UserDao userDao;
	
	@Override
	public Serializable saveUserType(UserType usertype) {  
		return userDao.saveUserType(usertype);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getUserTypes(UserType usertype) {
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist =  userDao.getUserTypes(usertype);
		 if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("recid",object[0] );
				 map.put("usertypeName",object[1]!=null ?object[1]:"" );
				 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 String createtime = formatter.format(object[2]);
				 map.put("createtime",createtime);
				 amp.add(map);
			 }
		 }
		return amp;
	}

	@Override
	public Serializable saveUserSheet(UserSheet userSheet) {
		return userDao.saveUserSheet(userSheet ) ;
	}
	@Override
	public Integer getUserSheetsCount(String pagesize, String currpage, String cxtj, UserSheet userSheet){
		return userDao.getUserSheetsCount(pagesize,currpage,cxtj,userSheet);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getUserSheets(String pagesize, String currpage, String cxtj, UserSheet userSheet) {
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist =  userDao.getUserSheets(pagesize,currpage,cxtj,userSheet);
		 if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("usertypeName",object[0] !=null ?object[0]:"" );
				 map.put("labelNameOne",object[1]!=null ?object[1]:"" );
				 map.put("labelNameTwo",object[2]!=null ?object[2]:"" );
				 map.put("labelNameThree",object[3]!=null ?object[3]:"" );
				 map.put("recid",object[4]!=null ?object[4]:"" );
				 map.put("userLoginname",object[5]!=null ?object[5]:"" );
				 map.put("userShowname",object[6]!=null ?object[6]:"" );
				 map.put("userHeadimg",object[7]!=null ?object[7]:"" );
				 map.put("userRemarks",object[8]!=null ?object[8]:"" );
				 map.put("userStatus",object[9]!=null ?object[9]:"" );
				 if(object[10] != null){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String createtime = formatter.format(object[10]);
					 map.put("userCreatetime",createtime);
				 }else{
					 map.put("userCreatetime","");
				 }
				 if(object[11] != null){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String lasttime = formatter.format(object[11]);
					 map.put("userLasttime",lasttime);
				 }else{
					 map.put("userLasttime","");
				 }
				 map.put("userLastaddress",object[12]!=null ?object[12]:"" );
				 map.put("userTencent",object[13]!=null ?object[13]:"" );
				 map.put("userVip",object[14]!=null ?object[14]:"" );
				 map.put("anchorStatus",object[15]!=null ?object[15]:"" );
				 map.put("wealthAmount",object[16]!=null ?object[16]:"" );
				 map.put("wealthGrade",object[17]!=null ?object[17]:"" );
				 if(object[18] !=null){
					 if((Integer)object[18] == 1){
						 map.put("userSex","男" );
					 }else if((Integer)object[18] == 2){
						 map.put("userSex","女" );
					 }
				 }else{
					 map.put("userSex","神秘" );
				 }
				
				 
				 map.put("isVip",object[19]!=null ?object[19]:"" );
				 map.put("userMobilephone",object[20]!=null ?object[20]:"" );
				 map.put("userSource",object[21]!=null ?object[21]:"" );
				 map.put("utypeRecid",object[22]!=null ?object[22]:"" );
				 map.put("userCity",object[23]!=null ?object[23]:"" );
				 map.put("leanCloud",object[24]!=null ?object[24]:"" );
				 map.put("wealthZong",object[25]!=null ?object[25]:"" );
				 amp.add(map);
			 }
		 }
		return amp;
	}

	public boolean UpdateUserSheet(UserSheet userSheet){
		return userDao.UpdateUserSheet(userSheet);
	}

	@Override
	public String UserloginVerification(UserSheet userSheet) {
		return userDao.UserloginVerification(userSheet);
	}

	@Override
	public boolean LoginNameVerification(String loginname) {
		return userDao.LoginNameVerification(loginname);
	}

	@Override
	public boolean LoginUserVerification(String loginname) {
		return userDao.LoginUserVerification(loginname);
	}
}
