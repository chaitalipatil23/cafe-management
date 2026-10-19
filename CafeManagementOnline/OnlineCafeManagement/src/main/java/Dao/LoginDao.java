package Dao;

public interface LoginDao {
	boolean userLogin(String useremail, String userpass);
	boolean adminLogin(String adminemail,String adminpass);
	

}
