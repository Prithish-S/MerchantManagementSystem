<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MERCHANT MANAGEMENT</title>
<jsp:include page="Common/CommonImports.jsp"></jsp:include>
	 <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
        }
        h1 {
            color: #333;
            align:left;
        }
        .container {
            background: #fff;
            border-radius: 8px;
            padding: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        .balance {
            font-size: 1.5em;
            color: green;
            margin: 20px 0;
        }
        .transactions {
            margin: 20px 0;
        }
        table {
            width:75%;
            border-collapse: collapse;
            margin-top: 5px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: left;
        }
        th {
            background-color: #e2e2e2;
        }
        .options {
            margin-top: 20px;
        }
        .button {
            padding: 10px 15px;
            margin-right: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            background-color: #007BFF;
            color: white;
        }
    </style>
</head>
<body>
<%@include file="header.jsp"%>

	<form:form method="POST" id="merchantinfo" modelAttribute="MerchantDTO"
			action="" autocomplete="off">
		<form:hidden path="userId" id="userId" name="userId" value="${userId}" />

<div class="container-fluid">
    <h1>Merchant Point Of Sale - POS</h1>
    <div class="balance">Account Balance: $5,000.00</div>

    <div class="transactions">
        <h2>Recent Transactions</h2>
        <table>
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Description</th>
                    <th>Amount</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>2024-10-01</td>
                    <td>Withdrawal</td>
                    <td>-$200.00</td>
                </tr>
                <tr>
                    <td>2024-09-28</td>
                    <td>Deposit</td>
                    <td>+$500.00</td>
                </tr>
                <tr>
                    <td>2024-09-25</td>
                    <td>Purchase at Grocery Store</td>
                    <td>-$150.00</td>
                </tr>
                <tr>
                    <td>2024-09-22</td>
                    <td>Salary Deposit</td>
                    <td>+$2,000.00</td>
                </tr>
            </tbody>
        </table>
    </div>

    <div class="options">
        <h2>Select an Option:</h2>
        <button class="button">Cash Debit</button>
        <button class="button">Deposit Cash</button>
        <button class="button">Check Balance</button>
        <button class="button">Transfer Funds</button>
        <button class="button">Exit</button>
    </div>
</div>


</form:form>
</body>
</html>