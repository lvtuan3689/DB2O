package com.db2o.connections;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.db2o.connections.error.ErrorProcessor;

public abstract class AbsConnection {
	protected String host = "127.0.0.1";
	protected String username = "";
	protected String password = "";
	protected String port = "";
	protected String dbname = "";
	protected String connectionString = "";
	protected DatabaseMetaData metas = null;

	protected Connection connection = null;

	public AbsConnection() {
	}

	public AbsConnection(String host, String username, String password, String port, String dbname) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.port = port;
		this.dbname = dbname;
		loadDriver();
	}

	public void changeTo(String host, String username, String password, String port, String dbname) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.port = port;
		this.dbname = dbname;
		getConnection();
	}

	public abstract void loadDriver();

	public abstract void getConnection();

	/**
	 * Open connection
	 */
	public boolean isOpen() {
		if (isClosed()) {
			getConnection();
		}
		return !isClosed();
	}

	/**
	 * @return the closed state
	 */
	public boolean isClosed() {
		try {
			return connection != null && connection.isClosed();
		} catch (SQLException e) {
			ErrorProcessor.process(this, e);
		}
		return true;
	}

	/**
	 * Close connection
	 */
	public boolean closeConnection() {
		try {
			if (connection != null && !isClosed())
				connection.close();
		} catch (SQLException e) {
			ErrorProcessor.process(this, e);
		}
		return isClosed();
	}

	/**
	 * @return the connectionString
	 */
	public String getConnectionString() {
		return connectionString;
	}

	protected DatabaseMetaData getMetas() {
		if (isOpen()) {
			try {
				metas = connection.getMetaData();
				return metas;
			} catch (SQLException e) {
				ErrorProcessor.process(this, e);
			}
		}
		closeConnection();
		return null;
	}

	public LinkedList<String> getAllSchemas() {
		LinkedList<String> allSchemas = new LinkedList<String>();
		if (metas == null) {
			getMetas();
		}
		if (metas != null) {
			try {
				ResultSet schemas = metas.getCatalogs();
				while (schemas.next()) {
					String schema = schemas.getString(1);
					allSchemas.add(schema);
				}
			} catch (SQLException e) {
				ErrorProcessor.process(this, e);
			}
		}
		return allSchemas;
	}

	public LinkedList<String> getAllTables(String catalog) {
		LinkedList<String> allTables = new LinkedList<String>();
		if (metas == null) {
			getMetas();
		}
		if (metas != null) {
			try {
				ResultSet tables = metas.getTables(catalog, null, null, new String[] { "TABLE" });
				while (tables.next()) {
					String tableName = tables.getString("TABLE_NAME");
					allTables.add(tableName);
				}
			} catch (SQLException e) {
				ErrorProcessor.process(this, e);
			}
		}
		return allTables;
	}

	public LinkedList<String> getAllColumns(String catalog, String table) {
		LinkedList<String> allTables = new LinkedList<String>();
		if (metas == null) {
			getMetas();
		}
		if (metas != null) {
			try {
				ResultSet tables = metas.getColumns(catalog, null, table, null);
				while (tables.next()) {
					String tableName = tables.getString("COLUMN_NAME");
					allTables.add(tableName);
				}
			} catch (SQLException e) {
				ErrorProcessor.process(this, e);
			}
		}
		return allTables;
	}
}