package com.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.store.conn.ConnectionFactory;

public class GameAreaDAO {

	Connection conn = ConnectionFactory.getConnection();
	
	public String getGaname(int gaid) {
		String ganame = "";
		String sql = "select * from gamearea where gaid = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, gaid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ganame = rs.getString("ganame");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ganame;
	}
	
	public String getGanet(int gaid) {
		String ganet = "";
		String sql = "select * from gamearea where gaid = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, gaid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ganet = rs.getString("ganet");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ganet;
	}
}
