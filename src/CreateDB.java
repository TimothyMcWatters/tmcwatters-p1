import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	public void createTable(String tableName) {
		try {
			statement = connection.createStatement();
//correct this line of code to only drop the table if needed
			System.out.println("Correct CreateDB line 69");
			statement.execute("DROP TABLE " + tableName);
			
			statement.execute("CREATE TABLE " + tableName +
					  "(make varchar(20), model varchar(20), weight double, engineSize double, numberOfDoors int, isImport boolean)");
			System.out.println("Created '" + tableName + "' table.");
		}
		catch (SQLException err) {
			System.err.println("SQL error.");
			err.printStackTrace(System.err);
			System.exit(0);
		}
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
