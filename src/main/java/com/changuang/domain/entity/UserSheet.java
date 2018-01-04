package com.changuang.domain.entity;
// Generated 2017-12-22 11:44:20 by Hibernate Tools 4.0.0.Final

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
 * UserSheet generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "user_sheet", catalog = "tochatting")
public class UserSheet implements java.io.Serializable {

	private Integer recid;
	private Integer utypeRecid;
	private String userLoginname;
	private String userShowname;
	private String userLoginpassword;
	private String userMobilephone;
	private String userHeadimg;
	private String userRemarks;
	private Integer userStatus;
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	private Date userCreatetime;
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	private Date userLasttime;
	private String userLastaddress;
	private String userTencent;
	private Integer userVip;
	private Integer anchorStatus;
	private Float wealthAmount;
	private Integer wealthGrade;
	private Integer userSex;
	private Integer isVip;
	private String userLabel;
	private String userLabelt;
	private String userLabels;
	private String userSource;
	private String userCity;

	public UserSheet() {
	}

	public UserSheet(Integer utypeRecid, String userLoginname, String userShowname, String userLoginpassword,
			String userMobilephone, String userHeadimg, String userRemarks, Integer userStatus, Date userCreatetime,
			Date userLasttime, String userLastaddress, String userTencent, Integer userVip, Integer anchorStatus,
			Float wealthAmount, Integer wealthGrade, Integer userSex, Integer isVip, String userLabel,
			String userLabelt, String userLabels, String userSource, String userCity) {
		this.utypeRecid = utypeRecid;
		this.userLoginname = userLoginname;
		this.userShowname = userShowname;
		this.userLoginpassword = userLoginpassword;
		this.userMobilephone = userMobilephone;
		this.userHeadimg = userHeadimg;
		this.userRemarks = userRemarks;
		this.userStatus = userStatus;
		this.userCreatetime = userCreatetime;
		this.userLasttime = userLasttime;
		this.userLastaddress = userLastaddress;
		this.userTencent = userTencent;
		this.userVip = userVip;
		this.anchorStatus = anchorStatus;
		this.wealthAmount = wealthAmount;
		this.wealthGrade = wealthGrade;
		this.userSex = userSex;
		this.isVip = isVip;
		this.userLabel = userLabel;
		this.userLabelt = userLabelt;
		this.userLabels = userLabels;
		this.userSource = userSource;
		this.userCity = userCity;
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

	@Column(name = "utype_recid")
	public Integer getUtypeRecid() {
		return this.utypeRecid;
	}

	public void setUtypeRecid(Integer utypeRecid) {
		this.utypeRecid = utypeRecid;
	}

	@Column(name = "user_loginname", length = 100)
	public String getUserLoginname() {
		return this.userLoginname;
	}

	public void setUserLoginname(String userLoginname) {
		this.userLoginname = userLoginname;
	}

	@Column(name = "user_showname", length = 60)
	public String getUserShowname() {
		return this.userShowname;
	}

	public void setUserShowname(String userShowname) {
		this.userShowname = userShowname;
	}

	@Column(name = "user_loginpassword", length = 200)
	public String getUserLoginpassword() {
		return this.userLoginpassword;
	}

	public void setUserLoginpassword(String userLoginpassword) {
		this.userLoginpassword = userLoginpassword;
	}

	@Column(name = "user_mobilephone", length = 20)
	public String getUserMobilephone() {
		return this.userMobilephone;
	}

	public void setUserMobilephone(String userMobilephone) {
		this.userMobilephone = userMobilephone;
	}

	@Column(name = "user_headimg")
	public String getUserHeadimg() {
		return this.userHeadimg;
	}

	public void setUserHeadimg(String userHeadimg) {
		this.userHeadimg = userHeadimg;
	}

	@Column(name = "user_remarks", length = 65535)
	public String getUserRemarks() {
		return this.userRemarks;
	}

	public void setUserRemarks(String userRemarks) {
		this.userRemarks = userRemarks;
	}

	@Column(name = "user_status")
	public Integer getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "user_createtime", length = 19)
	public Date getUserCreatetime() {
		return this.userCreatetime;
	}

	public void setUserCreatetime(Date userCreatetime) {
		this.userCreatetime = userCreatetime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "user_lasttime", length = 19)
	public Date getUserLasttime() {
		return this.userLasttime;
	}

	public void setUserLasttime(Date userLasttime) {
		this.userLasttime = userLasttime;
	}

	@Column(name = "user_lastaddress", length = 100)
	public String getUserLastaddress() {
		return this.userLastaddress;
	}

	public void setUserLastaddress(String userLastaddress) {
		this.userLastaddress = userLastaddress;
	}

	@Column(name = "user_tencent", length = 20)
	public String getUserTencent() {
		return this.userTencent;
	}

	public void setUserTencent(String userTencent) {
		this.userTencent = userTencent;
	}

	@Column(name = "user_vip")
	public Integer getUserVip() {
		return this.userVip;
	}

	public void setUserVip(Integer userVip) {
		this.userVip = userVip;
	}

	@Column(name = "anchor_status")
	public Integer getAnchorStatus() {
		return this.anchorStatus;
	}

	public void setAnchorStatus(Integer anchorStatus) {
		this.anchorStatus = anchorStatus;
	}

	@Column(name = "wealth_amount", precision = 12, scale = 0)
	public Float getWealthAmount() {
		return this.wealthAmount;
	}

	public void setWealthAmount(Float wealthAmount) {
		this.wealthAmount = wealthAmount;
	}

	@Column(name = "wealth_grade")
	public Integer getWealthGrade() {
		return this.wealthGrade;
	}

	public void setWealthGrade(Integer wealthGrade) {
		this.wealthGrade = wealthGrade;
	}

	@Column(name = "user_sex")
	public Integer getUserSex() {
		return this.userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	@Column(name = "is_vip")
	public Integer getIsVip() {
		return this.isVip;
	}

	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}

	@Column(name = "user_label", length = 60)
	public String getUserLabel() {
		return this.userLabel;
	}

	public void setUserLabel(String userLabel) {
		this.userLabel = userLabel;
	}

	@Column(name = "user_labelt", length = 60)
	public String getUserLabelt() {
		return this.userLabelt;
	}

	public void setUserLabelt(String userLabelt) {
		this.userLabelt = userLabelt;
	}

	@Column(name = "user_labels", length = 60)
	public String getUserLabels() {
		return this.userLabels;
	}

	public void setUserLabels(String userLabels) {
		this.userLabels = userLabels;
	}

	@Column(name = "user_source", length = 60)
	public String getUserSource() {
		return this.userSource;
	}

	public void setUserSource(String userSource) {
		this.userSource = userSource;
	}

	@Column(name = "user_city", length = 50)
	public String getUserCity() {
		return this.userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

}
