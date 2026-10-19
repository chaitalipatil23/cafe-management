package IMPL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.FoodDao;
import POJO.Food;
import Utility.DBUtility;

public class FoodDaoImpl implements FoodDao {

	@Override
	public boolean addfood(Food f) {
		// TODO Auto-generated method stub
		String sql = "insert into food (foodName,foodType,foodCategory,Des,Price,image) value (?,?,?,?,?,?)";
		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, f.getFoodName());
			ps.setString(2, f.getFoodType());
			ps.setString(3, f.getFoodCategory());
			ps.setString(4, f.getDes());
			ps.setDouble(5, f.getPrice());
			ps.setString(6, f.getImage());
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
	public boolean updatefood(Food f) {
		// TODO Auto-generated method stub
		String sql = "update food set foodName=?,foodType=?,foodCategory=?,Des=?,Price=?,image=? where foodId=?";
		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, f.getFoodName());
			ps.setString(2, f.getFoodType());
			ps.setString(3, f.getFoodCategory());
			ps.setString(4, f.getDes());
			ps.setDouble(5, f.getPrice());
			ps.setString(6, f.getImage());
			ps.setInt(7, f.getFood_Id());
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
	public boolean deletefood(int foodId) {
		// TODO Auto-generated method stub
		String sql = "delete from food where foodId=?";
		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, foodId);
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
	public Food SearchFood(int foodId) {
		// TODO Auto-generated method stub
		Food f = null;
		String sql = "select * from food where  foodId =?";
		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, foodId);
			try (ResultSet rs = ps.executeQuery()) {
				f = new Food();
				if (rs.next()) {
					f.setFood_Id(rs.getInt("foodId"));
					f.setFoodName(rs.getString("foodName"));
					f.setFoodType(rs.getString("foodType"));
					f.setFoodCategory(rs.getString("foodCategory"));
					f.setDes(rs.getString("Des"));
					f.setPrice(rs.getDouble("Price"));
					f.setImage(rs.getString("image"));
					return f;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Food> getAllfood() {
		// TODO Auto-generated method stub
		List<Food> l = new ArrayList<>();
		String sql = "select * from food";
		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Food f = new Food();
				f.setFood_Id(rs.getInt(1));
				f.setFoodName(rs.getString(2));
				f.setFoodType(rs.getString(3));
				f.setFoodCategory(rs.getString(4));
				f.setDes(rs.getString(5));
				f.setPrice(rs.getDouble(6));
				f.setImage(rs.getString(7));
				l.add(f);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public List<Food> searchFoodByname(String foodName) {
		// TODO Auto-generated method stub
		List<Food> l = new ArrayList<>();
		String sql = "select * from food where foodName=?";
		try (Connection con = DBUtility.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, foodName);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Food f = new Food();
					f.setFood_Id(rs.getInt(1));
					f.setFoodName(rs.getString(2));
					f.setFoodType(rs.getString(3));
					f.setFoodCategory(rs.getString(4));
					f.setDes(rs.getString(5));
					f.setPrice(rs.getDouble(6));
					f.setImage(rs.getString(7));
					l.add(f);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public List<Food> searchFoodBycategory(String foodCategory) {

		List<Food> l = new ArrayList<>();

		try (Connection con = DBUtility.getConnection()) {
			String sql = "select * from food where foodCategory=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, foodCategory);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Food f = new Food();
					f.setFood_Id(rs.getInt(1));
					f.setFoodName(rs.getString(2));
					f.setFoodType(rs.getString(3));
					f.setFoodCategory(rs.getString(4));
					f.setDes(rs.getString(5));
					f.setPrice(rs.getDouble(6));
					f.setImage(rs.getString(7));
					l.add(f);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
}
