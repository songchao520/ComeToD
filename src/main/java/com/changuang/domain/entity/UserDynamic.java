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
 * UserDynamic generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "user_dynamic", catalog = "tochatting")
public class UserDynamic implements java.io.Serializable {

	private Integer recid;
	private String dynamicContent;
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	private Date createTime;
	private Integer userRecid;
	private String dynamicImages;
	private String dynamicAddress;

	public UserDynamic() {
	}

	public UserDynamic(String dynamicContent, Date createTime, Integer userRecid, String dynamicImages,
			String dynamicAddress) {
		this.dynamicContent = dynamicContent;
		this.createTime = createTime;
		this.userRecid = userRecid;
		this.dynamicImages = dynamicImages;
		this.dynamicAddress = dynamicAddress;
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

	@Column(name = "dynamic_content", length = 200)
	public String getDynamicContent() {
		return this.dynamicContent;
	}

	public void setDynamicContent(String dynamicContent) {
		this.dynamicContent = dynamicContent;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "user_recid")
	public Integer getUserRecid() {
		return this.userRecid;
	}

	public void setUserRecid(Integer userRecid) {
		this.userRecid = userRecid;
	}

	@Column(name = "dynamic_images", length = 65535)
	public String getDynamicImages() {
		return this.dynamicImages;
	}

	public void setDynamicImages(String dynamicImages) {
		this.dynamicImages = dynamicImages;
	}

	@Column(name = "dynamic_address", length = 100)
	public String getDynamicAddress() {
		return this.dynamicAddress;
	}

	public void setDynamicAddress(String dynamicAddress) {
		this.dynamicAddress = dynamicAddress;
	}

}
