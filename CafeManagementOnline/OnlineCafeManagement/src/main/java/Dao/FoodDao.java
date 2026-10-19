package Dao;

import java.util.List;

import POJO.Food;

public interface FoodDao {
	boolean addfood(Food f);
	boolean updatefood(Food f);
	boolean deletefood(int foodId);
	Food SearchFood(int foodId);
	
	List<Food> getAllfood();
	List <Food> searchFoodByname(String foodname);
	List <Food> searchFoodBycategory(String foodCategory);
	
	

}
