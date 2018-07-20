package com.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.store.conn.ConnectionFactory;
import com.store.entity.Address;

public class AddressDAO {
	
	Connection conn = ConnectionFactory.getConnection();
	
	public ArrayList<Address> getAddressList(int uid) {
		ArrayList<Address> addressList = new ArrayList<Address>();
		String sql = "select * from address where auid = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Address address = new Address();
				address.setAid(rs.getInt("aid"));
				address.setAuid(uid);
				address.setAwho(rs.getString("awho"));
				address.setAphone(rs.getLong("aphone"));
				address.setAname(rs.getString("aname"));
				addressList.add(address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return addressList;
	}
	
	public boolean delTheAddress(int aid) {
		String sql = "delete from address where aid = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aid);
			int eur = ps.executeUpdate();
			if(eur == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addAAddress(int auid, String awho, long aphone, String aname) {
		
		String sql = "insert into address(auid,awho,aphone,aname) values(?,?,?,?)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, auid);
			ps.setString(2, awho);
			ps.setLong(3, aphone);
			ps.setString(4, aname);
			int eur = ps.executeUpdate();
			if(eur == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Address getTheAddress(int aid) {
		Address address = new Address();
		String sql = "select * from address where aid = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				address.setAid(aid);
				address.setAuid(rs.getInt("auid"));
				address.setAwho(rs.getString("awho"));
				address.setAphone(rs.getLong("aphone"));
				address.setAname(rs.getString("aname"));
			}
			return address;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return address;
	}
}
