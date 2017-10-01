package ServLets;

import JavaFiles.ConnectionManager;

import JavaFiles.ImgToBArray;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/WelcomeInsertServlet")
public class WelcomeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WelcomeInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {

		try {
			
			Statement stmt = ConnectionManager.getConnection().createStatement();

			String query = "SELECT isInitialized FROM options";
			ResultSet rs = stmt.executeQuery(query);
			rs.beforeFirst();
			boolean isInitialized = false;
			while (rs.next()) {
				isInitialized = rs.getBoolean(1);
			}

			if (isInitialized) {
				return;
			}

			ArrayList<String> lines = getInitializationStatements();

			// set isInitialized
			lines.add("INSERT INTO options (isInitialized) VALUES (true)");
			
			ArrayList<String> fixedImages = fixApartmentPhoto();
			
			ArrayList<String> morePhotos = InsertMorePhotos();

			ArrayList<String> finalLines = new ArrayList<String>();
			
			for (String line: lines) {
				finalLines.add(line);
			}
			for (String line: fixedImages) {
				finalLines.add(line);
			}
			for (String line: morePhotos) {
				finalLines.add(line);
			}
			
			
			
			
			for (String line : finalLines) {
				stmt.executeUpdate(line);
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private ArrayList<String> getInitializationStatements() {
		ArrayList<String> statements = new ArrayList<String>();
		
		ServletContext context = this.getServletContext();
		InputStream is = context.getResourceAsStream("/WEB-INF/init.txt");
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       statements.add(line);
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return statements;
	}
	
	private ArrayList<String> fixApartmentPhoto() throws IOException{
		ArrayList<String> statements = new ArrayList<String>();
		ArrayList<String> barray = getBArrayImgs();
		for(int i=0; i<12; i++) {
			String ba=barray.get(i).toString();
			String line = "UPDATE Apartment SET room_photo = '"+ ba+"' WHERE room_id= "+(i+1);
			statements.add(line);
		} 

		
		return statements;
	}
	
	private ArrayList<String> getBArrayImgs() throws IOException{
		ArrayList<String> barray = new ArrayList<String>();
		ImgToBArray imgToArray = new ImgToBArray();
		for(int i=1; i<13; i++) {
			String image =getImage(i);
			barray.add(imgToArray.convertImageToBArray(image).toString());
		}
		
		return barray;
	}
	
	private String getImage(int i) {
		ServletContext context = this.getServletContext();
		String fullPath = context.getRealPath("/WEB-INF/initializationImages/room"+i+".jpg");
		return fullPath;
	}
	
	private ArrayList<String> getMorePhotos() throws IOException{
		ArrayList<String> barray = new ArrayList<String>();
		ImgToBArray imgToArray = new ImgToBArray();
		for(int i=13; i<=20; i++) {						
			String image =getImage(i);
			barray.add(imgToArray.convertImageToBArray(image).toString());
		}
		
		return barray;
	}
	
	private ArrayList<String> InsertMorePhotos() throws IOException{
		ArrayList<String> lines = new ArrayList<String>();
		ArrayList<String> barray = getMorePhotos();
		
		lines.add("INSERT INTO MorePhotos (room_id,image) VALUES (1,'"+barray.get(0)+"')");
		lines.add("INSERT INTO MorePhotos (room_id,image) VALUES (1,'"+barray.get(1)+"')");
		lines.add("INSERT INTO MorePhotos (room_id,image) VALUES (6,'"+barray.get(2)+"')");
		lines.add("INSERT INTO MorePhotos (room_id,image) VALUES (7,'"+barray.get(3)+"')");
		lines.add("INSERT INTO MorePhotos (room_id,image) VALUES (10,'"+barray.get(4)+"')");
		lines.add("INSERT INTO MorePhotos (room_id,image) VALUES (10,'"+barray.get(5)+"')");
		lines.add("INSERT INTO MorePhotos (room_id,image) VALUES (10,'"+barray.get(6)+"')");
		lines.add("INSERT INTO MorePhotos (room_id,image) VALUES (12,'"+barray.get(7)+"')");
		
		return lines;
	}
	

}
