<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Banking App</title>
<link rel="stylesheet" href="style.css">
<style type="text/css">
.container {
	max-width: 800px;
	margin: 0 auto;
	text-align: center;
}

h1 {
	font-size: 36px;
	margin-bottom: 40px;
}

nav {
	display: block;
}

nav ul {
	display: flex;
	justify-content: center;
	list-style: none;
	margin: 0;
	padding: 0;
	display: block;
}

nav li {
	margin: 0 10px;
	margin-bottom: 10px;
}

.btn {
	background-color: #008CBA;
	border: none;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	cursor: pointer;
	border-radius: 8px;
}

.btn:hover {
	background-color: #005A6E;
}

li {
	
}
</style>
</head>
<body>
	<div class="container">
		<h1>Banking App</h1>
		<nav>
			<ul>
				<li><a href="Deposit.html"><button class="btn">Deposit</button></a></li>
				<li><a href="Withdraw.html"><button class="btn">Withdraw</button></a></li>
				<li><a href="CheckBalance.jsp"><button class="btn">Check
							Balance</button></a></li>
				<li><a href="ViewTransactions.jsp"><button class="btn">View
							Transactions</button></a></li>
				<li><a href="logout"><button class="btn">Logout</button></a></li>
			</ul>
		</nav>
	</div>
</body>
</html>
