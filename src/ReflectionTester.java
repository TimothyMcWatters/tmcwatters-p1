import java.sql.SQLException;

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
		
		//creates a database and table
		CreateDB db1 = new CreateDB();
		db1.createDB(dbName);
		db1.createTable(tableName);
		
		
		Vehicle v1 = new Vehicle("Chevy", "suv", 2250.00, 800.00, 4, false);
		String tableValues = "('Chevy', 'suv', 2250.00, 800.00, 4, false)";
		db1.insertIntoTable(tableName, tableValues); 
		db1.readTable(tableName);
		
		GenerateVehicles gv1 = new GenerateVehicles();
		gv1.populateVehicleList();

		
		for (int i = 0; i < 10; i ++) {
			System.out.println(GenerateVehicles.vehicleList[i].toString());
		}
		
		//Reflection reflection1 = new Reflection();
		//reflection1.analyzeInstanceFields("Vehicle");		

	}

}
