<%@page import="dto.Customer"%>
<%@page import="dto.BankAccount"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Select Account</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
}

h1 {
	font-size: 32px;
	margin-top: 50px;
	margin-bottom: 20px; <%--
	text-align: center;
	--%>
}

button {
	margin: auto;
}

button {
	font-size: 18px;
	padding: 10px;
	background-color: #7c4951;
	color: white;
	border: none;
	cursor: pointer;
	border-radius: 5px;
	margin-bottom: 10px;
}

button:hover {
	background-color: #3e8e41;
}

a {
	text-decoration: none;
}

.back-button {
	margin-top: 30px;
}
</style>
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
		List<BankAccount> list = (List<BankAccount>) request.getAttribute("list");
			if (list.isEmpty()) {
	%>
	<h1>No Active Accounts Found</h1>
	<%
		} else {
	%>
	<h1>Select Bank Account</h1>
	<%
		for (BankAccount account : list) {
	%>
	<a href="setaccount?accno=<%=account.getAccno()%>"><button><%=account.getAccno()%></button></a>
	<%
		}
	%>
	<%
		}
	%>
	<a href="CustomerHome.html"><button class="back-button">Back</button></a>
	<%
		}
	%>
</body>
</html>
