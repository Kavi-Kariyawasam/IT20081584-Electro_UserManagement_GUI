<%@page import="com.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%--  <%
    
if (request.getParameter("UID") != null)
{
	User UserObj = new User();
	String stsMsg = UserObj.deleteUser(request.getParameter("UID"));
	session.setAttribute("statusMsg", stsMsg);
}
%> --%> 
    
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.5.0.min.js"></script>
<script src="Components/users.js"></script>

 
<!-- ------------------------------------------------------ -->
</head>
<body >
<!-- ---------------------------------------------------------------------------------------------------------- -->
 

<div class="container"><div class="row"><div class="col-6">
<h1> <b>User Management</b>  </h1>
<form id="formUser" name="formUser" method="post" action="users.jsp">
 User Name :
<input id="Name" name="Name" type="text"
class="form-control form-control-sm">
<br> Acc Number :
<input id="Acc_Num" name="Acc_Num" type="text"
class="form-control form-control-sm">
<br> Email :
<input id="Email" name="Email" type="text"
class="form-control form-control-sm">
<br> Password :
<input id="Password" name="Password" type="text"
class="form-control form-control-sm">
 <br> Phone :
<input id="Phone" name="Phone" type="text"
class="form-control form-control-sm">
<br><input id="btnSave" name="btnSave" type="button" value="Save"
class="btn btn-primary">
<input type="hidden" id="hidUserIDSave"
name="hidUserIDSave" value="">
</form>
<%--<%
out.print(session.getAttribute("statusMsg"));
--%>
 
  <div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divUsersGrid">  
 <%  
  User UserObj = new User();
  out.print(UserObj.readUser());
  %>
   
</div>
</div> </div> </div>
<!-- -------------------------------------------------------------------------------------------------------------------- -->
<footer class="footer bg-dark mt-5">
		<div class="container">
			<div class="text-center text-light">
				<span>Created by , <span style="color: #87CEEB;">kavindi
						 </span> 2022
				</span>
			</div>
		</div>
	</footer>
	<!-- --------------------------------------------------------------- -->
</body>
</html> 