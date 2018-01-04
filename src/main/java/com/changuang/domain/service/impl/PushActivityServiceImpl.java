package com.changuang.domain.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.changuang.domain.dao.PushActivityDao;
import com.changuang.domain.entity.PushActivity;
import com.changuang.domain.service.PushActivityService;


/**
* @author songc
* @version 创建时间：2017年12月19日 下午2:21:53
* @ClassName 类名称
* @Description 类描述
*/
@Service
@Transactional
public class PushActivityServiceImpl implements PushActivityService {
	@Autowired
	PushActivityDao pushActivityDao;
	
	@Override
	public Serializable savePushActivity(PushActivity pushActivity) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getPushActivitys(String pagesize, String currpage, String cxtj, PushActivity pushActivity) {
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist =  pushActivityDao.getPushActivitys(pagesize,currpage,cxtj,pushActivity);
		if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("recid",object[0]!=null ?object[0]:"" );
				 map.put("activityHttp",object[1]!=null ?object[1]:"");
				 map.put("messageTitle",object[2]!=null ?object[2]:"");
				 map.put("messageContent",object[3]!=null ?object[3]:"");
				 map.put("author",object[4]!=null ?object[4]:"");
				 if(object[5]!=null ){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 String createTime = formatter.format(object[5]);
					 map.put("createTime", createTime);
				 }else{
					 map.put("createTime", "");
				 }
				 
				 map.put("source",object[6]!=null ?object[6]:"");
				 map.put("relevantImg",object[7]!=null ?object[7]:"");
				 
				 map.put("adminRecid", object[8]!=null ?object[8]:"");
				 map.put("userType", object[9]!=null ?object[9]:"");
				 map.put("userType", object[10]!=null ?object[10]:"");
				 map.put("userType", object[11]!=null ?object[11]:"");
				 map.put("userType", object[12]!=null ?object[12]:"");
				 amp.add(map);
			 }
		 }
		return amp;
	}

	@Override
	public boolean UpdatePushActivity(PushActivity pushActivity) {
		// TODO Auto-generated method stub
		return false;
	}

}
