package com.store.entity;

import java.util.Date;

public class Order {

	private int oid;
	private int oaid;
	private int ouid;
	private Date otime;
	private int omoney;
	private String ogoods;
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getOaid() {
		return oaid;
	}
	public void setOaid(int oaid) {
		this.oaid = oaid;
	}
	public int getOuid() {
		return ouid;
	}
	public void setOuid(int ouid) {
		this.ouid = ouid;
	}
	public Date getOtime() {
		return otime;
	}
	public void setOtime(Date otime) {
		this.otime = otime;
	}
	public int getOmoney() {
		return omoney;
	}
	public void setOmoney(int omoney) {
		this.omoney = omoney;
	}
	public String getOgoods() {
		return ogoods;
	}
	public void setOgoods(String ogoods) {
		this.ogoods = ogoods;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", oaid=" + oaid + ", ouid=" + ouid + ", otime=" + otime + ", omoney=" + omoney
				+ ", ogoods=" + ogoods + "]";
	}
	
}
