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

import com.changuang.domain.dao.FriendsDao;
import com.changuang.domain.entity.FriendSheet;

/**
* @author songc
* @version 创建时间：2017年12月6日 上午11:08:16
* @ClassName FriendsDaoImpl
* @Description 好友表，好友状态表的实现类
*/
@Repository
@Transactional
public class FriendsDaoImpl implements FriendsDao {
	@Autowired
	SessionFactory  sessionFactory;
	
	
	@Override
	public Integer getFriendSheetsCount(String pagesize, String currpage, String cxtj, FriendSheet friendSheet) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" select count(*) ");
		sbf.append("  from friend_sheet as fs ");
		sbf.append(" LEFT JOIN login_sheet as ls on fs.user_recidtwo = ls.user_recid ");
		sbf.append(" LEFT JOIN user_sheet as us on us.recid = fs.user_recidtwo ");
		sbf.append(" LEFT JOIN user_sheet as uss on uss.recid = fs.user_recidone ");
		sbf.append(" where 1=1 ");
		if(friendSheet.getUserRecidone() != null){
			sbf.append(" and fs.user_recidone = :userRecidone ");
		}
		if(friendSheet.getUserRecidtwo() != null){
			sbf.append(" and fs.user_recidtwo = :userRecidtwo ");
		}
		if(friendSheet.getFriendStatus() != null){
			sbf.append(" and fs.friend_status = :friendStatus ");
		}
		if(cxtj != null){
			sbf.append(" and (us.user_loginname like :cxtj or us.user_showname like :cxtj ");
			sbf.append(" or us.user_lastaddress like :cxtj ");
			sbf.append( ")");
		}
		if(friendSheet.getRecid() != null){
			sbf.append(" and fs.recid = :recid ");
		}
		sbf.append( " ORDER BY fs.recid DESC");
		if(pagesize == null){
			pagesize = "10000";
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
		if(friendSheet.getUserRecidone() != null){
			query.setInteger("userRecidone", friendSheet.getUserRecidone());	
		}
		if(friendSheet.getUserRecidtwo() != null){
			query.setInteger("userRecidtwo", friendSheet.getUserRecidtwo());	
		}
		if(friendSheet.getFriendStatus() != null){
			query.setInteger("friendStatus", friendSheet.getFriendStatus());	
		}
		if(cxtj != null){
			query.setString("cxtj","%"+cxtj+"%");	
		}
		if(friendSheet.getRecid() != null){
			query.setInteger("recid", friendSheet.getRecid());	
		}
		return Integer.parseInt(query.list().get(0).toString());
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getFriendSheets(String pagesize, String currpage, String cxtj, FriendSheet friendSheet) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" select fs.recid,fs.user_recidone,fs.user_recidtwo,fs.friend_status, ");
		sbf.append(" ls.recid as lgrecid,us.user_showname,us.user_headimg,uss.user_showname as ush,uss.user_headimg as ushe");
		sbf.append("  from friend_sheet as fs ");
		sbf.append(" LEFT JOIN login_sheet as ls on fs.user_recidtwo = ls.user_recid ");
		sbf.append(" LEFT JOIN user_sheet as us on us.recid = fs.user_recidtwo ");
		sbf.append(" LEFT JOIN user_sheet as uss on uss.recid = fs.user_recidone ");
		sbf.append(" where 1=1 ");
		if(friendSheet.getUserRecidone() != null){
			sbf.append(" and fs.user_recidone = :userRecidone ");
		}
		if(friendSheet.getUserRecidtwo() != null){
			sbf.append(" and fs.user_recidtwo = :userRecidtwo ");
		}
		if(friendSheet.getFriendStatus() != null){
			sbf.append(" and fs.friend_status = :friendStatus ");
		}
		if(cxtj != null){
			sbf.append(" and (us.user_loginname like :cxtj or us.user_showname like :cxtj ");
			sbf.append(" or us.user_lastaddress like :cxtj ");
			sbf.append( ")");
		}
		if(friendSheet.getRecid() != null){
			sbf.append(" and fs.recid = :recid ");
		}
		sbf.append( " ORDER BY fs.recid DESC");
		if(pagesize == null){
			pagesize = "10000";
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
		if(friendSheet.getUserRecidone() != null){
			query.setInteger("userRecidone", friendSheet.getUserRecidone());	
		}
		if(friendSheet.getUserRecidtwo() != null){
			query.setInteger("userRecidtwo", friendSheet.getUserRecidtwo());	
		}
		if(friendSheet.getFriendStatus() != null){
			query.setInteger("friendStatus", friendSheet.getFriendStatus());	
		}
		if(cxtj != null){
			query.setString("cxtj","%"+cxtj+"%");	
		}
		if(friendSheet.getRecid() != null){
			query.setInteger("recid", friendSheet.getRecid());	
		}
		return query.list();
	}

	@Override
	public Serializable saveFriendSheet(FriendSheet friendSheet) {
		friendSheet.setCreateTime(new Date());
		Session  session=sessionFactory.getCurrentSession();  
	    return   session.save(friendSheet);  
	}

	@Override
	public boolean UpdateFriendSheet(FriendSheet friendSheet) {
		Session  session=sessionFactory.getCurrentSession(); 
		StringBuffer sbf = new StringBuffer();
		sbf.append(" Update friend_sheet as fs set");
		if(friendSheet.getFriendStatus() != null){
			sbf.append(" fs.friend_status = :friendStatus");
		}
		sbf.append(" where 1=1 ");
		if(friendSheet.getRecid() != null){
			sbf.append("  and fs.recid=:recid");
			if(friendSheet.getUserRecidone()!=null && friendSheet.getUserRecidtwo()!=null){
				sbf.append("  and fs.user_recidone=:userRecidone and fs.user_recidtwo=:userRecidtwo ");
			}
		}else{
			if(friendSheet.getUserRecidone()!=null && friendSheet.getUserRecidtwo()!=null){
				sbf.append("  and fs.user_recidone=:userRecidone and fs.user_recidtwo=:userRecidtwo ");
			}else{
				return false;
			}
		}
		
		Query query = session.createSQLQuery (sbf.toString());
		if(friendSheet.getFriendStatus() != null){
			query.setInteger("friendStatus",friendSheet.getFriendStatus());  
		}
		if(friendSheet.getRecid() != null){
			query.setInteger("recid",friendSheet.getRecid());  
		}
		if(friendSheet.getUserRecidone()!=null && friendSheet.getUserRecidtwo()!=null){
			query.setInteger("userRecidone",friendSheet.getUserRecidone());  
			query.setInteger("userRecidtwo",friendSheet.getUserRecidtwo());  
		}
		Integer updateint = query.executeUpdate();
		if(updateint>0){
			return true;
		}else{
			return false;
		}	
	}


}
