package com.changuang.domain.entity;
// Generated 2017-12-25 17:57:09 by Hibernate Tools 4.0.0.Final

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
 * AnchorStatusSheet generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "anchor_status_sheet", catalog = "tochatting")
public class AnchorStatusSheet implements java.io.Serializable {

	private Integer recid;
	private Integer userRecid;
	private Integer anchorRecid;
	private String anchorName;
	private String anchorIdcord;
	private String examinePhoto;
	private Integer examineStatus;
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	private Date createTime;
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date userBirthday;
	private String userAddress;
	private String userOccupation;
	private String myPhoto;
	private String chatAbility;
	private String smallPhoto;

	public AnchorStatusSheet() {
	}

	public AnchorStatusSheet(Integer userRecid, Integer anchorRecid, String anchorName, String anchorIdcord,
			String examinePhoto, Integer examineStatus, Date createTime, Date userBirthday, String userAddress,
			String userOccupation, String myPhoto, String chatAbility, String smallPhoto) {
		this.userRecid = userRecid;
		this.anchorRecid = anchorRecid;
		this.anchorName = anchorName;
		this.anchorIdcord = anchorIdcord;
		this.examinePhoto = examinePhoto;
		this.examineStatus = examineStatus;
		this.createTime = createTime;
		this.userBirthday = userBirthday;
		this.userAddress = userAddress;
		this.userOccupation = userOccupation;
		this.myPhoto = myPhoto;
		this.chatAbility = chatAbility;
		this.smallPhoto = smallPhoto;
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

	@Column(name = "user_recid")
	public Integer getUserRecid() {
		return this.userRecid;
	}

	public void setUserRecid(Integer userRecid) {
		this.userRecid = userRecid;
	}

	@Column(name = "anchor_recid")
	public Integer getAnchorRecid() {
		return this.anchorRecid;
	}

	public void setAnchorRecid(Integer anchorRecid) {
		this.anchorRecid = anchorRecid;
	}

	@Column(name = "anchor_name", length = 20)
	public String getAnchorName() {
		return this.anchorName;
	}

	public void setAnchorName(String anchorName) {
		this.anchorName = anchorName;
	}

	@Column(name = "anchor_idcord", length = 25)
	public String getAnchorIdcord() {
		return this.anchorIdcord;
	}

	public void setAnchorIdcord(String anchorIdcord) {
		this.anchorIdcord = anchorIdcord;
	}

	@Column(name = "examine_photo", length = 200)
	public String getExaminePhoto() {
		return this.examinePhoto;
	}

	public void setExaminePhoto(String examinePhoto) {
		this.examinePhoto = examinePhoto;
	}

	@Column(name = "examine_status")
	public Integer getExamineStatus() {
		return this.examineStatus;
	}

	public void setExamineStatus(Integer examineStatus) {
		this.examineStatus = examineStatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "user_birthday", length = 10)
	public Date getUserBirthday() {
		return this.userBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	@Column(name = "user_address", length = 100)
	public String getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Column(name = "user_occupation", length = 20)
	public String getUserOccupation() {
		return this.userOccupation;
	}

	public void setUserOccupation(String userOccupation) {
		this.userOccupation = userOccupation;
	}

	@Column(name = "my_photo", length = 200)
	public String getMyPhoto() {
		return this.myPhoto;
	}

	public void setMyPhoto(String myPhoto) {
		this.myPhoto = myPhoto;
	}

	@Column(name = "chat_ability", length = 100)
	public String getChatAbility() {
		return this.chatAbility;
	}

	public void setChatAbility(String chatAbility) {
		this.chatAbility = chatAbility;
	}

	@Column(name = "small_photo", length = 200)
	public String getSmallPhoto() {
		return this.smallPhoto;
	}

	public void setSmallPhoto(String smallPhoto) {
		this.smallPhoto = smallPhoto;
	}

}
