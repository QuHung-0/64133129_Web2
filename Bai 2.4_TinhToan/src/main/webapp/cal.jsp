<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>kết quả</title>
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
	<div class="box">
		<%
		String strA = request.getParameter("a");
		String strB = request.getParameter("b");
		String pt = request.getParameter("pt");

		double a = Double.parseDouble(strA);
		double b = Double.parseDouble(strB);
		String message = "";

		if (pt.equals("Cộng"))
		{
			message = "Phép cộng của " + a + " và " + b + " là " + (a + b);
		} else if (pt.equals("Trừ"))
		{
			message = "Phép trừ của " + a + " và " + b + " là " + (a - b);
		} else if (pt.equals("Nhân"))
		{
			message = "Phép nhân của " + a + " và " + b + " là " + (a * b);
		} else if (pt.equals("Chia"))
		{
			if (b == 0)
			{
				message = "Lỗi! Không thể chia cho 0!";
			} else
			{
				double result = (double) a / b;
				message = "Phép chia của " + a + " và " + b + " là " + result;
			}
		} else
		{
			message = "Phép toán không hợp lệ!";
		}

		out.print(message);
		%>
	</div>
</body>
</html>
