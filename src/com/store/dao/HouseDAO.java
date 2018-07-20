package com.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.store.conn.ConnectionFactory;
import com.store.entity.House;

public class HouseDAO {

	Connection conn = ConnectionFactory.getConnection();
	
	public ArrayList<House> getHouseList(int uid) {
		ArrayList<House> houseList = new ArrayList<House>();
		String sql = "select * from house where huid = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				House house = new House();
				house.setHid(rs.getInt("hid"));
				house.setHuid(uid);
				house.setHpid(rs.getInt("hpid"));
				houseList.add(house);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return houseList;
	}
	
	public boolean addHouse(int huid, int hpid) {
		String sql = "insert into house(huid,hpid) values(?,?)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, huid);
			ps.setInt(2, hpid);
			int eur = ps.executeUpdate();
			if(eur == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delHouse(int hid) {
		String sql = "delete from house where hid = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, hid);
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
