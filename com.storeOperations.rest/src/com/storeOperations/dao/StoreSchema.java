package com.storeOperations.dao;

import java.sql.*;
import org.codehaus.jettison.json.JSONArray;
import com.storeOperations.util.ToJSON;

public class StoreSchema extends OracleStoreOperations {
	
	public int insertIntoItem_Master(String BRAND, String CODE, String DESCRIPTION, String UNIT_OF_MEASURE, String UNIT_PRICE_INR, String AVAILABLE_STOCK) throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		
		try{
			
			conn = OracleStoreOperationsConn();
			query = conn.prepareStatement("insert into item_master " +
										  "(BRAND, CODE, DESCRIPTION, UNIT_OF_MEASURE, UNIT_PRICE_INR, AVAILABLE_STOCK) " +
										  "values (?, ?, ?, ?, ?, ?)");
			
			query.setString(1, BRAND);
			query.setString(2, CODE);
			query.setString(3, DESCRIPTION);
			query.setString(4, UNIT_OF_MEASURE);
			int unitPriceINR = Integer.parseInt(UNIT_PRICE_INR);
			query.setInt(5, unitPriceINR);
			int availableStock = Integer.parseInt(AVAILABLE_STOCK);
			query.setInt(6, availableStock);
			
			System.out.println("Query: " + query);
			query.executeUpdate();
	
		} catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return 500;
		} catch(Exception e) {
			e.printStackTrace();
			return 500;
		}
		finally {
			if(conn != null) conn.close();
		}
		
		return 200;
	}
	
	public JSONArray queryReturnItemForBrand(String brand) throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = OracleStoreOperationsConn();
			query = conn.prepareStatement("select BRAND, CODE, DESCRIPTION, UNIT_OF_MEASURE, UNIT_PRICE_INR, AVAILABLE_STOCK " +
										  "FROM item_master " +
										  "where UPPER(brand) = ?");
			
			query.setString(1, brand.toUpperCase());
			ResultSet rs = query.executeQuery();
			
			json = converter.toJSONArray(rs);
			query.close();	
		} catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
			
		} catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if(conn != null) conn.close();
		}
		
		return json;
	}
	
	/*public JSONArray queryReturnStock(int stock) throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = OracleStoreOperationsConn();
			query = conn.prepareStatement("select CODE, DESCRIPTION, UNIT_OF_MEASURE, UNIT_PRICE_INR, AVAILABLE_STOCK " +
										  "FROM item_master " +
										  "where available_stock = ?");
			
			query.setInt(1, stock);
			ResultSet rs = query.executeQuery();
			
			json = converter.toJSONArray(rs);
			query.close();	
		} catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
			
		} catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if(conn != null) conn.close();
		}
		
		return json;
	}
	*/
	
	public JSONArray queryReturnItemForBrandAndCode(String brand, String code) throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = OracleStoreOperationsConn();
			query = conn.prepareStatement("select BRAND, CODE, DESCRIPTION, UNIT_OF_MEASURE, UNIT_PRICE_INR, AVAILABLE_STOCK " +
										  "FROM item_master " +
										  "where UPPER(brand) = ? " +
										  "and UPPER(code) = ?");
			
			query.setString(1, brand.toUpperCase());
			query.setString(2, code.toUpperCase());
			ResultSet rs = query.executeQuery();
			
			json = converter.toJSONArray(rs);
			query.close();	
		} catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
			
		} catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if(conn != null) conn.close();
		}
		
		return json;
	}
}
