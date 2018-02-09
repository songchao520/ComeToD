package com.changuang.domain.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.changuang.domain.dao.HomeUserDao;
import com.changuang.domain.dao.OperationDao;
import com.changuang.domain.entity.CommentSheet;
import com.changuang.domain.entity.FollowSheet;
import com.changuang.domain.entity.ReplySheet;
import com.changuang.domain.service.OperationService;

/**
* @author songc
* @version 创建时间：2017年12月7日 下午2:16:05
* @ClassName OperationServiceImpl
* @Description 用户关注，评论回复的业务逻辑实现类
*/
@Service
@Transactional
public class OperationServiceImpl implements OperationService {
	@Autowired
	OperationDao operatioDao;
	@Autowired
	HomeUserDao homeUserDao;
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取用户关注总数
	 */
	@Override
	public Integer getFollowSheetsCount(String pagesize, String currpage, String cxtj,FollowSheet followSheet){
		return operatioDao.getFollowSheetsCount(pagesize, currpage, cxtj, followSheet);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getFollowSheets(String pagesize, String currpage, String cxtj, FollowSheet followSheet) {
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist = operatioDao.getFollowSheets(pagesize, currpage, cxtj, followSheet);
		 if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("recid",object[0] );
				 map.put("userRecid",object[1]!=null ?object[1]:"" );
				 map.put("followUserrecid",object[2]!=null ?object[2]:"" );
				 map.put("userShowname",object[3]!=null ?object[3]:"" );
				 map.put("userHeadimg",object[4]!=null ?object[4]:"" );
				 map.put("userSex",object[5]!=null ?object[5]:"" );
				 if(cxtj!=null && cxtj.equals("chakanGuanZhu")){
					 if(object[6]!=null){
						 map.put("isStartAnchor",1);
						 map.put("homeRecid",object[6]);
						 map.put("anchorRecid",object[7]);
						 map.put("userNum",object[8]);
						 map.put("anchorClass",object[9]);
					 }else{
						 map.put("isStartAnchor",0);
					 }
				 }
				 amp.add(map);
			 }
		 }
		return amp;
	}

	@Override
	public Serializable saveFollowSheet(FollowSheet followSheet) {
		return operatioDao.saveFollowSheet(followSheet);
	}

	@Override
	public boolean DeleteFollowSheet(FollowSheet followSheet) {
		return operatioDao.DeleteFollowSheet(followSheet);
	}
	@Override
	public Integer getCommentSheetsCount(String pagesize, String currpage, String cxtj,CommentSheet commentSheet){
		return operatioDao.getCommentSheetsCount(pagesize, currpage, cxtj, commentSheet);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getCommentSheets(String pagesize, String currpage, String cxtj, CommentSheet commentSheet) {
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist = operatioDao.getCommentSheets(pagesize, currpage, cxtj, commentSheet);
		 if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("recid",object[0] );
				 map.put("userRecid",object[1]!=null ?object[1]:"" );
				 if(object[2]!=null){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String createtime = formatter.format(object[2]);
					 map.put("createTime" ,createtime );
				 }else{
					 map.put("createTime" ,"" );
				 }
				 map.put("dynamicRecid",object[3]!=null ?object[3]:"" );
				 map.put("commentContent",object[4]!=null ?object[4]:"" );
				 map.put("userShowname",object[5]!=null ?object[5]:"" );
				 map.put("userHeadimg",object[6]!=null ?object[6]:"" );
				 map.put("userLoginname",object[7]!=null ?object[7]:"" );
				 map.put("userSex",object[8]!=null ?object[8]:"" );
				 map.put("userWealthGrade",object[9]!=null ?object[9]:"" );
				 amp.add(map);
			 }
		 }
		return amp;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getCommentSheetsAndReply(String pagesize, String currpage, String cxtj, CommentSheet commentSheet) {
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist = operatioDao.getCommentSheets(pagesize, currpage, cxtj, commentSheet);
		 if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("recid",object[0] );
				 map.put("userRecid",object[1]!=null ?object[1]:"" );
				 if(object[2]!=null){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String createtime = formatter.format(object[2]);
					 map.put("createTime" ,createtime );
				 }else{
					 map.put("createTime" ,"" );
				 }
				 map.put("dynamicRecid",object[3]!=null ?object[3]:"" );
				 map.put("commentContent",object[4]!=null ?object[4]:"" );
				 map.put("userShowname",object[5]!=null ?object[5]:"" );
				 map.put("userHeadimg",object[6]!=null ?object[6]:"" );
				 map.put("userLoginname",object[7]!=null ?object[7]:"" );
				 map.put("userSex",object[8]!=null ?object[8]:"" );
				 map.put("userWealthGrade",object[9]!=null ?object[9]:"" );
				 ReplySheet replySheet = new ReplySheet();
				 replySheet.setCommentRecid((Integer) object[0]);
				 ArrayList<HashMap<String, Object>> amps = new ArrayList<>();
				 List alists = operatioDao.getReplySheets(pagesize, currpage, cxtj, replySheet);
				 if(alists != null && alists.size()>0){
					 for(int j = 0; j < alists.size();j++){
						 Object[] objects = (Object[])alists.get(j);
						 HashMap< String, Object> maps = new HashMap<>();
						 maps.put("recid",objects[0] );
						 maps.put("commentRecid",objects[1]!=null ?objects[1]:"" );
						 if(objects[2]!=null){
							 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							 String createtime = formatter.format(objects[2]);
							 maps.put("createTime" ,createtime );
						 }else{
							 maps.put("createTime" ,"" );
						 }
						 maps.put("userRecid",objects[3]!=null ?objects[3]:"" );
						 maps.put("replyContent",objects[4]!=null ?objects[4]:"" );
						 maps.put("userShowname",objects[5]!=null ?objects[5]:"" );
						 maps.put("userHeadimg",objects[6]!=null ?objects[6]:"" );
						 maps.put("replyUsername",objects[7]!=null ?objects[7]:"" );
						 maps.put("replyUserrecid",objects[8]!=null ?objects[8]:"" );
						 maps.put("userLoginname",objects[7]!=null ?objects[7]:"" );
						 maps.put("replyLoginname",objects[7]!=null ?objects[7]:"" );
						 amps.add(maps);
					 }
					
				 } 
				 map.put("replys", amps);
				 amp.add(map);
			 }
		 }
		return amp;
	}

	@Override
	public Serializable saveCommentSheet(CommentSheet commentSheet) {
		return operatioDao.saveCommentSheet(commentSheet);
	}

	@Override
	public boolean DeleteCommentSheet(CommentSheet commentSheet) {
		return operatioDao.DeleteCommentSheet(commentSheet);
	}
	@Override
	public Integer getReplySheetsCount(String pagesize, String currpage, String cxtj,ReplySheet replySheet){
		return  operatioDao.getReplySheetsCount(pagesize, currpage, cxtj, replySheet);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getReplySheets(String pagesize, String currpage, String cxtj, ReplySheet replySheet) {
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist = operatioDao.getReplySheets(pagesize, currpage, cxtj, replySheet);
		 if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("recid",object[0] );
				 map.put("commentRecid",object[1]!=null ?object[1]:"" );
				 if(object[2]!=null){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String createtime = formatter.format(object[2]);
					 map.put("createTime" ,createtime );
				 }else{
					 map.put("createTime" ,"" );
				 }
				 map.put("userRecid",object[3]!=null ?object[3]:"" );
				 map.put("replyContent",object[4]!=null ?object[4]:"" );
				 map.put("userShowname",object[5]!=null ?object[5]:"" );
				 map.put("userHeadimg",object[6]!=null ?object[6]:"" );
				 map.put("replyUsername",object[7]!=null ?object[7]:"" );
				 map.put("replyUserrecid",object[8]!=null ?object[8]:"" );
				 map.put("userLoginname",object[7]!=null ?object[7]:"" );
				 map.put("replyLoginname",object[7]!=null ?object[7]:"" );
				 amp.add(map);
			 }
		 }
		return amp;
	}

	@Override
	public Serializable saveReplySheet(ReplySheet replySheet) {
		return operatioDao.saveReplySheet(replySheet);
	}

	@Override
	public boolean DeleteReplySheet(ReplySheet replySheet) {
		return operatioDao.DeleteReplySheet(replySheet);
	}

}
