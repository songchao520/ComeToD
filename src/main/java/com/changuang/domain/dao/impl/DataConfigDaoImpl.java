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

import com.changuang.domain.dao.DataConfigDao;
import com.changuang.domain.entity.DataFixed;
import com.changuang.domain.entity.LabelSheet;

/**
* @author songc
* @version 创建时间：2017年12月22日 下午2:07:47
* @ClassName DataConfigDaoImpl
* @Description 类描述
*/
@Repository
@Transactional
public class DataConfigDaoImpl implements DataConfigDao {
	@Autowired
	SessionFactory  sessionFactory;

	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取标签
	 */
	@Override
	public Integer getLabelSheetsCount(String pagesize, String currpage, String cxtj,LabelSheet labelSheet){
		StringBuffer sbf = new StringBuffer();
		sbf.append("select count(*) from label_sheet ls");
		sbf.append(" where 1=1");
		if(labelSheet.getRecid()!=null){
			sbf.append(" and ls.recid=:recid");
		}
		if(cxtj != null){
			sbf.append(" and (ls.label_name like :cxtj ");
			sbf.append(" ");
			sbf.append( ")");
		}
		sbf.append( " order by ls.recid desc");
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
		if(labelSheet.getRecid()!=null){
			query.setInteger("recid",labelSheet.getRecid());  
		}
		if(cxtj != null){
			query.setString("cxtj","%"+cxtj+"%");	
		}
		return Integer.parseInt(query.list().get(0).toString());
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getLabelSheets(String pagesize, String currpage, String cxtj, LabelSheet labelSheet) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("select ls.* from label_sheet ls");
		sbf.append(" where 1=1");
		if(labelSheet.getRecid()!=null){
			sbf.append(" and ls.recid=:recid");
		}
		if(cxtj != null){
			sbf.append(" and (ls.label_name like :cxtj ");
			sbf.append(" ");
			sbf.append( ")");
		}
		sbf.append( " order by ls.recid desc");
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
		if(labelSheet.getRecid()!=null){
			query.setInteger("recid",labelSheet.getRecid());  
		}
		if(cxtj != null){
			query.setString("cxtj","%"+cxtj+"%");	
		}
		return query.list();
	}

	@Override
	public Serializable saveLabelSheet(LabelSheet labelSheet) {
		labelSheet.setCreateTime(new Date());
		Session  session=sessionFactory.getCurrentSession();  
	    return   session.save(labelSheet);  
	}

	@Override
	public boolean UpdateLabelSheet(LabelSheet labelSheet) {
		Session  session=sessionFactory.getCurrentSession(); 
		StringBuffer sbf = new StringBuffer();
		sbf.append(" Update label_sheet  set label_name = :labelName");
		sbf.append(" where 1=1 ");
		if(labelSheet.getRecid() != null){
			sbf.append("  and recid=:recid");
		}else{
			return false;
		}
		Query query = session.createSQLQuery (sbf.toString());
		if(labelSheet.getRecid() != null){
			query.setInteger("recid",labelSheet.getRecid());  
		}
		query.setString("labelName", labelSheet.getLabelName());	
		Integer updateint = query.executeUpdate();
		if(updateint>0){
			return true;
		}else{
			return false;
		}	
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getDataFixeds(String pagesize, String currpage, String cxtj, DataFixed dataFixed) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("select df.* from data_fixed df");
		sbf.append(" where 1=1");
		if(dataFixed.getRecid() != null){
			sbf.append(" and df.recid=:recid");
		}
		
		sbf.append( " order by df.recid desc");
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
		if(dataFixed.getRecid()!=null){
			query.setInteger("recid",dataFixed.getRecid());  
		}
		return query.list();
	}

	@Override
	public boolean UpdateDataFixed(DataFixed DataFixed) {
		Session  session=sessionFactory.getCurrentSession(); 
		StringBuffer sbf = new StringBuffer();
		sbf.append(" Update data_fixed  set ");
		boolean flag = true;
		if(DataFixed.getMinMoney() != null){
			sbf.append("min_money = :minMoney");
			flag = false;
		}
		if(DataFixed.getMoneyExchange() != null){
			if(flag){
				sbf.append(" money_exchange = :moneyExchange");
				flag = false;
			}else{
				sbf.append(" ,money_exchange = :moneyExchange");
			}			
		}
		sbf.append(" where 1=1 ");
		if(DataFixed.getRecid() != null){
			sbf.append("  and recid=:recid");
		}else{
			sbf.append("  and recid=1");
		}
		
		Query query = session.createSQLQuery (sbf.toString());
		if(DataFixed.getRecid() != null){
			query.setInteger("recid",DataFixed.getRecid());  
		}
		 if(DataFixed.getMinMoney() != null){
			 query.setFloat("minMoney", DataFixed.getMinMoney());	
		 }
		 if(DataFixed.getMoneyExchange() != null){
			 query.setFloat("moneyExchange", DataFixed.getMoneyExchange());	
		 }
		Integer updateint = query.executeUpdate();
		if(updateint>0){
			return true;
		}else{
			return false;
		}	
	}

}
