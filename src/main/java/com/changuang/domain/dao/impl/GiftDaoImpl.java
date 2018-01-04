package com.changuang.domain.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.changuang.domain.dao.GiftDao;
import com.changuang.domain.entity.GiftSheet;
import com.changuang.domain.entity.RewardSheet;

/**
* @author songc
* @version 创建时间：2017年12月5日 下午5:14:02
* @ClassName GiftDaoImpl
* @Description 礼物表和打赏礼物表的实现类
*/
@Repository
@Transactional
public class GiftDaoImpl implements GiftDao { 
	@Autowired
	SessionFactory  sessionFactory;
	
	@Override
	public Integer getGiftSheetsCount(String pagesize, String currpage, String cxtj, GiftSheet giftSheet) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("select count(*) from gift_sheet gs");
		sbf.append(" where 1=1");
		if(giftSheet.getRecid() != null){
			sbf.append(" and gs.recid = :recid");			
		}
		if(giftSheet.getIsShow() != null){
			sbf.append(" and gs.is_show = :isShow");
		}
		if(cxtj != null){
			sbf.append(" and (gs.gift_name like :cxtj ");
			sbf.append(" or gs.gift_money like :cxtj j");
			sbf.append( ")");
		}
		sbf.append( " order by gs.gift_money desc");
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
		if(giftSheet.getIsShow() != null){
			query.setInteger("isShow", giftSheet.getIsShow());	
		}
		if(giftSheet.getRecid() != null){
			query.setInteger("recid", giftSheet.getRecid());		
		}
		if(cxtj != null){
			query.setString("cxtj","%"+cxtj+"%");	
		}
		return Integer.parseInt(query.list().get(0).toString());
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getGiftSheets(String pagesize, String currpage, String cxtj, GiftSheet giftSheet) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("select gs.* from gift_sheet gs");
		sbf.append(" where 1=1");
		if(giftSheet.getRecid() != null){
			sbf.append(" and gs.recid = :recid");			
		}
		if(giftSheet.getIsShow() != null){
			sbf.append(" and gs.is_show = :isShow");
		}
		if(cxtj != null){
			sbf.append(" and (gs.gift_name like :cxtj ");
			sbf.append(" or gs.gift_money like :cxtj j");
			sbf.append( ")");
		}
		sbf.append( " order by gs.gift_money desc");
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
		if(giftSheet.getIsShow() != null){
			query.setInteger("isShow", giftSheet.getIsShow());	
		}
		if(giftSheet.getRecid() != null){
			query.setInteger("recid", giftSheet.getRecid());		
		}
		if(cxtj != null){
			query.setString("cxtj","%"+cxtj+"%");	
		}
		return query.list();
	}

	@Override
	public Serializable saveGiftSheet(GiftSheet giftSheet) {
		Session  session=sessionFactory.getCurrentSession();  
	    return   session.save(giftSheet);  
	}

	@Override
	public boolean UpdateGiftSheet(GiftSheet giftSheet) {
		Session  session=sessionFactory.getCurrentSession(); 
		StringBuffer sbf = new StringBuffer();
		sbf.append(" Update gift_sheet as aon set");
		boolean flag = true;
		if(giftSheet.getGiftName() != null){
			sbf.append(" aon.gift_name = :giftName");
			flag = false;
		}
		if(giftSheet.getGiftMoney() != null){
			if(flag){
				sbf.append(" aon.gift_money =:giftMoney");
				flag = false;
			}else{
				sbf.append(" ,aon.gift_money =:giftMoney");
			}			
		}
		if(giftSheet.getGiftPath() != null){
			if(flag){
				sbf.append(" aon.gift_path =:giftPath");
				flag = false;
			}else{
				sbf.append(" ,aon.gift_path =:giftPath");
			}			
		}
		if(giftSheet.getIsShow() != null){
			if(flag){
				sbf.append(" aon.is_show =:isShow");
				flag = false;
			}else{
				sbf.append(" ,aon.is_show =:isShow");
			}			
		}
		sbf.append(" where");
		if(giftSheet.getRecid() != null){
			sbf.append("  aon.recid=:recid");
		}
		Query query = session.createSQLQuery (sbf.toString());
		
		if(giftSheet.getGiftName() != null){
			query.setString("giftName", giftSheet.getGiftName());
		}
		if(giftSheet.getGiftMoney() != null){
			query.setFloat("giftMoney",giftSheet.getGiftMoney());  		
		}
		if(giftSheet.getGiftPath() != null){
			query.setString("giftPath", giftSheet.getGiftPath());		
		}
		if(giftSheet.getIsShow() != null){
			query.setInteger("isShow",giftSheet.getIsShow());  	
		}
		if(giftSheet.getRecid() != null){
			query.setInteger("recid",giftSheet.getRecid());  
		}
		Integer updateint = query.executeUpdate();
		if(updateint>0){
			return true;
		}else{
			return false;
		}	
	}
	public Integer getRewardSheetsCount(String pagesize, String currpage, String cxtj,RewardSheet rewardSheet){
		StringBuffer sbf = new StringBuffer();
		sbf.append(" select count(*) from reward_sheet as rs ");
		sbf.append(" LEFT JOIN user_sheet as us on rs.user_recid = us.recid ");
		sbf.append(" LEFT JOIN anchor_sheet as ash on rs.anchor_recid = ash.recid ");
		sbf.append(" LEFT JOIN user_sheet as uss on ash.user_recid = uss.recid ");
		sbf.append(" LEFT JOIN gift_sheet as gs on gs.recid = rs.gift_recid ");
		sbf.append(" where 1=1");
		if(rewardSheet.getRecid()!=null){
			sbf.append("  and rs.recid=:recid");
		}
		if(rewardSheet.getUserRecid() != null){
			sbf.append("  and rs.userRecid=:userRecid");
		}
		if(rewardSheet.getAnchorRecid() != null){
			sbf.append("  and  rs.anchor_online=:anchorRecid");
		}
		if(rewardSheet.getGiftRecid() != null){
			sbf.append("  and  rs.gift_recid=:giftRecid");
		}
		if(cxtj != null){
			sbf.append(" and (us.user_loginname like :cxtj or uss.user_showname like :cxtj or gs.gift_name like :cxtj");
			sbf.append(" or  gs.gift_name like :cxtj");
			sbf.append( ")");
		}
		sbf.append( " order by rs.recid desc");
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
		if(rewardSheet.getRecid()!=null){
			query.setInteger("recid", rewardSheet.getRecid());
		}
		if(rewardSheet.getUserRecid()!= null){
			query.setInteger("userRecid", rewardSheet.getUserRecid());
		}
		if(rewardSheet.getAnchorRecid() != null){
			query.setInteger("anchorRecid", rewardSheet.getAnchorRecid());
		}
		if(rewardSheet.getGiftRecid() != null){
			query.setInteger("giftRecid", rewardSheet.getGiftRecid());
		}
		if(cxtj != null){
			query.setString("cxtj","%"+cxtj+"%");	
		}
		return Integer.parseInt(query.list().get(0).toString());
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getRewardSheets(String pagesize, String currpage, String cxtj, RewardSheet rewardSheet) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" select us.user_showname username,uss.user_showname as anchorname,rs.* ,gs.gift_name from reward_sheet as rs ");
		sbf.append(" LEFT JOIN user_sheet as us on rs.user_recid = us.recid ");
		sbf.append(" LEFT JOIN anchor_sheet as ash on rs.anchor_recid = ash.recid ");
		sbf.append(" LEFT JOIN user_sheet as uss on ash.user_recid = uss.recid ");
		sbf.append(" LEFT JOIN gift_sheet as gs on gs.recid = rs.gift_recid ");
		sbf.append(" where 1=1");
		if(rewardSheet.getRecid()!=null){
			sbf.append("  and rs.recid=:recid");
		}
		if(rewardSheet.getUserRecid() != null){
			sbf.append("  and rs.userRecid=:userRecid");
		}
		if(rewardSheet.getAnchorRecid() != null){
			sbf.append("  and  rs.anchor_online=:anchorRecid");
		}
		if(rewardSheet.getGiftRecid() != null){
			sbf.append("  and  rs.gift_recid=:giftRecid");
		}
		if(cxtj != null){
			sbf.append(" and (us.user_loginname like :cxtj or uss.user_showname like :cxtj or gs.gift_name like :cxtj");
			sbf.append(" or  gs.gift_name like :cxtj");
			sbf.append( ")");
		}
		sbf.append( " order by rs.recid desc");
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
		if(rewardSheet.getRecid()!=null){
			query.setInteger("recid", rewardSheet.getRecid());
		}
		if(rewardSheet.getUserRecid()!= null){
			query.setInteger("userRecid", rewardSheet.getUserRecid());
		}
		if(rewardSheet.getAnchorRecid() != null){
			query.setInteger("anchorRecid", rewardSheet.getAnchorRecid());
		}
		if(rewardSheet.getGiftRecid() != null){
			query.setInteger("giftRecid", rewardSheet.getGiftRecid());
		}
		if(cxtj != null){
			query.setString("cxtj","%"+cxtj+"%");	
		}
		return query.list();
	}

	@Override
	public Serializable saveRewardSheet(RewardSheet rewardSheet) {
		Session  session=sessionFactory.getCurrentSession();  
	    return   session.save(rewardSheet);  
	}

	@Override
	public boolean UpdateRewardSheet(RewardSheet rewardSheet) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
