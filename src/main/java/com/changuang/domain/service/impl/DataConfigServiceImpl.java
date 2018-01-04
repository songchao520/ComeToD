package com.changuang.domain.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.changuang.domain.dao.DataConfigDao;
import com.changuang.domain.entity.DataFixed;
import com.changuang.domain.entity.LabelSheet;
import com.changuang.domain.service.DataConfigService;

/**
* @author songc
* @version 创建时间：2017年12月22日 下午2:21:32
* @ClassName 类名称
* @Description 类描述
*/
@Service
@Transactional
public class DataConfigServiceImpl implements DataConfigService {
	@Autowired
	DataConfigDao dataConfigDao;

	
	public Integer getLabelSheetsCount(String pagesize, String currpage, String cxtj,LabelSheet labelSheet){
		return dataConfigDao.getLabelSheetsCount(pagesize, currpage, cxtj, labelSheet);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getLabelSheets(String pagesize, String currpage, String cxtj, LabelSheet labelSheet) {
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist = dataConfigDao.getLabelSheets(pagesize, currpage, cxtj, labelSheet);
		 if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("recid",object[0] );
				 map.put("labelName",object[1]!=null ?object[1]:"" );
				 amp.add(map);
			 }
		 }
		return amp;
	}

	@Override
	public Serializable saveLabelSheet(LabelSheet labelSheet) {
		return dataConfigDao.saveLabelSheet(labelSheet);
	}

	@Override
	public boolean UpdateLabelSheet(LabelSheet labelSheet) {
		return dataConfigDao.UpdateLabelSheet(labelSheet);
	}

	@SuppressWarnings("rawtypes")
	public List getDataFixeds(String pagesize, String currpage, String cxtj,DataFixed DataFixed){
		ArrayList<HashMap<String, Object>> amp = new ArrayList<>();
		List alist = dataConfigDao.getDataFixeds(pagesize, currpage, cxtj, DataFixed);
		 if(alist != null && alist.size()>0){
			 for(int i = 0; i < alist.size();i++){
				 Object[] object = (Object[])alist.get(i);
				 HashMap< String, Object> map = new HashMap<>();
				 map.put("recid",object[0] );
				 map.put("minMoney",object[1]!=null ?object[1]:"" );
				 map.put("moneyExchange",object[2]!=null ?object[2]:"" );
				 amp.add(map);
			 }
		 }
		return amp;
	}

	public boolean UpdateDataFixed(DataFixed DataFixed){
		return dataConfigDao.UpdateDataFixed(DataFixed);
	}

}
