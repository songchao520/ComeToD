package com.changuang.domain.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.changuang.domain.dao.GiftDao;
import com.changuang.domain.entity.GiftSheet;
import com.changuang.domain.entity.RewardSheet;
import com.changuang.domain.service.GiftService;

/**
* @author songc
* @version 创建时间：2017年12月5日 下午5:16:12
* @ClassName GiftServiceImpl
* @Description 礼物表和打赏礼物表业务逻辑的实现类
*/
@Service
@Transactional
public class GiftServiceImpl implements GiftService {
	@Autowired
	GiftDao giftDao;

	@SuppressWarnings("rawtypes")
	@Override
	public List getGiftSheets(String pagesize, String currpage, String cxtj, GiftSheet giftSheet) {
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist = giftDao.getGiftSheets(pagesize, currpage, cxtj, giftSheet);
		 if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("recid",object[0] );
				 map.put("giftName",object[1]!=null ?object[1]:"" );
				 map.put("giftMoney",object[2]!=null ?object[2]:"" );
				 if(object[3]!=null){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String createtime = formatter.format(object[3]);
					 map.put("createTime" ,createtime );
				 }else{
					 map.put("createTime" ,"" );
				 }
				 map.put("giftPath",object[4]!=null ?object[4]:"" );
				 map.put("isShow",object[5]!=null ?object[5]:"" );
				 map.put("continuous",object[6]!=null ?object[6]:"" );
				 amp.add(map);
			 }
		 }
		return amp;
	}

	@Override
	public Serializable saveGiftSheet(GiftSheet giftSheet) {
		return giftDao.saveGiftSheet(giftSheet);
	}

	@Override
	public boolean UpdateGiftSheet(GiftSheet giftSheet) {
		return giftDao.UpdateGiftSheet(giftSheet);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getRewardSheets(String pagesize, String currpage, String cxtj, RewardSheet rewardSheet) {
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist = giftDao.getRewardSheets(pagesize, currpage, cxtj, rewardSheet);
		 if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("userShowname",object[0]!=null ?object[0]:"" );
				 map.put("anchorShowname",object[1]!=null ?object[1]:"" );
				 map.put("recid",object[2]!=null ?object[2]:"");
				 map.put("userRecid",object[3]!=null ?object[3]:"" );
				 map.put("anchorRecid",object[4]!=null ?object[4]:"");
				 if(object[5]!=null){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String createtime = formatter.format(object[5]);
					 map.put("createTime" ,createtime );
				 }else{
					 map.put("createTime" ,"" );
				 }
				 map.put("rewardAmount",object[6]!=null ?object[6]:"" );				
				 map.put("giftRecid",object[7]!=null ?object[7]:"" );
				 map.put("giftNumber",object[8]!=null ?object[8]:"" );
				 map.put("giftName",object[9]!=null ?object[9]:"" );
				 amp.add(map);
			 }
		 }
		return amp;
	}

	@Override
	public Serializable saveRewardSheet(RewardSheet rewardSheet) {
		return giftDao.saveRewardSheet(rewardSheet);
	}

	@Override
	public boolean UpdateRewardSheet(RewardSheet rewardSheet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer getGiftSheetsCount(String pagesize, String currpage, String cxtj, GiftSheet giftSheet) {
		return giftDao.getGiftSheetsCount(pagesize, currpage, cxtj, giftSheet);
	}

	@Override
	public Integer getRewardSheetsCount(String pagesize, String currpage, String cxtj, RewardSheet rewardSheet) {
		return giftDao.getRewardSheetsCount(pagesize, currpage, cxtj, rewardSheet);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getRankList(String pagesize, String currpage, String cxtj,RewardSheet rewardSheet){
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist = giftDao.getRankList(pagesize, currpage, cxtj, rewardSheet);
		 if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("userRecid",object[0]!=null ?object[0]:"" );
				 map.put("userShowname",object[1]!=null ?object[1]:"" );
				 map.put("userHeadimg",object[2]!=null ?object[2]:"");
				 map.put("moneySum",object[3]!=null ?object[3]:0 );
				 map.put("wealthGrade",object[4]!=null ?object[4]:"");
				 amp.add(map);
			 }
		 }
		 return amp;
	}
}
