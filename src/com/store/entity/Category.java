package com.store.entity;

public class Category {

	private int cid;
	private String cname;
	private int cscnum;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getCscnum() {
		return cscnum;
	}
	public void setCscnum(int cscnum) {
		this.cscnum = cscnum;
	}
	@Override
	public String toString() {
		return "subclass [cid=" + cid + ", cname=" + cname + ", cscnum=" + cscnum + "]";
	}
	
}
