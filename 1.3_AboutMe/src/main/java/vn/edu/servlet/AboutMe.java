package vn.edu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AboutMe")
public class AboutMe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AboutMe() {
        super();
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        
        String noiDungHTMLString = "<html>"
                + "<head>"
                + "<title>About Me</title>"
                + "<style>"
                + ".box { border: 2px solid #000; padding: 20px; width: 15%; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='box'>"
                + "<h1>Thông tin</h1>"
                + "<p><strong>Họ tên:</strong> NGUYỄN LỮ QUỐC HƯNG</p>"
                + "<p><strong>Lớp:</strong> 64.CNTT-CLC</p>"
                + "<p><strong>Trường:</strong> Đại Học Nha Trang</p>"
                + "</div>"
                + "</body>"
                + "</html>";

        response.getWriter().append(noiDungHTMLString);
    }




	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
