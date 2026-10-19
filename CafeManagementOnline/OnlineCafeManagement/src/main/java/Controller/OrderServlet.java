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
import IMPL.OrderDaoImpl;
import POJO.OrderFood;

/**
 * Servlet implementation class OrderServlet
 * Handles placing orders and viewing order history.
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("username");
		if (email == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		String process = request.getParameter("process");
		OrderDaoImpl odimpl = new OrderDaoImpl();

		if (process != null && process.equals("myOrders")) {
			List<OrderFood> olist = odimpl.showMyOrderHistory(email);
			session.setAttribute("olist", olist);
			response.sendRedirect("MyOrders.jsp");
		} else {
			response.sendRedirect("CartServlet?process=ShowCart");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("username");
		if (email == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		String process = request.getParameter("process");

		if (process != null && process.equals("PlaceOrder")) {
			OrderDaoImpl odimpl = new OrderDaoImpl();
			OrderFood o = new OrderFood();
			o.setEmailId(email);
			o.setOrderStatus("Placed");
			boolean flag = odimpl.placeOrder(o);
			if (flag) {
				// Empty the cart after a successful order
				new CartDaoImpl().deleteByemailId(email);
				session.setAttribute("msg", "Order placed successfully!");
			} else {
				session.setAttribute("errmsg", "Failed to place order. Your cart may be empty.");
			}
			List<OrderFood> olist = odimpl.showMyOrderHistory(email);
			session.setAttribute("olist", olist);
			response.sendRedirect("MyOrders.jsp");
		} else {
			doGet(request, response);
		}
	}
}
