package com.changuang.domain.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.changuang.domain.dao.SystemConfigDao;
import com.changuang.domain.entity.PageConfig;
import com.changuang.domain.entity.PageManage;

@Repository
@Transactional
public class SystemConfigDaoImpl implements SystemConfigDao {
	@Autowired
	SessionFactory  sessionFactory;
	
	public boolean deletePageManage(PageManage pmg){
			
			Session  session=sessionFactory.getCurrentSession();  
			session.beginTransaction();
			Query query = session.createSQLQuery ("delete from page_manage where usertyperecid=:usertyperecid and pageid=:pageid");
			query.setInteger("usertyperecid",pmg.getUsertyperecid());  
			query.setInteger("pageid",pmg.getPageid());  
			Integer deleteint = query.executeUpdate();
			session.getTransaction().commit(); 
			 if(deleteint>0){
				 return true;
			 }else{
				 return false;
			 }
	}

	public Serializable savePageManage(PageManage pmg){
		Session  session=sessionFactory.getCurrentSession();  
		return session.save(pmg);
	}
	@SuppressWarnings("rawtypes")
	public List getPagesByUser(Integer usertyperecid){
		 Session  session=sessionFactory.getCurrentSession();  
		 StringBuffer sbf = new StringBuffer();
		 sbf.append("select pc.* from page_config as pc left JOIN page_manage");
		 sbf.append(" as pm on pc.id = pm.pageid where pm.usertyperecid = :usertyperecid and pc.`status`=1");
		 Query query = session.createSQLQuery(sbf.toString());
		 query.setInteger("usertyperecid",usertyperecid);  
		 return query.list();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<PageConfig> getPageConfig(){
		 Session  session=sessionFactory.getCurrentSession();  
		 Query query = session.createQuery ("from PageConfig as pc where status = 1");
		 List results = query.list();
		 return results;
	}




}
