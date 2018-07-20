package com.store.entity;

public class House {

	private int hid;
	private int huid;
	private int hpid;
	
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public int getHuid() {
		return huid;
	}
	public void setHuid(int huid) {
		this.huid = huid;
	}
	public int getHpid() {
		return hpid;
	}
	public void setHpid(int hpid) {
		this.hpid = hpid;
	}
	
	@Override
	public String toString() {
		return "House [hid=" + hid + ", huid=" + huid + ", hpid=" + hpid + "]";
	}
	
}
