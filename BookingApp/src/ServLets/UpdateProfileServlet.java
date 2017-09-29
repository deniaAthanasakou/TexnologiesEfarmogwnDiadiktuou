package ServLets;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

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
					int user = update.updateImage(updateUser);
					if(user==0) {
						System.out.println("hostttt");
						response.sendRedirect("change_profile_host.html");
					}
					else if(user==1) {
						System.out.println("tenantttt");
						response.sendRedirect("change_profile.html");
					}
					if(user==2) {
						System.out.println("adminnnn");
						response.sendRedirect("change_profile_admin.html");
					}
				}
				if(request.getParameter("submitPersonal") != null) {
					System.out.println("Submit personal Info");
				}
				if(request.getParameter("submitEmail") != null) {
					System.out.println("Submit email Info");
				}
				if(request.getParameter("submitPass") != null) {
					System.out.println("Submit new pass");
				}
				if(request.getParameter("submitRoles") != null) {
					System.out.println("Submit roles Info");
				}
				if(request.getParameter("submitResidence") != null) {
					System.out.println("Submit residence Info");
				}
				if(request.getParameter("submitAdditional") != null) {
					System.out.println("Submit additional info");
				}
				
			}
		}catch(Exception e) {
			PrintStream s = null;
			e.printStackTrace(s);
		}
		
		
	}

}
