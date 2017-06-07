package com.storeOperations.rest.status;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.sql.*;
import com.storeOperations.dao.*;


@Path("/v1")
public class V1_status {
	@GET
	@Path("/status")
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>REST API response</p>";
	}
	
	@GET
	@Path("/database")
	@Produces(MediaType.TEXT_HTML)
	public String returnDataBaseStatus() throws Exception {
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
		
		try {
			
			//conn = OracleStoreOperations.OracleStoreOperationsConn().getConnection();
			conn = OracleStoreOperations.OracleStoreOperationsConn();
			query = conn.prepareStatement("select to_char(sysdate,'dd/MM/yyy hh:mm:ss') as DATETIME from sys.dual");
			
			ResultSet rs = query.executeQuery();
			
			while(rs.next()) {
				myString = rs.getString("DATETIME");
			}
			
			query.close();
			
			returnString = "<p>Database Status</p>" +
					"<p>Database Date/Time return: " + myString + "</p>";
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(conn != null) conn.close();
		}
		
		return returnString;
	}
}
