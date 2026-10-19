package Test;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import IMPL.CustomerDaoImpl;
import POJO.Customer;
import POJO.Food;

public class CustomerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String custName;
		 String emailId;
		 String custPassword;
		 String custAddress;
		 String custNo;
		 String custLocation;
		 CustomerDaoImpl cdimpl=new CustomerDaoImpl();
		 boolean flag;
		 
		 Scanner sc=new Scanner (System.in);
		 System.out.println(" 1.AddCustomer\n 2.UpdateCustomer\n 3.deleteCustomer\n 4.Search by email\n 5.Get all Customer\n   ");
		 System.out.println("Enter your choice");
		 int choice=sc.nextInt();
		 switch(choice) {
		 case 1:{
			 System.out.println("Enter the Customer name");
			  custName=sc.next();
			  System.out.println("Enter the Customer emailId");
			  emailId=sc.next();
			  System.out.println("Enter the Customer Password");
			  custPassword=sc.next();
			  System.out.println("Enter the Customer Address");
			  custAddress=sc.next();
			  System.out.println("Enter the Customer Phone no");
			  custNo=sc.next();
			  System.out.println("Enter the Customer Location");
			  custLocation=sc.next();
			  Customer c=new Customer(custName,emailId,custPassword,custAddress,custNo,custLocation);
			  flag=cdimpl.addCustomer(c);
			  if(flag) {
				  System.out.println("Customer added successfully");
			  }
			  else {
				  System.out.println("Unsuccessful");
			  }
			  
		 }
		 break;
		 case 2:{
			 System.out.println("Enter the existing email id to update");
			 emailId=sc.next();
			 System.out.println("Enter Customer Name");
			 custName=sc.next();
			 System.out.println("Enter Customer Password");
			  custPassword=sc.next();
			  System.out.println("Enter the Customer Address");
			  custAddress=sc.next();
			  System.out.println("Enter the Customer Phone no");
			  custNo=sc.next();
			  System.out.println("Enter the Customer Location");
			  custLocation=sc.next();
				
				Customer c=new Customer();
				c.setCustName(custName);
				c.setCustPassword(custPassword);
				c.setCustAddress(custAddress);
				c.setCustNo(custNo);
				c.setCustLocation(custLocation);
				c.setEmailId(emailId);
				flag=cdimpl.updateCustomer(c);
				if(flag) {
					System.out.println("Customer Updated Successfully");
				}
				else {
					System.out.println("Fail to Update");
				}
				
			 
		 }
		 break;
		 case 3:{
			 System.out.println("Enter the emailId to delete the Customer");
			 emailId=sc.next();
			 flag=cdimpl.deleteCustomer(emailId);
			 if(flag) {
				 System.out.println("Deleted Successfully");
			 }
			 else {
				 System.out.println("Unsuccessful");
			 }
			 
		 }
		 break;
		 case 4:{
			 System.out.println("Enter the emailId to search the Customer");
			 emailId=sc.next();
			 Customer c1=cdimpl.SearchCustomerByemail(emailId);
			 if(c1!=null) {
				 System.out.println(c1);
			 }
			 
		 }
		 break;
		 case 5:{
			 List <Customer> l=cdimpl.getAllCustomer();
			 if(l!=null) {
					Iterator<Customer> i=l.iterator();
					while(i.hasNext()) {
						System.out.println(i.next());
					}
				}
		 }
		 break;
		 }
	}

}
