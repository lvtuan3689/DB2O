package com.db2o.datatype;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataTypeConverter {
	@SuppressWarnings("serial")
	public static Map<String, String> DATA_TYPE_OF = new LinkedHashMap<String, String>() {
		{
			put("BIT", "Boolean");
			put("TINYINT", "Integer");
			put("BOOL", "Boolean");
			put("BOOLEAN", "Boolean");
			put("SMALLINT", "Integer");
			put("MEDIUMINT", "Integer");
			put("INTEGER", "Integer");
			put("INT", "Integer");
			put("BIGINT", "BigInteger");
			put("FLOAT", "Float");
			put("DOUBLE", "Double");
			put("DECIMAL", "BigDecimal");
			put("DATE", "Date");
			put("DATETIME", "Timestamp");
			put("TIMESTAMP", "Timestamp");
			put("TIME", "Time");
			put("YEAR", "Date");
			put("CHAR", "String");
			put("VARCHAR", "String");
			put("BINARY", "byte[]");
			put("VARBINARY", "byte[]");
			put("TINYBLOB", "byte[]");
			put("BLOB", "byte[]");
			put("TEXT", "String");
			put("TINYTEXT", "String");
			put("MEDIUMBLOB", "byte[]");
			put("MEDIUMTEXT", "String");
			put("LONGBLOB", "byte[]");
			put("LONGTEXT", "String");
			put("ENUM", "String");
			put("SET", "String");
		}
	};
}
