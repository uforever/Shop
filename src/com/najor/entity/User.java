package com.najor.entity;

public class User {

	private int uid;
	private String uname;
	private String upwd;
	private double umoney;
	private String uphone;
	private String uemail;
	
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
	public double getUmoney() {
		return umoney;
	}
	public void setUmoney(double umoney) {
		this.umoney = umoney;
	}
	public String getUphone() {
		return uphone;
	}
	public void setUphone(String uphone) {
		this.uphone = uphone;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	
	public User(String uname, String upwd, String uphone, String uemail) {
		super();
		this.uname = uname;
		this.upwd = upwd;
		this.uphone = uphone;
		this.uemail = uemail;
	}
	
	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", upwd=" + upwd + ", umoney=" + umoney + ", uphone=" + uphone
				+ ", uemail=" + uemail + "]";
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
