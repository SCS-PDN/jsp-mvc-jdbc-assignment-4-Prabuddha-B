<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Courses</title>
</head>

<body>
	<h2>Course List</h2>
	
	<%
		ArrayList<String> courses = (ArrayList<String>) request.getAttribute("courses");
		if(courses != null){
			for(String c : courses){	
				String[] parts = c.split(":");
				String id = parts[0];
				String name = parts[1];
		
	%>
		<form action="register" method="post">
			<%= name %>
			<input type="hidden" name="courseId" value="<%= id %>">
			<input type="submit" value="Register">
		</form>
		<br>
	<%
			}
		}
	%>
</body>
</html>