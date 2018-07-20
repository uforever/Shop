package com.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.store.entity.User;
import com.store.conn.ConnectionFactory;

public class UserDAO {
	
	Connection conn = ConnectionFactory.getConnection();
	
	public boolean validateUname(String uname) {
		
		String sql = "select * from user where uname = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public String getUpwd(String uname) {
		String upwd = "";
		String sql = "select * from user where uname = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				upwd = rs.getString("upwd");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return upwd;
	}
	
	public boolean register(String uname, String upwd, long uphone, long uqq) {
		
		String sql = "insert into user(uname,upwd,uphone,uqq,ugaid,umoney) values(?,?,?,?,0,5)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2, upwd);
			ps.setLong(3, uphone);
			ps.setLong(4, uqq);
			
			int eur = ps.executeUpdate();
			if(eur == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public int getUid(String uname) {
		
		String sql = "select * from user where uname = ?";
		int uid = 0;
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				uid = rs.getInt("uid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return uid;
	}
	
	public User getTheUser(int uid) {
		
		String sql = "select * from user where uid = ?";
		User theUser = new User();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				theUser.setUid(uid);
				theUser.setUname(rs.getString("uname"));
				theUser.setUpwd(rs.getString("upwd"));
				theUser.setUphone(rs.getLong("uphone"));
				theUser.setUqq(rs.getLong("uqq"));
				theUser.setUgaid(rs.getInt("ugaid"));
				theUser.setUmoney(rs.getInt("umoney"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return theUser;
	}
	
	public boolean updateInfom(int uid, long uphone, long uqq, int ugaid) {
		
		String sql = "update user set uphone = ?, uqq = ?, ugaid = ? where uid = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, uphone);
			ps.setLong(2, uqq);
			ps.setInt(3, ugaid);
			ps.setInt(4, uid);
			int eur = ps.executeUpdate();
			
			if(eur == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public int getUmoney(int uid) {
		String sql = "select * from user where uid = ?";
		int umoney = 0;
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				umoney = rs.getInt("umoney");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return umoney;
	}
	
	public boolean updateUmoney(int uid, int umoney) {
		String sql = "update user set umoney = ? where uid = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, umoney);
			ps.setInt(2, uid);
			int eur = ps.executeUpdate();
			if(eur == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
