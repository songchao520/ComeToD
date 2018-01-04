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

import com.changuang.domain.dao.AnchorDao;
import com.changuang.domain.dao.UserDao;
import com.changuang.domain.entity.AnchorOnline;
import com.changuang.domain.entity.AnchorSheet;
import com.changuang.domain.entity.AnchorStatusSheet;
import com.changuang.domain.entity.UserSheet;

/**
* @author songc
* @version 创建时间：2017年12月4日 上午11:13:47
* @ClassName AnchorDaoImpl
* @Description 主播审核表及主播表的实现类
*/
@Repository
@Transactional
public class AnchorDaoImpl implements AnchorDao {

	@Autowired
	SessionFactory  sessionFactory;
	@Autowired
	UserDao ud ;
	
	@Override
	public Integer getAnchorSheetsCount(String pagesize, String currpage, String cxtj, AnchorSheet anchorSheet,String sortZiduan) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("select count(*) from anchor_sheet ash");
		sbf.append(" where 1=1  and recid!=1000000");
		if(anchorSheet.getRecid() != null){
			sbf.append("  and ash.recid=:recid");
		}else{
			if(anchorSheet.getUserRecid() != null){
				sbf.append("  and  ash.user_recid=:userRecid");
			}
		}
		if(anchorSheet.getCreateTime() != null){
			sbf.append(" and date_format(ash.create_time,'%Y-%m-%d') = :createTime ");
		} 
		if(cxtj != null){
			sbf.append(" and (ash.user_name like :cxtj  or ash.user_address like :cxtj");
			sbf.append(" or ash.user_occupation like :cxtj ");
			sbf.append( ")");
		}
		if(sortZiduan!=null){
			sbf.append( " order by ash."+sortZiduan+" desc");
		}else{
			sbf.append( " order by ash.recid desc");
		}
		
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
		if(anchorSheet.getRecid() != null){
			query.setInteger("recid", anchorSheet.getRecid());
		}else{
			if(anchorSheet.getUserRecid() != null){
				query.setInteger("userRecid", anchorSheet.getUserRecid());
			}
		}
		if(anchorSheet.getCreateTime()!= null){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			 String createTime = formatter.format(anchorSheet.getCreateTime());
			 query.setString("createTime", createTime);			 
		}
		if(cxtj != null){
			query.setString("cxtj", cxtj);
		}
		return Integer.parseInt(query.list().get(0).toString());
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getAnchorSheets(String pagesize, String currpage, String cxtj, AnchorSheet anchorSheet,String sortZiduan) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("select ash.* from anchor_sheet ash");
		sbf.append(" where 1=1 and recid!=1000000");
		if(anchorSheet.getRecid() != null){
			sbf.append("  and ash.recid=:recid");
		}else{
			if(anchorSheet.getUserRecid() != null){
				sbf.append("  and  ash.user_recid=:userRecid");
			}
		}
		if(anchorSheet.getCreateTime() != null){
			sbf.append(" and date_format(ash.create_time,'%Y-%m-%d') = :createTime ");
		} 
		if(cxtj != null){
			sbf.append(" and (ash.user_name like :cxtj  or ash.user_address like :cxtj");
			sbf.append(" or ash.user_occupation like :cxtj ");
			sbf.append( ")");
		}
		
		if(sortZiduan!=null){
			sbf.append( " order by ash."+sortZiduan+" desc");
		}else{
			sbf.append( " order by ash.recid desc");
		}
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
		if(anchorSheet.getRecid() != null){
			query.setInteger("recid", anchorSheet.getRecid());
		}else{
			if(anchorSheet.getUserRecid() != null){
				query.setInteger("userRecid", anchorSheet.getUserRecid());
			}
		}
		if(anchorSheet.getCreateTime()!= null){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			 String createTime = formatter.format(anchorSheet.getCreateTime());
			 query.setString("createTime", createTime);			 
		}
		if(cxtj != null){
			query.setString("cxtj", cxtj);
		}
		return query.list();
	}

	@Override
	public Serializable saveAnchorSheet(AnchorSheet anchorSheet) {
		Session  session=sessionFactory.getCurrentSession();  
		float lg = 0;
		String sqls = "from AnchorStatusSheet  where userRecid =  ?";
		Query query = session.createQuery(sqls).setParameter(0, anchorSheet.getUserRecid()); 
		AnchorStatusSheet ass = (AnchorStatusSheet) query.list().get(0);
		anchorSheet.setCreateTime(new Date());
		anchorSheet.setAccumulativeGift(lg);
		anchorSheet.setBeconsumedAmount(lg);
		anchorSheet.setAmountOfcash(lg);
		anchorSheet.setBalance(lg);
		anchorSheet.setAnchorGrade(lg);
		anchorSheet.setAccumulativeTime(lg);
		anchorSheet.setVoiceChat(lg);
		anchorSheet.setVideoChat(lg);
		anchorSheet.setAnchorGlamour(lg);
		anchorSheet.setAnchorClass("3");
		anchorSheet.setIsHot(0);
		anchorSheet.setIsRecommend(0);
		anchorSheet.setIsForefront(0);
		anchorSheet.setUserName(ass.getAnchorName());
		anchorSheet.setUserBirthday(ass.getUserBirthday());
		anchorSheet.setUserIdcord(ass.getAnchorIdcord());
		anchorSheet.setUserAddress(ass.getUserAddress());
		anchorSheet.setUserOccupation(ass.getUserOccupation());getClass();
		anchorSheet.setMyPhoto(ass.getMyPhoto());	
		anchorSheet.setSmallPhoto(ass.getSmallPhoto());
		Serializable sz = session.save(anchorSheet);
		ass.setAnchorRecid(Integer.parseInt(sz.toString()));
		this.UpdateAnchorStatusSheet(ass);
	    return  sz ;  
	}

	@Override
	public boolean UpdateAnchorSheet(AnchorSheet anchorSheet) {
		Session  session=sessionFactory.getCurrentSession(); 
		StringBuffer sbf = new StringBuffer();
		sbf.append(" Update anchor_sheet as ass set");
		boolean flag = true;
		if(anchorSheet.getAccumulativeGift() != null){
			sbf.append(" ass.accumulative_gift = :accumulativeGift");
			flag = false;
		}
		if(anchorSheet.getBeconsumedAmount() != null){
			if(flag){
				sbf.append(" ass.beconsumed_amount =:beconsumedAmount");
				flag = false;
			}else{
				sbf.append(" ,ass.beconsumed_amount =:beconsumedAmount");
			}			
		}
		if(anchorSheet.getAmountOfcash() != null){
			if(flag){
				sbf.append(" ass.amount_ofcash =:amountOfcash");
				flag = false;
			}else{
				sbf.append(" ,ass.amount_ofcash =:amountOfcash");
			}			
		}
		if(anchorSheet.getBalance() != null){
			if(flag){
				sbf.append(" ass.balance =:balance");
				flag = false;
			}else{
				sbf.append(" ,ass.balance =:balance");
			}			
		}
		if(anchorSheet.getAnchorGrade() != null){
			if(flag){
				sbf.append(" ass.balance =:anchorGrade");
				flag = false;
			}else{
				sbf.append(" ,ass.balance =:anchorGrade");
			}			
		}
		if(anchorSheet.getAccumulativeTime() != null){
			if(flag){
				sbf.append(" ass.accumulative_time =:accumulativeTime");
				flag = false;
			}else{
				sbf.append(" ,ass.accumulative_time =:accumulativeTime");
			}			
		}
		if(anchorSheet.getVoiceChat() != null){
			if(flag){
				sbf.append(" ass.voice_chat =:voiceChat");
				flag = false;
			}else{
				sbf.append(" ,ass.voice_chat =:voiceChat");
			}			
		}
		if(anchorSheet.getVideoChat() != null){
			if(flag){
				sbf.append(" ass.video_chat =:videoChat");
				flag = false;
			}else{
				sbf.append(" ,ass.video_chat =:videoChat");
			}			
		}
		if(anchorSheet.getAnchorGlamour() != null){
			if(flag){
				sbf.append(" ass.anchor_glamour =:anchorGlamour");
				flag = false;
			}else{
				sbf.append(" ,ass.anchor_glamour =:anchorGlamour");
			}			
		}
		if(anchorSheet.getAnchorClass() != null){
			if(flag){
				sbf.append(" ass.anchor_class =:anchorClass");
				flag = false;
			}else{
				sbf.append(" ,ass.anchor_class =:anchorClass");
			}			
		}
		if(anchorSheet.getUserOccupation() != null){
			if(flag){
				sbf.append(" ass.user_occupation =:userOccupation");
				flag = false;
			}else{
				sbf.append(" ,ass.user_occupation =:userOccupation");
			}			
		}
		if(anchorSheet.getChatAbility() != null){
			if(flag){
				sbf.append(" ass.chat_ability =:chatAbility");
				flag = false;
			}else{
				sbf.append(" ,ass.chat_ability =:chatAbility");
			}			
		}
		if(anchorSheet.getIsHot() != null){
			if(flag){
				sbf.append(" ass.is_hot =:isHot");
				flag = false;
			}else{
				sbf.append(" ,ass.is_hot =:isHot");
			}			
		}
		if(anchorSheet.getIsRecommend() != null){
			if(flag){
				sbf.append(" ass.is_recommend =:isRecommend");
				flag = false;
			}else{
				sbf.append(" ,ass.is_recommend =:isRecommend");
			}			
		}
		if(anchorSheet.getIsForefront() != null){
			if(flag){
				sbf.append(" ass.is_forefront =:isForefront");
				flag = false;
			}else{
				sbf.append(" ,ass.is_forefront =:isForefront");
			}			
		}
		sbf.append(" where");
		if(anchorSheet.getRecid() != null){
			sbf.append("  ass.recid=:recid");
		}else{
			if(anchorSheet.getUserRecid() != null){
				sbf.append("  ass.user_recid=:userRecid");
			}
		}
		
		Query query = session.createSQLQuery (sbf.toString());
		
		if(anchorSheet.getAccumulativeGift() != null){
			query.setFloat("accumulativeGift", anchorSheet.getAccumulativeGift());
		}
		if(anchorSheet.getBeconsumedAmount() != null){
			query.setFloat("beconsumedAmount", anchorSheet.getBeconsumedAmount());		
		}
		if(anchorSheet.getAmountOfcash() != null){
			query.setFloat("amountOfcash", anchorSheet.getAmountOfcash());			
		}
		if(anchorSheet.getBalance() != null){
			query.setFloat("balance", anchorSheet.getBalance());	
		}
		if(anchorSheet.getAccumulativeTime() != null){
			query.setFloat("accumulativeTime", anchorSheet.getAccumulativeTime());			
		}
		if(anchorSheet.getVoiceChat() != null){
			query.setFloat("voiceChat", anchorSheet.getVoiceChat());					
		}
		if(anchorSheet.getVideoChat() != null){
			query.setFloat("videoChat", anchorSheet.getVideoChat());	
		}
		if(anchorSheet.getAnchorGlamour() != null){
			query.setFloat("anchorGlamour", anchorSheet.getAnchorGlamour());			
		}
		if(anchorSheet.getAnchorClass() != null){
			query.setString("anchorClass", anchorSheet.getAnchorClass());			
		}
		if(anchorSheet.getUserOccupation() != null){
			query.setString("userOccupation", anchorSheet.getUserOccupation());			
		}
		if(anchorSheet.getChatAbility() != null){
			query.setString("chatAbility", anchorSheet.getUserOccupation());					
		}
		if(anchorSheet.getIsHot() != null){
			query.setInteger("isHot", anchorSheet.getIsHot());	
		}
		if(anchorSheet.getIsRecommend() != null){
			query.setInteger("isRecommend", anchorSheet.getIsRecommend());			
		}
		if(anchorSheet.getIsForefront() != null){
			query.setInteger("isForefront", anchorSheet.getIsForefront());			
		}
		if(anchorSheet.getRecid() != null){
			query.setInteger("recid", anchorSheet.getRecid());
		}else{
			if(anchorSheet.getUserRecid() != null){
				query.setInteger("userRecid", anchorSheet.getUserRecid());
			}
		}
		
		
		Integer updateint = query.executeUpdate();
		if(updateint>0){
			return true;
		}else{
			return false;
		}	
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getAnchorStatusSheets(String pagesize, String currpage, String cxtj,
			AnchorStatusSheet anchorStatusSheet) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("select * from anchor_status_sheet as ass ");
		sbf.append(" where 1=1");
		if(anchorStatusSheet.getRecid() != null){
			sbf.append("  and ass.recid=:recid");
		}else{
			if(anchorStatusSheet.getUserRecid() != null){
				sbf.append("  and  ass.user_recid=:userRecid");
			}
		}
		if(anchorStatusSheet.getExamineStatus()!=null){
			sbf.append("  and  ass.examine_status=:examineStatus");
		}
		if(anchorStatusSheet.getCreateTime() != null){
			sbf.append(" and date_format(ass.create_time,'%Y-%m-%d') = :createTime ");
		} 
		if(cxtj != null){
			sbf.append(" and (ass.anchor_name like :cxtj  or ass.user_address like :cxtj");
			sbf.append(" or ass.user_occupation like :cxtj ");
			sbf.append( ")");
		}
		sbf.append( " order by ass.recid desc");
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
		if(anchorStatusSheet.getRecid() != null){
			query.setInteger("recid", anchorStatusSheet.getRecid());
		}else{
			if(anchorStatusSheet.getUserRecid() != null){
				query.setInteger("userRecid", anchorStatusSheet.getUserRecid());
			}
		}
		if(anchorStatusSheet.getExamineStatus()!=null){
			query.setInteger("examineStatus", anchorStatusSheet.getExamineStatus());
		}
		if(anchorStatusSheet.getCreateTime()!= null){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			 String createTime = formatter.format(anchorStatusSheet.getCreateTime());
			 query.setString("createTime", createTime);			 
		}
		if(cxtj != null){
			query.setString("cxtj", cxtj);
		}
		return query.list();
	}
	@Override
	public Integer getAnchorStatusSheetsCount(String pagesize, String currpage, String cxtj,
			AnchorStatusSheet anchorStatusSheet) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("select count(*) from anchor_status_sheet as ass ");
		sbf.append(" where 1=1");
		if(anchorStatusSheet.getRecid() != null){
			sbf.append("  and ass.recid=:recid");
		}else{
			if(anchorStatusSheet.getUserRecid() != null){
				sbf.append("  and  ass.user_recid=:userRecid");
			}
		}
		if(anchorStatusSheet.getExamineStatus()!=null){
			sbf.append("  and  ass.examine_status=:examineStatus");
		}
		if(anchorStatusSheet.getCreateTime() != null){
			sbf.append(" and date_format(ass.create_time,'%Y-%m-%d') = :createTime ");
		} 
		if(cxtj != null){
			sbf.append(" and (ass.anchor_name like :cxtj  or ass.user_address like :cxtj");
			sbf.append(" or ass.user_occupation like :cxtj ");
			sbf.append( ")");
		}
		sbf.append( " order by ass.recid desc");
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
		if(anchorStatusSheet.getRecid() != null){
			query.setInteger("recid", anchorStatusSheet.getRecid());
		}else{
			if(anchorStatusSheet.getUserRecid() != null){
				query.setInteger("userRecid", anchorStatusSheet.getUserRecid());
			}
		}
		if(anchorStatusSheet.getExamineStatus()!=null){
			query.setInteger("examineStatus", anchorStatusSheet.getExamineStatus());
		}
		if(anchorStatusSheet.getCreateTime()!= null){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			 String createTime = formatter.format(anchorStatusSheet.getCreateTime());
			 query.setString("createTime", createTime);			 
		}
		if(cxtj != null){
			query.setString("cxtj", cxtj);
		}
		return Integer.parseInt(query.list().get(0).toString());
	}

	@Override
	public Serializable saveAnchorStatusSheet(AnchorStatusSheet anchorStatusSheet) {
		anchorStatusSheet.setCreateTime(new Date());
		anchorStatusSheet.setExamineStatus(2);
		UserSheet us  = new UserSheet();
		us.setRecid(anchorStatusSheet.getUserRecid());
		us.setAnchorStatus(2);
		ud.UpdateUserSheet(us);
		Session  session=sessionFactory.getCurrentSession();  
	    return   session.save(anchorStatusSheet);  
	}

	@Override
	public boolean UpdateAnchorStatusSheet(AnchorStatusSheet anchorStatusSheet) {
		Session  session=sessionFactory.getCurrentSession(); 
		StringBuffer sbf = new StringBuffer();
		sbf.append(" Update anchor_status_sheet as ass set");
		boolean flag = true;
		if(anchorStatusSheet.getAnchorName() != null){
			sbf.append(" ass.anchor_name = :anchorName");
			flag = false;
		}
		if(anchorStatusSheet.getAnchorRecid() != null){
			if(flag){
				sbf.append(" ass.anchor_recid =:anchorRecid");
				flag = false;
			}else{
				sbf.append(" ,ass.anchor_recid =:anchorRecid");
			}			
		}
		if(anchorStatusSheet.getAnchorIdcord() != null){
			if(flag){
				sbf.append(" ass.anchor_idcord =:anchorIdcord");
				flag = false;
			}else{
				sbf.append(" ,ass.anchor_idcord =:anchorIdcord");
			}			
		}
		if(anchorStatusSheet.getExaminePhoto() != null){
			if(flag){
				sbf.append(" ass.examine_photo =:examinePhoto");
				flag = false;
			}else{
				sbf.append(" ,ass.examine_photo =:examinePhoto");
			}			
		}
		if(anchorStatusSheet.getExamineStatus() != null){
			if(flag){
				sbf.append(" ass.examine_status =:examineStatus");
				flag = false;
			}else{
				sbf.append(" ,ass.examine_status =:examineStatus");
			}			
		}
		if(anchorStatusSheet.getUserBirthday() != null){
			if(flag){
				sbf.append(" ass.user_birthday =:userBirthday");
				flag = false;
			}else{
				sbf.append(" ,ass.user_birthday =:userBirthday");
			}			
		}
		if(anchorStatusSheet.getUserAddress() != null){
			if(flag){
				sbf.append(" ass.user_address =:userAddress");
				flag = false;
			}else{
				sbf.append(" ,ass.user_address =:userAddress");
			}			
		}
		if(anchorStatusSheet.getUserOccupation() != null){
			if(flag){
				sbf.append(" ass.user_occupation =:userOccupation");
				flag = false;
			}else{
				sbf.append(" ,ass.user_occupation =:userOccupation");
			}			
		}
		if(anchorStatusSheet.getMyPhoto() != null){
			if(flag){
				sbf.append(" ass.my_photo =:myPhoto");
				flag = false;
			}else{
				sbf.append(" ,ass.my_photo =:myPhoto");
			}			
		}
		if(anchorStatusSheet.getChatAbility() != null){
			if(flag){
				sbf.append(" ass.chat_ability =:chatAbility");
				flag = false;
			}else{
				sbf.append(" ,ass.chat_ability =:chatAbility");
			}			
		}
		
		sbf.append(" where");
		if(anchorStatusSheet.getRecid() != null){
			sbf.append("  ass.recid=:recid");
		}else{
			if(anchorStatusSheet.getUserRecid() != null){
				sbf.append("  ass.user_recid=:userRecid");
			}
		}
		
		Query query = session.createSQLQuery (sbf.toString());
		
		if(anchorStatusSheet.getAnchorName() != null){
			query.setString("anchorName", anchorStatusSheet.getAnchorName());
		}
		if(anchorStatusSheet.getAnchorRecid() != null){
			query.setInteger("anchorRecid", anchorStatusSheet.getAnchorRecid());		
		}
		if(anchorStatusSheet.getAnchorIdcord() != null){
			query.setString("anchorIdcord", anchorStatusSheet.getAnchorIdcord());
		}
		if(anchorStatusSheet.getExaminePhoto() != null){
			query.setString("examinePhoto", anchorStatusSheet.getExaminePhoto());		
		}
		if(anchorStatusSheet.getExamineStatus() != null){
			query.setInteger("examineStatus", anchorStatusSheet.getExamineStatus());		
			if(anchorStatusSheet.getUserRecid()!=null){
				UserSheet us = new UserSheet();
				us.setRecid(anchorStatusSheet.getUserRecid());
				us.setAnchorStatus(anchorStatusSheet.getExamineStatus());
				ud.UpdateUserSheet(us);
			}
			
		}
		if(anchorStatusSheet.getUserBirthday() != null){
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String userBirthday = formatter.format(anchorStatusSheet.getUserBirthday());
			 query.setString("userBirthday",userBirthday);				
		}
		if(anchorStatusSheet.getUserAddress() != null){
			query.setString("userAddress",anchorStatusSheet.getUserAddress());			
		}
		if(anchorStatusSheet.getUserOccupation() != null){
			query.setString("userOccupation",anchorStatusSheet.getUserOccupation());				
		}
		if(anchorStatusSheet.getMyPhoto() != null){
			query.setString("myPhoto",anchorStatusSheet.getMyPhoto());			
		}
		if(anchorStatusSheet.getChatAbility() != null){
			query.setString("chatAbility",anchorStatusSheet.getChatAbility());			
		}
		
		if(anchorStatusSheet.getRecid() != null){
			query.setInteger("recid", anchorStatusSheet.getRecid());
		}else{
			if(anchorStatusSheet.getUserRecid() != null){
				query.setInteger("userRecid", anchorStatusSheet.getUserRecid());
			}
		}
		Integer updateint = query.executeUpdate();
		if(updateint>0){
			return true;
		}else{
			return false;
		}	
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getAnchorOnlines(String pagesize, String currpage, String cxtj, AnchorOnline anchorOnline) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" select aon.recid,us.recid as userr,ash.recid as aonrecid,aon.anchor_label as aonlb,ash.voice_chat as ashvc,");
		sbf.append(" ash.video_chat as ashvd,ash.anchor_class as ashac,aon.create_time as aonct,us.user_showname,");
		sbf.append(" aon.is_free as ashif,us.user_headimg from anchor_online as aon LEFT JOIN anchor_sheet as ash on aon.anchor_recid = ash.recid");
		sbf.append(" LEFT JOIN user_sheet as us on us.recid = ash.user_recid");
		
		sbf.append(" where 1=1");
		if(anchorOnline.getRecid() != null){
			sbf.append("  and aon.recid=:recid");
		}else{
			if(anchorOnline.getAnchorRecid() != null){
				sbf.append("  and  aon.anchor_online=:anchorRecid");
			}
		}
		if(anchorOnline.getIsFree() != null){
			sbf.append(" and aon.is_free =:isFree");
		}
		if(anchorOnline.getIsHot() != null){
			sbf.append(" order by ash.is_hot desc");
		}
		if(anchorOnline.getIsRecommend() != null){
			sbf.append(" order by ash.is_recommend desc");
		}
		
		sbf.append( " order by aon.recid desc");
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
		if(anchorOnline.getRecid() != null){
			query.setInteger("recid", anchorOnline.getRecid());
		}else{
			if(anchorOnline.getAnchorRecid() != null){
				query.setInteger("anchorRecid", anchorOnline.getAnchorRecid());
			}
		}
		if(anchorOnline.getIsFree() != null){
			query.setInteger("isFree", anchorOnline.getIsFree());
		}
		return query.list();
	}

	@Override
	public Serializable saveAnchorOnline(AnchorOnline anchorOnline) {
		Session  session=sessionFactory.getCurrentSession();  
	    return   session.save(anchorOnline);  
	}

	@Override
	public boolean UpdateAnchorOnline(AnchorOnline anchorOnline) {
		Session  session=sessionFactory.getCurrentSession(); 
		StringBuffer sbf = new StringBuffer();
		sbf.append(" Update anchor_online as aon set");
		boolean flag = true;
		if(anchorOnline.getAnchorLabel() != null){
			sbf.append(" aon.anchor_label = :anchorLabel");
			flag = false;
		}
		if(anchorOnline.getIsFree() != null){
			if(flag){
				sbf.append(" aon.is_free =:isFree");
				flag = false;
			}else{
				sbf.append(" ,aon.is_free =:isFree");
			}			
		}
		sbf.append(" where");
		if(anchorOnline.getRecid() != null){
			sbf.append("  aon.recid=:recid");
		}else{
			if(anchorOnline.getAnchorRecid() != null){
				sbf.append("  aon.anchor_recid=:anchorRecid");
			}
		}
		Query query = session.createSQLQuery (sbf.toString());
		
		if(anchorOnline.getAnchorLabel() != null){
			query.setString("anchorLabel", anchorOnline.getAnchorLabel());
		}
		if(anchorOnline.getIsFree() != null){
			query.setInteger("isFree",anchorOnline.getIsFree());  		
		}
		if(anchorOnline.getRecid() != null){
			query.setInteger("recid",anchorOnline.getRecid());  
		}else{
			if(anchorOnline.getAnchorRecid() != null){
				query.setInteger("anchorRecid",anchorOnline.getAnchorRecid());  
			}
		}
		Integer updateint = query.executeUpdate();
		if(updateint>0){
			return true;
		}else{
			return false;
		}	
	}

	@Override
	public boolean DeleteAnchorOnline(AnchorOnline anchorOnline) {
		Session  session=sessionFactory.getCurrentSession();  
		session.beginTransaction();
		StringBuffer sbf = new StringBuffer();
		sbf.append(" delete from anchor_online");
		sbf.append(" where");
		if(anchorOnline.getRecid() != null){
			sbf.append("  recid=:recid");
		}else{
			if(anchorOnline.getAnchorRecid() != null){
				sbf.append("  anchor_recid=:anchorRecid");
			}else{
				return false;
			}
		}
		Query query = session.createSQLQuery (sbf.toString());
		if(anchorOnline.getRecid() != null){
			query.setInteger("recid",anchorOnline.getRecid());  
		}else{
			if(anchorOnline.getAnchorRecid() != null){
				query.setInteger("anchorRecid",anchorOnline.getAnchorRecid());  
			}
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
