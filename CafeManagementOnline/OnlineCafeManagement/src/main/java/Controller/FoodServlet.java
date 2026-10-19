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

import IMPL.FoodDaoImpl;
import POJO.Food;

/**
 * Servlet implementation class FoodServlet
 */
@WebServlet("/FoodServlet")
public class FoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FoodServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String process = request.getParameter("process");
		FoodDaoImpl fdimpl = new FoodDaoImpl();
		if (process != null && process.equals("allfood")) {
			List<Food> list = fdimpl.getAllfood();
			session.setAttribute("list", list);
			response.sendRedirect("FoodList.jsp");
		} else if (process != null && process.equals("updateFood")) {
			int food_Id = Integer.parseInt(request.getParameter("food_Id"));
			Food f = fdimpl.SearchFood(food_Id);
			session.setAttribute("fobj", f);
			response.sendRedirect("UpdateFood.jsp");
		} else if (process != null && process.equals("deleteFood")) {
			int foodId = Integer.parseInt(request.getParameter("food_Id"));
			boolean flag = fdimpl.deletefood(foodId);
			if (flag) {
				session.setAttribute("msg", "Food Deleted Successfully");
			} else {
				session.setAttribute("errmsg", "Delete Failed");
			}
			session.setAttribute("list", fdimpl.getAllfood());
			response.sendRedirect("FoodList.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String process = request.getParameter("process");
		FoodDaoImpl fdimpl = new FoodDaoImpl();

		if (process != null && process.equals("Addfood")) {
			String foodName = request.getParameter("foodName");
			String foodType = request.getParameter("foodType");
			String foodCategory = request.getParameter("foodCategory");
			String foodDesc = request.getParameter("Des");
			Double price = Double.parseDouble(request.getParameter("Price"));
			String image = request.getParameter("image");

			Food f = new Food(foodName, foodType, foodCategory, foodDesc, price, image);
			boolean flag = fdimpl.addfood(f);
			if (flag) {
				session.setAttribute("msg", "Successfully Inserted in database");
				session.setAttribute("list", fdimpl.getAllfood());
				response.sendRedirect("FoodList.jsp");
			} else {
				request.setAttribute("errmsg", "Fail to Insert in Database");
				RequestDispatcher rd = request.getRequestDispatcher("AddFood.jsp");
				rd.forward(request, response);
			}
		} else if (process != null && process.equals("editFood")) {
			String foodName = request.getParameter("foodName");
			String foodType = request.getParameter("foodType");
			String foodCategory = request.getParameter("foodCategory");
			String foodDes = request.getParameter("des");
			double foodPrice = Double.parseDouble(request.getParameter("price"));
			String image = request.getParameter("image");
			int foodId = Integer.parseInt(request.getParameter("food_Id"));

			Food f = new Food();
			f.setFoodName(foodName);
			f.setFoodType(foodType);
			f.setFoodCategory(foodCategory);
			f.setDes(foodDes);
			f.setPrice(foodPrice);
			f.setImage(image);
			f.setFood_Id(foodId);

			boolean flag = fdimpl.updatefood(f);
			if (flag) {
				session.setAttribute("msg", "Successfully Updated");
				session.setAttribute("list", fdimpl.getAllfood());
				response.sendRedirect("FoodList.jsp");
			} else {
				session.setAttribute("errmsg", "Fail while try to update");
				RequestDispatcher rd = request.getRequestDispatcher("UpdateFood.jsp");
				rd.forward(request, response);
			}
		}
	}
}
