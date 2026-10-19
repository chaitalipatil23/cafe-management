package IMPL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Dao.OrderDao;
import POJO.OrderFood;
import Utility.DBUtility;

public class OrderDaoImpl implements OrderDao {

	@Override
	public boolean placeOrder(OrderFood o) {
		// TODO Auto-generated method stub
		LocalDateTime dt = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formateddate = dt.format(format);
		double totalBill = CalculateBill(o.getEmailId());
		if (totalBill <= 0) {
			return false;
		}
		String sql = "insert into orderfood (orderDate,totalBill,emailId,orderStatus) values (?,?,?,?)";
		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setTimestamp(1, Timestamp.valueOf(formateddate));
			ps.setDouble(2, totalBill);
			ps.setString(3, o.getEmailId());
			ps.setString(4, o.getOrderStatus());
			int i = ps.executeUpdate();
			if (i > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	private double CalculateBill(String emailId) {
		// TODO Auto-generated method stub
		double totalBill = 0;
		String sql = "select totalPrice from cart where cEmail=?";
		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, emailId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					totalBill += rs.getDouble("totalPrice");
				}
			}
			return totalBill;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public OrderFood showOrderById(int orderId) {
		// TODO Auto-generated method stub

		String sql = "select * from orderfood where orderId=?";

		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, orderId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					OrderFood o = new OrderFood();
					o.setOrderId(rs.getInt(1));
					o.setOrderDate(rs.getTimestamp(2).toLocalDateTime());
					o.setTotalBill(rs.getDouble(3));
					o.setEmailId(rs.getString(4));
					o.setOrderStatus(rs.getString(5));
					return o;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<OrderFood> showAllOrder() {
		// TODO Auto-generated method stub
		List<OrderFood> olist = new ArrayList<OrderFood>();
		String sql = "select * from orderfood ";
		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				OrderFood o = new OrderFood();
				o.setOrderId(rs.getInt(1));
				o.setOrderDate(rs.getTimestamp(2).toLocalDateTime());
				o.setTotalBill(rs.getDouble(3));
				o.setEmailId(rs.getString(4));
				o.setOrderStatus(rs.getString(5));
				olist.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return olist;
	}

	@Override
	public List<OrderFood> showMyOrderHistory(String emailId) {
		// TODO Auto-generated method stub
		List<OrderFood> olist = new ArrayList<OrderFood>();
		String sql = "select * from orderfood where emailId=?";
		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, emailId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					OrderFood o = new OrderFood();
					o.setOrderId(rs.getInt(1));
					o.setOrderDate(rs.getTimestamp(2).toLocalDateTime());
					o.setTotalBill(rs.getDouble(3));
					o.setEmailId(rs.getString(4));
					o.setOrderStatus(rs.getString(5));
					olist.add(o);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return olist;
	}
}
