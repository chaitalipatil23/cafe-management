package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import IMPL.CustomerDaoImpl;
import POJO.Customer;

/**
 * Servlet implementation class CustomerServlet
 * Handles customer registration (Register link in the header).
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("AddCustomer.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String process = request.getParameter("process");

		if (process != null && process.equals("Register")) {
			String custName = request.getParameter("custName");
			String emailId = request.getParameter("emailId");
			String custPassword = request.getParameter("custPassword");
			String custAddress = request.getParameter("custAddress");
			String custNo = request.getParameter("custNo");
			String custLocation = request.getParameter("custLocation");

			if (emailId == null || emailId.trim().isEmpty() || custPassword == null
					|| custPassword.trim().isEmpty()) {
				request.setAttribute("error", "Email and password are required");
				RequestDispatcher rd = request.getRequestDispatcher("AddCustomer.jsp");
				rd.forward(request, response);
				return;
			}

			CustomerDaoImpl cdimpl = new CustomerDaoImpl();
			if (cdimpl.SearchCustomerByemail(emailId.trim()) != null) {
				request.setAttribute("error", "This email is already registered. Please login.");
				RequestDispatcher rd = request.getRequestDispatcher("AddCustomer.jsp");
				rd.forward(request, response);
				return;
			}

			Customer c = new Customer(custName, emailId.trim(), custPassword, custAddress, custNo,
					custLocation);
			boolean flag = cdimpl.addCustomer(c);
			if (flag) {
				session.setAttribute("login", "customer");
				session.setAttribute("username", emailId.trim());
				session.setAttribute("msg", "Registration successful. Welcome " + custName + "!");
				response.sendRedirect("Myindex.jsp");
			} else {
				request.setAttribute("error", "Registration failed. Please try again.");
				RequestDispatcher rd = request.getRequestDispatcher("AddCustomer.jsp");
				rd.forward(request, response);
			}
		}
	}
}
