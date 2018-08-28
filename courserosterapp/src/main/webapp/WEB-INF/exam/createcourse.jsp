<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<meta charset="UTF-8">
	<title>Create Course</title>
</head>
<body>
<div class="container">
	<div class="row">
		<h3>Create Course</h3>
	</div>
	<div class="row">
		<p><form:errors path="courseObj.*"/></p>
	</div>
	<div class="row">
    
    		<form:form method="POST" action="/courses/new" modelAttribute="courseObj">
    			<p>
		            <form:label path="name">Name:</form:label>
		            <form:input type="text" path="name"/>
		        </p>
		        <p>
		            <form:label path="instructor">Instructor:</form:label>
		            <form:input type="text" path="instructor"/>
		        </p>
		        <p>
		            <form:label path="capacity">Capacity:</form:label>
		            <form:input path="capacity" value="0"/>
		        </p>
        		<input type="submit" class="btn btn-primary" value="Create"/>
        		<a href="/courses" class="btn btn-primary">Back</a>
   			</form:form>
	</div>
</div>

	
</body>
</html>