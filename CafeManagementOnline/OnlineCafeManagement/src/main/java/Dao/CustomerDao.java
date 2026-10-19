package Dao;

import java.util.List;

import POJO.Customer;

public interface CustomerDao {
	boolean addCustomer(Customer c);
	boolean updateCustomer(Customer c);
	boolean deleteCustomer(String emailId);
	Customer SearchCustomerByemail(String emailId);
	List<Customer> getAllCustomer();
}
 