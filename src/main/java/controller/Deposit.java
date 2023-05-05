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

@WebServlet("/deposit")
public class Deposit extends HttpServlet {
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
			account.setAmount(account.getAmount() + amt);

			BankTransaction bankTransaction = new BankTransaction();
			bankTransaction.setDeposit(amt);
			bankTransaction.setWithdraw(0);
			bankTransaction.setBalance(account.getAmount());
			bankTransaction.setDateTime(LocalDateTime.now());

			List<BankTransaction> list = account.getTransactions();
			list.add(bankTransaction);

			account.setTransactions(list);

			bankDAO.update(account);

			res.getWriter().print("<h1>Amount Added Sucessfully</h1>");
			req.getRequestDispatcher("AccountHome.jsp").include(req, res);
		}
	}
}
