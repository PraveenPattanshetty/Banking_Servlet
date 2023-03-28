package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BankDAO;
import dto.BankAccount;

@WebServlet("/changestatus")
public class ChangeAccountStatus extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Long accno = Long.parseLong(req.getParameter("accno"));

		BankDAO bankDAO = new BankDAO();
		BankAccount account = bankDAO.find(accno);

		if (account.isStatus()) {
			account.setStatus(false);
		} else {
			account.setStatus(true);
		}
		bankDAO.update(account);
		
		res.getWriter().print("<h1>Update Succesful</h1>");
		req.setAttribute("list", bankDAO.fetchAll());
		req.getRequestDispatcher("AdminHome.jsp").include(req, res);
	}
}
