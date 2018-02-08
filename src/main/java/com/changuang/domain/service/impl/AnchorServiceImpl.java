package com.changuang.domain.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.changuang.domain.dao.AnchorDao;
import com.changuang.domain.dao.HomeUserDao;
import com.changuang.domain.dao.OperationDao;
import com.changuang.domain.entity.AnchorOnline;
import com.changuang.domain.entity.AnchorSheet;
import com.changuang.domain.entity.AnchorStatusSheet;
import com.changuang.domain.entity.FollowSheet;
import com.changuang.domain.entity.HomeUser;
import com.changuang.domain.service.AnchorService;

/**
* @author songc
* @version 创建时间：2017年12月4日 上午11:17:33
* @ClassName AnchorService
* @Description 主播审核表及主播表业务逻辑实现类
*/
@Service
@Transactional
public class AnchorServiceImpl implements AnchorService {
	@Autowired 
	AnchorDao anchorDao;
	@Autowired 
	OperationDao operationDao;
	@Autowired
	HomeUserDao homeUserDao;
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取主播
	 */
	public Integer getAnchorSheetsCount(String pagesize, String currpage, String cxtj, AnchorSheet anchorSheet,String sortZiduan){
		return anchorDao.getAnchorSheetsCount(pagesize, currpage, cxtj, anchorSheet,sortZiduan);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getAnchorSheets(String pagesize, String currpage, String cxtj, AnchorSheet anchorSheet,String sortZiduan) {
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist = anchorDao.getAnchorSheets(pagesize, currpage, cxtj, anchorSheet,sortZiduan);
		 if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("recid",object[0] );
				 map.put("userRecid",object[1]!=null ?object[1]:"" );
				 map.put("accumulativeGift",object[2]!=null ?object[2]:"" );
				 map.put("beconsumedAmount",object[3]!=null ?object[3]:"" );
				 map.put("amountOfcash",object[4]!=null ?object[4]:"" );
				 map.put("balance",object[5]!=null ?object[5]:"" );
				 map.put("anchorGrade",object[6]!=null ?object[6]:"" );
				 map.put("accumulativeTime",object[7]!=null ?object[7]:"" );
				 if(object[8]!=null){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String createtime = formatter.format(object[8]);
					 map.put("createTime" ,createtime );
				 }else{
					 map.put("createTime" ,"" );
				 }
				 map.put("userName",object[9]!=null ?object[9]:"" );
				 map.put("voiceChat",object[12]!=null ?object[12]:"" );
				 map.put("videoChat",object[13]!=null ?object[13]:"" );
				 map.put("anchorGlamour",object[14]!=null ?object[14]:"" );
				 map.put("anchorClass",object[15]!=null ?object[15]:"" );
				 map.put("userOccupation",object[17]!=null ?object[17]:"" );
				 map.put("chatAbility",object[19]!=null ?object[19]:"" );
				 map.put("isHot",object[20]!=null ?object[20]:"" );
				 map.put("isRecommend",object[21]!=null ?object[21]:"" );
				 map.put("isForefront",object[22]!=null ?object[22]:"" );
				 map.put("smallPhoto",object[23]!=null ?object[23]:"" );
				 amp.add(map);
			 }
		 }
		return amp;
	}

	@Override
	public Serializable saveAnchorSheet(AnchorSheet anchorSheet) {
		return anchorDao.saveAnchorSheet(anchorSheet);
	}

	@Override
	public boolean UpdateAnchorSheet(AnchorSheet anchorSheet) {
		return anchorDao.UpdateAnchorSheet(anchorSheet);
	}
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取审核主播表总数
	 */
	public Integer getAnchorStatusSheetsCount(String pagesize, String currpage, String cxtj,
			AnchorStatusSheet anchorStatusSheet){
		return anchorDao.getAnchorStatusSheetsCount(pagesize, currpage, cxtj, anchorStatusSheet);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getAnchorStatusSheets(String pagesize, String currpage, String cxtj,
			AnchorStatusSheet anchorStatusSheet) {
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist = anchorDao.getAnchorStatusSheets(pagesize, currpage, cxtj, anchorStatusSheet);
		 if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("recid",object[0] );
				 map.put("userRecid",object[1]!=null ?object[1]:"" );
				 map.put("anchorRecid",object[2]!=null ?object[2]:"" );
				 map.put("anchorName",object[3]!=null ?object[3]:"" );
				 map.put("anchorIdcord",object[4]!=null ?object[4]:"" );
				 map.put("examinePhoto",object[5]!=null ?object[5]:"" );
				 map.put("examineStatus",object[6]!=null ?object[6]:"" );
				 if(object[7]!=null){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String createtime = formatter.format(object[7]);
					 map.put("createTime" ,createtime );
				 }else{
					 map.put("createTime" ,"" );
				 }
				 if(object[8]!=null){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					 String userBirthday = formatter.format(object[8]);
					 map.put("userBirthday" ,userBirthday );
				 }else{
					 map.put("userBirthday" ,"" );
				 }
				
				 map.put("userAddress",object[9]!=null ?object[9]:"" );
				 map.put("userOccupation",object[10]!=null ?object[10]:"" );
				 map.put("myPhoto",object[11]!=null ?object[11]:"" );
				 map.put("chatAbility",object[12]!=null ?object[12]:"" );
				 map.put("smallPhoto",object[13]!=null ?object[13]:"" );
				 amp.add(map);
			 }
		 }
		return amp;
	}

	@Override
	public Serializable saveAnchorStatusSheet(AnchorStatusSheet anchorStatusSheet) {
		return anchorDao.saveAnchorStatusSheet(anchorStatusSheet);
	}

	@Override
	public boolean UpdateAnchorStatusSheet(AnchorStatusSheet anchorStatusSheet) {
		return anchorDao.UpdateAnchorStatusSheet(anchorStatusSheet);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getAnchorOnlines(String pagesize, String currpage, String cxtj, AnchorOnline anchorOnline,Integer thisRecid) {
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist = anchorDao.getAnchorOnlines(pagesize, currpage, cxtj, anchorOnline);
		 if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("recid",object[0] );
				 HomeUser homeUser = new HomeUser();
				 if(object[0] != null){
					 homeUser.setHomeRecid((Integer) object[0]);
					 Integer homeUsersNum = homeUserDao.getHomeUsersCount(pagesize, currpage, cxtj, homeUser);
					 map.put("homeUsersNum",homeUsersNum );
				 }
				 
				 map.put("userRecid",object[1]!=null ?object[1]:"" );
				 map.put("anchorRecid",object[2]!=null ?object[2]:"" );
				 map.put("userCity",object[3]!=null ?object[3]:"" );
				 map.put("anchorVc",object[4]!=null ?object[4]:"" );
				 map.put("anchorVd",object[5]!=null ?object[5]:"" );
				 map.put("anchorClass",object[6]!=null ?object[6]:"" );
				 if(object[7]!=null){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String createtime = formatter.format(object[7]);
					 map.put("createTime" ,createtime );
				 }else{
					 map.put("createTime" ,"" );
				 }
				
				 map.put("userShowname",object[8]!=null ?object[8]:"" );
				 map.put("anchorIsFree",object[9]!=null ?object[9]:"" );
				 map.put("userHeadimg",object[10]!=null ?object[10]:"" );
				 map.put("LabelOne",object[11]!=null ?object[11]:"" );
				 map.put("LabelTwo",object[12]!=null ?object[12]:"" );
				 map.put("LabelThere",object[13]!=null ?object[13]:"" );
				 map.put("smallPhoto",object[14]!=null ?object[14]:"" );
				 map.put("bigPhoto",object[15]!=null ?object[15]:"" );
				 map.put("userSex",object[16]!=null ?object[16]:"" );
				 map.put("homeName",object[17]!=null ?object[17]:"" );
				 map.put("homeNotice",object[18]!=null ?object[18]:"" );
				 map.put("userRecid",object[19]!=null ?object[19]:"" );
				 map.put("leanCloud",object[20]!=null ?object[20]:"" );
				 map.put("labelNameOne",object[21]!=null ?object[21]:"" );
				 map.put("labelNameTwo",object[22]!=null ?object[22]:"" );
				 map.put("labelNameThree",object[23]!=null ?object[23]:"" );
				 map.put("userNum",object[24]!=null ?object[24]:"" );
				 if(thisRecid != null){
					 FollowSheet followSheet = new FollowSheet();
					 followSheet.setUserRecid(thisRecid);
					 followSheet.setAnchorRecid((Integer) object[19]);
					 Integer ints = operationDao.getFollowSheetsCount(pagesize, currpage, cxtj, followSheet);
					 if(ints>0){
						 map.put("isFollow",1 );
					 }else{
						 map.put("isFollow",0 );
					 }
				 }
				 
				 amp.add(map);
			 }
		 }
		return amp;
	}

	@Override
	public Serializable saveAnchorOnline(AnchorOnline anchorOnline) {
		return anchorDao.saveAnchorOnline(anchorOnline);
	}

	@Override
	public boolean UpdateAnchorOnline(AnchorOnline anchorOnline) {
		return anchorDao.UpdateAnchorOnline(anchorOnline);
	}

	@Override
	public boolean DeleteAnchorOnline(AnchorOnline anchorOnline) {
		return anchorDao.DeleteAnchorOnline(anchorOnline);
	}

}
