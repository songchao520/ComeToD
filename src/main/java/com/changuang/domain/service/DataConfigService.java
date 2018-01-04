package com.changuang.domain.service;

import java.io.Serializable;
import java.util.List;

import com.changuang.domain.entity.DataFixed;
import com.changuang.domain.entity.LabelSheet;

/**
* @author songc
* @version 创建时间：2017年12月22日 下午2:20:45
* @ClassName 类名称
* @Description 类描述
*/
public interface DataConfigService {
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取标签总数
	 */
	public Integer getLabelSheetsCount(String pagesize, String currpage, String cxtj,LabelSheet labelSheet);
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取标签
	 */
	@SuppressWarnings("rawtypes")
	public List getLabelSheets(String pagesize, String currpage, String cxtj,LabelSheet labelSheet);
	/**
	 * @desc 添加标签
	 * @param anchorSheet
	 * @return
	 */
	public Serializable saveLabelSheet(LabelSheet labelSheet);
	/**
	 * @desc 修改标签信息
	 * @param anchorSheet
	 * @return
	 */
	public boolean UpdateLabelSheet(LabelSheet labelSheet);
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取金额限制
	 */
	@SuppressWarnings("rawtypes")
	public List getDataFixeds(String pagesize, String currpage, String cxtj,DataFixed DataFixed);
	/**
	 * @desc 修改金额限制
	 * @param anchorSheet
	 * @return
	 */
	public boolean UpdateDataFixed(DataFixed DataFixed);
}
