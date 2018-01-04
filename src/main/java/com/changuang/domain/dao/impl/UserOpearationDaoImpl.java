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

import com.changuang.domain.dao.UserOpearationDao;
import com.changuang.domain.entity.MonthlyMembers;
import com.changuang.domain.entity.WithDrawals;

/**
* @author songc
* @version 创建时间：2017年12月7日 下午6:54:52
* @ClassName UserOpearationDaoImpl
* @Description 提现，充值会员实现类
*/
@Repository
@Transactional
public class UserOpearationDaoImpl implements UserOpearationDao {
	@Autowired
	SessionFactory  sessionFactory;
	
	@Override
	public Serializable saveMonthlyMembers(MonthlyMembers monthlyMembers) {
		Session  session=sessionFactory.getCurrentSession();  
	    return   session.save(monthlyMembers);  
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getMonthlyMemberss(String pagesize, String currpage, String cxtj, MonthlyMembers monthlyMembers) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" select * from monthly_members as mm ");
		sbf.append(" where mm.recid != 1000000 ");
		if(monthlyMembers.getUserRecid() != null){
			sbf.append(" and mm.user_recid = :userRecid ");
		}
		if(monthlyMembers.getRecid() != null){
			sbf.append(" and mm.recid = :recid ");
		}
		if(monthlyMembers.getIsAvailable() != null){
			sbf.append(" and mm.is_available = :isAvailable ");
		}
		sbf.append( " ORDER BY mm.recid DESC");
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
		if(monthlyMembers.getUserRecid() != null){
			query.setInteger("userRecid", monthlyMembers.getUserRecid());	
		}
		if(monthlyMembers.getRecid() != null){
			query.setInteger("recid", monthlyMembers.getRecid());	
		}
		if(monthlyMembers.getIsAvailable() != null){
			query.setInteger("isAvailable", monthlyMembers.getIsAvailable());	
		}
		return query.list();
	}

	@Override
	public boolean UpdateMonthlyMembers(MonthlyMembers monthlyMembers) {
		Session  session=sessionFactory.getCurrentSession(); 
		StringBuffer sbf = new StringBuffer();
		sbf.append(" Update monthly_members as mm set");
		boolean flag = true;
		if(monthlyMembers.getAvailableTime() != null){
			sbf.append(" mm.available_time = :availableTime");
			flag = false;
		}
		if(monthlyMembers.getIsAvailable() != null){
			if(flag){
				sbf.append(" mm.is_available =:isAvailable");
				flag = false;
			}else{
				sbf.append(" ,mm.is_available =:isAvailable");
			}			
		}
		sbf.append(" where");
		if(monthlyMembers.getRecid() != null){
			sbf.append("  mm.recid=:recid");
		}
		Query query = session.createSQLQuery (sbf.toString());
		
		if(monthlyMembers.getAvailableTime() != null){
			query.setString("availableTime", monthlyMembers.getAvailableTime());
		}
		if(monthlyMembers.getIsAvailable() != null){
			query.setFloat("isAvailable",monthlyMembers.getIsAvailable());  		
		}
		if(monthlyMembers.getRecid() != null){
			query.setInteger("recid",monthlyMembers.getRecid());  
		}
		Integer updateint = query.executeUpdate();
		if(updateint>0){
			return true;
		}else{
			return false;
		}	
	}

	@Override
	public Serializable saveWithDrawals(WithDrawals withDrawals) {
		Session  session=sessionFactory.getCurrentSession();  
		withDrawals.setCreateTime(new Date());
		withDrawals.setTransferboolean(0);
	    return   session.save(withDrawals);  
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getWithDrawalss(String pagesize, String currpage, String cxtj, WithDrawals withDrawals) {
		//注意：提现表的主播键其实是用户主键。
		Session  session=sessionFactory.getCurrentSession();  
		StringBuffer sbf = new StringBuffer();
		sbf.append("select us.user_showname,us.user_loginname,wd.* from with_drawals as wd ");
		sbf.append("LEFT JOIN user_sheet as us on wd.user_recid = us.recid where wd.recid!=1000000 ");
		
		if(withDrawals.getUserRecid() != null){
			sbf.append(" and wd.user_recid =:userRecid");
		}
		if(withDrawals.getTransferboolean() != null){
			sbf.append(" and wd.transferboolean =:transferboolean");
		}
		if(withDrawals.getCreateTime() != null){
			
			sbf.append(" and date_format(wd.create_time,'%Y-%m-%d')=:createtime");
		}
		if(cxtj != null){
			sbf.append(" and (us.user_showname like :cxtj or us.user_loginname like :cxtj or wd.transferpeople like :cxtj");
			sbf.append( ")");
		}
		sbf.append( " order by wd.recid desc");
		
		Query query = session.createSQLQuery (sbf.toString());
		if(withDrawals.getUserRecid() != null){
			query.setLong("userRecid",withDrawals.getUserRecid());	
		}
		if(withDrawals.getTransferboolean() != null){
			query.setInteger("transferboolean",withDrawals.getTransferboolean());	
		}
		if(withDrawals.getCreateTime() != null){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(withDrawals.getCreateTime());
			query.setString("createtime",dateString);	
		}
		if(cxtj != null){
			query.setString("cxtj","%"+cxtj+"%");	
		}
		return  query.list();
	}

	@Override
	public boolean UpdateWithDrawals(WithDrawals withDrawals) {
		Session  session=sessionFactory.getCurrentSession();  
		StringBuffer sbf = new StringBuffer();
		sbf.append("UPDATE with_drawals as wd set");
		
		sbf.append(" wd.transfertime =:transfertime,transferboolean=:transferboolean,transferpeople=:transferpeople");
		
		sbf.append(" where recid=:recid");
		session.beginTransaction();
		Query query = session.createSQLQuery (sbf.toString());
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String transfertime = formatter.format(withDrawals.getTransfertime());
		query.setString("transfertime", transfertime);
		query.setInteger("transferboolean", withDrawals.getTransferboolean());
		query.setInteger("transferpeople", withDrawals.getTransferpeople());
		query.setLong("recid", withDrawals.getRecid());
		Integer updateint = query.executeUpdate();
		session.getTransaction().commit(); 
		if(updateint>0){
			return true;
		}else{
			return false;
		}	
	}

}
