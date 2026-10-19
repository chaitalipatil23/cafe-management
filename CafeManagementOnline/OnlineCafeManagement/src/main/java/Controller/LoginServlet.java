package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import IMPL.LoginDaoImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Logout: invalidate session and go back to login page
		String action = request.getParameter("action");
		if ("logout".equals(action)) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			response.sendRedirect("Login.jsp");
			return;
		}
		response.sendRedirect("Login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("email");
		String password = request.getParameter("password");
		LoginDaoImpl ldimpl = new LoginDaoImpl();
		boolean flag = ldimpl.userLogin(username, password);
		if (flag) {
			session.setAttribute("login", "customer");
			session.setAttribute("msg", "Successful Login by customer");
			session.setAttribute("username", username);
			RequestDispatcher rd = request.getRequestDispatcher("Myindex.jsp");
			rd.forward(request, response);
		} else {
			flag = ldimpl.adminLogin(username, password);
			if (flag) {
				session.setAttribute("login", "admin");
				session.setAttribute("msg", "Successful Login by Admin");
				session.setAttribute("username", username);
				RequestDispatcher rd = request.getRequestDispatcher("Myindex.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("error", "Invalid email or password");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		}
	}
}
