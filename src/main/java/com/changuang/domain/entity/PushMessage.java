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
 * PushMessage generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "push_message", catalog = "tochatting")
public class PushMessage implements java.io.Serializable {

	private Integer recid;
	private String messageTitle;
	private String messageContent;
	private String author;
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	private Date createTime;
	private String source;
	private String relevantImg;
	private Integer adminRecid;

	public PushMessage() {
	}

	public PushMessage(String messageTitle, String messageContent, String author, Date createTime, String source,
			String relevantImg, Integer adminRecid) {
		this.messageTitle = messageTitle;
		this.messageContent = messageContent;
		this.author = author;
		this.createTime = createTime;
		this.source = source;
		this.relevantImg = relevantImg;
		this.adminRecid = adminRecid;
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

	@Column(name = "message_title", length = 50)
	public String getMessageTitle() {
		return this.messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	@Column(name = "message_content", length = 65535)
	public String getMessageContent() {
		return this.messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	@Column(name = "author", length = 20)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "source", length = 20)
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "relevant_img", length = 200)
	public String getRelevantImg() {
		return this.relevantImg;
	}

	public void setRelevantImg(String relevantImg) {
		this.relevantImg = relevantImg;
	}

	@Column(name = "admin_recid")
	public Integer getAdminRecid() {
		return this.adminRecid;
	}

	public void setAdminRecid(Integer adminRecid) {
		this.adminRecid = adminRecid;
	}

}
