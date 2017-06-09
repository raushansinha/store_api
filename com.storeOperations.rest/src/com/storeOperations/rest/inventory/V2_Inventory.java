package com.storeOperations.rest.inventory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.*;

import org.codehaus.jettison.json.JSONArray;

import java.sql.*;
import com.storeOperations.dao.*;
import com.storeOperations.util.*;

@Path("/inv/v2")
public class V2_Inventory {

	@GET
	@Path("/Inventory")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnItem(
			@QueryParam("code") String code) 
			throws Exception {
		
		PreparedStatement query = null;
		//String myString = null;
		String returnString = null;
		Connection conn = null;
		Response rb = null;
		
		try {
			
			if(code == null) {
				return Response.status(400).entity("Error: please specify item code for this serach").build();
			}
			
			StoreSchema dao  = new StoreSchema();
			
			JSONArray json = new JSONArray();
			
			json = dao.queryReturnAnItem(code);
			
			rb = Response.ok(json.toString()).build();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(conn != null) conn.close();
		}
		
		return rb;
	}
}
