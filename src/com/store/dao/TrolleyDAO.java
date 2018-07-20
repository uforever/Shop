package com.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.store.entity.Trolley;
import com.store.conn.ConnectionFactory;

public class TrolleyDAO {
	
	Connection conn = ConnectionFactory.getConnection();
	
	public boolean changeTnum(int tid, int tnum) {
		String sql = "update trolley set tnum=? where tid=?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, tnum);
			ps.setInt(2, tid);
			int eur = ps.executeUpdate();
			if(eur == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean delTheTrolley(int tid) {
		String sql = "delete from trolley where tid = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, tid);
			int eur = ps.executeUpdate();
			if(eur == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean addTrolley(int tuid, int tpid, int tnum) {
		
		String sql = "insert into trolley(tuid,tpid,tnum) values(?,?,?)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, tuid);
			ps.setInt(2, tpid);
			ps.setInt(3, tnum);
			
			int eur = ps.executeUpdate();
			if(eur == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public int getTrolleySum(int uid) {
		
		int trolleySum = 0;
		String sql = "select * from trolley where tuid = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				trolleySum += rs.getInt("tnum");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trolleySum;
	}
	
	public ArrayList<Trolley> getTrolleyList(int uid){
		
		ArrayList<Trolley> trolleyList = new ArrayList<Trolley>();
		String sql = "select * from trolley where tuid = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Trolley trolley = new Trolley();
				trolley.setTid(rs.getInt("tid"));
				trolley.setTuid(uid);
				trolley.setTpid(rs.getInt("tpid"));
				trolley.setTnum(rs.getInt("tnum"));
				trolleyList.add(trolley);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trolleyList;
	}
	
	public int getTnum(int tid) {
		int tnum = 0;
		String sql = "select * from trolley where tid = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, tid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				tnum = rs.getInt("tnum");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return tnum;
	}
}
