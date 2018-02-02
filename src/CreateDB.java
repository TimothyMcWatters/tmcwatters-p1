import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Timothy McWatters
 * @version 1.0
 * 
 * COP 4027 Advanced Computer Programming
 * Project 1
 * File Name: CreateDB.java
 * 
 * This Program: Uses reflection to improve structure, works with Derby database
 * and the Java Reflection API, and automates database creation from class definitions
 * and instances.
 */

public class CreateDB {
	private static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String protocol = "jdbc:derby:";
	private Connection connection = null;
	private Statement statement = null;
	private Log log = null;
	private String logName;
	
	/**
	 * Loads the Database's embedded driver for use of Apache Derby
	 */
	private void loadDBDriver() {
		try {
			Class.forName(driver);
			System.out.println("Loaded the embedded driver.");
		}
		catch (Exception err ) {
			System.err.println("Unable to load the embedded driver.");
			err.printStackTrace(System.err);
			System.exit(0);
		}
	}
	
	/**
	 * Creates a Derby Database
	 * @param dbName = The name of the database to be created
	 */
	public void createDB(String dbName) {
		//create log to log each SQL operation performed on this database
		logName = dbName + "Log.txt";
		log = new Log(logName);
		log.createLog();
		System.out.println(logName + " created.");
		
		loadDBDriver();
		try {
			System.out.println("Connecting to and creating the database...");
			connection = DriverManager.getConnection(protocol + dbName + ";create=true");
			log.createLogEntry(protocol + dbName + ";create=true");
			System.out.println("Database has been created.");
		}
		catch (SQLException err) {
			System.err.println("SQL error.");
			err.printStackTrace(System.err);
			System.exit(0);
		}
	}
	
	/**
	 * Builds a string for the fields used to create a table using a Derby Database 
	 * @param instanceFields = An ArrayList<String> representation of the fields to build a string of values from
	 * @return valueString = The String of fields used in a database table creation
	 */
	public String buildTableFieldString(ArrayList<String> instanceFields) {
		String field;
		String fieldString = "(";
		String type;
		for (int index = 0; index < instanceFields.size()/2; index++) {
			field = instanceFields.get(index * 2 + 1);
			fieldString += field;
			
			type = instanceFields.get(index * 2);
			if (type.equalsIgnoreCase("String")) {
				fieldString += " varchar(50)";
			}
			else if (type.equalsIgnoreCase("int")) {
				fieldString += " int";
			}
			else if (type.equalsIgnoreCase("double")) {
				fieldString += " double";
			}
			else if (type.equalsIgnoreCase("boolean")) {
				fieldString += " boolean";
			}
			else {
				fieldString += "";
			}
			if ((index + 1) < (instanceFields.size()/2)) {
				fieldString += ", ";
			}
		}
		fieldString += ")";
		return fieldString;
	}
		
	/**
	 * Creates a table in the Derby Database
	 * @param tableName = The name of the database table to be created
	 */
	public void createTable(String tableName, ArrayList<String> instanceFields) {
		String tableInstanceFields = buildTableFieldString(instanceFields);
		try {
			statement = connection.createStatement();
//correct this line of code to only drop the table if needed
			System.out.println("Correct CreateDB line 72, maybe \"DROP TABLE IF EXISTS \"?");
			statement.execute("DROP TABLE " + tableName);
			log.createLogEntry("DROP TABLE " + tableName);
			
			statement.execute("CREATE TABLE " + tableName + tableInstanceFields);
			log.createLogEntry("CREATE TABLE " + tableName + tableInstanceFields);
			System.out.println("Created '" + tableName + "' table.");
		}
		catch (SQLException err) {
			System.err.println("SQL error.");
			err.printStackTrace(System.err);
			System.exit(0);
		}
	}

	/**
	 * Builds a string for the values used to insert a record into a table using a Derby Database 
	 * @param instanceFields = An ArrayList<String> representation of the fields to build a string of values from
	 * @return valueString = The String of values used in a database record insertion
	 */
	public String buildTableRecordString(ArrayList<String> instanceValues) {
		String value;
		String valueString = "(";
		String type;
		for (int index = 0; index < instanceValues.size()/2; index++) {
			value = instanceValues.get(index * 2 + 1);			
			type = instanceValues.get(index * 2);
			if (type.equalsIgnoreCase("String")) {
				valueString += "'" + value + "'";
			}
			else {
				valueString += value;
			}
			if ((index + 1) < (instanceValues.size()/2)) {
				valueString += ", ";
			}
		}
		valueString += ")";
		return valueString;
	}	
	
	/**
	 * Inserts a record into a Derby Database table
	 * @param tableName = The name of the database table to be enter a record
	 * @param tableValues = The String representation of the record to be inserted into the table
	 */
	public void insertIntoTable(String tableName, ArrayList<String> instanceValues) {
		String tableValues = buildTableRecordString(instanceValues);
		try {
			statement.execute("INSERT INTO " + tableName + " VALUES " + tableValues);
			System.out.println("Record successfully inserted into table: " + tableName);
			log.createLogEntry("INSERT INTO " + tableName + " VALUES " + tableValues);
		}
		catch (SQLException err) {
			System.err.println("SQL error.");
			err.printStackTrace(System.err);
			System.exit(0);
		}
	}

	/**
	 * Builds a string used to select a record from a Derby Database 
	 * @param instanceFields = An ArrayList<String> representation of the fields to select a record from a table
	 * @return valueString = The String of fields used in record selection
	 */
	public String buildTableSelectString(ArrayList<String> instanceFields) {
		String field;
		String selectString = "SELECT ";
		for (int index = 0; index < instanceFields.size()/2; index++) {
			field = instanceFields.get(index * 2 + 1);
			selectString += field;
			if ((index + 1) < (instanceFields.size()/2)) {
				selectString += ", ";
			}
		}
		selectString += " FROM ";
		return selectString;
	}			
	
	/**
	 * Reads a record from a Derby Database table and then calls upon displayRecord() method
	 * @param tableName = The name of the Derby DB table to read
	 */
	public void readTable(String tableName, ArrayList<String> instanceFields) throws SQLException {
		String selectStatementString = buildTableSelectString(instanceFields);
		ResultSet resultSet = null;
		resultSet = statement.executeQuery(selectStatementString + tableName);
		log.createLogEntry(selectStatementString + tableName);
		System.out.println("\nHere are all records from the table \"" + tableName + "\":");
		while(resultSet.next()) {
			displayRecord(resultSet);
		}
	}
	
	/**
	 * Displays (prints) a record from a Derby Database table's result set
	 * @param resultSet = The result set from the Derby Database table to display
	 */
	private void displayRecord(ResultSet resultSet) throws SQLException {
		ResultSetMetaData rsmd = resultSet.getMetaData();
		while (resultSet.next()) {
			for (int index = 1; index <= rsmd.getColumnCount(); index++) {
				if (index > 1) {
					System.out.print(", ");
				}
				String columnValue = resultSet.getString(index);
				System.out.print(columnValue);
			}
			System.out.println();
		}
	}
	
	/**
	 * Closes the database log's outputStream
	 */
	public void closeOutputStream() {
		log.closeOutputStream();
	}
	
	/**
	 * Prints the database log
	 * @param logName = the database log's name to print
	 */
	public void printDBLog() {
		System.out.println("\n" + this.logName + " contains the following SQL operations performed: ");
		this.log.printLog(this.logName);
	}
	
}