package com.db2o.connections.error;

import java.sql.SQLException;

public class ErrorProcessor {
	public static void process(Object owner, Exception e) {
		if (e instanceof SQLException) {
			SQLException ex = (SQLException) e;
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} else if (e instanceof InstantiationException) {
			e.printStackTrace();
		} else if (e instanceof IllegalAccessException) {
			e.printStackTrace();
		} else if (e instanceof InstantiationException) {
			e.printStackTrace();
		} else if (e instanceof ClassNotFoundException) {
			e.printStackTrace();
		}
	}
}