package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import IMPL.CartDaoImpl;
import IMPL.FoodDaoImpl;
import POJO.Cart;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CartDaoImpl cdimpl = new CartDaoImpl();
		FoodDaoImpl fdimpl = new FoodDaoImpl();
		HttpSession session = request.getSession();
		String process = request.getParameter("process");
		if (process != null && process.equals("addToCart")) {
			String email = (String) session.getAttribute("username");
			if (email == null) {
				response.sendRedirect("Login.jsp");
				return;
			}
			int food_Id = Integer.parseInt(request.getParameter("food_Id"));
			int quantity = 1;
			Cart c = new Cart();
			c.setFoodid(food_Id);
			c.setcEmail(email);
			c.setFquantity(quantity);
			boolean flag = cdimpl.addtoCart(c);
			if (flag) {
				session.setAttribute("msg", "1 item added in cart");
			} else {
				session.setAttribute("errmsg", "fail to add in cart");
			}
			session.setAttribute("list", fdimpl.getAllfood());
			response.sendRedirect("FoodList.jsp");
		} else if (process != null && process.equals("ShowCart")) {
			String email = (String) session.getAttribute("username");
			if (email == null) {
				response.sendRedirect("Login.jsp");
				return;
			}
			List<Cart> clist = cdimpl.searchByEmailId(email);
			if (clist == null || clist.isEmpty()) {
				request.setAttribute("errMsg", "Your cart is empty");
				session.setAttribute("list", new FoodDaoImpl().getAllfood());
				RequestDispatcher rd = request.getRequestDispatcher("FoodList.jsp");
				rd.forward(request, response);
			} else {
				session.setAttribute("clist", clist);
				response.sendRedirect("CartList.jsp");
			}
		} else if (process != null && process.equals("removeFromCart")) {
			int cartId = Integer.parseInt(request.getParameter("cartId"));
			cdimpl.deleteBycartId(cartId);
			String email = (String) session.getAttribute("username");
			session.setAttribute("clist", cdimpl.searchByEmailId(email));
			response.sendRedirect("CartList.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
