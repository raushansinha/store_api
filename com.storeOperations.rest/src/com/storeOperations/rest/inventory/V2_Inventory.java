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

import java.sql.*;
import com.storeOperations.dao.*;
import com.storeOperations.util.*;

@Path("/v2/Inventory")
public class V2_Inventory {


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnItem(
			@QueryParam("brand") String brand) 
			throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		Response rb = null;
		
		try {
			
			if(brand == null) {
				return Response.status(400).entity("Error: Please specify item code for this serach").build();
			}
			
			StoreSchema dao  = new StoreSchema();
			
			JSONArray json = new JSONArray();
			
			json = dao.queryReturnItemForBrand(brand);
			
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
	
	@Path("/{brand}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getItem(
			@PathParam("brand")String brand) 
			throws Exception {
		
		Connection conn = null;
		Response rb = null;
		
		try {
			
			StoreSchema dao  = new StoreSchema();
			
			JSONArray json = new JSONArray();
			
			json = dao.queryReturnItemForBrand(brand);
			
			rb = Response.ok(json.toString()).build();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(conn != null) conn.close();
		}
		
		return rb;
	}
	
	/*@Path("/{stock}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStock(
			@PathParam("stock") int stock) 
			throws Exception {
		
		Connection conn = null;
		Response rb = null;
		
		try {
						
			StoreSchema dao  = new StoreSchema();
			
			JSONArray json = new JSONArray();
			
			json = dao.queryReturnStock(stock);
			
			rb = Response.ok(json.toString()).build();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(conn != null) conn.close();
		}
		
		return rb;
	}*/
	
	@Path("/{brand}/{code}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getItemStock(
			@PathParam("brand") String brand,
			@PathParam("code") String code) 
			throws Exception {
		
		Connection conn = null;
		Response rb = null;
		
		try {
						
			StoreSchema dao  = new StoreSchema();
			
			JSONArray json = new JSONArray();
			
			json = dao.queryReturnItemForBrandAndCode(brand, code);
			
			rb = Response.ok(json.toString()).build();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(conn != null) conn.close();
		}
		
		return rb;
	}
	
	@POST
	@Path("/create")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED) // Only web form
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStoreItem(String incomingData) throws Exception {
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		StoreSchema dao = new StoreSchema();
		
		try {
			
			System.out.println("incoming Data: " + incomingData);
			
			ObjectMapper mapper = new ObjectMapper();
			ItemEntry itemEntry = mapper.readValue(incomingData, ItemEntry.class);
			
			int http_code = dao.insertIntoItem_Master(itemEntry.BRAND, 
													  itemEntry.CODE, 
													  itemEntry.DESCRIPTION, 
													  itemEntry.UNIT_OF_MEASURE, 
													  itemEntry.UNIT_PRICE_INR, 
													  itemEntry.AVAILABLE_STOCK);
			
			if(http_code == 200) {
				//returnString = jsonArray.toString();
				returnString = "Item Inserted";
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

class ItemEntry {
	public String BRAND;
	public String CODE;
	public String DESCRIPTION;
	public String UNIT_OF_MEASURE;
	public String UNIT_PRICE_INR;
	public String AVAILABLE_STOCK;
}
