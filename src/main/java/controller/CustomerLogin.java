package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDAO;
import dto.Customer;

@WebServlet("/Customerlogin")
public class CustomerLogin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int cust_id = Integer.parseInt(req.getParameter("cust_id"));
		String password = req.getParameter("password");

		CustomerDAO dao = new CustomerDAO();
		Customer customer = dao.login(cust_id);

		if (customer == null) {
			res.getWriter().print("<h1>Inavlid Customer ID</h1>");
			req.getRequestDispatcher("LogIn.html").include(req, res);
		} else {
			if (customer.getPassword().equals(password)) {
				req.getSession().setAttribute("customer", customer);
				res.getWriter().print("<h1>Login Successful</h1>");
				req.getRequestDispatcher("CustomerHome.html").include(req, res);
			} else {
				res.getWriter().print("<h1>Invalid Password</h1>");
				req.getRequestDispatcher("LogIn.html").include(req, res);
			}
		}
	}
}
