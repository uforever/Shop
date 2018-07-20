package com.store.entity;

public class User {
	
	private int uid;
	private String uname;
	private String upwd;
	private long uphone;
	private long uqq;
	private int ugaid;
	private int umoney;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public long getUphone() {
		return uphone;
	}
	public void setUphone(long uphone) {
		this.uphone = uphone;
	}
	public long getUqq() {
		return uqq;
	}
	public void setUqq(long uqq) {
		this.uqq = uqq;
	}
	public int getUgaid() {
		return ugaid;
	}
	public void setUgaid(int ugaid) {
		this.ugaid = ugaid;
	}
	public int getUmoney() {
		return umoney;
	}
	public void setUmoney(int umoney) {
		this.umoney = umoney;
	}
	
	
	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", upwd=" + upwd + ", uphone=" + uphone + ", uqq=" + uqq
				+ ", ugaid=" + ugaid + ", umoney=" + umoney + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uname == null) ? 0 : uname.hashCode());
		result = prime * result + ((upwd == null) ? 0 : upwd.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (uname == null) {
			if (other.uname != null)
				return false;
		} else if (!uname.equals(other.uname))
			return false;
		if (upwd == null) {
			if (other.upwd != null)
				return false;
		} else if (!upwd.equals(other.upwd))
			return false;
		return true;
	}
	
}
