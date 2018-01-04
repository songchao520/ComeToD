package com.changuang.domain.entity;
// Generated 2017-12-23 17:09:45 by Hibernate Tools 4.0.0.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DataFixed generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "data_fixed", catalog = "tochatting")
public class DataFixed implements java.io.Serializable {

	private Integer recid;
	private Float minMoney;
	private Float moneyExchange;

	public DataFixed() {
	}

	public DataFixed(Float minMoney, Float moneyExchange) {
		this.minMoney = minMoney;
		this.moneyExchange = moneyExchange;
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

	@Column(name = "min_money", precision = 12, scale = 0)
	public Float getMinMoney() {
		return this.minMoney;
	}

	public void setMinMoney(Float minMoney) {
		this.minMoney = minMoney;
	}

	@Column(name = "money_exchange", precision = 12, scale = 0)
	public Float getMoneyExchange() {
		return this.moneyExchange;
	}

	public void setMoneyExchange(Float moneyExchange) {
		this.moneyExchange = moneyExchange;
	}

}
