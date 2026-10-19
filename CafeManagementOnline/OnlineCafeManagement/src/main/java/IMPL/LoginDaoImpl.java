package IMPL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Dao.LoginDao;
import Utility.DBUtility;

public class LoginDaoImpl implements LoginDao {

	@Override
	public boolean userLogin(String useremail, String userpass) {
		// TODO Auto-generated method stub
		if (useremail == null || userpass == null) {
			return false;
		}
		String sql = "select * from customer where emailId=?";
		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, useremail);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					String pass = rs.getString("custPassword");
					if (userpass.equals(pass)) {
						return true;
					} else {
						return false;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean adminLogin(String adminemail, String adminpass) {
		// TODO Auto-generated method stub
		if (adminemail == null || adminpass == null) {
			return false;
		}
		String sql = "select * from admin where email=?";
		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, adminemail);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					String pass = rs.getString("password");
					if (adminpass.equals(pass)) {
						return true;
					} else {
						return false;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
