package Dao;

import java.util.List;

import POJO.Cart;

public interface CartDao {
	boolean addtoCart(Cart cart);
	List <Cart> getCartList();
	Cart searchByCartId(int cartId);
	List<Cart> searchByEmailId(String emailId);
	boolean deleteBycartId(int cartId);
	boolean deleteByemailId(String email);
	boolean updatecart(int cartId,int fquantity);

}
