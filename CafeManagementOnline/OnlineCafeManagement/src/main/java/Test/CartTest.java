package Test;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import IMPL.CartDaoImpl;
import IMPL.FoodDaoImpl;
import IMPL.LoginDaoImpl;
import POJO.Cart;
import POJO.Food;

public class CartTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean flag,exit=false;
		Cart c=null;
		FoodDaoImpl fdimpl=new FoodDaoImpl();
		LoginDaoImpl ldimpl=new LoginDaoImpl();
		CartDaoImpl cdimpl=new CartDaoImpl();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter EmailId");
		String  emailId=sc.next();
		System.out.println("Enter password");
		String password=sc.next();
		boolean login=false;
		 login=ldimpl.userLogin(emailId, password);
		if(login==true) {
			do {
				
				System.out.println("1---------->Add to cart");
				System.out.println("2---------->Show my cart");
				System.out.println("3---------->Update quantity of item");
				System.out.println("4---------->Delete item from cart");
				System.out.println("5---------->Clear my cart");
				int choice=sc.nextInt();
				switch(choice) {
				case 1:{
					List <Food> list=fdimpl.getAllfood();
					for(Food l:list) {
						System.out.println(l);
					}
					System.out.println("Enter FoodId which food wants to add in cart");
					int foodid=sc.nextInt();
					System.out.println("Enter food quantity");
					int fquantity=sc.nextInt();
					c=new Cart();
					c.setFoodid(foodid);
					c.setFquantity(fquantity);
					c.setcEmail(emailId);
					flag=cdimpl.addtoCart(c);
					if(flag) {
						System.out.println("Successfully added in cart");
						
					}
					else {
						System.out.println("Fail to add into cart");
					}
					
				}
				break;
				case 2:{
					List <Cart>list1=cdimpl.searchByEmailId(emailId);
					if(list1==null || list1.isEmpty()) {
						System.out.println("Cart is empty...Please add item into cart");
					}
					else {
						double bill=0;
						
						for(Cart l:list1) {
							bill=bill+l.getTotalPrice();
							System.out.println(l);
							
						}
						System.out.println("Amount Payable= "+bill);
					}
					
				}
				break;
				case 3:{
					System.out.println("Enter CartId whose items quantity wants to increase");
					int cartId=sc.nextInt();
					System.out.println("Enter quantity of specific item");
					int quantity=sc.nextInt();
					flag=cdimpl.updatecart(cartId, quantity);
					if(flag) {
						System.out.println("Quantity Updated Successfully");
					}
					else {
						System.out.println("Failed ");
					}
				}
				break;
				case 4:{
					System.out.println("Enter CartId to remove an item");
					int cartId=sc.nextInt();
					flag=cdimpl.deleteBycartId(cartId);
					if(flag) {
						System.out.println("Successfully deleted");
					}
					else {
						System.out.println("Unsuccessful");
					}
				}
				break;
				case 5:{
					flag=cdimpl.deleteByemailId(emailId);
					if(flag) {
						System.out.println("Successful cleared all items from cart");
					}
					else {
						System.out.println("Unsuccessful");
					}
					}
				break;
				
				}//switch closing
				
			}while (exit==false);//loop closing
		
			
		}
		else {
			login=ldimpl.adminLogin(emailId, password);
			if(login) {
				List<Cart> list=cdimpl.getCartList();
				if(list==null && list.isEmpty()) {
					System.out.println("No one has added any item into Cart");
				}
				else {
					for(Cart l:list) {
						System.out.println(l);
					}
				}
			}
			else {
				System.out.println("Please check Email-id and password \nLogin Failed...it could not proceed further");
			}
		}
		
		

	}

}
