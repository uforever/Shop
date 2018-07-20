package com.store.entity;

public class Gamearea {

	private int gaid;
	private String ganame;
	private String ganet;
	public int getGaid() {
		return gaid;
	}
	public void setGaid(int gaid) {
		this.gaid = gaid;
	}
	public String getGaname() {
		return ganame;
	}
	public void setGaname(String ganame) {
		this.ganame = ganame;
	}
	public String getGanet() {
		return ganet;
	}
	public void setGanet(String ganet) {
		this.ganet = ganet;
	}
	@Override
	public String toString() {
		return "Gamearea [gaid=" + gaid + ", ganame=" + ganame + ", ganet=" + ganet + "]";
	}
	
}
