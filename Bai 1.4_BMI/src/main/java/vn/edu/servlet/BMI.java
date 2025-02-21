package vn.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BMI")
public class BMI extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public BMI()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
        String formHtml = "<form method=\"POST\" action=\"/1.4_BMI/BMI\">"
                + "<label>Chiều cao (m):</label>"
                + "<input type=\"text\" name=\"chieuCao\"><br>"
                + "<label>Cân nặng (kg):</label>"
                + "<input type=\"text\" name=\"canNang\"><br>"
                + "<input type=\"submit\" value=\"Submit\">"
                + "</form>";
        out.append(formHtml);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		String chieuCao = request.getParameter("chieuCao");
		String canNang = request.getParameter("canNang");

		if (chieuCao != null && canNang != null)
		{
			try
			{
				double d_chieuCao = Double.parseDouble(chieuCao);
				double d_canNang = Double.parseDouble(canNang);
				double bmi = d_canNang / (d_chieuCao * d_chieuCao);

				String nhanXet;
				if (bmi < 18.5)
				{
					nhanXet = "Bạn thiếu cân.";
				} else if (bmi >= 18.5 && bmi < 24.9)
				{
					nhanXet = "Bạn có cân nặng bình thường.";
				} else if (bmi >= 25 && bmi < 29.9)
				{
					nhanXet = "Bạn hơi thừa cân.";
				} else
				{
					nhanXet = "Bạn bị béo phì.";
				}

				PrintWriter traVe = response.getWriter();
				traVe.append("Chiều cao: " + d_chieuCao + " m<br>");
				traVe.append("Cân nặng: " + d_canNang + " kg<br>");
				traVe.append("Chỉ số BMI của bạn là: " + bmi + "<br>");
				traVe.append(nhanXet);
			} catch (NumberFormatException e)
			{
				response.getWriter().append("Hãy nhập số hợp lệ cho chiều cao và cân nặng.");
			}
		} else
		{
			response.getWriter().append("Hãy điền đầy đủ thông tin.");
		}
	}
}
