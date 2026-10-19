package Test;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import IMPL.FoodDaoImpl;
import POJO.Food;

public class FoodTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int food_Id;
		 String foodName;
		 String foodType;
		 String foodCategory;
		 String Des;
		 double Price;
		 String image;
		 FoodDaoImpl fimpl=new FoodDaoImpl();
		 Boolean flag;
		
		Scanner sc=new Scanner(System.in);
		System.out.println(" 1.Add Food\n 2.Update Food\n 3.Delete Food\n 4.Search Food\n 5.Get All Food\n 6.Search Food by name\n 7.Search Food by Category");
		int choice;
		System.out.println("Enter Your Choice");
		choice=sc.nextInt();
		
		
		switch(choice) {
		case 1:
		{
		System.out.println("Enter Food Name");
		foodName=sc.next();
		sc.nextLine();
		System.out.println("Enter Food type");
		foodType=sc.nextLine();
		System.out.println("Enter Food Category");
		foodCategory=sc.nextLine();
		System.out.println("Enter Food Desc");
		Des=sc.nextLine();
		System.out.println("Enter Food Price");
		Price=sc.nextDouble();
		System.out.println("Enter Food Image");
		image=sc.nextLine();
		sc.nextLine();
			Food f=new Food(foodName,foodType,foodCategory,Des,Price,image);
		flag=fimpl.addfood(f);
		if(flag) {
			System.out.println("Food Item added Successfully");
		 }
		else {
			System.out.println("Fail to Insert");
		}
		
		}
		break;
		case 2:
		{
			System.out.println("Enter the existing food Id to Update");
			food_Id=sc.nextInt();
		System.out.println("Enter Food Name");
		foodName=sc.next();
		sc.nextLine();
		System.out.println("Enter Food type");
		foodType=sc.nextLine();
		System.out.println("Enter Food Category");
		foodCategory=sc.nextLine();
		System.out.println("Enter Food Desc");
		Des=sc.nextLine();
		System.out.println("Enter Food Price");
		Price=sc.nextDouble();
		System.out.println("Enter Food Image");
		image=sc.nextLine();
		sc.nextLine();
		Food f=new Food();
		f.setFoodName(foodName);
		f.setFoodType(foodType);
		f.setFoodCategory(foodCategory);
		f.setDes(Des);
		f.setPrice(Price);
		f.setImage(image);
		f.setFood_Id(food_Id);
		flag=fimpl.updatefood(f);
		if(flag) {
			System.out.println("Food Item Updated Successfully");
		}
		else {
			System.out.println("Fail to Update");
		}
		
		}
		break;
		case 3:
		{
			System.out.println("Enter existing food id which wants to delete ");
			food_Id=sc.nextInt();
			flag=fimpl.deletefood(food_Id);
			if(flag) {
				System.out.println("Successfully deleted ");
			}
			else {
				System.out.println("Fail to delete");
			}
		}
			break;
		case 4:
		{
			System.out.println("Enter id which yo want to search");
			food_Id=sc.nextInt();
			Food f1=fimpl.SearchFood(food_Id);
			if(f1!=null) {
				System.out.println(f1);
			}
		}
		break;
		case 5:
		{
			List <Food> l=fimpl.getAllfood();
			if(l!=null) {
				Iterator<Food> i=l.iterator();
				while(i.hasNext()) {
					System.out.println(i.next());
				}
			}
		}
			break;
			case 6:
			{
				System.out.println("Enter existing foodname");
				foodName=sc.next();
				List <Food> l=fimpl.searchFoodByname(foodName);
				if(l!=null) {
					Iterator<Food> i=l.iterator();
					while(i.hasNext()) {
						System.out.println(i.next());
					}
				}
			}
				break;
			case 7:
			{
				System.out.println("Enter existing foodCategory");
				foodCategory=sc.next();
				List <Food> l=fimpl.searchFoodBycategory(foodCategory);
				
				if(l!=null) {
					Iterator<Food> i=l.iterator();
					while(i.hasNext()) {
						System.out.println(i.next());
					}
				}
			}
				break;
				
			}
		}
		
	}



	
