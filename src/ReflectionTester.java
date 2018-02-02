import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Timothy McWatters
 * @version 1.0
 * 
 * COP 4027 Advanced Computer Programming
 * Project 1
 * File Name: ReflectionTester.java
 * 
 * This Program: Uses reflection to improve structure, works with Derby database
 * and the Java Reflection API, and automates database creation from class definitions
 * and instances.
 */

public class ReflectionTester {

	public static void main(String[] args) throws SQLException {
		String dbName = "Vehicles";
		String tableName = "vehicles";
		
		//use reflection to analyze Vehicle class and extract the instance fields
		Reflection reflection1 = new Reflection();
		ArrayList<String> instanceFields = reflection1.analyzeInstanceFields("Vehicle");
		
		//create a database
		CreateDB db1 = new CreateDB();
		
		//create a SQL command for database table creation and create a database table
		db1.createDB(dbName);
		db1.createTable(tableName, instanceFields);
		
		//create 10 Vehicle instances
		GenerateVehicles gv1 = new GenerateVehicles();
		gv1.populateVehicleList();

		//use reflection to analyze Vehicle class and extract the instance fields
		for (int i = 0; i < 10; i ++) {
			ArrayList<String> tableValues = reflection1.getInstanceFieldValues(GenerateVehicles.vehicleList[i]);
			db1.insertIntoTable(tableName, tableValues);
		}

		//create a SQL command to read and display each of the records from a database table
		db1.readTable(tableName, instanceFields);
		
		//read and display log file of SQL Operations performed
		db1.printDBLog();
		
		db1.closeOutputStream();

	}

}
