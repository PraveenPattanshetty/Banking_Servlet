package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BankDAO;
import dto.BankAccount;
import dto.BankTransaction;
import dto.Customer;

@WebServlet("/withdraw")
public class Withdraw extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Customer customer = (Customer) req.getSession().getAttribute("customer");

		if (customer == null) {
			res.getWriter().print("<h1>Session Expired</h1><h1>Login Again</h1>");
			req.getRequestDispatcher("LogIn.Html").include(req, res);
		} else {

			double amt = Double.parseDouble(req.getParameter("amt"));

			long accno = (Long) req.getSession().getAttribute("accno");
			BankDAO bankDAO = new BankDAO();
			BankAccount account = bankDAO.find(accno);

			if (amt > account.getAmount()) {
				res.getWriter().print("<h1>Insufficient Balance</h1>");
				req.getRequestDispatcher("AccountHome.jsp").include(req, res);
			} else {
				if (amt > account.getAcclimit()) {
					res.getWriter()
							.print("<h1>Out of limit</h1><h1>Enter amount within" + account.getAcclimit() + "</h1>");
					req.getRequestDispatcher("AccountHome.jsp").include(req, res);
				} else {
					account.setAmount(account.getAmount() - amt);

					BankTransaction bankTransaction = new BankTransaction();
					bankTransaction.setDeposit(0);
					bankTransaction.setWithdraw(amt);
					bankTransaction.setBalance(account.getAmount());
					bankTransaction.setDateTime(LocalDateTime.now());

					List<BankTransaction> list = account.getTransactions();
					list.add(bankTransaction);

					account.setTransactions(list);

					bankDAO.update(account);

					res.getWriter().print("<h1>Amount Withdrawn Sucessfully</h1>");
					req.getRequestDispatcher("AccountHome.jsp").include(req, res);
				}
			}
		}
	}
}
