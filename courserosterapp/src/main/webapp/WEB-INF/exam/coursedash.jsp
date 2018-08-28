<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
 
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<meta charset="UTF-8">
	<title>Sign Up/Log in</title>
</head>
<body>
	<div class="container">
  		<div class="row">
  			<h3>Welcome, <c:out value="${user.name}"/></h3>
  		</div>
  		<div class="row">
  		<h5>Courses | <a href="/logout">Log out</a></h5> 
  		
  		
  			<table class="table">
				  <thead class="thead-dark">
				    <tr>
				      <th scope="col">Course</th>
				      <th scope="col">Instructor</th>
				      <th scope="col">Sign ups</th>
				      <th scope="col">Action</th>
				    </tr>
				  </thead>
				  <tbody>
				  <c:forEach items="${courses}" var="c">
				    <tr>
				 	  <td><a href="/courses/${c.id}/view"><c:out value="${c.name}"/></a></td>
				      <td><c:out value="${c.instructor}"/></td>
				      <td><c:out value= "${c.users.size()}"/>/<c:out value="${c.capacity}"/></td>
				      
				      <td><a href="/addcourse/${c.id}">add</a></td>
				    </tr>
				   </c:forEach>
				  </tbody>
				</table>
				<a href="/courses/new" class="btn btn-primary">Create Course </a>
				
				
  		</div>
	</div>
</body>
</html>