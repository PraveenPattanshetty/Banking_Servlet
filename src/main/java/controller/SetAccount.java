package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BankDAO;
import dto.BankAccount;

@WebServlet("/setaccount")
public class SetAccount extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		long accno = Long.parseLong(req.getParameter("accno"));
		// BankDAO bankDAO = new BankDAO();
		// BankAccount account = bankDAO.find(accno);
		req.getSession().setAttribute("accno", accno);
		req.getRequestDispatcher("AccountHome.jsp").include(req, res);

	}
}
