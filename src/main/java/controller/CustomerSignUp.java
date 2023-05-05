package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDAO;
import dto.Customer;

@WebServlet("/customersignup")
public class CustomerSignUp extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		CustomerDAO dao = new CustomerDAO();

		// String Name = req.getParameter("name");
//		 String Mobile = req.getParameter("mobile");
		// String Password = req.getParameter("password");
		// String Gender = req.getParameter("gender");
		String Email = req.getParameter("email");
//		String DOB = req.getParameter("dob");

		Long Mobile = Long.parseLong(req.getParameter("mobile"));

		// res.getWriter().print("<h1>"+Name+"</h1>");
		// res.getWriter().print("<h1>"+Mobile+"</h1>");
		// res.getWriter().print("<h1>"+Email+"</h1>");
		// res.getWriter().print("<h1>"+Password+"</h1>");
		// res.getWriter().print("<h1>"+Gender+"</h1>");
		// res.getWriter().print("<h1>"+DOB+"</h1>");

		Date date = Date.valueOf(req.getParameter("dob"));// this is in sql
															// date.toLocalDate()
															// to
															// exact date in
															// java
		int age = Period.between(date.toLocalDate(), LocalDate.now()).getYears();

		if (age < 18) {
			res.getWriter().print(
					"<h1>YOU ARE NOT ELIGIBLE TO CREATE A BANK ACCOUNT </h1>" + "<h1>WAIT TILL YOU BECOME 18+</h1>");
			req.getRequestDispatcher("SignUp.html").include(req, res);
		} else {
			if (dao.check(Mobile).isEmpty() && dao.check(Email).isEmpty()) {

				Customer customer = new Customer();
				customer.setName(req.getParameter("name"));
				customer.setGender(req.getParameter("gender"));
				customer.setPassword(req.getParameter("password"));
				customer.setEmail(Email);
				customer.setDOB(date);
				customer.setMobile(Mobile);

				dao.save(customer);

				Customer customer2 = dao.check(Email).get(0);

				res.getWriter().print("<h1>Account Created Succesfully</h1>");
				if (customer2.getGender().equals("male")) {
					res.getWriter().print("<h1>Hello Sir</h1>");
				} else {
					res.getWriter().print("<h1>Hello Ma'am</h1>");
				}
				res.getWriter().print("<h1>Your Customer Id is: " + customer2.getCust_id() + "</h1>");
				req.getRequestDispatcher("Home.html").include(req, res);

			} else {
				res.getWriter().print("<h1>Email or Mobile Number Already Exists</h1>"
						+ "<h1>Please Enter With Different Email Or Mobile Number</h1>");
				req.getRequestDispatcher("SignUp.html").include(req, res);
			}
		}
	}
}
