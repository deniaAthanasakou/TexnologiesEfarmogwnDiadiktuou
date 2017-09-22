package ServLets;


import JavaFiles.HandleUser;
import JavaFiles.JavaMysqlSelect;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Users;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterUser() {
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
		// TODO Auto-generated method stub
		
		HandleUser handleUser = new HandleUser();

		//get fields
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String tel = request.getParameter("tel");
		int telNumber = 0;
		if(tel.length()!=0) {
			telNumber = Integer.parseInt(tel);
		}
		String tenant = request.getParameter("tenant");
		String host = request.getParameter("host");
		String birthday = request.getParameter("birthday");
		String imagePath = request.getParameter("imagePath");
		String country = request.getParameter("country");
		String city = request.getParameter("city");

		String postalCode = request.getParameter("postal_code");
		int postalCodeNumber = 0;
		if(postalCode.length()!=0) {
			postalCodeNumber = Integer.parseInt(postalCode);
		}

		String streetAddress = request.getParameter("street_address");

		String addressNumber = request.getParameter("address_number");
		int addressNumberContent = 0;
		if(addressNumber.length()!=0) {
			addressNumberContent = Integer.parseInt(addressNumber);
		}

		Users newUser = new Users(username,password,email,name,surname,telNumber,birthday,imagePath,country,city,postalCodeNumber,streetAddress,addressNumberContent,host,tenant);

		//System.out.println(request.getParameter("submit"));
		if (request.getParameter("submit") != null) {
			if((handleUser.insertUser(newUser))==0) {
				System.out.println("Submit was successfull!!");
				response.sendRedirect("./html files/register_done.html");
			}else {
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script type=text/javascript>");
				out.println("alert('Το Όνομα Χρήστη χρησιμοποιείται ήδη.');");
				out.println("window.history.back();");
				out.println("</script>");
			}
		}
		else {
			System.out.println("Submit was unsuccessfull!!");
		}

	}

}
