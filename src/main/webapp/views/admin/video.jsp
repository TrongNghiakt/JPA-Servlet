<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
 
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Video management</title>
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
    			<form action = "${pageContext.request.contextPath}/admin/videos" method = "POST" enctype = "multipart/form-data">
    				<div class = "form-group">
    					<label for ="videoId"> Video ID: </label>
    					<input type = "text" name = "videoid" id = "videoid" class = "form-control" value="${video.videoid}"  required>
    				</div>
    				<div class = "form-group">
    					<label for ="videoName"> Title: </label>
    					<input type = "text" name = "title" id = "title" class = "form-control" value="${video.title}">
    				</div>
    				<div class = "form-group">
    					<label for ="description"> Description: </label>
    					<input type = "text" name = "description" id = "description" class = "form-control" value="${video.description}">
    				</div>
    				<div class = "form-group">
    					<label for ="poster"> Poster: </label>
    					<input type = "file" name = "poster" id = "poster" class = "form-control" value="${video.poster}">
    				</div>
    				<div class = "form-group">
    					<label for ="views"> Views: </label>
    					<input type = "text" name = "views" id = "views" class = "form-control" value="${video.views != null ? video.views : ''}" min="0"">
    				</div>
    				
    				
    				<div class="form-group mb-3">
		              <label for="role">Active:</label>
		              <div class="form-check form-check-inline">
		                <input type="radio" name="active" id="inactive"  class="form-check-input" value="0"
		                 <c:if test="${video.active == 0}">checked</c:if>>
		                <label for="inactive" class="form-check-label">Inactive</label>
		              </div>
		              <div class="form-check form-check-inline">
		                <input type="radio" name="active" id="active" class="form-check-input" value="1"
		                 <c:if test="${video.active == 1}">checked</c:if>>
		                <label for="active" class="form-check-label">Active</label>
		              </div>
            		</div>
    				
    				<div class = "form-group">
    					<button class="btn btn-primary" formaction="${pageContext.request.contextPath}/admin/video/create">Create</button>
    					<button class = "btn btn-warning" formaction = "${pageContext.request.contextPath}/admin/video/update">Update</button>
    					<button class = "btn btn-danger" formaction = "${pageContext.request.contextPath}/admin/video/delete">Delete</button>
    					<button class = "btn btn-info" formaction = "${pageContext.request.contextPath}/admin/video/reset">Reset</button>
    				</div>
    			</form>
    		</div>
    	</div>
    	<br>
    	 <form id="searchForm" action="${pageContext.request.contextPath}/admin/video/search" method="post" class="d-flex mb-4 align-items-center">
            <input type="text" name="title-search" id ="titleSearch" class="form-control me-2" placeholder="Search by title" value="${searchQuery != null ? searchQuery : ''}">
            <button type="submit" class="btn btn-primary me-2">Search</button>
        </form>
    	
    	<div class = "row">
    		<div class ="col">
    			<table class = "table table-striped">
    				<tr>
    					<td>Video ID</td>
    					<td>Title</td>
    					<td>Description</td>
    					<td>Poster</td>
    					<td>Views</td>
    					<td>Acive</td>
    					<td>&nbsp;</td>   <!-- khoang trang  -->
    				</tr>
    				<tbody>
		              <!-- Sử dụng c:forEach để lặp qua danh sách người dùng -->
		              <c:forEach var="video" items="${videos}">
		                <tr>
		                  <td>${video.videoid}</td>
		                  <td>${video.title}</td>
		                  <td>${video.description}</td>
		                  <td>${video.poster}</td>
		                  <td>${video.views}</td>
		                  <td>
		                  		<c:choose>
		                  			<c:when test = "${video.active==0 }">
		                  				Inactive
		                  			</c:when>
		                  			<c:otherwise>
		                  				Active
		                  			</c:otherwise>
		                  		</c:choose>
		                  		
		                  </td>
		                  <td>
		                  		<a href="${pageContext.request.contextPath}/admin/video/edit?videoid=${video.videoid}">Edit</a> |
                   				<a href="${pageContext.request.contextPath}/admin/video/delete?videoid=${video.videoid}" onclick="return confirm('Are you sure you want to delete this video?');">Delete</a>
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