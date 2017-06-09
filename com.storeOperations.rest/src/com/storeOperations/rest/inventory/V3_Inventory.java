package com.storeOperations.rest.inventory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.*;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import java.sql.*;
import com.storeOperations.dao.*;
import com.storeOperations.util.*;

@Path("/v3/Inventory")
public class V3_Inventory {

	@POST
	@Path("/create")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED) // Only web form
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStoreItem(String incomingData) throws Exception {
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		StoreSchema dao = new StoreSchema();
		
		try {
			
			JSONObject itemData = new JSONObject(incomingData);
			System.out.println("incoming Data: " + incomingData);
			
//			ObjectMapper mapper = new ObjectMapper();
//			ItemEntry itemEntry = mapper.readValue(incomingData, ItemEntry.class);
			
			int http_code = dao.insertIntoItem_Master( itemData.optString("BRAND"), 
														itemData.optString("CODE"), 
														itemData.optString("DESCRIPTION"), 
														itemData.optString("UNIT_OF_MEASURE"), 
														itemData.optString("UNIT_PRICE_INR"), 
														itemData.optString("AVAILABLE_STOCK")
														);
			
			if(http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG","Item has been entered successfully, version 3");
				returnString = jsonArray.put(jsonArray).toString();
			} else {
				return Response.status(500).entity("Unable to process Item").build();
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		} 
		
		return Response.ok(returnString).build();
	}
}
