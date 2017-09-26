package ServLets;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaFiles.InputAptSql;

import Entities.Facility;
import Entities.Freedate;
import Entities.Location;
import Entities.Apartment;
import Entities.Rule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date; 

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
			 
			//get fields
			String street = request.getParameter("street");
			int address_num =  Integer.parseInt(request.getParameter("address_num"));
			String postal = request.getParameter("postal");
			String neighborhood = request.getParameter("neighborhood");
			String city = request.getParameter("city");
			String country = request.getParameter("country");
			
			String transport = request.getParameter("transport");
			
		   
		    
			
			//Dates		
			
			//ArrayList<Date> from = new ArrayList<Date>();
			//ArrayList<Date> to = new ArrayList<Date>();
			Date from = new Date();
			Date to = new Date();
			
			
			String fromDateStr = request.getParameter("from");
			try {
				 from = new SimpleDateFormat("dd-MM-yyyy").parse(fromDateStr.replaceAll("/", "-"));
			} catch (ParseException e2) {
				e2.printStackTrace();
			}
			
			String toDateStr = request.getParameter("to");
			try {
				 to = new SimpleDateFormat("dd-MM-yyyy").parse(toDateStr.replaceAll("/", "-"));
			} catch (ParseException e2) {
				e2.printStackTrace();
			}
			
		
			
			int beds = Integer.parseInt(request.getParameter("beds"));
			int bathrooms = Integer.parseInt(request.getParameter("bathrooms"));
			int rooms = Integer.parseInt(request.getParameter("rooms"));
			int bedrooms = Integer.parseInt(request.getParameter("bedrooms"));
			String type = request.getParameter("type");	
			int space = Integer.parseInt(request.getParameter("space"));
			int maxPeople = Integer.parseInt(request.getParameter("maxPeople"));
			int minValue = Integer.parseInt(request.getParameter("minValue"));
			int costperpers = Integer.parseInt(request.getParameter("costperpers"));
			int minDays = Integer.parseInt(request.getParameter("minDays"));
			String description = request.getParameter("description");
			int costPerDay = Integer.parseInt(request.getParameter("costPerDay"));
			
			String smokingStr =  request.getParameter("smoking");
			String eventsStr =request.getParameter("events");
			String petsStr =request.getParameter("pets");
			String livingroomStr =  request.getParameter("livingroom");
			
			String wifiStr =  request.getParameter("wifi");
			String airconditionStr =request.getParameter("aircondition");
			String heatingStr =request.getParameter("heating");
			String kitchenStr =  request.getParameter("kitchen");
			String tvStr =request.getParameter("tv");
			String parkingStr =request.getParameter("parking");
			String elevatorStr =  request.getParameter("elevator");
			
			
			String imagePath = request.getParameter("imagePath");
			
			String myMap = request.getParameter("myMap"); 
			
			boolean smoking;
			boolean events;
			boolean pets;
			boolean livingroom;
			
			boolean wifi;
			boolean aircondition;
			boolean heating;
			boolean kitchen;
			boolean tv;
			boolean parking;
			boolean elevator;
			
			if(smokingStr.equals("Yes"))
				smoking=true;
			else
				smoking=false;
			
			if(eventsStr.equals("Yes"))
				events=true;
			else 
				events=false;
			
			if(petsStr.equals("Yes"))
				pets=true;
			else 
				pets=false;
			
			if(livingroomStr.equals("Yes"))
				livingroom=true;
			else 
				livingroom=false;
			
			if(wifiStr.equals("Yes"))
				wifi=true;
			else 
				wifi=false;
			
			if(airconditionStr.equals("Yes"))
				aircondition=true;
			else 
				aircondition=false;
			
			if(heatingStr.equals("Yes"))
				heating=true;
			else 
				heating=false;
			
			if(kitchenStr.equals("Yes"))
				kitchen=true;
			else 
				kitchen=false;
			
			if(tvStr.equals("Yes"))
				tv=true;
			else 
				tv=false;
			
			if(parkingStr.equals("Yes"))
				parking=true;
			else 
				parking=false;
			
			if(elevatorStr.equals("Yes"))
				elevator=true;
			else 
				elevator=false;
			
			
		/*	
			System.out.println(street);
			System.out.println(address_num);
			System.out.println(postal);
			System.out.println(neighborhood);
			System.out.println(city);
			System.out.println(country);
			System.out.println(transport);
			System.out.println(beds);
			System.out.println(bathrooms);
			System.out.println(rooms);
			
			System.out.println(type);
			System.out.println(costPerDay);
			
			System.out.println("l "+livingroom);
			System.out.println(wifi);
			System.out.println(kitchen);
			System.out.println(heating);
			System.out.println(tv);
			System.out.println(aircondition);
			System.out.println(parking);
			System.out.println(elevator);
			
			
			System.out.println(space);
			System.out.println(maxPeople);
			System.out.println(minValue);
			System.out.println("s "+smoking);
			System.out.println(costperpers);
			System.out.println(pets);
			System.out.println(events);
			System.out.println(minDays);
			System.out.println(description);
			System.out.println(bedrooms);
			
			System.out.println("img " +imagePath);
			System.out.println("map " +map);		

			*/
			
			
			
			//null
			int critics =0;
			float averageCritic= 0;
			int host_id=-1;
			
			ArrayList<String> photos = null;
			

			
			Apartment apt = new Apartment(averageCritic, space, costPerDay, costperpers, description, host_id, livingroom,maxPeople,minValue,bathrooms,bedrooms,beds,critics,rooms,imagePath,type);
			Facility fac = new Facility(aircondition,elevator,heating,kitchen,parking,tv,wifi);
			Rule rule = new Rule (events,minDays,pets, smoking );
			Freedate fd = new Freedate(from, to);
			Location loc = new Location(address_num,city,country,myMap,neighborhood,postal,street,transport);
			
			

			
		//	Apartments apt = new Apartments(imagePath,costPerDay,type,rooms, critics,average_critic,beds,bathrooms,bedrooms,livingroom,space,description,maxPeople,minValue,costperpers,photos,host_id,smoking,pets,events,minDays,myMap,address_num,street,postal,city,country,neighborhood,transport,wifi,aircondition,heating,kitchen,tv,parking,elevator,from,to);

	
	    	
			InputAptSql myClass = new InputAptSql();

	        if (request.getParameter("submit") != null) {
	        	System.out.println("MAGKAS BEFORE");
	            myClass.inputApt(apt,loc,rule,fac,fd);
	            System.out.println("MAGKAS AFTER");
	        } else {
	        	System.out.println("GUFTOS");
	        }

	        //request.getRequestDispatcher("/WEB-INF/some-result.jsp").forward(request, response);
		//doGet(request, response);
	}
	
	
	private static byte[] convertImageToBArray(String ImageName) throws IOException {

		System.out.println("first line");
		// open image
		File imgPath = new File(ImageName);
		System.out.println("2 line"+ImageName);
		
		BufferedImage bufferedImage = ImageIO.read(imgPath);
		System.out.println("3 line");

		// get DataBufferBytes from Raster
		WritableRaster raster = bufferedImage .getRaster();
		System.out.println("4 line");
		DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
		System.out.println("5 line");

		return ( data.getData() );
	}


}
