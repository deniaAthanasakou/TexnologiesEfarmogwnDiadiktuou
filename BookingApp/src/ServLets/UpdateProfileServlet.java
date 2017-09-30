package ServLets;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jettison.json.JSONObject;

import Entities.User;
import JavaFiles.ImgToBArray;
import JavaFiles.PopulateUser;
import JavaFiles.UpdateProfile;

/**
 * Servlet implementation class UpdateProfile
 */
@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfileServlet() {
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
		System.out.println("INSIDE UPDATE PROFILE");
		try {
			System.out.println("TRYYYY");
			HttpSession session = request.getSession(false); 
			if (session != null) {
				String username = (String) session.getAttribute("username");
				User updateUser = new User();
				updateUser.setUsername(username);
				if(request.getParameter("submitImage") != null) {
					System.out.println("Submit an image");
					String imgPath = request.getParameter("imagePath");
					ImgToBArray image = new ImgToBArray();
					updateUser.setPhoto(image.convertImageToBArray(imgPath));
					UpdateProfile update = new UpdateProfile();
					update.updateImage(updateUser);
					response.setContentType("text/html; charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script type=text/javascript>");
					out.println("alert('Η εικόνα προφίλ ενημερώθηκε επιτυχώς.');");
					out.println("window.history.back();");
					out.println("</script>");
					out.close();
				}
				if(request.getParameter("submitPersonal") != null) {
					System.out.println("Submit personal Info");
					String name = request.getParameter("name");
					String surname = request.getParameter("surname");
					updateUser.setName(name);
					updateUser.setSurname(surname);
					UpdateProfile update = new UpdateProfile();
					update.updatePersonal(updateUser);
					response.setContentType("text/html; charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script type=text/javascript>");
					out.println("alert('Τα προσωπικά σας στοιχεία ενημερώθηκαν επιτυχώς.');");
					out.println("window.history.back();");
					out.println("</script>");
					out.close();
				}
				if(request.getParameter("submitEmail") != null) {
					System.out.println("Submit email Info");
					String email = request.getParameter("email");
					updateUser.setEmail(email);
					UpdateProfile update = new UpdateProfile();
					update.updateEmail(updateUser);
					response.setContentType("text/html; charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script type=text/javascript>");
					out.println("alert('Το email σας ενημερώθηκε επιτυχώς.');");
					out.println("window.history.back();");
					out.println("</script>");
					out.close();
				}
				if(request.getParameter("submitPass") != null) {
					System.out.println("Submit new pass");
					String old_password = request.getParameter("old_password");
					String password = request.getParameter("password");
					updateUser.setPassword(password);
					UpdateProfile update = new UpdateProfile();
					boolean correctPass = update.updatePass(updateUser,old_password);
					response.setContentType("text/html; charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script type=text/javascript>");
					if(correctPass) {
						out.println("alert('Ο κωδικός πρόσβασής σας ενημερώθηκε επιτυχώς.');");
					}else {
						out.println("alert('Ο κωδικός πρόσβασής δεν ενημερώθηκε επιτυχώς λόγω λανθασμένου παλιού κωδικού πρόσβασης.');");
					}
					out.println("window.history.back();");
					out.println("</script>");
					out.close();
				}
				if(request.getParameter("submitRoles") != null) {
					System.out.println("Submit roles Info");
					String tenant = request.getParameter("tenant");
					String host = request.getParameter("host");
					byte tenantB = 0;
					byte hostB = 0;
					if(tenant!=null) {
						tenantB=1;
					}
					if(host!=null) {
						hostB=1;
					}
					System.out.print("tenant: " + tenant);
					System.out.print("host: " + host);
					updateUser.setRoleHost(hostB);
					updateUser.setRoleTenant(tenantB);
					UpdateProfile update = new UpdateProfile();
					update.updateRoles(updateUser);
					response.setContentType("text/html; charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script type=text/javascript>");
					out.println("alert('Ο ρόλος σας στο σύστημα ενημερώθηκε επιτυχώς.');");
					out.println("window.history.back();");
					out.println("</script>");
					out.close();
				}
				if(request.getParameter("submitResidence") != null) {
					System.out.println("Submit residence Info");
					String country = request.getParameter("country");
					Locale.setDefault(new Locale("en", "US"));
					Locale l = new Locale("", country);
					String city = request.getParameter("city");
					String postalCode = request.getParameter("postal_code");
					String streetAddress = request.getParameter("street_address");
					String addressNum = request.getParameter("address_number");
					country = l.getDisplayCountry();
					updateUser.setCountry(l.getDisplayCountry());

					if(addressNum=="") {
						addressNum = "0";
					}
					updateUser.setCity(city);
					updateUser.setPostalCode(postalCode);
					updateUser.setStreetAddress(streetAddress);
					updateUser.setAddressNumber(Integer.valueOf(addressNum));
					UpdateProfile update = new UpdateProfile();
					update.updateResidence(updateUser);
					response.setContentType("text/html; charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script type=text/javascript>");
					out.println("alert('Τα στοιχεία διαμονής σας ενημερώθηκαν επιτυχώς.');");
					out.println("window.history.back();");
					out.println("</script>");
					out.close();
				}
				if(request.getParameter("submitAdditional") != null) {
					System.out.println("Submit additional info");
					String tel = request.getParameter("tel");
					String birthdayStr = request.getParameter("birthday");
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date birthday = sdf.parse(birthdayStr);
					updateUser.setTel(tel);
					updateUser.setBirthday(birthday);
					UpdateProfile update = new UpdateProfile();
					update.updateAdditional(updateUser);
					response.setContentType("text/html; charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script type=text/javascript>");
					out.println("alert('Το τηλέφωνο ή η ημερομηνία γέννησής σας ενημερώθηκαν επιτυχώς.');");
					out.println("window.history.back();");
					out.println("</script>");
					out.close();
				}
			}
		}catch(Exception e) {
			PrintStream s = null;
			e.printStackTrace(s);
		}
		
		
	}

}
