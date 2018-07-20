package com.store.entity;

public class Trolley {

	private int tid;
	private int tuid;
	private int tpid;
	private int tnum;
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getTuid() {
		return tuid;
	}
	public void setTuid(int tuid) {
		this.tuid = tuid;
	}
	public int getTpid() {
		return tpid;
	}
	public void setTpid(int tpid) {
		this.tpid = tpid;
	}
	public int getTnum() {
		return tnum;
	}
	public void setTnum(int tnum) {
		this.tnum = tnum;
	}
	
	@Override
	public String toString() {
		return "Trolley [tid=" + tid + ", tuid=" + tuid + ", tpid=" + tpid + ", tnum=" + tnum + "]";
	}
	
}
