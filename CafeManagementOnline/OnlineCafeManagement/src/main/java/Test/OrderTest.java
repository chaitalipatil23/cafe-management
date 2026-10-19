package Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import IMPL.CartDaoImpl;
import IMPL.LoginDaoImpl;
import IMPL.OrderDaoImpl;
import POJO.Cart;
import POJO.OrderFood;

public class OrderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		String orderStatus;
		OrderFood o=null;
		OrderDaoImpl odimpl=new OrderDaoImpl();
		LoginDaoImpl ldimpl=new LoginDaoImpl();
		CartDaoImpl cdimpl=new CartDaoImpl();
		Boolean login,flag;
		boolean exit=false;
		List<OrderFood> olist=new ArrayList<OrderFood>();
		System.out.println("Enter emailId");
		String emailId=sc.next();
		System.out.println("Enter password");
		String password=sc.next();
		
		login=ldimpl.userLogin(emailId, password);
		if(login) {
			do {
				System.out.println("1->Order place");
				System.out.println("2->Show my order history");
				int choice;
				System.out.println("Enter your Choice");
				choice=sc.nextInt();
				switch(choice) {
				case 1:{
				List <Cart> clist=cdimpl.searchByEmailId(emailId);
				if(clist!=null && clist.isEmpty()!=true) {
					for(Cart l:clist) {
						System.out.println(l);
						System.out.println("-----------------");
					}
					System.out.println("Proceed to buy Say yes or no");
					String answer=sc.next().toLowerCase();
					if(answer.equals("yes")) {
						orderStatus="processing";
						o=new OrderFood();
						o.setOrderStatus(orderStatus);
						o.setEmailId(emailId);
						flag=odimpl.placeOrder(o);
						if(flag) {
							System.out.println("Order placed Successfully");
							LocalTime time=LocalTime.now().plusHours(1);
							System.out.println("Delivery will be done before "+time);
						}
						else {
							System.out.println("Error while placing order");
						}
					}
					else if(answer.equals("no")) {
						System.out.println("Thankyou for being with us");
					}
					else {
						System.out.println("Please give answer in yes or no only");
					}
				}//outer if closing
				else {
					System.out.println("You do not have item in your cart...Therefore you cant place order");
				}
				}
				break;
				case 2:{
					olist=odimpl.showMyOrderHistory(emailId);
					if(olist!=null && olist.isEmpty()!=true) {
						for(OrderFood o1:olist) {
							System.out.println("Order no: "+o1.getOrderId());
							System.out.println("Oder date: "+o1.getOrderDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
							System.out.println("Bill: "+o1.getTotalBill());
							System.out.println("Status: "+o1.getOrderStatus());
							System.out.println("--------------------------------------");
						}
					}
						else {
							System.out.println("You have not ordered any thing from us til now");
						}
				}
				}//switch closing
			} while(exit==false);//do closing
		}//if closing
		else {
			login=ldimpl.adminLogin(emailId, password);
			if(login) {
			System.out.println("1->Get order by orderId");
			System.out.println("2->Get all orders");
			int choice=sc.nextInt();
			switch (choice) {
			case 1:{
				System.out.println("Enter the order Id");
				int orderid=sc.nextInt();
				 o=odimpl.showOrderById(orderid);
				 if(o!=null ) {
				 System.out.println(o);
				 }else {
					 System.out.println("Order not found");
				 }
			}
			break;
			case 2:{
				olist=odimpl.showAllOrder();
				 if (olist.isEmpty()) {
				        System.out.println("No orders found.");
				    } else {
				        for (OrderFood o1 : olist) {
				            System.out.println(o1);
				        }
				    }
				 
				    break;
			}//switch closing
		}
			}else {
				System.out.println("Please enter correct emailId and password");
			}
	}

	}
}
