<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
 
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>User management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  </head>
  <body>
    <main class = "container">
    	<div class = "row">
    		<div class = "col">
    			<c:if test = "${not empty message}">
	    			<div class = "alert alert-success">${message}</div>
    			</c:if>
    			
    			<c:if test = "${not empty error}">
	    			<div class = "alert alert-danger">${error}</div>
    			</c:if>
    			
    			
    		</div>
    	</div>
    	
    
    
    	<div class = "row">
    		<div class = "col">
    			<form action = "${pageContext.request.contextPath}/admin/users" method = "POST">
    				<div class = "form-group">
    					<label for ="userId"> User ID: </label>
    					<input type = "text" name = "userid" id = "userid" class = "form-control" value="${user.userid}"  required>
    				</div>
    				<div class = "form-group">
    					<label for ="userName"> Username: </label>
    					<input type = "text" name = "username" id = "username" class = "form-control" value="${user.username}"  required>
    				</div>
    				<div class = "form-group">
    					<label for ="password"> Password: </label>
    					<input type = "password" name = "password" id = "password" class = "form-control" value="${user.password}"  required>
    				</div>
    				<div class = "form-group">
    					<label for ="fullname"> Fullname: </label>
    					<input type = "text" name = "fullname" id = "fullname" class = "form-control" value="${user.fullname}">
    				</div>
    				<div class = "form-group">
    					<label for ="email"> Email Address: </label>
    					<input type = "text" name = "email" id = "email" class = "form-control" value="${user.email}">
    				</div>
    				
    				<div class="form-group mb-3">
		              <label for="role">Role:</label>
		              <div class="form-check form-check-inline">
		                <input type="radio" name="role" id="admin" value="admin" class="form-check-input" value="0"
		                 <c:if test="${user.role == 0}">checked</c:if>>
		                <label for="admin" class="form-check-label">Admin</label>
		              </div>
		              <div class="form-check form-check-inline">
		                <input type="radio" name="role" id="user" value="user" class="form-check-input" value="1"
		                 <c:if test="${user.role == 1}">checked</c:if>>
		                <label for="user" class="form-check-label">User</label>
		              </div>
            		</div>
    				
    				<div class = "form-group">
    					<button class="btn btn-primary" formaction="${pageContext.request.contextPath}/admin/user/create">Create</button>
    					<button class = "btn btn-warning" formaction = "${pageContext.request.contextPath}/admin/user/update">Update</button>
    					<button class = "btn btn-danger" formaction = "${pageContext.request.contextPath}/admin/user/delete">Delete</button>
    					<button class = "btn btn-info" formaction = "${pageContext.request.contextPath}/admin/user/reset">Reset</button>
    				</div>
    			</form>
    		</div>
    	</div>
    	<div class = "row">
    		<div class ="col">
    			<table class = "table table-striped">
    				<tr>
    					<td>User ID</td>
    					<td>Username</td>
    					<td>Fullname</td>
    					<td>Password</td>
    					<td>Email</td>
    					<td>Role</td>
    					<td>&nbsp;</td>   <!-- khoang trang  -->
    				</tr>
    				<tbody>
		              <!-- Sử dụng c:forEach để lặp qua danh sách người dùng -->
		              <c:forEach var="user" items="${users}">
		                <tr>
		                  <td>${user.userid}</td>
		                  <td>${user.username}</td>
		                  <td>${user.fullname}</td>
		                  <td>${user.password}</td>
		                  <td>${user.email}</td>
		                  <td>
		                  		<c:choose>
		                  			<c:when test = "${user.role==0 }">
		                  				Admin
		                  			</c:when>
		                  			<c:otherwise>
		                  				User
		                  			</c:otherwise>
		                  		</c:choose>
		                  		
		                  </td>
		                  <td>
		                  		<a href="${pageContext.request.contextPath}/admin/user/edit?userid=${user.userid}">Edit</a> |
                   				<a href="${pageContext.request.contextPath}/admin/user/delete?userid=${user.userid}" onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
		                  </td>
		                </tr>
		              </c:forEach>
		            </tbody>

    			</table>
    		</div>
    	</div>
    </main>
    
    
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
  </body>
  
</html>