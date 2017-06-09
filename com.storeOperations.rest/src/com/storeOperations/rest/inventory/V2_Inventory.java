package com.storeOperations.rest.inventory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.*;

import org.codehaus.jettison.json.JSONArray;

import java.sql.*;
import com.storeOperations.dao.*;
import com.storeOperations.util.*;

@Path("/v2/Inventory")
public class V2_Inventory {


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnItem(
			@QueryParam("code") String code) 
			throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		Response rb = null;
		
		try {
			
			if(code == null) {
				return Response.status(400).entity("Error: Please specify item code for this serach").build();
			}
			
			StoreSchema dao  = new StoreSchema();
			
			JSONArray json = new JSONArray();
			
			json = dao.queryReturnAnItem(code);
			
			rb = Response.ok(json.toString()).build();
			
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		finally {
			if(conn != null) conn.close();
		}
		
		return rb;
	}
	
/*	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnErrorOnItem() throws Exception {
		return Response.status(400).entity("Error: Please specify item code for this search").build();
	}
*/
	
	@Path("/{code}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getItem(
			@PathParam("code")String code) 
			throws Exception {
		
		Connection conn = null;
		Response rb = null;
		
		try {
			System.out.println("Code: " + code);
			
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
