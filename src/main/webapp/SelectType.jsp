<%@page import="dto.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select Type</title>
</head>
<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f1f1f1;
}

h1 {
	margin-bottom: 10px;
	text-align: center;
}

.container {
	max-width: 500px;
	margin: 50px auto;
	padding: 50px 60px;
	background-color: #ffffff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(41, 40, 40, 0.2);
}

form {
	/* display: flex; */
	flex-direction: column;
}

label {
	font-weight: bold;
	margin-bottom: 5px;
}

input, select {
	padding: 10px;
	margin-bottom: 10px;
	border-radius: 5px;
	border: none;
}

button {
	padding: 10px;
	border-radius: 5px;
	border: none;
	background-color: #4CAF50;
	color: #fff;
	font-weight: bold;
	cursor: pointer;
	margin-bottom: 10px;
}

button:hover {
	background-color: #3e8e41;
}

button {
	padding: 10px;
	border-radius: 10px;
	border: none;
	background-color: #4CAF50;
	color: #fff;
	cursor: pointer;
	margin-bottom: 10px;
	display: inline-block;
	width: 40%;
}
</style>
<body>

	<%
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			response.getWriter().print("<h1>Session Expired</h1><h1>Login Again</h1>");
			request.getRequestDispatcher("LogIn.Html").include(request, response);
		} else {
	%>

	<h1>
		HELLO
		<%=customer.getName()%></h1>
	<h1>SELECT TYPE OF ACCOUNT</h1>
	<div class="container">
		<form action="createbankaccount">
			<label for="accounttype">SELECT ACCOUNT TYPE</label>
			<!-- <select id="account" name="account" required> -->

			<input type="radio" value="savings" name="banktype" required>Savings
			<input type="radio" value="current" name="banktype">Current
			<!-- <option value="">--Select your Account-Type--</option>
                <option value="SAVINGS">SAVINGS</option>
                <option value="CURRENT">CURRENT</option> -->
			</select>
			<button type="submit">Submit</button>
			<button type="reset">Cancel</button>
		</form>
	</div>
	<%
		}
	%>
</body>
</html>