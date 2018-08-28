<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<meta charset="UTF-8">
	<title>View</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<h2>Course: <c:out value="${signups[0][2]}"/></h2>		
		</div>	
		<div class="row"> 
			<h4> Instructor: <c:out value="${course.instructor}"/> </h4>
		</div>
		<div class="row"> 
			<h4> Sign ups:<c:out value= "${signups.size()}"/> </h4>
		</div>
		<div class="row">
			 <table class="table">
				  <thead class="thead-dark">
				    <tr>
				      <th scope="col">Name</th>
				      <th scope="col">Sign Up Date</th>
				      <th scope="col">Action</th>
				    </tr>
				  </thead>
				  <tbody>
				  <c:out value="${signups}"/>
				  <c:forEach items="${signups}" var="s">
				    <tr>
				 	  <td><c:out value="${s[0]}"/></td>
				      <td><c:out value="${s[1]}"/></td>
				      <td><a href="/remove"></a></td>
				    </tr>
				   </c:forEach>
				  </tbody>
			  </table> 
		</div>
		<div class="row">
			<div class="container"><a href="/courses/${course.id}/edit" class="btn btn-primary">Edit</a>
			<a href="/courses" class="btn btn-primary">Back</a>
        	<a href="/delete/${course.id}" class="btn btn-primary">Delete</a>
			</div>  	
		</div>
	</div>
</body>
</html>