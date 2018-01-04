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
 * MoneyFlow generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "money_flow", catalog = "tochatting")
public class MoneyFlow implements java.io.Serializable {

	private Integer recid;
	private Integer userRecid;
	private Integer anchorRecid;
	private Float moneyAmount;
	private Integer moneyCategory;
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	private Date createTime;

	public MoneyFlow() {
	}

	public MoneyFlow(Integer userRecid, Integer anchorRecid, Float moneyAmount, Integer moneyCategory,
			Date createTime) {
		this.userRecid = userRecid;
		this.anchorRecid = anchorRecid;
		this.moneyAmount = moneyAmount;
		this.moneyCategory = moneyCategory;
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

	@Column(name = "money_amount", precision = 12, scale = 0)
	public Float getMoneyAmount() {
		return this.moneyAmount;
	}

	public void setMoneyAmount(Float moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	@Column(name = "money_category")
	public Integer getMoneyCategory() {
		return this.moneyCategory;
	}

	public void setMoneyCategory(Integer moneyCategory) {
		this.moneyCategory = moneyCategory;
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