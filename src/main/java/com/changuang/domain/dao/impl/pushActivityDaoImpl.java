package com.changuang.domain.dao.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.changuang.domain.dao.PushActivityDao;
import com.changuang.domain.entity.PushActivity;

/**
* @author songc
* @version 创建时间：2017年12月19日 下午1:55:19
* @ClassName pushActivityDaoImpl
* @Description 类描述
*/
@Repository
@Transactional
public class pushActivityDaoImpl implements PushActivityDao {
	@Autowired
	SessionFactory  sessionFactory;

	@Override
	public Serializable savePushActivity(PushActivity pushActivity) {
		pushActivity.setCreateTime(new Date());
		pushActivity.setIsShow(1);
		Session  session=sessionFactory.getCurrentSession();  
		return session.save(pushActivity);
	}

	@Override
	public Integer getPushActivitysCount(String pagesize, String currpage, String cxtj, PushActivity pushActivity) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("select count(*) ");
		sbf.append(" from push_activity as pa  where 1=1 ");
		if(pushActivity.getRecid() != null){
			sbf.append(" and pa.recid = :recid ");			
		}
		if(pushActivity.getIsShow() != null){
			if(pushActivity.getIsShow() == 0){
				sbf.append(" ");			
			}else{
				sbf.append(" and pa.is_show = :isShow ");			
			}
			
		}else{
			sbf.append("  and is_show=1 ");		
		}
		if(pushActivity.getSource()!=null){
			sbf.append(" and pa.source = :source ");			
		}
		if(cxtj != null){
			sbf.append(" and (pa.activity_http like :cxtj or pa.message_title like :cxtj or pa.message_content like :cxtj");
			sbf.append(" pa.author like :cxtj or pa.source like :cxtj or pa.user_type like :cxtj");
			sbf.append( ")");
		}
		sbf.append( " order by pa.recid desc");
		if(pagesize == null){
			pagesize = "1";
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
		if(pushActivity.getRecid() != null){
			query.setInteger("recid",pushActivity.getRecid());	
		}
		if(pushActivity.getIsShow() != null){
			query.setInteger("isShow",pushActivity.getIsShow());			
		}
		if(pushActivity.getSource()!=null){
			query.setString("source",pushActivity.getSource());				
		}
		if(cxtj != null){
			query.setString("cxtj","%"+cxtj+"%");	
		}
		return Integer.parseInt(query.list().get(0).toString());
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getPushActivitys(String pagesize, String currpage, String cxtj, PushActivity pushActivity) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("select pa.recid,pa.activity_http,pa.message_title,pa.message_content,pa.author, ");
		sbf.append("pa.create_time,pa.source,pa.relevant_img,pa.admin_recid,pa.user_type,pa.start_time ");
		sbf.append(",pa.end_time,pa.is_show ");
		sbf.append(" from push_activity as pa  where 1=1 ");
		if(pushActivity.getRecid() != null){
			sbf.append(" and pa.recid = :recid ");			
		}
		if(pushActivity.getIsShow() != null){
			if(pushActivity.getIsShow() == 0){
				sbf.append(" ");			
			}else{
				sbf.append(" and pa.is_show = :isShow ");			
			}
			
		}else{
			sbf.append("  and is_show=1 ");		
		}
		if(pushActivity.getSource()!=null){
			sbf.append(" and pa.source = :source ");			
		}
		if(cxtj != null){
			sbf.append(" and (pa.activity_http like :cxtj or pa.message_title like :cxtj or pa.message_content like :cxtj");
			sbf.append(" pa.author like :cxtj or pa.source like :cxtj or pa.user_type like :cxtj");
			sbf.append( ")");
		}
		sbf.append( " order by pa.recid desc");
		if(pagesize == null){
			pagesize = "1";
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
		if(pushActivity.getRecid() != null){
			query.setInteger("recid",pushActivity.getRecid());	
		}
		if(pushActivity.getIsShow() != null){
			query.setInteger("isShow",pushActivity.getIsShow());			
		}
		if(pushActivity.getSource()!=null){
			query.setString("source",pushActivity.getSource());				
		}
		if(cxtj != null){
			query.setString("cxtj","%"+cxtj+"%");	
		}
		return query.list();
	}

	@Override
	public boolean UpdatePushActivity(PushActivity pushActivity) {
		Session  session=sessionFactory.getCurrentSession(); 
		StringBuffer sbf = new StringBuffer();
		sbf.append(" Update push_activity as pa set");
		boolean flag = true;
		if(pushActivity.getActivityHttp() != null){
			sbf.append(" pa.activity_http = :activityHttp");
			flag = false;
		}
		if(pushActivity.getMessageTitle() != null){
			if(flag){
				sbf.append(" pa.message_title =:messageTitle");
				flag = false;
			}else{
				sbf.append(" ,pa.message_title =:messageTitle");
			}			
		}
		if(pushActivity.getMessageContent() != null){
			if(flag){
				sbf.append(" pa.message_content =:messageContent");
				flag = false;
			}else{
				sbf.append(" ,pa.message_content =:messageContent");
			}			
		}
		if(pushActivity.getAuthor() != null){
			if(flag){
				sbf.append(" pa.author =:author");
				flag = false;
			}else{
				sbf.append(" ,pa.author =:author");
			}			
		}
		if(pushActivity.getSource() != null){
			if(flag){
				sbf.append(" pa.source =:source");
				flag = false;
			}else{
				sbf.append(" ,pa.source =:source");
			}			
		}
		if(pushActivity.getRelevantImg() != null){
			if(flag){
				sbf.append(" pa.relevant_img =:relevantImg");
				flag = false;
			}else{
				sbf.append(" ,pa.relevant_img =:relevantImg");
			}			
		}
		if(pushActivity.getUserType() != null){
			if(flag){
				sbf.append(" pa.user_type =:userType");
				flag = false;
			}else{
				sbf.append(" ,pa.user_type =:userType");
			}			
		}
		if(pushActivity.getStartTime() != null){
			if(flag){
				sbf.append(" pa.start_time =:startTime");
				flag = false;
			}else{
				sbf.append(" ,pa.start_time =:startTime");
			}			
		}
		if(pushActivity.getEndTime() != null){
			if(flag){
				sbf.append(" pa.end_time =:endTime");
				flag = false;
			}else{
				sbf.append(" ,pa.end_time =:endTime");
			}			
		}
		if(pushActivity.getIsShow() != null){
			if(flag){
				sbf.append(" pa.is_show =:isShow");
				flag = false;
			}else{
				sbf.append(" ,pa.is_show =:isShow");
			}			
		}
		sbf.append(" where");
		if(pushActivity.getRecid() != null){
			sbf.append("  pa.recid=:recid");
		}else{
			return false;
		}
		Query query = session.createSQLQuery (sbf.toString());
		if(pushActivity.getActivityHttp() != null){
			query.setString("activityHttp", pushActivity.getActivityHttp());		
		}
		if(pushActivity.getMessageTitle() != null){
			query.setString("messageTitle", pushActivity.getMessageTitle());			
		}
		if(pushActivity.getMessageContent() != null){
			query.setString("messageContent", pushActivity.getMessageContent());			
		}
		if(pushActivity.getAuthor() != null){
			query.setString("author", pushActivity.getAuthor());		
		}
		if(pushActivity.getSource() != null){
			query.setString("source", pushActivity.getSource());				
		}
		if(pushActivity.getRelevantImg() != null){
			query.setString("relevantImg", pushActivity.getRelevantImg());				
		}
		if(pushActivity.getUserType() != null){
			query.setInteger("userType", pushActivity.getUserType());						
		}
		if(pushActivity.getStartTime() != null){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String startTime = formatter.format(pushActivity.getStartTime());
			 query.setString("startTime", startTime);		
		}
		if(pushActivity.getEndTime() != null){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String endTime = formatter.format(pushActivity.getEndTime());
			 query.setString("endTime", endTime);		
		}
		if(pushActivity.getIsShow() != null){
			query.setInteger("isShow", pushActivity.getIsShow());					
		}
		if(pushActivity.getRecid() != null){
			query.setInteger("recid",pushActivity.getRecid());  
		}
		Integer updateint = query.executeUpdate();
		if(updateint>0){
			return true;
		}else{
			return false;
		}	
	}

}
