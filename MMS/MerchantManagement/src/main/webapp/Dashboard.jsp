<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>MERCHANT MANAGEMENT</title>
    <jsp:include page="Common/CommonImports.jsp"></jsp:include>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/newCustom.css" />
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="/js/addUser.js"></script>

    <style>
		body {
		    font-family: 'Arial', sans-serif;
		    background-color: #f8f9fa; /* Light gray background for a clean look */
		    margin: 0;
		    padding: 20px;
		}

		/* Dashboard Title */
		.dashboard-title {
		    text-align: center; /* Center the title */
		    font-size: 2em; /* Large font size */
		    font-weight: bold; /* Bold text */
		    color: #343a40; /* Dark gray color for contrast */
		    margin-bottom: 30px; /* Space below the title */
		}

		/* Dashboard Grid */
		.dashboard-grid {
		    display: grid; /* Use CSS Grid for layout */
		    grid-template-columns: repeat(auto-fill, minmax(250px, 1fr)); /* Responsive columns */
		    gap: 20px; /* Space between grid items */
		    margin-bottom: 40px; /* Space below the grid */
		}

		/* Card Styles */
		.card {
		    background-color: #ffffff; /* White background */
		    border-radius: 10px; /* Rounded corners */
		    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
		    padding: 20px; /* Inner padding */
		    transition: transform 0.2s, box-shadow 0.2s; /* Smooth transition for hover effects */
		}

		.card:hover {
		    transform: translateY(-5px); /* Slight lift on hover */
		    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); /* Enhanced shadow on hover */
		}

		/* Card Title */
		.card-title {
		    font-size: 1.2em; /* Larger font size */
		    font-weight: bold; /* Bold text */
		    color: #007bff; /* Bootstrap primary color */
		    margin-bottom: 10px; /* Space below the title */
		}

		/* Card Content */
		.card-content {
		    font-size: 1.5em; /* Larger number font */
		    color: #343a40; /* Dark gray color */
		    text-align: center; /* Center align numbers */
		}

		/* Table Container */
		.table-container {
		    overflow-x: auto; /* Enable horizontal scrolling on small screens */
		    margin-bottom: 40px; /* Space below the table */
		}

		/* Table Styles */
		.table {
		    width: 100%; /* Full width */
		    border-collapse: collapse; /* Remove gaps between cells */
		    background-color: #ffffff; /* White background */
		    border-radius: 10px; /* Rounded corners */
		    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Subtle shadow */
		    overflow: hidden; /* Hide overflow for rounded corners */
		}

		.table thead {
		    background-color: #007bff; /* Bootstrap primary color */
		    color: #ffffff; /* White text */
		}

		.table th, .table td {
		    padding: 15px; /* Cell padding */
		    text-align: left; /* Left align text */
		    border-bottom: 1px solid #dee2e6; /* Light gray border */
		}

		.table tbody tr:hover {
		    background-color: #f1f1f1; /* Light gray background on hover */
		}

		/* Badge Styles */
		.badge {
		    padding: 5px 10px; /* Padding inside badges */
		    border-radius: 12px; /* Rounded badges */
		    font-size: 0.9em; /* Smaller font size */
		    color: #ffffff; /* White text */
		}

		.badge-success {
		    background-color: #28a745; /* Green for success */
		}

		.badge-danger {
		    background-color: #dc3545; /* Red for danger */
		}

		/* Responsive Adjustments */
		@media (max-width: 768px) {
		    .dashboard-title {
		        font-size: 1.5em; /* Smaller font on mobile */
		        margin-bottom: 20px;
		    }

		    .card-title {
		        font-size: 1em; /* Smaller title on mobile */
		    }

		    .card-content {
		        font-size: 1.2em; /* Smaller numbers on mobile */
		    }

		    .table th, .table td {
		        padding: 10px; /* Less padding on mobile */
		    }
		}
    </style>
</head>
<body>
	<%@include file="header.jsp"%>
	    <form:form method="post" id="dashboard" name="dashboard" class="form-signin" modelAttribute="loginDTO">
	        <form:hidden path="userId" id="userId" value="${userId}" />
	        <h3 class="dashboard-title">Dashboard</h3>

	        <!-- Dashboard Grid for Metrics -->
	        <div class="dashboard-grid">
	            <div class="card">
	                <div class="card-title">Total Users</div>
	                <div class="card-content">
	                    <h2>1,250</h2>
	                </div>
	            </div>

	            <div class="card">
	                <div class="card-title">Transactions Today</div>
	                <div class="card-content">
	                    <h2>320</h2>
	                </div>
	            </div>

	            <div class="card">
	                <div class="card-title">Revenue</div>
	                <div class="card-content">
	                    <h2>Rs 45,000</h2>
	                </div>
	            </div>
	            <!-- Add more cards as needed -->
	        </div>

	        <!-- Example Table -->
	        <div class="table-container">
	            <table class="table">
	                <thead>
	                    <tr>
	                        <th>Transaction ID</th>
	                        <th>User</th>
	                        <th>Amount</th>
	                        <th>Date</th>
	                        <th>Status</th>
	                    </tr>
	                </thead>
	                <tbody>
	                    <tr>
	                        <td>TXN123456</td>
	                        <td>John Doe</td>
	                        <td>$500</td>
	                        <td>2024-04-25</td>
	                        <td><span class="badge badge-success">Completed</span></td>
	                    </tr>
	                    <tr>
	                        <td>TXN123457</td>
	                        <td>Jane Smith</td>
	                        <td>$1,200</td>
	                        <td>2024-04-25</td>
	                        <td><span class="badge badge-danger">Failed</span></td>
	                    </tr>
	                    <!-- Add more rows as needed -->
	                </tbody>
	            </table>
	        </div>
	    </form:form>
	    <%@include file="footer.jsp"%>
</body>
</html>
