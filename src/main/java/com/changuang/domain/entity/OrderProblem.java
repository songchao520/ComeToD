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
 * OrderProblem generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "order_problem", catalog = "tochatting")
public class OrderProblem implements java.io.Serializable {

	private Integer recid;
	private Integer orderRecid;
	private Integer userRecid;
	private String feedbackContent;
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	private Date createTime;
	private Integer isreading;
	private String reader;
	private String replyContent;
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	private Date replyTime;

	public OrderProblem() {
	}

	public OrderProblem(Integer orderRecid, Integer userRecid, String feedbackContent, Date createTime,
			Integer isreading, String reader, String replyContent, Date replyTime) {
		this.orderRecid = orderRecid;
		this.userRecid = userRecid;
		this.feedbackContent = feedbackContent;
		this.createTime = createTime;
		this.isreading = isreading;
		this.reader = reader;
		this.replyContent = replyContent;
		this.replyTime = replyTime;
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

	@Column(name = "order_recid")
	public Integer getOrderRecid() {
		return this.orderRecid;
	}

	public void setOrderRecid(Integer orderRecid) {
		this.orderRecid = orderRecid;
	}

	@Column(name = "user_recid")
	public Integer getUserRecid() {
		return this.userRecid;
	}

	public void setUserRecid(Integer userRecid) {
		this.userRecid = userRecid;
	}

	@Column(name = "feedback_content")
	public String getFeedbackContent() {
		return this.feedbackContent;
	}

	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "isreading")
	public Integer getIsreading() {
		return this.isreading;
	}

	public void setIsreading(Integer isreading) {
		this.isreading = isreading;
	}

	@Column(name = "reader", length = 50)
	public String getReader() {
		return this.reader;
	}

	public void setReader(String reader) {
		this.reader = reader;
	}

	@Column(name = "reply_content")
	public String getReplyContent() {
		return this.replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reply_time", length = 19)
	public Date getReplyTime() {
		return this.replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

}