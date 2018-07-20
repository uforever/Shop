package com.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.store.conn.ConnectionFactory;
import com.store.entity.Order;

public class OrderDAO {

	Connection conn = ConnectionFactory.getConnection();
	
	public boolean createOrder(int oaid, int ouid, int omoney, String ogoods) {
		String sql = "insert into bforder(oaid,ouid,otime,omoney,ogoods) values(?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			Timestamp otime = new Timestamp(new Date().getTime());
			ps.setInt(1, oaid);
			ps.setInt(2, ouid);
			ps.setTimestamp(3, otime);
			ps.setInt(4, omoney);
			ps.setString(5, ogoods);
			int eur = ps.executeUpdate();
			if(eur == 1) {
				System.out.println("时间："+otime+"，"+ouid+"号用户提交了订单");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public ArrayList<Order> getOrderList(int uid) {
		ArrayList<Order> orderList = new ArrayList<Order>();
		String sql = "select * from bforder where ouid = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Order order = new Order();
				order.setOid(rs.getInt("oid"));
				order.setOuid(uid);
				order.setOaid(rs.getInt("oaid"));
				order.setOtime(rs.getDate("otime"));
				order.setOmoney(rs.getInt("omoney"));
				order.setOgoods(rs.getString("ogoods"));
				orderList.add(order);
			}
			return orderList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}
	
}
