package ServLets;

import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JavaFiles.ChooseUserPage;

/**
 * Servlet implementation class ChooseUserServlet
 */
@WebServlet("/ChooseUserServlet")
public class ChooseUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChooseUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			HttpSession session = request.getSession(false); 
			if (session != null) {
				String username = (String) session.getAttribute("username");
				ChooseUserPage page = new ChooseUserPage();
				int correctPage = page.getUser(username);
				if(correctPage==0) {
					response.sendRedirect("./html files/user_page.html");
				}else if(correctPage==1) {
					response.sendRedirect("./html files/host_page.html");
				}else if(correctPage==2) {
					response.sendRedirect("./html files/admin_page.html");
				}else if(correctPage==3) {
					System.out.println("USER PAGE NOT FOUND");
				}
			}
		}catch(Exception e) {
			PrintStream s = null;
			e.printStackTrace(s);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
