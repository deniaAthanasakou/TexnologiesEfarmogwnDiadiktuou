package ServLets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WelcomePageServlet
 */
@WebServlet("/WelcomePageServlet")
public class WelcomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WelcomePageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String neighborhood = request.getParameter("location");
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String people = request.getParameter("people");
		Cookie neighborhoodCookie = new Cookie("neighborhood", neighborhood);
		Cookie fromCookie = new Cookie("from", from);
		Cookie toCookie = new Cookie("to", to);
		Cookie peopleCookie = new Cookie("people", people);
		neighborhoodCookie.setMaxAge(60*60*24);
		fromCookie.setMaxAge(60*60*24);
		toCookie.setMaxAge(60*60*24);
		peopleCookie.setMaxAge(60*60*24);
		response.addCookie(neighborhoodCookie);
		response.addCookie(fromCookie);
		response.addCookie(toCookie);
		response.addCookie(peopleCookie);
		response.sendRedirect("./html files/apartmentResultsGeneral.html");
	}

}
