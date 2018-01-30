package com.changuang.domain.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.changuang.domain.dao.HomeUserDao;
import com.changuang.domain.entity.HomeUser;

/**
* @author songc
* @version 创建时间：2018年1月29日 下午4:01:55
* @ClassName HomeUserDaoImpl
* @Description 房间用户
*/
@Repository
@Transactional
public class HomeUserDaoImpl implements HomeUserDao {
	@Autowired
	SessionFactory  sessionFactory;

	@Override
	public Integer getHomeUsersCount(String pagesize, String currpage, String cxtj, HomeUser homeUser) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" select count(*) ");
		sbf.append("  from home_user as hu ");
		sbf.append("  left join user_sheet as us on us.recid = hu.user_recid ");
		sbf.append(" where hu.recid!=1000000");
		if(homeUser.getRecid() != null){
			sbf.append("  and hu.recid=:recid");
		}else if(homeUser.getHomeRecid() != null){
			sbf.append("  and hu.home_recid=:homeRecid");
		}
		Session  session=sessionFactory.getCurrentSession();  
		Query query = session.createSQLQuery (sbf.toString());
		if(homeUser.getRecid() != null){
			query.setInteger("recid",homeUser.getRecid());  
		}else if(homeUser.getHomeRecid() != null){
			
				query.setInteger("homeRecid",homeUser.getHomeRecid());  
			
		}
		return Integer.parseInt(query.list().get(0).toString());
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getHomeUsers(String pagesize, String currpage, String cxtj, HomeUser homeUser) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" select hu.recid,us.recid as userrecid,us.user_headimg ");
		sbf.append(" ,us.user_showname,us.user_loginname,us.wealth_grade ");
		sbf.append("  from home_user as hu ");
		sbf.append("  left join user_sheet as us on us.recid = hu.user_recid ");
		sbf.append(" where hu.recid!=1000000");
		if(homeUser.getRecid() != null){
			sbf.append("  and hu.recid=:recid");
		}else if(homeUser.getHomeRecid() != null){
			sbf.append("  and hu.home_recid=:homeRecid");
		}
		Session  session=sessionFactory.getCurrentSession();  
		Query query = session.createSQLQuery (sbf.toString());
		if(homeUser.getRecid() != null){
			query.setInteger("recid",homeUser.getRecid());  
		}else if(homeUser.getHomeRecid() != null){
			
				query.setInteger("homeRecid",homeUser.getHomeRecid());  
			
		}
		return query.list();
	}

	@Override
	public Serializable saveHomeUser(HomeUser homeUser) {
		Session  session=sessionFactory.getCurrentSession();  
		homeUser.setCreateTime(new Date());
		return session.save(homeUser);
	}

	@Override
	public boolean DeleteHomeUser(HomeUser homeUser) {
		Session  session=sessionFactory.getCurrentSession();  
		session.beginTransaction();
		StringBuffer sbf = new StringBuffer();
		sbf.append(" delete from home_user");
		sbf.append(" where recid!=1000000");
		if(homeUser.getRecid() != null){
			sbf.append("  and recid=:recid");
		}else if(homeUser.getHomeRecid() != null){
			sbf.append("  and home_recid=:homeRecid");
		}else if(homeUser.getUserRecid() != null){
			sbf.append("  and user_recid=:userRecid");
		
	   }
		Query query = session.createSQLQuery (sbf.toString());
		if(homeUser.getRecid() != null){
			query.setInteger("recid",homeUser.getRecid());  
		}else if(homeUser.getHomeRecid() != null){
			
				query.setInteger("homeRecid",homeUser.getHomeRecid());  
			
		}else if(homeUser.getUserRecid() != null){
			
			query.setInteger("userRecid",homeUser.getUserRecid());  
		
	   }
		
		Integer deleteint = query.executeUpdate();
		session.getTransaction().commit(); 
		 if(deleteint>0){
			 return true;
		 }else{
			 return false;
		 }
	}


	
}
