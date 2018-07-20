package com.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.store.entity.Product;
import com.store.conn.ConnectionFactory;

public class ProductDAO {
	
	Connection conn = ConnectionFactory.getConnection();
	
	public String getPname(int pid) {
		
		String pname = "";
		String sql = "select * from product where pid = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pname = rs.getString("pname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pname;
	}
	
	public int getPprice(int pid) {
		
		int pprice = 0;
		String sql = "select * from product where pid = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pprice = rs.getInt("pprice");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pprice;
	}

	public String getPpicpath(int pid) {
		
		String ppicpath = "";
		String sql = "select * from product where pid = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ppicpath = rs.getString("ppicpath");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ppicpath;
	}
	
	public Product getTheProduct(int pid) {
		
		Product theProduct = new Product();
		String sql = "select * from product where pid = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				theProduct.setPid(pid);
				theProduct.setPname(rs.getString("pname"));
				theProduct.setPprice(rs.getInt("pprice"));
				theProduct.setPscid(rs.getInt("pscid"));
				theProduct.setPpicpath(rs.getString("ppicpath"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return theProduct;
	}
	
	public int getPscid(int pid) {
		String sql = "select * from product where pid = ?";
		int pscid = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pscid = rs.getInt("pscid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pscid;
	}
	
	public String getScname(int scid) {
		String sql = "select * from subclass where scid = ?";
		String scname = "";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, scid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				scname = rs.getString("scname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return scname;
	}
}
