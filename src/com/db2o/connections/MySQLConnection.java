/**
 *
 *
 */
package com.db2o.connections;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.db2o.connections.error.ErrorProcessor;

/**
 * @author tuan.le
 *
 */
public class MySQLConnection extends AbsConnection {

	public MySQLConnection(String host, String username, String password, String port, String dbname) {
		super(host, username, password, port, dbname);
	}

	@Override
	public void loadDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			getConnection();
		} catch (Exception e) {
			ErrorProcessor.process(this, e);
		}
	}

	@Override
	public void getConnection() {
		closeConnection();
		try {
			connectionString = String.format("jdbc:mysql://%s/%s?user=%s&password=%s", host, dbname, username,
					password);
			connection = DriverManager.getConnection(connectionString);
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			ErrorProcessor.process(this, e);
		}
	}
}
