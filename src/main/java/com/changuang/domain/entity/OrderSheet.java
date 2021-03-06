package com.changuang.domain.entity;
// Generated 2017-12-7 17:37:53 by Hibernate Tools 4.0.0.Final

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
 * OrderSheet generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "order_sheet", catalog = "tochatting")
public class OrderSheet implements java.io.Serializable {

	private Integer recid;
	private Integer userRecid;
	private Integer anchorUser;
	private Integer anchorRecid;
	private Integer orderStatus;
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	private Date startTime;
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	private Date endTime;
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	private Date createTime;
	private String purchaseTime;
	private String useTime;
	private String orderEvaluate;
	private Float orderScore;
	private Float tipAnchor;

	public OrderSheet() {
	}

	public OrderSheet(Integer userRecid, Integer anchorUser, Integer anchorRecid, Integer orderStatus, Date startTime,
			Date endTime, Date createTime, String purchaseTime, String useTime, String orderEvaluate, Float orderScore,
			Float tipAnchor) {
		this.userRecid = userRecid;
		this.anchorUser = anchorUser;
		this.anchorRecid = anchorRecid;
		this.orderStatus = orderStatus;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createTime = createTime;
		this.purchaseTime = purchaseTime;
		this.useTime = useTime;
		this.orderEvaluate = orderEvaluate;
		this.orderScore = orderScore;
		this.tipAnchor = tipAnchor;
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

	@Column(name = "anchor_user")
	public Integer getAnchorUser() {
		return this.anchorUser;
	}

	public void setAnchorUser(Integer anchorUser) {
		this.anchorUser = anchorUser;
	}

	@Column(name = "anchor_recid")
	public Integer getAnchorRecid() {
		return this.anchorRecid;
	}

	public void setAnchorRecid(Integer anchorRecid) {
		this.anchorRecid = anchorRecid;
	}

	@Column(name = "order_status")
	public Integer getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time", length = 19)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time", length = 19)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "purchase_time", length = 20)
	public String getPurchaseTime() {
		return this.purchaseTime;
	}

	public void setPurchaseTime(String purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	@Column(name = "use_time", length = 20)
	public String getUseTime() {
		return this.useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	@Column(name = "order_evaluate")
	public String getOrderEvaluate() {
		return this.orderEvaluate;
	}

	public void setOrderEvaluate(String orderEvaluate) {
		this.orderEvaluate = orderEvaluate;
	}

	@Column(name = "order_score", precision = 12, scale = 0)
	public Float getOrderScore() {
		return this.orderScore;
	}

	public void setOrderScore(Float orderScore) {
		this.orderScore = orderScore;
	}

	@Column(name = "tip_anchor", precision = 12, scale = 0)
	public Float getTipAnchor() {
		return this.tipAnchor;
	}

	public void setTipAnchor(Float tipAnchor) {
		this.tipAnchor = tipAnchor;
	}

}
