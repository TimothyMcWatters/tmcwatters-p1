import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
		loadDBDriver();
		
		try {
			System.out.println("Connecting to and creating the database...");
			connection = DriverManager.getConnection(protocol + dbName + ";create=true");
			System.out.println("Database has been created.");
		}
		catch (SQLException err) {
			System.err.println("SQL error.");
			err.printStackTrace(System.err);
			System.exit(0);
		}
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
			
			statement.execute("CREATE TABLE " + tableName + tableInstanceFields);
			System.out.println("Created '" + tableName + "' table.");
		}
		catch (SQLException err) {
			System.err.println("SQL error.");
			err.printStackTrace(System.err);
			System.exit(0);
		}
	}
	
	/**
	 * Builds the command to create a table using a Derby Database 
	 * @param tableName = The name of the database table to be created
	 * @param tableValues = An ArrayList<String> representation of the record to be inserted into the table
	 */
	public String buildTableFieldString(ArrayList<String> instanceFields) {
		String value;
		String valueString = "(";
		String type;
		for (int index = 0; index < instanceFields.size()/2; index++) {
			value = instanceFields.get(index * 2 + 1);
			valueString += "" + value;
			
			type = instanceFields.get(index * 2);
			if (type.equalsIgnoreCase("String")) {
				valueString += " varchar(50)";
			}
			else if (type.equalsIgnoreCase("int")) {
				valueString += " int";
			}
			else if (type.equalsIgnoreCase("double")) {
				valueString += " double";
			}
			else if (type.equalsIgnoreCase("boolean")) {
				valueString += " boolean";
			}
			else {
				valueString += "";
			}
			if ((index + 1) < (instanceFields.size()/2)) {
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
	public void insertIntoTable(String tableName, String tableValues) {
		try {
			System.out.println("Inserting values into table " + tableName);
			statement.execute("INSERT INTO " + tableName + " VALUES " + tableValues);
			System.out.println("Values inserted");
		}
		catch (SQLException err) {
			System.err.println("SQL error.");
			err.printStackTrace(System.err);
			System.exit(0);
		}
	}
	
	/**
	 * Displays (prints) a record from a Derby Database table's result set
	 * @param resultSet = The result set from the Derby Database table to display
	 */
	private void displayRecord(ResultSet resultSet) throws SQLException {
		String make = resultSet.getString("make");
		String model = resultSet.getString("model");
		double weight = resultSet.getDouble("weight");
		double engineSize = resultSet.getDouble("engineSize");
		int numberOfDoors = resultSet.getInt("numberOfDoors");
		boolean isImport = resultSet.getBoolean("isImport");
		
		System.out.println(make + ", " + model + ", " + weight + ", " + engineSize + ", " 
		+ numberOfDoors + ", " + isImport);
	}
	
	/**
	 * Reads a record from a Derby Database table and then calls upon displayRecord() method
	 * @param tableName = The name of the Derby DB table to read
	 */
	public void readTable(String tableName) throws SQLException {
		ResultSet resultSet = null;
		resultSet = statement.executeQuery("SELECT make, model, weight, engineSize, numberOfDoors, isImport FROM " + tableName);
		
		while(resultSet.next()) {
			displayRecord(resultSet);
		}
	}
	
}
