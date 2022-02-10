package com.study.work.work20211219;

public class Clazz {
	private Integer id;
	private String name;
	private Integer credit;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCredit() {
		return credit;
	}
	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	@Override
	public String toString() {
		return "Clazz [id=" + id + ", name=" + name + ", credit=" + credit + "]";
	}
	
}
