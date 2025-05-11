<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="/css/newCustom.css" />
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" />
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/login.js"></script>
    <style>
        body, html {
            margin: 0;
            padding: 0;
        }

        /* Header styling */
        .heading-colour {
            background-color: teal;
            margin: 0;
            padding: 0;
        }

        .projectName {
            font-size: 40px;
            font-weight: bold;
            margin: 0;
            padding: 20px;
            color: white;
            text-align: center;
        }

        /* Profile dropdown styling */
        .dropdown {
            position: relative;
            display: inline-block;
        }

        .profile-img {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            cursor: pointer;
            margin-right: 10px;
			position:relative;
			right:50px;
        }

        .dropdown-menu {
            display: none;
            position: absolute;
            top: 50px;
            right: 30px;
            background-color: white;
            width: 300px;
            box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.1);
            z-index: 999;
        }

        .dropdown-menu li {
            list-style-type: none;
            padding: 10px;
        }

        .dropdown-menu li a {
            text-decoration: none;
            color: teal;
            font-weight: bold;
        }

        .dropdown:hover .dropdown-menu {
            display: block;
        }

        /* Navigation styling */
        nav {
            border-bottom-left-radius: 15px;
            border-bottom-right-radius: 15px;
            width: 100%;
            position: relative;
        }

        #navbar {
            background-color: #f06292;
            color: white;
            font-weight: bold;
            font-size: 18px;
            width: 100%;
            padding: 10px 0;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        nav ul {
            display: flex;
            justify-content: center; /* Centers the items */
            padding: 0;
            margin: 0;
            list-style-type: none;
            width: auto;
        }

        nav ul li {
            text-align: center;
            margin: 0 10px; /* Reduce the space between items */
        }

        nav ul li a {
            color: white;
            font-weight: bold;
            text-transform: uppercase;
            font-size: 16px;
            padding: 5px 8px; /* Reduce padding to reduce space */
            text-decoration: none;
        }

        nav ul li a:hover {
            background-color: teal;
            color: white;
            border-radius: 5px;
        }

        /* Profile image inside the navbar */
        .profile-container {
            display: flex;
            align-items: center;
			position:relative;
			right:150px;
        }
    </style>
</head>

<body>
    <div class="heading-colour">
        <h3 class="projectName">MERCHANT MANAGEMENT PORTAL</h3>
    </div>

    <header>
        <nav>
            <div id="navbar">
                <ul>
                    <li>
                        User Management
                        <ul>
                            <li><a href="#" onclick="urlPostAction('/getUserForm');">User</a></li>
                            <li><a href="#" onclick="urlPostAction('/viewRole');">Role</a></li>
                        </ul>
                    </li>
                    <li>
                        Merchant
                        <ul>
                            <li><a href="#" onclick="urlPostAction('/addEditMerchant');">On-Board</a></li>
                            <li><a href="#" onclick="urlPostAction('/fileExtraction');">Load Files</a></li>
                        </ul>
                    </li>
                    <li>
                        Reports
                        <ul>
                            <li><a href="#" onclick="urlPostAction('/reportDwld');">Generate</a></li>
                        </ul>
                    </li>
                    <li>
                        Search
                        <ul>
                            <li><a href="#" onclick="urlPostAction('/merKYView');">Transaction Search</a></li>
                        </ul>
                    </li>
                </ul>
                <div class="profile-container">
                    <div class="dropdown">
                        <img src="images/user.png" alt="Profile" class="profile-img" />
                        <ul class="dropdown-menu">
                            <li><a href="#" onclick="urlPostAction('/viewUser');">Profile</a></li>
                            <li><a href="#" onclick="urlPostAction('/changePassword');">Change Password</a></li>
                            <li><a href="#" onclick="urlPostAction('/logout');">Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
    </header>
</body>

</html>
