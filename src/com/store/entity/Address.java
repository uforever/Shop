package com.store.entity;

public class Address {
	
	private int aid;
	private int auid;
	private String awho;
	private long aphone;
	private String aname;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getAuid() {
		return auid;
	}
	public void setAuid(int auid) {
		this.auid = auid;
	}
	public String getAwho() {
		return awho;
	}
	public void setAwho(String awho) {
		this.awho = awho;
	}
	public long getAphone() {
		return aphone;
	}
	public void setAphone(long aphone) {
		this.aphone = aphone;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	@Override
	public String toString() {
		return "Address [aid=" + aid + ", auid=" + auid + ", awho=" + awho + ", aphone=" + aphone + ", aname=" + aname
				+ "]";
	}
}
