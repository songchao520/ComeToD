package com.changuang.domain.dao.impl;

import java.io.Serializable;
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
		// TODO Auto-generated method stub
		return null;
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
			sbf.append(" and pa.is_show = :isShow ");			
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
		// TODO Auto-generated method stub
		return false;
	}

}
