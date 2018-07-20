package com.store.entity;

public class Subclass {

	private int scid;
	private String scname;
	private int scpnum;
	private int supcid;
	public int getScid() {
		return scid;
	}
	public void setScid(int scid) {
		this.scid = scid;
	}
	public String getScname() {
		return scname;
	}
	public void setScname(String scname) {
		this.scname = scname;
	}
	public int getScpnum() {
		return scpnum;
	}
	public void setScpnum(int scpnum) {
		this.scpnum = scpnum;
	}
	public int getSupcid() {
		return supcid;
	}
	public void setSupcid(int supcid) {
		this.supcid = supcid;
	}
	@Override
	public String toString() {
		return "Subclass [scid=" + scid + ", scname=" + scname + ", scpnum=" + scpnum + ", supcid=" + supcid + "]";
	}
	
}
