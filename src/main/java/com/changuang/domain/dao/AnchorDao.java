package com.changuang.domain.dao;

import java.io.Serializable;
import java.util.List;

import com.changuang.domain.entity.AnchorOnline;
import com.changuang.domain.entity.AnchorSheet;
import com.changuang.domain.entity.AnchorStatusSheet;

/**
* @author songc
* @version 创建时间：2017年12月4日 上午11:12:25
* @ClassName AnchorDao
* @Description 获取修改主播审核状态和修改主播信息。
*/
public interface AnchorDao {
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取主播
	 */
	public Integer getAnchorSheetsCount(String pagesize, String currpage, String cxtj, AnchorSheet anchorSheet,String sortZiduan);
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取主播
	 */
	@SuppressWarnings("rawtypes")
	public List getAnchorSheets(String pagesize, String currpage, String cxtj,AnchorSheet anchorSheet,String sortZiduan);
	/**
	 * @desc 添加主播
	 * @param anchorSheet
	 * @return
	 */
	public Serializable saveAnchorSheet(AnchorSheet anchorSheet);
	/**
	 * @desc 修改主播信息
	 * @param anchorSheet
	 * @return
	 */
	public boolean UpdateAnchorSheet(AnchorSheet anchorSheet);
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取审核主播表总数
	 */
	public Integer getAnchorStatusSheetsCount(String pagesize, String currpage, String cxtj,
			AnchorStatusSheet anchorStatusSheet);
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取审核主播表
	 */
	@SuppressWarnings("rawtypes")
	public List getAnchorStatusSheets(String pagesize, String currpage, String cxtj,AnchorStatusSheet anchorStatusSheet);
	/**
	 * @desc 添加审核主播（审核失败走修改主播信息）
	 * @param anchorSheet
	 * @return
	 */
	public Serializable saveAnchorStatusSheet(AnchorStatusSheet anchorStatusSheet);
	/**
	 * @desc 修改审核主播信息
	 * @param anchorSheet
	 * @return
	 */
	public boolean UpdateAnchorStatusSheet(AnchorStatusSheet anchorStatusSheet);
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取在线主播
	 */
	@SuppressWarnings("rawtypes")
	public List getAnchorOnlines(String pagesize, String currpage, String cxtj,AnchorOnline anchorOnline);
	/**
	 * @desc 添加在线主播（审核失败走修改主播信息）
	 * @param anchorSheet
	 * @return
	 */
	public Serializable saveAnchorOnline(AnchorOnline anchorOnline);
	/**
	 * @desc 修改在线主播
	 * @param anchorSheet
	 * @return
	 */
	public boolean UpdateAnchorOnline(AnchorOnline anchorOnline);
	/**
	 * @desn 删除在线主播
	 * @param anchorOnline
	 * @return
	 */
	public boolean DeleteAnchorOnline(AnchorOnline anchorOnline);
	
}
