package com.changuang.domain.dao;

import java.io.Serializable;
import java.util.List;
import com.changuang.domain.entity.GiftSheet;
import com.changuang.domain.entity.RewardSheet;

/**
* @author songc
* @version 创建时间：2017年12月5日 下午5:12:34
* @ClassName GiftDao
* @Description 操作礼物表和打赏礼物表的抽象接口
*/
public interface GiftDao {
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取礼物数据总量
	 */
	public Integer getGiftSheetsCount(String pagesize, String currpage, String cxtj,GiftSheet giftSheet);
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取礼物
	 */
	@SuppressWarnings("rawtypes")
	public List getGiftSheets(String pagesize, String currpage, String cxtj,GiftSheet giftSheet);
	/**
	 * @desc 添加礼物
	 * @param anchorSheet
	 * @return
	 */
	public Serializable saveGiftSheet(GiftSheet giftSheet);
	/**
	 * @desc 修改礼物信息
	 * @param anchorSheet
	 * @return
	 */
	public boolean UpdateGiftSheet(GiftSheet giftSheet);
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取打赏总量
	 */
	public Integer getRewardSheetsCount(String pagesize, String currpage, String cxtj,RewardSheet rewardSheet);
	/**
	 * 
	 * @param pagesize
	 * @param currpage
	 * @param cxtj
	 * @return 
	 * @desc 获取打赏礼物
	 */
	@SuppressWarnings("rawtypes")
	public List getRewardSheets(String pagesize, String currpage, String cxtj,RewardSheet rewardSheet);
	/**
	 * @desc 添加打赏礼物
	 * @param anchorSheet
	 * @return
	 */
	public Serializable saveRewardSheet(RewardSheet rewardSheet);
	/**
	 * @desc 修改打赏礼物信息
	 * @param anchorSheet
	 * @return
	 */
	public boolean UpdateRewardSheet(RewardSheet rewardSheet);
}
