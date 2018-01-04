package com.changuang.domain.dao.impl;

import java.io.Serializable;
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
	
	@SuppressWarnings("rawtypes")
	@Override
	public List getFriendSheets(String pagesize, String currpage, String cxtj, FriendSheet friendSheet) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" select fs.recid,fs.user_recidone,fs.user_recidtwo,fs.friend_status, ");
		sbf.append(" ls.recid as lgrecid,us.user_showname,us.user_headimg from friend_sheet as fs ");
		sbf.append(" LEFT JOIN login_sheet as ls on fs.user_recidtwo = ls.user_recid ");
		sbf.append(" LEFT JOIN user_sheet as us on us.recid = fs.user_recidtwo ");
		sbf.append(" where 1=1 ");
		if(friendSheet.getUserRecidone() != null){
			sbf.append(" and fs.user_recidone = :userRecidone ");
		}
		if(friendSheet.getRecid() != null){
			sbf.append(" and fs.recid = :recid ");
		}
		sbf.append( " ORDER BY ls.recid DESC");
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
		if(friendSheet.getUserRecidone() != null){
			query.setInteger("userRecidone", friendSheet.getUserRecidone());	
		}
		if(friendSheet.getRecid() != null){
			query.setInteger("recid", friendSheet.getRecid());	
		}
		return query.list();
	}

	@Override
	public Serializable saveFriendSheet(FriendSheet friendSheet) {
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
