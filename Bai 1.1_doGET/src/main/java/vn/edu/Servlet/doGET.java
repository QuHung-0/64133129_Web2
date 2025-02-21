package vn.edu.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class doGET
 */
@WebServlet("/doGET")
public class doGET extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public doGET()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Biến request lưu các thông tin y/c
		// biến respose dại diện kết quả trả về cho người dùng
		
		// để ghi trả dữ liệu, ta dùng writer của đối tượng response
		PrintWriter dau_ghi_kq = response.getWriter();
		dau_ghi_kq.print("Servlet");
	}
	
	

}
