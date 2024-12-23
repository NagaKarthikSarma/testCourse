<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1> welcome to Lumen Hotel</h1>

<form action="welcome" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>   


    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>

    <input type="submit" value="Login">
</form>  
</body>
</html>