package com.storeOperations.rest.inventory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

import org.codehaus.jettison.json.JSONArray;

import java.sql.*;
import com.storeOperations.dao.*;
import com.storeOperations.util.*;

@Path("/inv/v1")
public class V1_inventory {
	
	@GET
	@Path("/Inventory")
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllSroreItems() throws Exception {
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
		Response rb = null;
		
		try {
			
			conn = OracleStoreOperations.OracleStoreOperationsConn();
			query = conn.prepareStatement("select * from item_master");
			
			ResultSet rs = query.executeQuery();
			
			ToJSON converter = new ToJSON();
			JSONArray json = new JSONArray();
			
			json = converter.toJSONArray(rs);
			query.close();
			
			returnString = json.toString();
			rb = Response.ok(returnString).build();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(conn != null) conn.close();
		}
		
		return rb;
	}
}
