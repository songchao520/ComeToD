package com.changuang.domain.entity;
// Generated 2017-12-1 14:27:22 by Hibernate Tools 4.0.0.Final

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
 * FollowSheet generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "follow_sheet", catalog = "tochatting")
public class FollowSheet implements java.io.Serializable {

	private Integer recid;
	private Integer userRecid;
	private Integer anchorRecid;
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	private Date createTime;

	public FollowSheet() {
	}

	public FollowSheet(Integer userRecid, Integer anchorRecid, Date createTime) {
		this.userRecid = userRecid;
		this.anchorRecid = anchorRecid;
		this.createTime = createTime;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
