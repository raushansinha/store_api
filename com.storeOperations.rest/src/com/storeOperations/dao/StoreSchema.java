package com.storeOperations.dao;

import java.sql.*;
import org.codehaus.jettison.json.JSONArray;
import com.storeOperations.util.ToJSON;

public class StoreSchema extends OracleStoreOperations {
	
	public JSONArray queryReturnAnItem(String code) throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = OracleStoreOperationsConn();
			query = conn.prepareStatement("select CODE, DESCRIPTION, UNIT_OF_MEASURE, UNIT_PRICE_INR, AVAILABLE_STOCK " +
										  "FROM item_master " +
										  "where UPPER(code) = ?");
			
			query.setString(1, code.toUpperCase());
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
