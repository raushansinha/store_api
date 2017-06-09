package com.storeOperations.util;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import org.owasp.esapi.ESAPI;

import java.sql.*;

public class ToJSON {

	public JSONArray toJSONArray(ResultSet rs) throws Exception {
		JSONArray json = new JSONArray();
		String temp;
		
		try {
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			while(rs.next()) {
				int numColumn = rsmd.getColumnCount();
				
				JSONObject obj = new JSONObject();
				
				for(int i=1; i<=numColumn; i++) {
					
					String columnName = rsmd.getColumnName(i);
					
					if(rsmd.getColumnType(i) == Types.ARRAY) {
						obj.put(columnName, rs.getArray(columnName));
						System.out.println(columnName);
					} else if(rsmd.getColumnType(i) == Types.BIGINT) {
						obj.put(columnName, rs.getInt(columnName));
						System.out.println(columnName);
					} else if(rsmd.getColumnType(i) == Types.BOOLEAN) {
						obj.put(columnName, rs.getBoolean(columnName));
						System.out.println(columnName);
					} else if(rsmd.getColumnType(i) == Types.BLOB) {
						obj.put(columnName, rs.getBlob(columnName));
						System.out.println(columnName);
					} else if(rsmd.getColumnType(i) == Types.DOUBLE) {
						obj.put(columnName, rs.getDouble(columnName));
						System.out.println(columnName);
					} else if(rsmd.getColumnType(i) == Types.FLOAT) {
						obj.put(columnName, rs.getFloat(columnName));
						System.out.println(columnName);
					} else if(rsmd.getColumnType(i) == Types.INTEGER) {
						obj.put(columnName, rs.getInt(columnName));
						System.out.println(columnName);
					} else if(rsmd.getColumnType(i) == Types.VARCHAR) {
						
//						temp = rs.getString(columnName);
//						temp = ESAPI.encoder().canonicalize(temp);
//						temp = ESAPI.encoder().encodeForHTML(temp);
//						obj.put(columnName, temp);
						
						obj.put(columnName, rs.getString(columnName));
						System.out.println(columnName);
					} else if(rsmd.getColumnType(i) == Types.NVARCHAR) {
						obj.put(columnName, rs.getString(columnName));
						System.out.println(columnName);
					} else if(rsmd.getColumnType(i) == Types.TINYINT) {
						obj.put(columnName, rs.getInt(columnName));
						System.out.println(columnName);
					} else if(rsmd.getColumnType(i) == Types.SMALLINT) {
						obj.put(columnName, rs.getInt(columnName));
						System.out.println(columnName);
					} else if(rsmd.getColumnType(i) == Types.DATE) {
						obj.put(columnName, rs.getDate(columnName));
						System.out.println(columnName);
					} else if(rsmd.getColumnType(i) == Types.TIMESTAMP) {
						obj.put(columnName, rs.getTimestamp(columnName));
						System.out.println(columnName);
					} else if(rsmd.getColumnType(i) == Types.NUMERIC) {
						obj.put(columnName, rs.getBigDecimal(columnName));
						System.out.println(columnName);
					} else {
						obj.put(columnName, rs.getObject(columnName));
						System.out.println(columnName);
					}
				}
				
				json.put(obj);
			}
			

			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return json;
	}
}
