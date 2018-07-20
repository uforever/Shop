package com.store.entity;

public class Product {

	private int pid;
	private String pname;
	private int pprice;
	private int pscid;
	private String ppicpath;
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPprice() {
		return pprice;
	}
	public void setPprice(int pprice) {
		this.pprice = pprice;
	}
	public int getPscid() {
		return pscid;
	}
	public void setPscid(int pscid) {
		this.pscid = pscid;
	}
	public String getPpicpath() {
		return ppicpath;
	}
	public void setPpicpath(String ppicpath) {
		this.ppicpath = ppicpath;
	}
	
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", pprice=" + pprice + ", pscid=" + pscid + ", ppicpath="
				+ ppicpath + "]";
	}
	
}
