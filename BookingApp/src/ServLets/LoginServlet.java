package ServLets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JavaFiles.UserLogin;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//System.out.println("INSIDE POST LOGIN");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserLogin checkLogin = new UserLogin();
		if(checkLogin.loginUser(username, password)) {
			System.out.println("USER EXISTS");
			//System.out.println("before session");
	        HttpSession session = request.getSession();  
			//System.out.println("after session");
	        session.setAttribute("username", username);
	        response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script type=text/javascript>");
			out.println("window.history.back();");
			out.println("</script>");
			out.close();
			
		}else {
			System.out.println("USER DOES NOT EXIST");
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script type=text/javascript>");
			out.println("alert('�� ����� ������ � � ������� ��������� ����� �����.');");
			out.println("window.history.back();");
			out.println("</script>");
			out.close();
		}
		
	}

}
