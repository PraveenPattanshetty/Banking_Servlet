package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BankDAO;
import dao.CustomerDAO;
import dto.BankAccount;
import dto.Customer;

@WebServlet("/createbankaccount")
public class CreateBankAccount extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String banktype = req.getParameter("banktype");

		Customer customer = (Customer) req.getSession().getAttribute("customer");

		List<BankAccount> list = customer.getAccounts();
		boolean flag = true;

		for (BankAccount account : list) {
			if (account.getType().equals(banktype)) {
				flag = false;
				break;
			}
		}
		if (flag) {

			BankAccount account = new BankAccount();
			account.setType(banktype);
			if (banktype.equals("savings")) {
				account.setAcclimit(10000);
			} else {
				account.setAcclimit(50000);
			}
			account.setCustomer(customer);
			BankDAO bankDAO = new BankDAO();
			bankDAO.save(account);

			// List<BankAccount> list2 = new ArrayList<BankAccount>();
			
			// list2.add(account);
			// customer.setAccounts(list2);

			list.add(account);
			customer.setAccounts(list);

			CustomerDAO customerDAO = new CustomerDAO();
			customerDAO.update(customer);

			res.getWriter().print("<h1>Account Created Succesfully</h1>");
			req.getRequestDispatcher("LogIn.html").include(req, res);
		} else {
			res.getWriter().print("<h1>" + banktype + "Account Already Exists</h1>");
			req.getRequestDispatcher("CustomerHome.html").include(req, res);
		}
	}
}
