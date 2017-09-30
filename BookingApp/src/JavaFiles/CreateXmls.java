package JavaFiles;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

public class CreateXmls {
	public String areaHostingXML() throws SQLException, ClassNotFoundException, TransformerException {
		System.out.println("INSIDE XML AREA HOSTING");
		Statement stmt = ConnectionManager.getConnection().createStatement();

		//check for same username before insert
		String checkQuery = "SELECT cost_per_day, type, number_rooms, number_critics, average_critic, number_beds, number_bathrooms, number_bedrooms, livingroom, capacity, description, max_tenants, min_cost_booking, cost_per_person, map, address_number, street, postal_code, city, country, neighborhood, transportation, smoking_allowed, pets_allowed, events, min_days_booking, wifi, aircondition, heating, kitchen, tv, parking, elevator" + 
				" FROM Apartment" + 
				" NATURAL JOIN Location" + 
				" NATURAL JOIN Rule" + 
				" NATURAL JOIN Facilities";
		
		System.out.println(checkQuery);

		ResultSet rs = stmt.executeQuery(checkQuery);
		rs.beforeFirst();
		int rowCount = rs.last() ? rs.getRow() : 0;
	
		if(rowCount!=0) {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = null;
			try {
				docBuilder = docFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("booking");
			doc.appendChild(rootElement);
			
			rs.beforeFirst();
			while(rs.next()) {
				
				// apartment elements
				Element apartment = doc.createElement("apartment");
				rootElement.appendChild(apartment);
				
				// cost per day elements
				Element costPerDay = doc.createElement("cost_per_day");
				costPerDay.appendChild(doc.createTextNode(rs.getString("cost_per_day")));
				apartment.appendChild(costPerDay);
				
				// type elements
				Element type = doc.createElement("type");
				type.appendChild(doc.createTextNode(rs.getString("type")));
				apartment.appendChild(type);
				
				// number rooms elements
				Element numberRooms = doc.createElement("number_rooms");
				numberRooms.appendChild(doc.createTextNode(rs.getString("number_rooms")));
				apartment.appendChild(numberRooms);
				

				// number critics elements
				Element numberCritics = doc.createElement("number_critics");
				numberCritics.appendChild(doc.createTextNode(rs.getString("number_critics")));
				apartment.appendChild(numberCritics);
				
				// avg critics elements
				Element avgCritic = doc.createElement("average_critic");
				avgCritic.appendChild(doc.createTextNode(rs.getString("average_critic")));
				apartment.appendChild(avgCritic);
				
				// number beds elements
				Element numberBeds = doc.createElement("number_beds");
				numberBeds.appendChild(doc.createTextNode(rs.getString("number_beds")));
				apartment.appendChild(numberBeds);
				
				// number baths elements
				Element numberBaths = doc.createElement("number_bathrooms");
				numberBaths.appendChild(doc.createTextNode(rs.getString("number_bathrooms")));
				apartment.appendChild(numberBaths);
				
				// number bedrooms elements
				Element numberBedrooms = doc.createElement("number_bedrooms");
				numberBedrooms.appendChild(doc.createTextNode(rs.getString("number_bedrooms")));
				apartment.appendChild(numberBedrooms);
				
				// livingroom elements
				Element livingRoom = doc.createElement("livingroom");
				livingRoom.appendChild(doc.createTextNode(rs.getString("livingroom")));
				apartment.appendChild(livingRoom);
				
				// capacity elements
				Element capacity = doc.createElement("capacity");
				capacity.appendChild(doc.createTextNode(rs.getString("capacity")));
				apartment.appendChild(capacity);
				
				// description elements
				Element description = doc.createElement("description");
				description.appendChild(doc.createTextNode(rs.getString("description")));
				apartment.appendChild(description);
				
				// max tenants elements
				Element maxTenants = doc.createElement("max_tenants");
				maxTenants.appendChild(doc.createTextNode(rs.getString("max_tenants")));
				apartment.appendChild(maxTenants);
				
				// min_cost_booking elements
				Element minCostBooking = doc.createElement("min_cost_booking");
				minCostBooking.appendChild(doc.createTextNode(rs.getString("min_cost_booking")));
				apartment.appendChild(minCostBooking);
				
				// cost_per_person elements
				Element costPerPerson = doc.createElement("cost_per_person");
				costPerPerson.appendChild(doc.createTextNode(rs.getString("cost_per_person")));
				apartment.appendChild(costPerPerson);
				
				// map elements
				Element map = doc.createElement("map");
				map.appendChild(doc.createTextNode(rs.getString("map")));
				apartment.appendChild(map);
				
				// address_number elements
				Element addressNumber = doc.createElement("address_number");
				addressNumber.appendChild(doc.createTextNode(rs.getString("address_number")));
				apartment.appendChild(addressNumber);
				
				// street elements
				Element street = doc.createElement("street");
				street.appendChild(doc.createTextNode(rs.getString("street")));
				apartment.appendChild(street);
				
				// postal_code elements
				Element postalCode = doc.createElement("postal_code");
				postalCode.appendChild(doc.createTextNode(rs.getString("postal_code")));
				apartment.appendChild(postalCode);
				
				// city elements
				Element city = doc.createElement("city");
				city.appendChild(doc.createTextNode(rs.getString("city")));
				apartment.appendChild(city);
				
				// country elements
				Element country = doc.createElement("country");
				country.appendChild(doc.createTextNode(rs.getString("country")));
				apartment.appendChild(country);
				
				// neighborhood elements
				Element neighborhood = doc.createElement("neighborhood");
				neighborhood.appendChild(doc.createTextNode(rs.getString("neighborhood")));
				apartment.appendChild(neighborhood);
				
				// transportation elements
				Element transportation = doc.createElement("transportation");
				transportation.appendChild(doc.createTextNode(rs.getString("transportation")));
				apartment.appendChild(transportation);
				
				// smoking_allowed elements
				Element smokingAllowed = doc.createElement("smoking_allowed");
				smokingAllowed.appendChild(doc.createTextNode(rs.getString("smoking_allowed")));
				apartment.appendChild(smokingAllowed);
				
				// pets_allowed elements
				Element petsAllowed = doc.createElement("pets_allowed");
				petsAllowed.appendChild(doc.createTextNode(rs.getString("pets_allowed")));
				apartment.appendChild(petsAllowed);

				// events elements
				Element events = doc.createElement("events");
				events.appendChild(doc.createTextNode(rs.getString("events")));
				apartment.appendChild(events);
				
				// min_days_booking elements
				Element minDaysBooking = doc.createElement("min_days_booking");
				minDaysBooking.appendChild(doc.createTextNode(rs.getString("min_days_booking")));
				apartment.appendChild(minDaysBooking);
				
				// wifi elements
				Element wifi = doc.createElement("wifi");
				wifi.appendChild(doc.createTextNode(rs.getString("wifi")));
				apartment.appendChild(wifi);
				
				// aircondition elements
				Element aircondition = doc.createElement("aircondition");
				aircondition.appendChild(doc.createTextNode(rs.getString("aircondition")));
				apartment.appendChild(aircondition);
				
				// heating elements
				Element heating = doc.createElement("heating");
				heating.appendChild(doc.createTextNode(rs.getString("heating")));
				apartment.appendChild(heating);
				
				// kitchen elements
				Element kitchen = doc.createElement("kitchen");
				kitchen.appendChild(doc.createTextNode(rs.getString("kitchen")));
				apartment.appendChild(kitchen);
				
				
				// tv elements
				Element tv = doc.createElement("tv");
				tv.appendChild(doc.createTextNode(rs.getString("tv")));
				apartment.appendChild(tv);
				
				// parking elements
				Element parking = doc.createElement("parking");
				parking.appendChild(doc.createTextNode(rs.getString("parking")));
				apartment.appendChild(parking);
				
				// elevator elements
				Element elevator = doc.createElement("elevator");
				elevator.appendChild(doc.createTextNode(rs.getString("elevator")));
				apartment.appendChild(elevator);

				
			}
			DOMImplementationLS domImplementation = (DOMImplementationLS) doc.getImplementation();
		    LSSerializer lsSerializer = domImplementation.createLSSerializer();
		    return lsSerializer.writeToString(doc);   
			
			
		}
		return null;
	}
	
	public String bookingXML() throws SQLException, ClassNotFoundException, TransformerException {
		System.out.println("INSIDE XML BOOKING");
		Statement stmt = ConnectionManager.getConnection().createStatement();

		//check for same username before insert
		String checkQuery = "SELECT * FROM Booking";
		
		System.out.println(checkQuery);

		ResultSet rs = stmt.executeQuery(checkQuery);
		rs.beforeFirst();
		int rowCount = rs.last() ? rs.getRow() : 0;
	
		if(rowCount!=0) {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = null;
			try {
				docBuilder = docFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("booking");
			doc.appendChild(rootElement);
			
			rs.beforeFirst();
			while(rs.next()) {
				
				//bookInfo elements
				Element bookInfo = doc.createElement("bookInfo");
				rootElement.appendChild(bookInfo);
				
				//listing_id, room_id, tenant_id, date_from, date_to
				// cost per day elements
				Element listingId = doc.createElement("listing_id");
				listingId.appendChild(doc.createTextNode(rs.getString("listing_id")));
				bookInfo.appendChild(listingId);
				
				// room_id elements
				Element roomId = doc.createElement("room_id");
				roomId.appendChild(doc.createTextNode(rs.getString("room_id")));
				bookInfo.appendChild(roomId);
				
				// tenant_id elements
				Element tenantId = doc.createElement("tenant_id");
				tenantId.appendChild(doc.createTextNode(rs.getString("tenant_id")));
				bookInfo.appendChild(tenantId);
				
				// date_from elements
				Element dateFrom = doc.createElement("date_from");
				dateFrom.appendChild(doc.createTextNode(rs.getString("date_from")));
				bookInfo.appendChild(dateFrom);
				
				// date_to elements
				Element dateTo = doc.createElement("date_to");
				dateTo.appendChild(doc.createTextNode(rs.getString("date_to")));
				bookInfo.appendChild(dateTo);
				
			}
			
			DOMImplementationLS domImplementation = (DOMImplementationLS) doc.getImplementation();
		    LSSerializer lsSerializer = domImplementation.createLSSerializer();
		    return lsSerializer.writeToString(doc);   
			
		}
		return null;
	}
	

}
