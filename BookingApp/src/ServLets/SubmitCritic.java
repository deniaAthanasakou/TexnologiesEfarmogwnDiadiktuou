package ServLets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Criticshost;
import Entities.CriticshostPK;
import JavaFiles.SubmitHost;

/**
 * Servlet implementation class SubmitCritic
 */
@WebServlet("/SubmitCritic")
public class SubmitCritic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitCritic() {
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
		System.out.println("SUBMIT HOST CRITIC");
		String tenantId = "4";
		String hostId = "2";			//get them
		String criticStr = request.getParameter("critic_host");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		SubmitHost critic = new SubmitHost();
		CriticshostPK id = new CriticshostPK(date,Integer.parseInt(hostId),Integer.parseInt(tenantId));
		Criticshost info = new Criticshost(id,Integer.parseInt(criticStr));
		try {
			boolean submission = critic.submitCritic(info);
			if(submission) {
				response.setContentType("text/html ; charset=\"UTF-8\""); 
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script type=text/javascript>");
				out.println("alert('Η κριτική προστέθηκε επιτυχώς.');");
				out.println("window.history.back();");
				out.println("</script>");
				out.close();
			}else {
				response.setContentType("text/html ; charset=\"UTF-8\""); 
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script type=text/javascript>");
				out.println("alert('Η κριτική υπάρχει ήδη.');");
				out.println("window.history.back();");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
