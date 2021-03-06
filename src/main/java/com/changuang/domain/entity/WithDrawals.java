package com.changuang.domain.entity;
// Generated 2017-12-25 10:33:45 by Hibernate Tools 4.0.0.Final

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
 * WithDrawals generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "with_drawals", catalog = "tochatting")
public class WithDrawals implements java.io.Serializable {

	private Integer recid;
	private Float moneyAmount;
	private Integer userRecid;
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	private Date createTime;
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	private Date transfertime;
	private Integer transferboolean;
	private Integer transferpeople;
	private String alipaynumber;
	private String withdpeople;
	private Integer withdway;
	private String withdbank;

	public WithDrawals() {
	}

	public WithDrawals(Float moneyAmount, Integer userRecid, Date createTime, Date transfertime,
			Integer transferboolean, Integer transferpeople, String alipaynumber, String withdpeople, Integer withdway,
			String withdbank) {
		this.moneyAmount = moneyAmount;
		this.userRecid = userRecid;
		this.createTime = createTime;
		this.transfertime = transfertime;
		this.transferboolean = transferboolean;
		this.transferpeople = transferpeople;
		this.alipaynumber = alipaynumber;
		this.withdpeople = withdpeople;
		this.withdway = withdway;
		this.withdbank = withdbank;
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

	@Column(name = "money_amount", precision = 12, scale = 0)
	public Float getMoneyAmount() {
		return this.moneyAmount;
	}

	public void setMoneyAmount(Float moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	@Column(name = "user_recid")
	public Integer getUserRecid() {
		return this.userRecid;
	}

	public void setUserRecid(Integer userRecid) {
		this.userRecid = userRecid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "transfertime", length = 19)
	public Date getTransfertime() {
		return this.transfertime;
	}

	public void setTransfertime(Date transfertime) {
		this.transfertime = transfertime;
	}

	@Column(name = "transferboolean")
	public Integer getTransferboolean() {
		return this.transferboolean;
	}

	public void setTransferboolean(Integer transferboolean) {
		this.transferboolean = transferboolean;
	}

	@Column(name = "transferpeople")
	public Integer getTransferpeople() {
		return this.transferpeople;
	}

	public void setTransferpeople(Integer transferpeople) {
		this.transferpeople = transferpeople;
	}

	@Column(name = "alipaynumber", length = 50)
	public String getAlipaynumber() {
		return this.alipaynumber;
	}

	public void setAlipaynumber(String alipaynumber) {
		this.alipaynumber = alipaynumber;
	}

	@Column(name = "withdpeople", length = 60)
	public String getWithdpeople() {
		return this.withdpeople;
	}

	public void setWithdpeople(String withdpeople) {
		this.withdpeople = withdpeople;
	}

	@Column(name = "withdway")
	public Integer getWithdway() {
		return this.withdway;
	}

	public void setWithdway(Integer withdway) {
		this.withdway = withdway;
	}

	@Column(name = "withdbank", length = 60)
	public String getWithdbank() {
		return this.withdbank;
	}

	public void setWithdbank(String withdbank) {
		this.withdbank = withdbank;
	}

}
