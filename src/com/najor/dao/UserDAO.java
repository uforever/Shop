package com.najor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.najor.conn.ConnectionFactory;

public class UserDAO {

	Connection conn = ConnectionFactory.getConnection();
	
	public boolean validateUserName(String user_name) {
		
		String sql = "select * from s_user where uname = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user_name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public String getPwd(String user_name) {
		String password = "";
		String sql = "select * from s_user where uname = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user_name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				password = rs.getString("upwd");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return password;
	}
	
	public boolean regist(String user_name,String password,String email,String telephone) {
		
		String sql = "insert into s_user (uname,upwd,umoney,uphone,uemail) values(?,?,5.0,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user_name);
			ps.setString(2, password);
			ps.setString(3, telephone);
			ps.setString(4, email);
			int num = ps.executeUpdate();
			if(num == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		UserDAO userDAO = new UserDAO();
		boolean res = userDAO.validateUserName("qxym");
		System.out.println(res);
	}
}
