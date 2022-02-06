package com.study.work.work20220123.model;

import java.util.Date;

public class TransactionLog {
	private Integer wid;
	private Integer bid;
	private String bname;
	private Integer qty;
	private Integer amount;
	private Date buydate;
	
	public TransactionLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TransactionLog(Integer wid, Integer bid, String bname, Integer qty, Integer amount, Date buydate) {
		super();
		this.wid = wid;
		this.bid = bid;
		this.bname = bname;
		this.qty = qty;
		this.amount = amount;
		this.buydate = buydate;
	}

	public Integer getWid() {
		return wid;
	}
	public void setWid(Integer wid) {
		this.wid = wid;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Date getBuydate() {
		return buydate;
	}
	public void setBuydate(Date buydate) {
		this.buydate = buydate;
	}
}
