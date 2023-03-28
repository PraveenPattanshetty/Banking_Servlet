package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BankDAO;

@WebServlet("/adminlogin")
public class AdminLogin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		if (email.equals("admin") && password.equals("admin")) {
			resp.getWriter().print("<h1>Login Succesful</h1>");

			BankDAO dao = new BankDAO();

			req.setAttribute("list", dao.fetchAll());
			req.getRequestDispatcher("AdminHome.jsp").include(req, resp);
		} else {
			resp.getWriter().print("<h1>Inavlid Credentials</h1>");
			req.getRequestDispatcher("AdminLogIn.html").include(req, resp);
		}

	}
}
