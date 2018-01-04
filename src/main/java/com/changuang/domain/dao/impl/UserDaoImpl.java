/**
 * 
 */
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

import com.changuang.domain.dao.UserDao;
import com.changuang.domain.entity.AnchorOnline;
import com.changuang.domain.entity.AnchorSheet;
import com.changuang.domain.entity.UserSheet;
import com.changuang.domain.entity.UserType;
import com.changuang.web.controller.LoginController;

/**
* @author songc
* @version 创建时间：2017年12月1日 下午3:02:41
* @ClassName UserDaoImpl
* @Description UserDao实现类
*/

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	
	@Autowired
	SessionFactory  sessionFactory;
	 
	@Override
	public Serializable saveUserType(UserType usertype) {
		  Session  session=sessionFactory.getCurrentSession();  
	      return   session.save(usertype);  
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getUserTypes(UserType usertype) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("select * from user_type ");
		Session  session=sessionFactory.getCurrentSession();  
		Query query = session.createSQLQuery (sbf.toString());
		return query.list();
	}

	@Override
	public Serializable saveUserSheet(UserSheet userSheet) {
		userSheet.setIsVip(0);
		userSheet.setUserStatus(0);
		userSheet.setUtypeRecid(1);
		float lg = 0;
		userSheet.setWealthAmount(lg);
		userSheet.setUserVip(-1);
		userSheet.setWealthGrade(0);
		userSheet.setAnchorStatus(1);
		userSheet.setUserCreatetime(new Date());
		
		if(userSheet.getUserShowname() == null){
			Integer s = (int)((Math.random()*9+1)*100000);
			userSheet.setUserShowname("user_"+s);
		}
		if(userSheet.getUserSource()==null){
			userSheet.setUserShowname("xitong");
		}
		if(userSheet.getUserLoginpassword()==null){
			userSheet.setUserLoginpassword("1");
		}
		userSheet.setUserCity("火星");
		Session  session=sessionFactory.getCurrentSession();  
	    return   session.save(userSheet); 
	}
	
	@Override
	public Integer getUserSheetsCount(String pagesize, String currpage, String cxtj, UserSheet userSheet){

		StringBuffer sbf = new StringBuffer();
		sbf.append("select count(*) ");
		sbf.append("  from user_sheet as us ");
		sbf.append("left join user_type as ut on us.utype_recid = ut.recid ");
		sbf.append("left join label_sheet as ls on us.user_label = ls.recid ");
		sbf.append("left join label_sheet as ls2 on us.user_labelt = ls2.recid ");
		sbf.append("left join label_sheet as ls3 on us.user_labels = ls3.recid ");
		sbf.append("where us.recid!=1000000 ");
		if(userSheet.getRecid() != null){
			sbf.append(" and us.recid = :recid");			
		}
		if(userSheet.getUserLoginname()!=null){
			sbf.append(" and us.user_loginname = :userLoginname");			
		}
		if(userSheet.getUserCreatetime() != null){
			sbf.append(" and date_format(us.user_createtime,'%Y-%m-%d') = :userCreatetime ");
		}
		if(userSheet.getUtypeRecid()!=null){
			sbf.append(" and us.utype_recid = :utypeRecid");			
		}
		if(cxtj != null){
			sbf.append(" and (us.user_loginname like :cxtj or us.user_showname like :cxtj or us.user_lastaddress like :cxtj");
			sbf.append(" or ls.label_name like :cxtj or ls2.label_name like :cxtj or ls3.label_name like :cxtj");
			sbf.append( ")");
		}
		sbf.append( " order by us.recid desc");
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
		if(userSheet.getRecid() != null){
			query.setInteger("recid",userSheet.getRecid());	
		}
		if(userSheet.getUserLoginname()!=null){
			query.setString("userLoginname",userSheet.getUserLoginname());			
		}
		if(userSheet.getUserCreatetime() != null){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			 String userCreatetime = formatter.format(userSheet.getUserCreatetime());
			 query.setString("userCreatetime", userCreatetime);			 
		}
		if(userSheet.getUtypeRecid()!=null){
			query.setInteger("utypeRecid",userSheet.getUtypeRecid());	
		}
		if(cxtj != null){
			query.setString("cxtj","%"+cxtj+"%");	
		}
		return Integer.parseInt(query.list().get(0).toString());
	
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List getUserSheets(String pagesize, String currpage, String cxtj, UserSheet userSheet) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("select ut.usertype_name,ls.label_name lb1,ls2.label_name lb2,ls3.label_name lb3,us.recid,us.user_loginname, ");
		sbf.append("us.user_showname,us.user_headimg,us.user_remarks, ");
		sbf.append("us.user_status,us.user_createtime,us.user_lasttime,us.user_lastaddress, ");
		sbf.append("us.user_tencent,us.user_vip,us.anchor_status,us.wealth_amount,us.wealth_grade,us.user_sex,us.is_vip ");
		sbf.append(" ,us.user_mobilephone,us.user_source,us.utype_recid,us.user_city from user_sheet as us ");
		sbf.append("left join user_type as ut on us.utype_recid = ut.recid ");
		sbf.append("left join label_sheet as ls on us.user_label = ls.recid ");
		sbf.append("left join label_sheet as ls2 on us.user_labelt = ls2.recid ");
		sbf.append("left join label_sheet as ls3 on us.user_labels = ls3.recid ");
		sbf.append("where us.recid!=1000000 ");
		if(userSheet.getRecid() != null){
			sbf.append(" and us.recid = :recid");			
		}
		if(userSheet.getUserLoginname()!=null){
			sbf.append(" and us.user_loginname = :userLoginname");			
		}
		if(userSheet.getUserCreatetime() != null){
			sbf.append(" and date_format(us.user_createtime,'%Y-%m-%d') = :userCreatetime ");
		}
		if(userSheet.getUtypeRecid()!=null){
			sbf.append(" and us.utype_recid = :utypeRecid");			
		}
		if(cxtj != null){
			sbf.append(" and (us.user_loginname like :cxtj or us.user_showname like :cxtj or us.user_lastaddress like :cxtj");
			sbf.append("  or ls.label_name like :cxtj or ls2.label_name like :cxtj or ls3.label_name like :cxtj");
			sbf.append( ")");
		}
		sbf.append( " order by us.recid desc");
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
		if(userSheet.getRecid() != null){
			query.setInteger("recid",userSheet.getRecid());	
		}
		if(userSheet.getUserLoginname()!=null){
			query.setString("userLoginname",userSheet.getUserLoginname());			
		}
		if(userSheet.getUserCreatetime() != null){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			 String userCreatetime = formatter.format(userSheet.getUserCreatetime());
			 query.setString("userCreatetime", userCreatetime);			 
		}
		if(userSheet.getUtypeRecid()!=null){
			query.setInteger("utypeRecid",userSheet.getUtypeRecid());		
		}
		if(cxtj != null){
			query.setString("cxtj","%"+cxtj+"%");	
		}
		return query.list();
	}
	
	@Override
	public boolean UpdateUserSheet(UserSheet userSheet){
		Session  session=sessionFactory.getCurrentSession(); 
		StringBuffer sbf = new StringBuffer();
		/*sbf.append("insert into user_sheet(utype_recid,user_loginname,user_showname,user_loginpassword,user_mobilephone,  ");
		sbf.append("user_headimg,user_remarks,user_status,user_createtime,user_lasttime  ");
		sbf.append("i,user_lastaddress,user_tencent,user_vip,anchor_status,wealth_amount,wealth_grade,user_sex,is_vip,  ");
		sbf.append("user_label,user_labelt,user_labels) select utype_recid,user_loginname,user_showname,user_loginpassword,  ");
		sbf.append("user_mobilephone,user_headimg,user_remarks,user_status,user_createtime,user_lasttime  ");
		sbf.append(",user_lastaddress,user_tencent,user_vip,anchor_status,wealth_amount,wealth_grade,user_sex,is_vip,  ");
		sbf.append("user_label,user_labelt,user_labels from user_sheet where recid =:recid  ");
		session.beginTransaction();*/
		Query query = null;
		/*if(userSheet.getUserLastaddress() == null){
		query = session.createSQLQuery (sbf.toString());
		query.setInteger("recid", userSheet.getRecid());
		query.executeUpdate();
		}*/
		
		sbf.setLength(0);
		sbf.append(" Update user_sheet as us set");
		boolean flag = true;
		if(userSheet.getUserShowname()!=null){
			sbf.append(" us.user_showname =:userShowname");
			flag = false;
		}
		if(userSheet.getUtypeRecid() != null){
			if(flag){
				sbf.append(" us.utype_recid =:utypeRecid");
				flag = false;
			}else{
				sbf.append(" ,us.utype_recid =:utypeRecid");
			}			
		}
		if(userSheet.getUserLoginpassword() != null){
			if(flag){
				sbf.append(" us.user_loginpassword =:userLoginpassword");
				flag = false;
			}else{
				sbf.append(" ,us.user_loginpassword =:userLoginpassword");
			}			
		}
		if(userSheet.getUserMobilephone() != null && userSheet.getRecid() != null){
			if(flag){
				sbf.append(" us.user_mobilephone =:userMobilephone");
				flag = false;
			}else{
				sbf.append(" ,us.user_mobilephone =:userMobilephone");
			}			
		}
		if(userSheet.getUserHeadimg() != null){
			if(flag){
				sbf.append(" us.user_headimg =:userHeadimg");
				flag = false;
			}else{
				sbf.append(" ,us.user_headimg =:userHeadimg");
			}			
		}
		if(userSheet.getUserRemarks() != null){
			if(flag){
				sbf.append(" us.user_remarks =:userRemarks");
				flag = false;
			}else{
				sbf.append(" ,us.user_remarks =:userRemarks");
			}			
		}
		if(userSheet.getUserStatus() != null){
			if(flag){
				sbf.append(" us.user_status =:userStatus");
				flag = false;
			}else{
				sbf.append(" ,us.user_status =:userStatus");
			}			
		}
		if(userSheet.getUserLasttime() != null){
			if(flag){
				sbf.append(" us.user_lasttime =:userLasttime");
				flag = false;
			}else{
				sbf.append(" ,us.user_lasttime =:userLasttime");
			}			
		}
		if(userSheet.getUserLastaddress() != null){
			if(flag){
				sbf.append(" us.user_lastaddress =:userLastaddress");
				flag = false;
			}else{
				sbf.append(" ,us.user_lastaddress =:userLastaddress");
			}			
		}
		if(userSheet.getUserVip() != null){
			if(flag){
				sbf.append(" us.user_vip =:userVip");
				flag = false;
			}else{
				sbf.append(" ,us.user_vip =:userVip");
			}			
		}
		if(userSheet.getAnchorStatus() != null){
			if(flag){
				sbf.append(" us.anchor_status =:anchorStatus");
				flag = false;
			}else{
				sbf.append(" ,us.anchor_status =:anchorStatus");
			}			
		}
		if(userSheet.getWealthAmount() != null){
			if(flag){
				sbf.append(" us.wealth_amount =:wealthAmount");
				flag = false;
			}else{
				sbf.append(" ,us.wealth_amount =:wealthAmount");
			}			
		}
		if(userSheet.getWealthGrade() != null){
			if(flag){
				sbf.append(" us.wealth_grade =:wealthGrade");
				flag = false;
			}else{
				sbf.append(" ,us.wealth_grade =:wealthGrade");
			}			
		}
		if(userSheet.getUserSex() != null){
			if(flag){
				sbf.append(" us.user_sex =:userSex");
				flag = false;
			}else{
				sbf.append(" ,us.user_sex =:userSex");
			}			
		}
		if(userSheet.getIsVip() != null){
			if(flag){
				sbf.append(" us.is_vip =:isVip");
				flag = false;
			}else{
				sbf.append(" ,us.is_vip =:isVip");
			}			
		}
		if(userSheet.getUserLabel() != null){
			if(flag){
				sbf.append(" us.user_label =:userLabel");
				flag = false;
			}else{
				sbf.append(" ,us.user_label =:userLabel");
			}			
		}
		if(userSheet.getUserLabelt() != null){
			if(flag){
				sbf.append(" us.user_labelt =:userLabelt");
				flag = false;
			}else{
				sbf.append(" ,us.user_labelt =:userLabelt");
			}			
		}
		if(userSheet.getUserLabels() != null){
			if(flag){
				sbf.append(" us.user_labels =:userLabels");
				flag = false;
			}else{
				sbf.append(" ,us.user_labels =:userLabels");
			}			
		}
		if(userSheet.getUserCity() != null){
			if(flag){
				sbf.append(" us.user_city =:userCity");
				flag = false;
			}else{
				sbf.append(" ,us.user_city =:userCity");
			}			
		}
		sbf.append(" where 1=1");
		if(userSheet.getRecid()!=null){
			sbf.append(" and us.recid=:recid");
		}else{
			if(userSheet.getUserMobilephone()!=null){
				sbf.append(" and us.user_mobilephone=:userMobilephone");
			}
		}
		
		
		query = session.createSQLQuery (sbf.toString());
		if(userSheet.getUserShowname()!=null){
			query.setString("userShowname", userSheet.getUserShowname());
		}
		if(userSheet.getUtypeRecid() != null){
			query.setInteger("utypeRecid", userSheet.getUtypeRecid());		
		}
		if(userSheet.getUserLoginpassword()!=null){
			query.setString("userLoginpassword", userSheet.getUserLoginpassword());
		}
		if(userSheet.getUserMobilephone() != null  && userSheet.getRecid() != null){
			query.setString("userMobilephone", userSheet.getUserMobilephone());
		}
		if(userSheet.getUserHeadimg()!=null){
			query.setString("userHeadimg", userSheet.getUserHeadimg());
		}
		if(userSheet.getUserRemarks()!=null){
			query.setString("userRemarks", userSheet.getUserRemarks());
		}
		if(userSheet.getUserStatus()!=null){
			query.setInteger("userStatus", userSheet.getUserStatus());
			if(userSheet.getUserStatus() == 1 && userSheet.getRecid()!=null){
				LoginController.sessionids.put(userSheet.getRecid()+"", userSheet.getRecid()+"");
			}
		}
		
		 if(userSheet.getUserLasttime() != null){
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String userLasttime = formatter.format(userSheet.getUserLasttime());
			 query.setString("userLasttime", userLasttime);			 
		 }
		 if(userSheet.getUserLastaddress() != null){
			 query.setString("userLastaddress", userSheet.getUserLastaddress());	
		 }
		 if(userSheet.getUserVip() != null){
			 query.setInteger("userVip", userSheet.getUserVip());	
		 }
		 if(userSheet.getAnchorStatus() != null){
			 query.setInteger("anchorStatus", userSheet.getAnchorStatus());	
		 }
		 if(userSheet.getWealthAmount() != null){
			 query.setFloat("wealthAmount", userSheet.getWealthAmount());	
		 }
		 if(userSheet.getWealthGrade() != null){
			 query.setInteger("wealthGrade", userSheet.getWealthGrade());	
		 }
		 if(userSheet.getUserSex() != null){
			 query.setInteger("userSex", userSheet.getUserSex());	
		 }
		 if(userSheet.getIsVip() != null){
			 query.setInteger("isVip", userSheet.getIsVip());	
		 }
		 if(userSheet.getUserLabel() != null){
			 query.setString("userLabel", userSheet.getUserLabel());			
		 }
		 if(userSheet.getUserLabelt() != null){
			 query.setString("userLabelt", userSheet.getUserLabelt());			
		 }
		 if(userSheet.getUserLabels() != null){
			 query.setString("userLabels", userSheet.getUserLabels());			
		 }
		 if(userSheet.getUserCity() != null){
			 query.setString("userCity", userSheet.getUserCity());			
			}
		 if(userSheet.getRecid()!=null){
			 query.setInteger("recid", userSheet.getRecid());
		 }else{
			 if(userSheet.getUserMobilephone()!=null){
				 query.setString("userMobilephone", userSheet.getUserMobilephone());
			 }
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
	public String UserloginVerification(UserSheet userSheet) {
		StringBuffer sbf = new StringBuffer();
		 sbf.append("select count(*) from user_sheet as us  where 1=1");
		 if(userSheet.getUserLoginname() != null){
			 sbf.append("  and us.user_loginname = :userLoginname and us.user_status =1 ");
		 }
		 Session  session=sessionFactory.getCurrentSession();  
		Query query = session.createSQLQuery (sbf.toString());
		if(userSheet.getUserLoginname() != null){
			query.setString("userLoginname",userSheet.getUserLoginname());	
		}
		List results = query.list();
		int lg = Integer.parseInt(results.get(0).toString());
		if(lg>0){
			return "0";
		}
		sbf.setLength(0);
		 sbf.append("select us.recid,us.anchor_status from user_sheet as us  where 1=1");
		 if(userSheet.getUserLoginname() != null && userSheet.getUserLoginpassword()!=null){
			 sbf.append("  and us.user_loginname = :userLoginname and us.user_loginpassword =:userLoginpassword ");
		 }else{
			 return "-1";
		 }
		 session=sessionFactory.getCurrentSession();  
		query = session.createSQLQuery (sbf.toString());
		if(userSheet.getUserLoginname() != null){
			query.setString("userLoginname",userSheet.getUserLoginname());	
		}
		if(userSheet.getUserLoginpassword() != null){
			query.setString("userLoginpassword",userSheet.getUserLoginpassword());	
		}
		results = query.list();
		if(results.size()>0){
			Object[] object  = (Object[]) results.get(0);
			 if(object[0]!=null){
				 lg = Integer.parseInt(object[0]+"");
				 if(object[1] != null){
					 int ans =  Integer.parseInt(object[1]+"");
					 if(ans == 3){
						 AnchorDaoImpl adl = new AnchorDaoImpl();
						 AnchorSheet as = new AnchorSheet();
						 as.setRecid(lg);
						 Object[] objects  = (Object[]) adl.getAnchorSheets(null, null, null, as,null).get(0);
						 AnchorOnline anchorOnline = new AnchorOnline();
						 anchorOnline.setAnchorRecid((Integer) objects[0]);
						 anchorOnline.setAnchorClass((Integer) object[15]);
						 anchorOnline.setAnchorLabel((String) object[19]);
						 anchorOnline.setIsHot((Integer) object[20]);
						 anchorOnline.setIsFree(1);
						 anchorOnline.setIsRecommend((Integer) object[21]);
						 adl.saveAnchorOnline(anchorOnline);
					 }
				 }
				
				 return lg+"";
			 }
		}
		 
		
		sbf.setLength(0);
		 sbf.append("select us.recid,us.anchor_status from user_sheet as us  where 1=1");
		 if(userSheet.getUserLoginname() != null){
			 sbf.append("  and us.user_mobilephone = :userLoginname and user_loginpassword =:userLoginpassword ");
		 }
		 session=sessionFactory.getCurrentSession();  
		query = session.createSQLQuery (sbf.toString());
		if(userSheet.getUserLoginname() != null){
			query.setString("userLoginname",userSheet.getUserLoginname());	
		}
		if(userSheet.getUserLoginpassword() != null){
			query.setString("userLoginpassword",userSheet.getUserLoginpassword());	
		}
		results = query.list();
		if(results.size()>0){
		Object[] object  = (Object[]) results.get(0);
		 if(object[0]!=null){
			 lg = Integer.parseInt(object[0]+"");
			 if(object[1] != null){
				 int ans =  Integer.parseInt(object[1]+"");
				 if(ans == 3){
					 AnchorDaoImpl adl = new AnchorDaoImpl();
					 AnchorSheet as = new AnchorSheet();
					 as.setRecid(lg);
					 Object[] objects  = (Object[]) adl.getAnchorSheets(null, null, null, as,null).get(0);
					 AnchorOnline anchorOnline = new AnchorOnline();
					 anchorOnline.setAnchorRecid((Integer) objects[0]);
					 anchorOnline.setAnchorClass((Integer) object[15]);
					 anchorOnline.setAnchorLabel((String) object[19]);
					 anchorOnline.setIsHot((Integer) object[20]);
					 anchorOnline.setIsFree(1);
					 anchorOnline.setIsRecommend((Integer) object[21]);
					 adl.saveAnchorOnline(anchorOnline);
				 }
			 }
		 }
			 return lg+"";
		 }
		return "-1";
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean LoginNameVerification(String loginname) {
		StringBuffer sbf = new StringBuffer();
		 sbf.append("select count(*) from user_sheet as us  where 1=1");
		 if(loginname != null){
			 sbf.append("  and us.user_mobilephone = :userLoginname ");
		 }
		 Session  session=sessionFactory.getCurrentSession();  
		Query query = session.createSQLQuery (sbf.toString());
		if(loginname != null){
			query.setString("userLoginname",loginname);	
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
	public boolean LoginUserVerification(String loginname) {
		StringBuffer sbf = new StringBuffer();
		 sbf.append("select count(*) from user_sheet as us  where 1=1");
		 if(loginname != null){
			 sbf.append("  and us.user_loginname = :userLoginname and us.user_source!='xitong' ");
		 }
		 Session  session=sessionFactory.getCurrentSession();  
		Query query = session.createSQLQuery (sbf.toString());
		if(loginname != null){
			query.setString("userLoginname",loginname);	
		}
		List results = query.list();
		int lg = Integer.parseInt(results.get(0).toString());
		if(lg>0){
			return false;
		}else{
			return true;
		}
	}

}
