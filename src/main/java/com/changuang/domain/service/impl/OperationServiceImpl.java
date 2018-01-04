package com.changuang.domain.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
