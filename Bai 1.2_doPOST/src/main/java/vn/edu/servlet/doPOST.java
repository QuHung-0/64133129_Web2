package vn.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class doPOST
 */
@WebServlet("/doPOST")
public class doPOST extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public doPOST()
	{
		super();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");
	    response.setCharacterEncoding("utf-8");
	    PrintWriter traVe = response.getWriter();
	    traVe.append("ban vua gui yeu cau dang get, day la phan hoi cua toi");

	    String noiDungHTMLString = "<form method=\"POST\" action=\"/1.2_doPOST/doPOST\">"
	            + " <label> Ho: </label>"
	            + " <input type=\"text\" name=\"fname\"> <br>"
	            + " <label> Ten: </label>"
	            + " <input type=\"text\" name=\"lname\"> <br>"
	            + " <input type=\"submit\" value=\"Gui di\">"
	            + " </form>";
	    traVe.append(noiDungHTMLString);
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String value1 = request.getParameter("fname");
		String value2 = request.getParameter("lname");
		PrintWriter traVe = response.getWriter();
		traVe.append("Ban vua gui yeu cau dang POST, day la phan hoi cua toi");
		traVe.append("Gia tri tham fname =");
		traVe.append(value1);
		traVe.append("\nGia tri tham so lname =");
		traVe.append(value2);
	}

}
