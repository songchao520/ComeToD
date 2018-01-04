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

import com.changuang.domain.dao.AdminUserDao;
import com.changuang.domain.entity.AdminUser;

/**
* @author songc
* @version 创建时间：2017年12月19日 上午10:24:10
* @ClassName AdminUserImpl
* @Description 用户管理业务逻辑
*/
@Repository
@Transactional
public class AdminUserDaoImpl implements AdminUserDao {
	@Autowired
	SessionFactory  sessionFactory;

	@Override
	public Serializable saveAdminUser(AdminUser adminUser) {
		adminUser.setAdminStatus(0);
		adminUser.setCreateTime(new Date());
		Session  session=sessionFactory.getCurrentSession();  
	    return   session.save(adminUser); 
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getAdminUsers(String pagesize, String currpage, String cxtj, AdminUser adminUser) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("select au.*,ut.usertype_name from admin_user as au ");
		sbf.append("left join user_type as ut on au.utype_recid = ut.recid ");
		sbf.append("where 1=1  ");
		if(adminUser.getUtypeRecid()!=null){
			sbf.append(" and au.utype_recid = :utypeRecid");
		}
		if(adminUser.getRecid()!=null){
			sbf.append(" and au.recid = :recid");
		}else{
			sbf.append(" and au.utype_recid != 6");
		}
		sbf.append( " order by au.recid desc");
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
		if(adminUser.getUtypeRecid()!=null){
			query.setInteger("adminUser", adminUser.getUtypeRecid());
		}
		if(adminUser.getRecid()!=null){
			query.setInteger("recid", adminUser.getRecid());
		}
		return query.list();
	}
	@Override
	public Integer getAdminUsersCount(String pagesize, String currpage, String cxtj, AdminUser adminUser) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("select count(*) from admin_user as au ");
		sbf.append("left join user_type as ut on au.utype_recid = ut.recid ");
		sbf.append("where 1=1");
		if(adminUser.getUtypeRecid()!=null){
			sbf.append(" and au.utype_recid = :utypeRecid");
		}
		if(adminUser.getRecid()!=null){
			sbf.append(" and au.recid = :recid");
		}else{
			sbf.append(" and au.utype_recid != 6");
		}
		sbf.append( " order by au.recid desc");
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
		if(adminUser.getUtypeRecid()!=null){
			query.setInteger("adminUser", adminUser.getUtypeRecid());
		}
		if(adminUser.getRecid()!=null){
			query.setInteger("recid", adminUser.getRecid());
		}
		return Integer.parseInt(query.list().get(0).toString());
	}

	@Override
	public boolean UpdateAdminUser(AdminUser adminUser) {
		Session  session=sessionFactory.getCurrentSession(); 
		StringBuffer sbf = new StringBuffer();
		Query query = null;
		sbf.setLength(0);
		sbf.append(" Update admin_user as au set");
		boolean flag = true;
		if(adminUser.getAdminShowname()!=null){
			sbf.append(" au.admin_showname =:adminShowname");
			flag = false;
		}
		if(adminUser.getAdminPasswrod() != null){
			if(flag){
				sbf.append(" au.admin_passwrod =:adminPasswrod");
				flag = false;
			}else{
				sbf.append(" ,au.admin_passwrod =:adminPasswrod");
			}			
		}
		if(adminUser.getAdminPhone() != null){
			if(flag){
				sbf.append(" au.admin_phone =:adminPhone");
				flag = false;
			}else{
				sbf.append(" ,au.admin_phone =:adminPhone");
			}			
		}
		if(adminUser.getAdminStatus() != null){
			if(flag){
				sbf.append(" au.admin_status =:adminStatus");
				flag = false;
			}else{
				sbf.append(" ,au.admin_status =:adminStatus");
			}			
		}
		if(adminUser.getAdminTencent() != null){
			if(flag){
				sbf.append(" au.admin_tencent =:adminTencent");
				flag = false;
			}else{
				sbf.append(" ,au.admin_tencent =:adminTencent");
			}			
		}
		if(adminUser.getAdminUsername() != null){
			if(flag){
				sbf.append(" au.admin_username =:adminUsername");
				flag = false;
			}else{
				sbf.append(" ,au.admin_username =:adminUsername");
			}			
		}
		if(adminUser.getAdminId() != null){
			if(flag){
				sbf.append(" au.admin_id =:adminId");
				flag = false;
			}else{
				sbf.append(" ,au.admin_id =:adminId");
			}			
		}
		sbf.append(" where au.recid=:recid");
	    query = session.createSQLQuery (sbf.toString());
		query.setInteger("recid", adminUser.getRecid());
		if(adminUser.getAdminShowname()!=null){
			query.setString("adminShowname", adminUser.getAdminShowname());
		}
		if(adminUser.getAdminPasswrod() != null){
			query.setString("adminPasswrod", adminUser.getAdminPasswrod());		
		}
		if(adminUser.getAdminPhone() != null){
			query.setString("adminPhone", adminUser.getAdminPhone());	
		}
		if(adminUser.getAdminStatus() != null){
			query.setInteger("adminStatus", adminUser.getAdminStatus());		
		}
		if(adminUser.getAdminTencent() != null){
			query.setString("adminTencent", adminUser.getAdminTencent());			
		}
		if(adminUser.getAdminUsername() != null){
			query.setString("adminUsername", adminUser.getAdminUsername());	
		}
		if(adminUser.getAdminId() != null){
			query.setString("adminId", adminUser.getAdminId());	
		}
		Integer updateint = query.executeUpdate();
		// session.getTransaction().commit(); 
		if(updateint>0){
			return true;
		}else{
			return false;
		}	
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean LoginNameVerification(String loginname) {
		StringBuffer sbf = new StringBuffer();
		 sbf.append("select count(*) from admin_user as au  where 1=1");
		 if(loginname != null){
			 sbf.append("  and au.admin_loginname = :adminLoginname ");
		 }
		 Session  session=sessionFactory.getCurrentSession();  
		Query query = session.createSQLQuery (sbf.toString());
		if(loginname != null){
			query.setString("adminLoginname",loginname);	
		}
		List results = query.list();
		int lg = Integer.parseInt(results.get(0).toString());
		if(lg>0){
			return false;
		}else{
			return true;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String adminloginVerification(AdminUser adminUser) {
		StringBuffer sbf = new StringBuffer();
		 sbf.append("select count(*) from admin_user as au  where 1=1");
		 if(adminUser.getAdminLoginname() != null){
			 sbf.append("  and au.admin_loginname = :adminLoginname and au.admin_status =1 ");
		 }
		 Session  session=sessionFactory.getCurrentSession();  
		Query query = session.createSQLQuery (sbf.toString());
		if(adminUser.getAdminLoginname() != null){
			query.setString("adminLoginname",adminUser.getAdminLoginname());	
		}
		List results = query.list();
		int lg = Integer.parseInt(results.get(0).toString());
		if(lg>0){
			return "0";
		}
		sbf.setLength(0);
		 sbf.append("select au.recid from admin_user as au  where 1=1");
		 if(adminUser.getAdminLoginname() != null && adminUser.getAdminPasswrod()!=null){
			 sbf.append("  and au.admin_loginname = :adminLoginname and au.admin_passwrod =:adminPasswrod ");
		 }else{
			 return "-1";
		 }
		 session=sessionFactory.getCurrentSession();  
		query = session.createSQLQuery (sbf.toString());
		if(adminUser.getAdminLoginname() != null){
			query.setString("adminLoginname",adminUser.getAdminLoginname());	
		}
		if(adminUser.getAdminPasswrod() != null){
			query.setString("adminPasswrod",adminUser.getAdminPasswrod());	
		}
		results = query.list();
		if(results.size()>0){
				 lg = Integer.parseInt(results.get(0).toString());			
				 return lg+"";
		}
		 sbf.setLength(0);
		 sbf.append("select au.recid from admin_user as au  where 1=1");
		 if(adminUser.getAdminLoginname() != null && adminUser.getAdminPasswrod()!=null){
			 sbf.append("  and au.admin_phone = :adminLoginname and au.admin_passwrod =:adminPasswrod ");
		 }
		query = session.createSQLQuery (sbf.toString());
		if(adminUser.getAdminLoginname() != null){
			query.setString("adminLoginname",adminUser.getAdminLoginname());	
		}
		if(adminUser.getAdminPasswrod() != null){
			query.setString("adminPasswrod",adminUser.getAdminPasswrod());	
		}
		results = query.list();
		if(results.size()>0){
			lg = Integer.parseInt(results.get(0).toString());			
			 return lg+"";
		}
			return "-1";
			
		}


}
