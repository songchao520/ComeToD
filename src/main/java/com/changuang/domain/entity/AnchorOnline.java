package com.changuang.domain.entity;
// Generated 2018-2-7 15:40:44 by Hibernate Tools 4.0.0.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * AnchorOnline generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "anchor_online", catalog = "tochatting")
public class AnchorOnline implements java.io.Serializable {

	private Integer recid;
	private Integer anchorRecid;
	private String anchorLabel;
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	private Date createTime;
	private Integer anchorClass;
	private Integer isFree;
	private Integer isHot;
	private Integer isRecommend;
	private Float voiceChat;
	private Float videoChat;
	private String anchorName;
	private String anchorNotice;
	private Integer userRecid;
	private Integer userNum;

	public AnchorOnline() {
	}

	public AnchorOnline(Integer anchorRecid, String anchorLabel, Date createTime, Integer anchorClass, Integer isFree,
			Integer isHot, Integer isRecommend, Float voiceChat, Float videoChat, String anchorName,
			String anchorNotice, Integer userRecid, Integer userNum) {
		this.anchorRecid = anchorRecid;
		this.anchorLabel = anchorLabel;
		this.createTime = createTime;
		this.anchorClass = anchorClass;
		this.isFree = isFree;
		this.isHot = isHot;
		this.isRecommend = isRecommend;
		this.voiceChat = voiceChat;
		this.videoChat = videoChat;
		this.anchorName = anchorName;
		this.anchorNotice = anchorNotice;
		this.userRecid = userRecid;
		this.userNum = userNum;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "recid", unique = true, nullable = false)
	public Integer getRecid() {
		return this.recid;
	}

	public void setRecid(Integer recid) {
		this.recid = recid;
	}

	@Column(name = "anchor_recid")
	public Integer getAnchorRecid() {
		return this.anchorRecid;
	}

	public void setAnchorRecid(Integer anchorRecid) {
		this.anchorRecid = anchorRecid;
	}

	@Column(name = "anchor_label", length = 20)
	public String getAnchorLabel() {
		return this.anchorLabel;
	}

	public void setAnchorLabel(String anchorLabel) {
		this.anchorLabel = anchorLabel;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "anchor_class")
	public Integer getAnchorClass() {
		return this.anchorClass;
	}

	public void setAnchorClass(Integer anchorClass) {
		this.anchorClass = anchorClass;
	}

	@Column(name = "is_free")
	public Integer getIsFree() {
		return this.isFree;
	}

	public void setIsFree(Integer isFree) {
		this.isFree = isFree;
	}

	@Column(name = "is_hot")
	public Integer getIsHot() {
		return this.isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	@Column(name = "is_recommend")
	public Integer getIsRecommend() {
		return this.isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	@Column(name = "voice_chat", precision = 12, scale = 0)
	public Float getVoiceChat() {
		return this.voiceChat;
	}

	public void setVoiceChat(Float voiceChat) {
		this.voiceChat = voiceChat;
	}

	@Column(name = "video_chat", precision = 12, scale = 0)
	public Float getVideoChat() {
		return this.videoChat;
	}

	public void setVideoChat(Float videoChat) {
		this.videoChat = videoChat;
	}

	@Column(name = "anchor_name")
	public String getAnchorName() {
		return this.anchorName;
	}

	public void setAnchorName(String anchorName) {
		this.anchorName = anchorName;
	}

	@Column(name = "anchor_notice")
	public String getAnchorNotice() {
		return this.anchorNotice;
	}

	public void setAnchorNotice(String anchorNotice) {
		this.anchorNotice = anchorNotice;
	}

	@Column(name = "user_recid")
	public Integer getUserRecid() {
		return this.userRecid;
	}

	public void setUserRecid(Integer userRecid) {
		this.userRecid = userRecid;
	}

	@Column(name = "user_num")
	public Integer getUserNum() {
		return this.userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

}
