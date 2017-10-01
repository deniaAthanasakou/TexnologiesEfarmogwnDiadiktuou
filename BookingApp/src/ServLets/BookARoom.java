package ServLets;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JavaFiles.BookingRoom;

/**
 * Servlet implementation class BookARoom
 */
@WebServlet("/BookARoom")
public class BookARoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookARoom() {
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
		String from = request.getParameter("fromDateTxt");
		String to = request.getParameter("toDateTxt");
		Cookie[] cookies = null;
		cookies = request.getCookies();
		String room_id= "";
		for (Cookie cookie : cookies) {
			
			if (cookie.getName().equals("room_id")) {
				room_id = cookie.getValue();
			}
			
		}
		
		String username="";
		try {
			
			HttpSession session = request.getSession(false); 
			if (session != null) {
				username = (String) session.getAttribute("username");
			}else {
				response.setContentType("text/html ; charset=\"UTF-8\""); 
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script type=text/javascript>");
				out.println("alert('Δεν είστε συνδεδεμένος για να ενοικιάσετε το διαμέρισμα.');");
				out.println("window.history.back();");
				out.println("</script>");
				out.close();
			}
	        
		}catch(Exception e) {
			PrintStream s = null;
			e.printStackTrace(s);
		}
		
		
		
		BookingRoom book = new BookingRoom();
		try {
			if(book.bookARoom(from,to,room_id,username)) {
				response.setContentType("text/html ; charset=\"UTF-8\""); 
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script type=text/javascript>");
				out.println("alert('Η ενοικίαση έγινε με επιτυχία!');");
				out.println("window.history.back();");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
