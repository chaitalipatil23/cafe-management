package IMPL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.CustomerDao;
import POJO.Customer;
import Utility.DBUtility;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public boolean addCustomer(Customer c) {
		// TODO Auto-generated method stub
		try (Connection con = DBUtility.getConnection()) {
			String sql = "insert into customer (custName,emailId,custPassword,custAddress,custNo,custLocation) values (?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, c.getCustName());
			ps.setString(2, c.getEmailId());
			ps.setString(3, c.getCustPassword());
			ps.setString(4, c.getCustAddress());
			ps.setString(5, c.getCustNo());
			ps.setString(6, c.getCustLocation());
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

	@Override
	public boolean updateCustomer(Customer c) {
		// TODO Auto-generated method stub
		String sql = "update customer set custName=?,custPassword=?,custAddress=?,custNo=?,custLocation=?  where emailId=?";
		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, c.getCustName());
			ps.setString(2, c.getCustPassword());
			ps.setString(3, c.getCustAddress());
			ps.setString(4, c.getCustNo());
			ps.setString(5, c.getCustLocation());
			ps.setString(6, c.getEmailId());
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

	@Override
	public boolean deleteCustomer(String emailId) {
		// TODO Auto-generated method stub
		String sql = "delete from customer where emailId=?";
		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, emailId);
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

	@Override
	public Customer SearchCustomerByemail(String emailId) {
		// TODO Auto-generated method stub
		String sql = "select * from customer where emailId=?";
		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, emailId);
			try (ResultSet rs = ps.executeQuery()) {
				Customer c = new Customer();
				if (rs.next()) {
					c.setCustName(rs.getString(1));
					c.setEmailId(rs.getString(2));
					c.setCustPassword(rs.getString(3));
					c.setCustAddress(rs.getString(4));
					c.setCustNo(rs.getString(5));
					c.setCustLocation(rs.getString(6));
					return c;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		List<Customer> l = new ArrayList<Customer>();
		String sql = "select * from customer";
		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Customer c = new Customer();
				c.setCustName(rs.getString(1));
				c.setEmailId(rs.getString(2));
				c.setCustPassword(rs.getString(3));
				c.setCustAddress(rs.getString(4));
				c.setCustNo(rs.getString(5));
				c.setCustLocation(rs.getString(6));
				l.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
}
