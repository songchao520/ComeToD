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

import com.changuang.domain.dao.OperationDao;
import com.changuang.domain.entity.CommentSheet;
import com.changuang.domain.entity.FollowSheet;
import com.changuang.domain.entity.ReplySheet;

/**
* @author songc
* @version 创建时间：2017年12月7日 上午9:47:20
* @ClassName OperationDaoImpl
* @Description 关注，评论回复实现类
*/
@Repository
@Transactional
public class OperationDaoImpl implements OperationDao {
	@Autowired
	SessionFactory  sessionFactory;
	@Override
	public Integer getFollowSheetsCount(String pagesize, String currpage, String cxtj,FollowSheet followSheet){
		StringBuffer sbf = new StringBuffer();
		sbf.append(" select count(*)");
		sbf.append("  from follow_sheet as fs ");
		sbf.append(" LEFT JOIN user_sheet as us on fs.anchor_recid = us.recid ");
		sbf.append(" where fs.recid != 1000000 ");
		if(followSheet.getUserRecid() != null){
			sbf.append(" and fs.user_recid = :userRecid ");
		}
		if(followSheet.getRecid() != null){
			sbf.append(" and fs.recid = :recid ");
		}
		if(followSheet.getAnchorRecid() != null){
			sbf.append(" and fs.anchor_recid = :anchorRecid ");
		}
		sbf.append( " ORDER BY fs.recid DESC");
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
		if(followSheet.getUserRecid() != null){
			query.setInteger("userRecid", followSheet.getUserRecid());	
		}
		if(followSheet.getRecid() != null){
			query.setInteger("recid", followSheet.getRecid());	
		}
		if(followSheet.getAnchorRecid() != null){
			query.setInteger("anchorRecid", followSheet.getAnchorRecid());	
		}
		return Integer.parseInt(query.list().get(0).toString());
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getFollowSheets(String pagesize, String currpage, String cxtj, FollowSheet followSheet) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" select fs.recid,fs.user_recid as urecid,fs.anchor_recid as furecid, ");
		sbf.append(" us.user_showname,us.user_headimg,us.user_sex from follow_sheet as fs ");
		sbf.append(" LEFT JOIN user_sheet as us on fs.anchor_recid = us.recid ");
		sbf.append(" where fs.recid != 1000000 ");
		if(followSheet.getUserRecid() != null){
			sbf.append(" and fs.user_recid = :userRecid ");
		}
		if(followSheet.getRecid() != null){
			sbf.append(" and fs.recid = :recid ");
		}
		if(followSheet.getAnchorRecid() != null){
			sbf.append(" and fs.anchor_recid = :anchorRecid ");
		}
		sbf.append( " ORDER BY fs.recid DESC");
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
		if(followSheet.getUserRecid() != null){
			query.setInteger("userRecid", followSheet.getUserRecid());	
		}
		if(followSheet.getRecid() != null){
			query.setInteger("recid", followSheet.getRecid());	
		}
		if(followSheet.getAnchorRecid() != null){
			query.setInteger("anchorRecid", followSheet.getAnchorRecid());	
		}
		return query.list();
	}

	@Override
	public Serializable saveFollowSheet(FollowSheet followSheet) {
		followSheet.setCreateTime(new Date());
		Session  session=sessionFactory.getCurrentSession();  
	    return   session.save(followSheet);  
	}

	@Override
	public boolean DeleteFollowSheet(FollowSheet followSheet) {
		Session  session=sessionFactory.getCurrentSession();  
		session.beginTransaction();
		StringBuffer sbf = new StringBuffer();
		sbf.append(" delete from follow_sheet");
		sbf.append(" where 1=1");
		if(followSheet.getRecid() != null){
			sbf.append(" and recid=:recid");
		}else if(followSheet.getUserRecid() !=null && followSheet.getAnchorRecid()!=null){
			sbf.append(" and user_recid=:userRecid and anchor_recid=:anchorRecid");
		}else{
			return false;
		}
		Query query = session.createSQLQuery (sbf.toString());
		if(followSheet.getRecid() != null){
			query.setInteger("recid",followSheet.getRecid());  
		}else if(followSheet.getUserRecid() !=null && followSheet.getAnchorRecid()!=null){
			query.setInteger("userRecid",followSheet.getUserRecid());  
			query.setInteger("anchorRecid",followSheet.getAnchorRecid());  
		}
		
		Integer deleteint = query.executeUpdate();
		session.getTransaction().commit(); 
		 if(deleteint>0){
			 return true;
		 }else{
			 return false;
		 }
	}
	@Override
	public Integer getCommentSheetsCount(String pagesize, String currpage, String cxtj,CommentSheet commentSheet){
		StringBuffer sbf = new StringBuffer();
		sbf.append(" select count(*) ");
		sbf.append("  from comment_sheet as cs  ");
		sbf.append(" left join user_sheet as us on cs.user_recid = us.recid ");
		sbf.append(" where cs.recid != 1000000 ");
		if(commentSheet.getUserRecid() != null){
			sbf.append(" and cs.user_recid = :userRecid ");
		}
		if(commentSheet.getRecid() != null){
			sbf.append(" and cs.recid = :recid ");
		}
		if(commentSheet.getDynamicRecid() != null){
			sbf.append(" and cs.dynamic_recid = :dynamicRecid ");
		}
		if(cxtj != null){
			sbf.append(" and (us.user_loginname like :cxtj ");
			sbf.append(" or cs.comment_content like :cxtj j");
			sbf.append( ")");
		}
		sbf.append( " ORDER BY cs.recid DESC");
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
		if(commentSheet.getUserRecid() != null){
			query.setInteger("userRecid", commentSheet.getUserRecid());	
		}
		if(commentSheet.getRecid() != null){
			query.setInteger("recid", commentSheet.getRecid());	
		}
		if(cxtj != null){
			query.setString("cxtj","%"+cxtj+"%");	
		}
		if(commentSheet.getDynamicRecid() != null){
			query.setInteger("dynamicRecid", commentSheet.getDynamicRecid());	
		}
		return Integer.parseInt(query.list().get(0).toString());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getCommentSheets(String pagesize, String currpage, String cxtj, CommentSheet commentSheet) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" select cs.recid,cs.user_recid,cs.create_time,cs.dynamic_recid,cs.comment_content, ");
		sbf.append(" us.user_showname,us.user_headimg,us.user_loginname,us.user_sex,us.wealth_grade from comment_sheet as cs  ");
		sbf.append(" left join user_sheet as us on cs.user_recid = us.recid ");
		sbf.append(" where cs.recid != 1000000 ");
		if(commentSheet.getUserRecid() != null){
			sbf.append(" and cs.user_recid = :userRecid ");
		}
		if(commentSheet.getRecid() != null){
			sbf.append(" and cs.recid = :recid ");
		}
		if(commentSheet.getDynamicRecid() != null){
			sbf.append(" and cs.dynamic_recid = :dynamicRecid ");
		}
		if(cxtj != null){
			sbf.append(" and (us.user_loginname like :cxtj ");
			sbf.append(" or cs.comment_content like :cxtj j");
			sbf.append( ")");
		}
		sbf.append( " ORDER BY cs.recid DESC");
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
		if(commentSheet.getUserRecid() != null){
			query.setInteger("userRecid", commentSheet.getUserRecid());	
		}
		if(commentSheet.getRecid() != null){
			query.setInteger("recid", commentSheet.getRecid());	
		}
		if(cxtj != null){
			query.setString("cxtj","%"+cxtj+"%");	
		}
		if(commentSheet.getDynamicRecid() != null){
			query.setInteger("dynamicRecid", commentSheet.getDynamicRecid());	
		}
		return query.list();
	}

	@Override
	public Serializable saveCommentSheet(CommentSheet commentSheet) {
		commentSheet.setCreateTime(new Date());
		Session  session=sessionFactory.getCurrentSession();  
	    return   session.save(commentSheet);  
	}

	@Override
	public boolean DeleteCommentSheet(CommentSheet commentSheet) {
		Session  session=sessionFactory.getCurrentSession();  
		session.beginTransaction();
		StringBuffer sbf = new StringBuffer();
		sbf.append(" delete from comment_sheet");
		sbf.append(" where 1=1");
		if(commentSheet.getRecid() != null){
			sbf.append(" and recid=:recid");
		}else if(commentSheet.getUserRecid() !=null && commentSheet.getDynamicRecid()!=null){
			sbf.append(" and user_recid=:userRecid and dynamic_recid=:dynamicRecid");
		}else{
			return false;
		}
		Query query = session.createSQLQuery (sbf.toString());
		if(commentSheet.getRecid() != null){
			query.setInteger("recid",commentSheet.getRecid());  
		}else if(commentSheet.getUserRecid() !=null && commentSheet.getDynamicRecid()!=null){
			query.setInteger("userRecid",commentSheet.getUserRecid());  
			query.setInteger("dynamicRecid",commentSheet.getDynamicRecid());  
		}
		
		Integer deleteint = query.executeUpdate();
		session.getTransaction().commit(); 
		 if(deleteint>0){
			 return true;
		 }else{
			 return false;
		 }
	}
	@Override
	public Integer getReplySheetsCount(String pagesize, String currpage, String cxtj,ReplySheet replySheet){
		StringBuffer sbf = new StringBuffer();
		sbf.append(" select count(*) ");
		sbf.append("  from reply_sheet as rs ");
		sbf.append(" left join user_sheet as us on rs.user_recid = us.recid ");
		sbf.append(" left join user_sheet as uss on rs.reply_userrecid = uss.recid ");
		sbf.append(" where rs.recid != 1000000 ");
		if(replySheet.getUserRecid() != null){
			sbf.append(" and rs.user_recid = :userRecid ");
		}
		if(replySheet.getRecid() != null){
			sbf.append(" and rs.recid = :recid ");
		}
		if(replySheet.getCommentRecid() != null){
			sbf.append(" and rs.comment_recid = :commentRecid ");
		}
		if(replySheet.getReplyUserrecid() != null){
			sbf.append(" and rs.reply_userrecid = :replyUserrecid ");
		}
		if(cxtj != null){
			sbf.append(" and (us.user_loginname like :cxtj ");
			sbf.append(" or rs.reply_content like :cxtj j");
			sbf.append( ")");
		}
		sbf.append( " ORDER BY rs.recid");
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
		if(replySheet.getUserRecid() != null){
			query.setInteger("userRecid", replySheet.getUserRecid());	
		}
		if(replySheet.getRecid() != null){
			query.setInteger("recid", replySheet.getRecid());	
		}
		if(cxtj != null){
			query.setString("cxtj","%"+cxtj+"%");	
		}
		if(replySheet.getCommentRecid() != null){
			query.setInteger("commentRecid", replySheet.getCommentRecid());	
		}
		if(replySheet.getReplyUserrecid() != null){
			query.setInteger("replyUserrecid", replySheet.getReplyUserrecid());	
		}
		return Integer.parseInt(query.list().get(0).toString());
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getReplySheets(String pagesize, String currpage, String cxtj, ReplySheet replySheet) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" select rs.recid,rs.comment_recid,rs.create_time,rs.user_recid,rs.reply_content, ");
		sbf.append(" us.user_showname,us.user_headimg,uss.user_showname as reusername,rs.reply_userrecid,  ");
		sbf.append(" us.user_loginname,uss.user_loginname as reloginname ");
		sbf.append("  from reply_sheet as rs ");
		sbf.append(" left join user_sheet as us on rs.user_recid = us.recid ");
		sbf.append(" left join user_sheet as uss on rs.reply_userrecid = uss.recid ");
		sbf.append(" where rs.recid != 1000000 ");
		if(replySheet.getUserRecid() != null){
			sbf.append(" and rs.user_recid = :userRecid ");
		}
		if(replySheet.getRecid() != null){
			sbf.append(" and rs.recid = :recid ");
		}
		if(replySheet.getCommentRecid() != null){
			sbf.append(" and rs.comment_recid = :commentRecid ");
		}
		if(replySheet.getReplyUserrecid() != null){
			sbf.append(" and rs.reply_userrecid = :replyUserrecid ");
		}
		if(cxtj != null){
			sbf.append(" and (us.user_loginname like :cxtj ");
			sbf.append(" or rs.reply_content like :cxtj j");
			sbf.append( ")");
		}
		sbf.append( " ORDER BY rs.recid");
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
		if(replySheet.getUserRecid() != null){
			query.setInteger("userRecid", replySheet.getUserRecid());	
		}
		if(replySheet.getRecid() != null){
			query.setInteger("recid", replySheet.getRecid());	
		}
		if(cxtj != null){
			query.setString("cxtj","%"+cxtj+"%");	
		}
		if(replySheet.getCommentRecid() != null){
			query.setInteger("commentRecid", replySheet.getCommentRecid());	
		}
		if(replySheet.getReplyUserrecid() != null){
			query.setInteger("replyUserrecid", replySheet.getReplyUserrecid());	
		}
		return query.list();
	}

	@Override
	public Serializable saveReplySheet(ReplySheet replySheet) {
		replySheet.setCreateTime(new Date());
		Session  session=sessionFactory.getCurrentSession();  
	    return   session.save(replySheet);  
	}

	@Override
	public boolean DeleteReplySheet(ReplySheet replySheet) {
		Session  session=sessionFactory.getCurrentSession();  
		session.beginTransaction();
		StringBuffer sbf = new StringBuffer();
		sbf.append(" delete from reply_sheet");
		sbf.append(" where 1=1");
		if(replySheet.getRecid() != null){
			sbf.append(" and recid=:recid");
		}else if(replySheet.getUserRecid() !=null && replySheet.getCommentRecid()!=null){
			sbf.append(" and user_recid=:userRecid and comment_recid=:commentRecid");
		}else{
			return false;
		}
		Query query = session.createSQLQuery (sbf.toString());
		if(replySheet.getRecid() != null){
			query.setInteger("recid",replySheet.getRecid());  
		}else if(replySheet.getUserRecid() !=null && replySheet.getCommentRecid()!=null){
			query.setInteger("userRecid",replySheet.getUserRecid());  
			query.setInteger("commentRecid",replySheet.getCommentRecid());  
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
