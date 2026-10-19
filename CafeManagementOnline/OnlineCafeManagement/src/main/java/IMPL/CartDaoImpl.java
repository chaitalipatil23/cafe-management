package IMPL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.CartDao;
import POJO.Cart;
import POJO.Food;
import Utility.DBUtility;

public class CartDaoImpl implements CartDao {

	@Override
	public boolean addtoCart(Cart c) {
		// TODO Auto-generated method stub

		String sql = "insert into cart (foodid,cEmail,fquantity,fname,fprice,totalPrice) values(?,?,?,?,?,?)";
		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			FoodDaoImpl fdimpl = new FoodDaoImpl();
			Food f = fdimpl.SearchFood(c.getFoodid());
			if (f == null) {
				return false;
			}
			ps.setInt(1, c.getFoodid());
			ps.setString(2, c.getcEmail());
			ps.setInt(3, c.getFquantity());
			ps.setString(4, f.getFoodName());
			ps.setDouble(5, f.getPrice());
			double totalPrice = f.getPrice() * c.getFquantity();
			ps.setDouble(6, totalPrice);
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
	public List<Cart> getCartList() {
		// TODO Auto-generated method stub
		String sql = "select * from cart";
		List<Cart> l = new ArrayList<Cart>();
		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Cart c = new Cart();
				c.setCartid(rs.getInt("cartid"));
				c.setFoodid(rs.getInt("foodid"));
				c.setcEmail(rs.getString("cEmail"));
				c.setFquantity(rs.getInt("fquantity"));
				c.setFname(rs.getString("fname"));
				c.setFprice(rs.getDouble("fprice"));
				c.setTotalPrice(rs.getDouble("totalPrice"));
				l.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return l;
	}

	@Override
	public Cart searchByCartId(int cartId) {
		// TODO Auto-generated method stub
		String sql = "select * from cart where cartid=?";

		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, cartId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Cart c = new Cart();
					c.setCartid(rs.getInt("cartid"));
					c.setFoodid(rs.getInt("foodid"));
					c.setcEmail(rs.getString("cEmail"));
					c.setFquantity(rs.getInt("fquantity"));
					c.setFname(rs.getString("fname"));
					c.setFprice(rs.getDouble("fprice"));
					c.setTotalPrice(rs.getDouble("totalPrice"));
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
	public List<Cart> searchByEmailId(String emailId) {
		// TODO Auto-generated method stub

		List<Cart> l = new ArrayList<Cart>();
		try (Connection con = DBUtility.getConnection()) {
			String sql = "select * from cart where cEmail=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, emailId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Cart c = new Cart();
					c.setCartid(rs.getInt("cartid"));
					c.setFoodid(rs.getInt("foodid"));
					c.setcEmail(rs.getString("cEmail"));
					c.setFquantity(rs.getInt("fquantity"));
					c.setFname(rs.getString("fname"));
					c.setFprice(rs.getDouble("fprice"));
					c.setTotalPrice(rs.getDouble("totalPrice"));
					l.add(c);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return l;
	}

	@Override
	public boolean deleteBycartId(int cartId) {
		// TODO Auto-generated method stub
		String sql = "delete from cart where cartid=?";
		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, cartId);
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
	public boolean deleteByemailId(String email) {
		// TODO Auto-generated method stub
		String sql = "delete from cart where cEmail=?";
		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, email);
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
	public boolean updatecart(int cartid, int fquantity) {
		// TODO Auto-generated method stub
		String sql = "update cart set fquantity=? where cartid=?";
		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, fquantity);
			ps.setInt(2, cartid);
			int i = ps.executeUpdate();
			if (i > 0) {
				Cart c = searchByCartId(cartid);
				double totalprice = c.getFprice() * c.getFquantity();
				String sql1 = "update cart set totalPrice=? where cartid=?";
				PreparedStatement ps1 = con.prepareStatement(sql1);
				ps1.setDouble(1, totalprice);
				ps1.setInt(2, cartid);
				int i1 = ps1.executeUpdate();
				if (i1 > 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
