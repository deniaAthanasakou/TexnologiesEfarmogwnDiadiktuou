package ServLets;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;

import JavaFiles.CreateXmls;

/**
 * Servlet implementation class ExportData
 */
@WebServlet("/ExportData")
public class ExportData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportData() {
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
		System.out.println("INSIDE EXPORT");
		CreateXmls xml = new CreateXmls();
		if(request.getParameter("areaHosting") != null) {
			System.out.println("XML AREA");
			try {
				String source = xml.areaHostingXML();
				response.setContentType("text/xml");
			    response.setHeader("Content-Disposition", "attachment; filename=\"areaHosting.xml\"");
			    OutputStream outputStream = response.getOutputStream();
		        outputStream.write(source.getBytes());
		        outputStream.flush();
		        outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(request.getParameter("booking") != null) {
			System.out.println("XML booking");
			String source = null;
			try {
				source = xml.bookingXML();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("text/xml");
		    response.setHeader("Content-Disposition", "attachment; filename=\"booking.xml\"");
		    OutputStream outputStream = response.getOutputStream();
	        outputStream.write(source.getBytes());
	        outputStream.flush();
	        outputStream.close();
		}
		if(request.getParameter("criticsApt") != null) {
			System.out.println("XML criticsApt");
			String source = null;
			try {
				source = xml.criticsAptXML();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("text/xml");
		    response.setHeader("Content-Disposition", "attachment; filename=\"criticsApt.xml\"");
		    OutputStream outputStream = response.getOutputStream();
	        outputStream.write(source.getBytes());
	        outputStream.flush();
	        outputStream.close();
		}
		if(request.getParameter("criticsHost") != null) {
			System.out.println("XML criticsHost");
			String source = null;
			try {
				source = xml.criticsHostXML();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("text/xml");
		    response.setHeader("Content-Disposition", "attachment; filename=\"criticsHost.xml\"");
		    OutputStream outputStream = response.getOutputStream();
	        outputStream.write(source.getBytes());
	        outputStream.flush();
	        outputStream.close();
		}
	}

}
