<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String taiKhoan = request.getParameter("tk");
	String matKhau = request.getParameter("mk");

	if ("ABC".equals(taiKhoan) && "MNK".equals(matKhau))
	{
		response.sendRedirect("UserProfile.html");
	} else
	{
		response.sendRedirect("Login.html");
	}
	%>
</body>
</html>