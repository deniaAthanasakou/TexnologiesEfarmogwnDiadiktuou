package ServLets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Apartment;
import Entities.Facility;
import Entities.Freedate;
import Entities.Location;
import Entities.Rule;
import JavaFiles.ChangeAptUpdateSql;
import JavaFiles.InputAptSql;

/**
 * Servlet implementation class ChangeAptUpdate
 */
@WebServlet("/ChangeAptUpdate")
public class ChangeAptUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeAptUpdate() {
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
		//get fields
		String street = request.getParameter("street");
		int address_num =  Integer.parseInt(request.getParameter("address_num"));
		String postal = request.getParameter("postal");
		String neighborhood = request.getParameter("neighborhood");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		
		String transport = request.getParameter("transport");
		
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
		String type = request.getParameter("roomtype");	
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

		int critics =-1;				//this field will not be changed
		float averageCritic= -1;		//this field will not be changed
		
		
		int host_id=-1;					//this field will not be changed
		
		//get room_id
		int room_id=7;
		

		
		Apartment apt = new Apartment(averageCritic, space, costPerDay, costperpers, description, host_id, livingroom,maxPeople,minValue,bathrooms,bedrooms,beds,critics,rooms,imagePath,type);
		Facility fac = new Facility(aircondition,elevator,heating,kitchen,parking,tv,wifi);
		Rule rule = new Rule (events,minDays,pets, smoking );
		Freedate fd = new Freedate(from, to);
		Location loc = new Location(address_num,city,country,myMap,neighborhood,postal,street,transport);
		
		ChangeAptUpdateSql myClass = new ChangeAptUpdateSql();

        if (request.getParameter("submit") != null) {

        	myClass.updateApt(room_id,apt,loc,rule,fac,fd);
        	response.sendRedirect("./html files/aptChangeDone.html");
  
        } 
		
		

	}

}
