package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BankAccount;
import dto.Customer;

@WebServlet("/fetchactiveaccount")
public class FetchActiveAccount extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Customer customer = (Customer) req.getSession().getAttribute("customer");

		if (customer == null) {
			res.getWriter().print("<h1>Session Expired</h1><h1>Login Again</h1>");
			req.getRequestDispatcher("LogIn.Html").include(req, res);
		} else {
			List<BankAccount> list = customer.getAccounts();
			List<BankAccount> list2 = new ArrayList<BankAccount>();
			for (BankAccount account : list) {
				if (account.isStatus()) {
					list2.add(account);
				}
			}
			req.setAttribute("list", list2);
			req.getRequestDispatcher("Accounts.jsp").include(req, res);
		}
	}
}
