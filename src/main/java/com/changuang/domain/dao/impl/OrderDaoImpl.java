package com.changuang.domain.dao.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.changuang.domain.dao.OrderDao;
import com.changuang.domain.entity.OrderProblem;
import com.changuang.domain.entity.OrderSheet;

/**
* @author songc
* @version 创建时间：2017年12月7日 下午5:12:58
* @ClassName OrderDao
* @Description 订单及订单投诉的实现类
*/
@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {
	@Autowired
	SessionFactory  sessionFactory;
	
	@Override
	public Serializable saveOrder(OrderSheet orderSheet) {
		Session  session=sessionFactory.getCurrentSession();  
		return session.save(orderSheet);
	}
	
	@Override
	public Integer getOrdersCount(String pagesize, String currpage, String cxtj, OrderSheet orderSheet) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" select count(*) ");
		sbf.append("  from order_sheet as os ");
		sbf.append("  left join order_status as oss on os.order_status = oss.recid ");
		sbf.append(" left join user_sheet as us on os.anchor_user = us.recid ");
		sbf.append(" left join user_sheet as uss on os.user_recid = uss.recid ");
		sbf.append(" where os.recid != 1000000 ");
		if(orderSheet.getUserRecid() != null){
			sbf.append(" and os.user_recid = :userRecid ");
		}
		if(orderSheet.getCreateTime() != null){
			sbf.append(" and date_format(os.create_time,'%Y-%m-%d') = :createTime ");
		} 
		if(orderSheet.getRecid() != null){
			sbf.append(" and os.recid = :recid ");
		}
		
		if(orderSheet.getAnchorRecid() != null){
			sbf.append(" and os.anchor_recid = :anchorRecid ");
		}
		if(cxtj != null){
			sbf.append(" and (us.user_loginname like :cxtj or us.user_showname like :cxtj or uss.user_loginname like :cxtj");
			sbf.append(" or uss.user_showname like :cxtj  or oss.status_name like :cxtj ");
			sbf.append( ")");
		}
		sbf.append( " ORDER BY os.recid DESC");
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
		if(orderSheet.getUserRecid() != null){
			query.setInteger("userRecid", orderSheet.getUserRecid());	
		}
		if(orderSheet.getCreateTime()!= null){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			 String createTime = formatter.format(orderSheet.getCreateTime());
			 query.setString("createTime", createTime);			 
		}
		if(orderSheet.getRecid() != null){
			query.setInteger("recid", orderSheet.getRecid());	
		}
		if(cxtj != null){
			query.setString("cxtj","%"+cxtj+"%");	
		}
		if(orderSheet.getAnchorRecid() != null){
			query.setInteger("anchorRecid", orderSheet.getAnchorRecid());	
		}
		return Integer.parseInt(query.list().get(0).toString());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getOrders(String pagesize, String currpage, String cxtj, OrderSheet orderSheet) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" select os.recid,os.user_recid,os.anchor_user,os.anchor_recid,os.order_status, ");
		sbf.append(" os.start_time,os.end_time,os.create_time,os.purchase_time,os.use_time,os.order_evaluate, ");
		sbf.append(" os.order_score,os.tip_anchor,oss.status_name,us.user_showname,us.user_headimg, ");
		sbf.append(" uss.user_loginname as userloginname,us.user_loginname as anchorloginname ");
		sbf.append("  from order_sheet as os ");
		sbf.append("  left join order_status as oss on os.order_status = oss.recid ");
		sbf.append(" left join user_sheet as us on os.anchor_user = us.recid ");
		sbf.append(" left join user_sheet as uss on os.user_recid = uss.recid ");
		sbf.append(" where os.recid != 1000000 ");
		if(orderSheet.getUserRecid() != null){
			sbf.append(" and os.user_recid = :userRecid ");
		}
		if(orderSheet.getCreateTime() != null){
			sbf.append(" and date_format(os.create_time,'%Y-%m-%d') = :createTime ");
		} 
		if(orderSheet.getRecid() != null){
			sbf.append(" and os.recid = :recid ");
		}
		
		if(orderSheet.getAnchorRecid() != null){
			sbf.append(" and os.anchor_recid = :anchorRecid ");
		}
		if(cxtj != null){
			sbf.append(" and (us.user_loginname like :cxtj or us.user_showname like :cxtj or uss.user_loginname like :cxtj");
			sbf.append(" or uss.user_showname like :cxtj  or oss.status_name like :cxtj ");
			sbf.append( ")");
		}
		sbf.append( " ORDER BY os.recid DESC");
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
		if(orderSheet.getUserRecid() != null){
			query.setInteger("userRecid", orderSheet.getUserRecid());	
		}
		if(orderSheet.getCreateTime()!= null){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			 String createTime = formatter.format(orderSheet.getCreateTime());
			 query.setString("createTime", createTime);			 
		}
		if(orderSheet.getRecid() != null){
			query.setInteger("recid", orderSheet.getRecid());	
		}
		if(cxtj != null){
			query.setString("cxtj","%"+cxtj+"%");	
		}
		if(orderSheet.getAnchorRecid() != null){
			query.setInteger("anchorRecid", orderSheet.getAnchorRecid());	
		}
		return query.list();
	}

	@Override
	public boolean UpdateOrder(OrderSheet orderSheet) {
		Session  session=sessionFactory.getCurrentSession(); 
		StringBuffer sbf = new StringBuffer();
		sbf.append(" Update order_sheet as os set");
		boolean flag = true;
		if(orderSheet.getStartTime() != null){
			sbf.append(" os.start_time = :startTime");
			flag = false;
		}
		if(orderSheet.getEndTime() != null){
			if(flag){
				sbf.append(" os.end_time =:endTime");
				flag = false;
			}else{
				sbf.append(" ,os.end_time =:endTime");
			}			
		}
		if(orderSheet.getUseTime() != null){
			if(flag){
				sbf.append(" os.use_time =:useTime");
				flag = false;
			}else{
				sbf.append(" ,os.use_time =:useTime");
			}			
		}
		if(orderSheet.getOrderEvaluate() != null){
			if(flag){
				sbf.append(" os.order_evaluate =:orderEvaluate");
				flag = false;
			}else{
				sbf.append(" ,os.order_evaluate =:orderEvaluate");
			}			
		}
		if(orderSheet.getOrderScore() != null){
			if(flag){
				sbf.append(" os.order_score =:orderScore");
				flag = false;
			}else{
				sbf.append(" ,os.order_score =:orderScore");
			}			
		}
		if(orderSheet.getTipAnchor() != null){
			if(flag){
				sbf.append(" os.tip_anchor =:tipAnchor");
				flag = false;
			}else{
				sbf.append(" ,os.tip_anchor =:tipAnchor");
			}			
		}
		sbf.append(" where");
		if(orderSheet.getRecid() != null){
			sbf.append("  os.recid=:recid");
		}
		Query query = session.createSQLQuery (sbf.toString());
		
		if(orderSheet.getStartTime() != null){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String startTime = formatter.format(orderSheet.getStartTime());		
			query.setString("startTime",startTime);
		}
		if(orderSheet.getEndTime() != null){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String endTime = formatter.format(orderSheet.getEndTime());		
			query.setString("endTime",endTime);
		}
		if(orderSheet.getUseTime() != null){
			query.setString("endTime",orderSheet.getUseTime());
		}
		if(orderSheet.getOrderEvaluate() != null){
			query.setString("orderEvaluate",orderSheet.getOrderEvaluate());		
		}
		if(orderSheet.getOrderScore() != null){
			query.setFloat("orderScore",orderSheet.getOrderScore());				
		}
		if(orderSheet.getTipAnchor() != null){
			query.setFloat("tipAnchor",orderSheet.getTipAnchor());		
		}
		if(orderSheet.getRecid() != null){
			query.setInteger("recid",orderSheet.getRecid());  
		}
		Integer updateint = query.executeUpdate();
		if(updateint>0){
			return true;
		}else{
			return false;
		}	
	}

	@Override
	public Serializable saveOrderProblem(OrderProblem orderproblem) {
		Session  session=sessionFactory.getCurrentSession();  
	      return   session.save(orderproblem); 
	}

	@SuppressWarnings({ "rawtypes"})
	@Override
	public List getOrderProblems(String pagesize, String currpage, String cxtj, OrderProblem orderproblem) {
		Session  session=sessionFactory.getCurrentSession();  
		StringBuffer sbf = new StringBuffer();
		sbf.append("select op.recid,op.order_recid,op.user_recid,op.feedback_content,op.create_time, ");
		sbf.append(" op.isreading,op.reader,op.reply_content,op.reply_time,os.tip_anchor, ");
		sbf.append(" us.user_showname,ans.recid as anuser,ans.user_showname as aname,ans.user_headimg,au.admin_showname");
		sbf.append("  from order_problem as op  ");
		sbf.append(" left join order_sheet as os on os.recid = op.order_recid ");
		sbf.append(" left join user_sheet as us on us.recid = op.user_recid ");
		sbf.append(" left join user_sheet as ans on ans.recid = os.anchor_user ");
		sbf.append(" left join admin_user as au on au.recid = op.reader ");
		sbf.append(" where op.recid != 1000000 ");
		if(orderproblem.getCreateTime() != null){
			sbf.append(" op.create_time =:createTime");
		}
		if(orderproblem.getIsreading() != null){
			sbf.append(" op.isreading =:isreading");
		}
		if(orderproblem.getOrderRecid() != null){
			sbf.append(" and op.orderRecid=:orderRecid");
		}
		if(orderproblem.getUserRecid() != null){
			sbf.append(" and op.user_recid=:userRecid");
		}
		if(orderproblem.getRecid() != null){
			sbf.append(" and op.recid=:recid");
		}
		if(cxtj != null){
			sbf.append(" and (us.user_loginname like :cxtj or us.user_showname like :cxtj or ans.user_loginname like :cxtj");
			sbf.append(" or ans.user_showname like :cxtj ");
			sbf.append( ")");
		}
		sbf.append(" order by op.recid desc");
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
		Query query = session.createSQLQuery (sbf.toString());
		if(orderproblem.getCreateTime() != null){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String createTime = formatter.format(orderproblem.getCreateTime());		
			query.setString("createTime", createTime);
		}
		if(orderproblem.getIsreading() != null){
			query.setInteger("isreading", orderproblem.getIsreading());
		}
		if(orderproblem.getOrderRecid() != null){
			query.setInteger("orderRecid", orderproblem.getOrderRecid());
		}
		if(orderproblem.getUserRecid() != null){
			query.setInteger("userRecid", orderproblem.getUserRecid());
		}
		if(orderproblem.getRecid() != null){
			query.setInteger("recid", orderproblem.getRecid());
		}
		if(cxtj != null){
			query.setString("cxtj","%"+cxtj+"%");	
		}
		return query.list();
	}

	@Override
	public boolean updateOrderProblems(OrderProblem orderproblem) {
		Session  session=sessionFactory.getCurrentSession(); 
		StringBuffer sbf = new StringBuffer();
		sbf.append(" Update order_problem as op set");
		if(orderproblem.getReplyContent() != null){
			sbf.append(" op.reply_content='"+orderproblem.getReplyContent()+"'");
		}
		if(orderproblem.getReplyTime() != null){
			if(orderproblem.getReplyTime() != null){
				sbf.append(" ,op.reply_time=now()");
			}else{
				sbf.append(" op.reply_time=now()");
			}
			
		}
		sbf.append(" ,op.reader='"+orderproblem.getReader()+"'");
		sbf.append(" ,op.isreading=1");
		sbf.append(" where 1=1");
		if(orderproblem.getRecid() != null){
			sbf.append(" and op.recid ='"+orderproblem.getRecid()+"'");
		}
		session.beginTransaction();
		Query query = session.createSQLQuery (sbf.toString());
		Integer updateint = query.executeUpdate();
		session.getTransaction().commit(); 
		if(updateint>0){
			return true;
		}else{
			return false;
		}	
	}

}
