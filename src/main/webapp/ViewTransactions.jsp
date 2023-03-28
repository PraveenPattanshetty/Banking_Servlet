<%@page import="dto.Customer"%>
<%@page import="dto.BankTransaction"%>
<%@page import="java.util.List"%>
<%@page import="dto.BankAccount"%>
<%@page import="dao.BankDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Transaction</title>
</head>
<body>
	<%
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			response.getWriter().print("<h1>Session Expired</h1><h1>Login Again</h1>");
			request.getRequestDispatcher("LogIn.Html").include(request, response);
		} else {
	%>
	<%
		long accno = (Long) session.getAttribute("accno");
			BankDAO bankDAO = new BankDAO();
			BankAccount account = bankDAO.find(accno);
			List<BankTransaction> list = account.getTransactions();
	%>
	<h1>
		Accno:<%=accno%></h1>
	<h1>
		Type:<%=account.getType()%></h1>
	<h1>Transactions</h1>
	<table border="1">
		<tr>
			<td>Transaction_ID</td>
			<td>Depsoit</td>
			<td>Withdraw</td>
			<td>Balance</td>
			<td>Time</td>
		</tr>
		<%
			for (BankTransaction transaction : list) {
		%>
		<tr>
			<td><%=transaction.getId()%></td>
			<td><%=transaction.getDeposit()%></td>
			<td><%=transaction.getWithdraw()%></td>
			<td><%=transaction.getBalance()%></td>
			<td><%=transaction.getDateTime()%></td>
		</tr>
		<%
			}
		%>
	</table>
	<br>
	<br>
	<a href="AccountHome.jsp"><button>Back</button> </a>
	<%
		}
	%>
</body>
</html>