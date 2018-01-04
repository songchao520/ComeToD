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

import com.changuang.domain.dao.DynamicDao;
import com.changuang.domain.entity.DynamicLikes;
import com.changuang.domain.entity.UserDynamic;

/**
* @author songc
* @version 创建时间：2017年12月6日 下午4:03:56
* @ClassName DynamicDaoImpl
* @Description 用户动态和点赞的实现类
*/
@Repository
@Transactional
public class DynamicDaoImpl implements DynamicDao {
	@Autowired
	SessionFactory  sessionFactory;
	
	@SuppressWarnings("rawtypes")
	@Override
	public List getUserDynamics(String pagesize, String currpage, String cxtj, UserDynamic userDynamic) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("select ud.* from user_dynamic as ud");
		sbf.append(" where 1=1");
		if(userDynamic.getUserRecid() != null){
			sbf.append(" and ud.user_recid = :userRecid");
		}
		if(userDynamic.getRecid() != null){
			sbf.append(" and ud.recid = :recid");
		}
		sbf.append( " order by ud.recid desc");
		if(pagesize == null){
			pagesize = "10";
		}
		if(currpage == null){
			currpage = "1";
		}
		int pagesizes = Integer.parseInt(pagesize);
		int currpages = Integer.parseInt(currpage);
		int startint = (currpages-1)*pagesizes;
		sbf.append(" limit "+startint+","+pagesizes);
		Session  session=sessionFactory.getCurrentSession();  
		Query query = session.createSQLQuery (sbf.toString());
		if(userDynamic.getUserRecid() != null){
			query.setInteger("userRecid", userDynamic.getUserRecid());	
		}
		if(userDynamic.getRecid() != null){
			query.setInteger("recid", userDynamic.getRecid());	
		}
		return query.list();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getUserDynamics(String pagesize, String currpage, String cxtj,UserDynamic userDynamic,Integer thisRecid){
		if(pagesize == null){
			pagesize = "10";
		}
		if(currpage == null){
			currpage = "1";
		}
		int pagesizes = Integer.parseInt(pagesize);
		int currpages = Integer.parseInt(currpage);
		int startint = (currpages-1)*pagesizes;
		String pages = startint+","+pagesizes;
		StringBuffer sbf = new StringBuffer();
		sbf.append("select  ");
		sbf.append("ud.*,us.user_showname,us.user_headimg,sel.selcount,cms.cmscount,  ");
		sbf.append("( case when  ud.recid in(  ");
		sbf.append("select dl.dynamic_recid from dynamic_likes as dl  ");
		sbf.append("where dl.dynamic_recid in (  ");
		sbf.append("select t.recid from (select recid from user_dynamic order by recid desc limit  "+pages+") as t  ");
		sbf.append(" ) ");
		sbf.append("and dl.user_recid = :thisRecid ");
		sbf.append(")  then 1 ");
		sbf.append(" else 0 ");
		sbf.append(" end ");
		sbf.append(" ) as isDz ");
		sbf.append("from user_dynamic as ud ");
		sbf.append("left join user_sheet as us on ud.user_recid = us.recid ");
		sbf.append("left join  ");
		sbf.append("(select dl.dynamic_recid as selrecid,count(*) as selcount ");
		sbf.append(" from dynamic_likes as dl ");
		sbf.append("where dl.dynamic_recid in ( ");
		sbf.append("select t.recid from (select recid from user_dynamic order by recid desc limit "+pages+") as t  ");
		sbf.append(" ) GROUP BY selrecid) as sel on sel.selrecid = ud.recid ");
		sbf.append(" left join  ");
		sbf.append(" (select cs.dynamic_recid as cmsrecid,count(*) as cmscount  ");
		sbf.append("  from comment_sheet as cs  ");
		sbf.append(" where cs.dynamic_recid in (  ");
		sbf.append(" select t.recid from (select recid from user_dynamic order by recid desc limit  "+pages+") as t  ");
		sbf.append(" ) GROUP BY cmsrecid) as cms on cms.cmsrecid = ud.recid ");
		sbf.append("where 1=1");
		if(userDynamic.getUserRecid() != null){
			sbf.append(" and ud.user_recid = :userRecid");
		}
		if(userDynamic.getRecid() != null){
			sbf.append(" and ud.recid = :recid");
		}
		sbf.append( " order by ud.recid desc");
		
		sbf.append(" limit "+startint+","+pagesizes);
		Session  session=sessionFactory.getCurrentSession();  
		Query query = session.createSQLQuery (sbf.toString());
		if(userDynamic.getUserRecid() != null){
			query.setInteger("userRecid", userDynamic.getUserRecid());	
		}
		if(userDynamic.getRecid() != null){
			query.setInteger("recid", userDynamic.getRecid());	
		}
		if(thisRecid!=null){
			query.setInteger("thisRecid", thisRecid);	
		}
		return query.list();
	}
	@Override
	public Serializable saveUserDynamic(UserDynamic userDynamic) {
		userDynamic.setCreateTime(new Date());
		Session  session=sessionFactory.getCurrentSession();  
	    return   session.save(userDynamic);
	}

	@Override
	public boolean UpdateUserDynamic(UserDynamic userDynamic) {
		Session  session=sessionFactory.getCurrentSession(); 
		StringBuffer sbf = new StringBuffer();
		sbf.append(" Update user_dynamic as ud set");
		boolean flag = true;
		if(userDynamic.getDynamicContent() != null){
			sbf.append(" ud.dynamic_content = :dynamicContent");
			flag = false;
		}
		if(userDynamic.getDynamicImages() != null){
			if(flag){
				sbf.append(" ud.dynamic_images =:dynamicImages");
				flag = false;
			}else{
				sbf.append(" ,ud.dynamic_images =:dynamicImages");
			}			
		}
		sbf.append(" where");
		if(userDynamic.getRecid() != null){
			sbf.append("  ud.recid=:recid");
		}else{
			return false;
		}
		Query query = session.createSQLQuery (sbf.toString());
		
		if(userDynamic.getDynamicContent() != null){
			query.setString("dynamicContent", userDynamic.getDynamicContent());
		}
		if(userDynamic.getDynamicImages() != null){
			query.setString("dynamicImages",userDynamic.getDynamicImages());  		
		}
		if(userDynamic.getRecid() != null){
			query.setInteger("recid",userDynamic.getRecid());  
		}
		Integer updateint = query.executeUpdate();
		if(updateint>0){
			return true;
		}else{
			return false;
		}	
	}

	@Override
	public boolean DeleteUserDynamic(UserDynamic userDynamic) {
		Session  session=sessionFactory.getCurrentSession();  
		session.beginTransaction();
		StringBuffer sbf = new StringBuffer();
		sbf.append(" delete from user_dynamic");
		sbf.append(" where");
		if(userDynamic.getRecid() != null){
			sbf.append("  recid=:recid");
		}else{
			return false;
		}
		Query query = session.createSQLQuery (sbf.toString());
		if(userDynamic.getRecid() != null){
			query.setInteger("recid",userDynamic.getRecid());  
		}
		
		Integer deleteint = query.executeUpdate();
		session.getTransaction().commit(); 
		 if(deleteint>0){
			 return true;
		 }else{
			 return false;
		 }
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getDynamicLikes(String pagesize, String currpage, String cxtj, DynamicLikes dynamicLikes) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" select dl.*,us.user_showname from dynamic_likes as dl ");
		sbf.append(" left join user_sheet as us on dl.user_recid = us.recid ");
		sbf.append(" where 1=1");
		if(dynamicLikes.getUserRecid() != null && dynamicLikes.getDynamicRecid()!=null){
			sbf.append(" and dl.user_recid = :userRecid and dl.dynamic_recid=:dynamicRecid");
		}
		if(dynamicLikes.getRecid() != null){
			sbf.append(" and dl.recid = :recid");
		}
		sbf.append( " order by dl.recid desc");
		if(pagesize == null){
			pagesize = "10";
		}
		if(currpage == null){
			currpage = "1";
		}
		int pagesizes = Integer.parseInt(pagesize);
		int currpages = Integer.parseInt(currpage);
		int startint = (currpages-1)*pagesizes;
		sbf.append(" limit "+startint+","+pagesizes);
		Session  session=sessionFactory.getCurrentSession();  
		Query query = session.createSQLQuery (sbf.toString());
		if(dynamicLikes.getUserRecid() != null){
			query.setInteger("userRecid", dynamicLikes.getUserRecid());	
			query.setInteger("dynamicRecid", dynamicLikes.getDynamicRecid());	
		}
		if(dynamicLikes.getRecid() != null){
			query.setInteger("recid", dynamicLikes.getRecid());	
		}
		return query.list();
	}

	@Override
	public Serializable saveDynamicLikes(DynamicLikes dynamicLikes) {
		dynamicLikes.setCreateTime(new Date());
		Session  session=sessionFactory.getCurrentSession();  
	    return   session.save(dynamicLikes);
	}

	@Override
	public boolean DeleteDynamicLikes(DynamicLikes dynamicLikes) {
		Session  session=sessionFactory.getCurrentSession();  
		session.beginTransaction();
		StringBuffer sbf = new StringBuffer();
		sbf.append(" delete from dynamic_likes");
		sbf.append(" where 1=1");
		if(dynamicLikes.getRecid() != null){
			sbf.append("  and recid=:recid");
		}else if(dynamicLikes.getUserRecid() != null && dynamicLikes.getDynamicRecid()!=null){
			sbf.append(" and user_recid = :userRecid and dynamic_recid=:dynamicRecid");
		}else{
			return false;
		}
		Query query = session.createSQLQuery (sbf.toString());
		if(dynamicLikes.getRecid() != null){
			query.setInteger("recid",dynamicLikes.getRecid());  
		}else if(dynamicLikes.getUserRecid() != null && dynamicLikes.getDynamicRecid()!=null){
			query.setInteger("userRecid",dynamicLikes.getUserRecid());  
			query.setInteger("dynamicRecid",dynamicLikes.getDynamicRecid());  
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
