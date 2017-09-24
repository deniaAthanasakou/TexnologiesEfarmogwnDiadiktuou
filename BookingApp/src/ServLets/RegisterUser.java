package ServLets;


import JavaFiles.HandleUser;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.User;

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
	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HandleUser handleUser = new HandleUser();

		//get fields
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String tel = request.getParameter("tel");
		String tenant = request.getParameter("tenant");
		
		byte roleTenant = 0;
		if(tenant!=null) {
			roleTenant = 1;
		}
		String host = request.getParameter("host");
		byte roleHost = 0;
		if(host!=null) {
			roleHost = 1;
		}
		
		String birthday = request.getParameter("birthday").replaceAll("/", "-");
		Date date = null;
		if(birthday.length()!=0) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			try {
				date = sdf.parse(birthday);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
		}
		
		
		
		String imagePath = request.getParameter("imagePath");
		byte[] photo = null;
		if(imagePath.length() != 0) {
			photo = convertImageToBArray(imagePath);
		}
		
		String country = request.getParameter("country");
		String city = request.getParameter("city");

		String postalCode = request.getParameter("postal_code");

		String streetAddress = request.getParameter("street_address");

		String addressNumber = request.getParameter("address_number");
		int addressNumberContent = 0;
		if(addressNumber.length()!=0) {
			addressNumberContent = Integer.parseInt(addressNumber);
		}

		User newUser = new User(username,password,email,name,surname,tel,date,photo,country,city,postalCode,streetAddress,addressNumberContent,roleHost,roleTenant);

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
	
	private static byte[] convertImageToBArray(String ImageName) throws IOException {

		// open image
		File imgPath = new File(ImageName);
		BufferedImage bufferedImage = ImageIO.read(imgPath);

		// get DataBufferBytes from Raster
		WritableRaster raster = bufferedImage .getRaster();
		DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

		return ( data.getData() );
	}

}
