package ServLets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaFiles.InputAptSql;

/**
 * Servlet implementation class InputAptServlet
 */
@WebServlet("/InputAptServlet")
public class InputAptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InputAptServlet() {
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
			System.out.println("INSIDE SERVLETt");
	    	
			InputAptSql myClass = new InputAptSql();
	    	
	    	
	    	//System.out.println(request.getParameter("submit"));
	        if (request.getParameter("submit") != null) {
	        	System.out.println("MAGKAS BEFORE");
	            myClass.inputApt();
	            System.out.println("MAGKAS AFTER");
	        } else {
	        	System.out.println("GUFTOS");
	        }

	        //request.getRequestDispatcher("/WEB-INF/some-result.jsp").forward(request, response);
		doGet(request, response);
	}

}
