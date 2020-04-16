/**
 * 
 */
package com.company.sample.application;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Connects to a database
 * 
 * @author mahasiva
 *
 */
public class RDSConnect {

	private static final RDSConnect connect = new RDSConnect();

	private String jdbcUrl;

	private RDSConnect() {
		initialize();
	}

	private void initialize() {
		if (System.getenv("RDS_HOSTNAME") != null) {
			try {
				Class.forName("org.postgresql.Driver");
				String dbName = System.getenv("RDS_DB_NAME");
				String userName = System.getenv("RDS_USERNAME");
				String password = System.getenv("RDS_PASSWORD");
				String hostname = System.getenv("RDS_HOSTNAME");
				String port = System.getenv("RDS_PORT");
				jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName
						+ "&password=" + password;

			} catch (Exception excp) {
			}

		}
	}
	
	/**
	 * Execute adhoc query
	 * @param query
	 */
	public void executeQuery(String query) {
		try {
			Connection conn = DriverManager.getConnection(jdbcUrl);
			conn.createStatement().execute(query);
			conn.close();
		}catch(Exception excp) {
			throw new RuntimeException("Query failed to execute");
		}
	}
}
