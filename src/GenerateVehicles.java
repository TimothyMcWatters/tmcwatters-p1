import java.util.Random;

/**
 * @author Timothy McWatters
 * @version 1.0
 * 
 * COP 4027 Advanced Computer Programming
 * Project 1
 * File Name: GenerateVehicles.java
 * 
 * This Program: Uses reflection to improve structure, works with Derby database
 * and the Java Reflection API, and automates database creation from class definitions
 * and instances.
 */

public class GenerateVehicles {
	public static final int VEHICLE_LIST_SIZE = 10; 
	public static Vehicle[] vehicleList = new Vehicle[VEHICLE_LIST_SIZE];
	Random randomGenerator = new Random();
	
	/**
	 * Populates the array "vehicleList" with "VEHICLE_LIST_SIZE" elements of Vehicle 
	 * objects by calling the createNewVehicle() method
	 */
	public void populateVehicleList() {
		for (int i = 0; i < VEHICLE_LIST_SIZE; i++) {
			vehicleList[i] = createNewVehicle();
		}
	}

	/**
	 * Creates a new instance of Vehicle and populates its instance fields
	 * calling random generator methods for each.
	 */
	private Vehicle createNewVehicle() {
		String make = generateMake();
		String model = generateModel();
		double weight = generateWeight(model);
		double engineSize = generateEngineSize(model);
		int numberOfDoors = generateNumberOfDoors(model);
		boolean isImport = generateIsImport(make);
		
		Vehicle vehicle = new Vehicle(make, model, weight, engineSize, numberOfDoors, isImport);
		return vehicle;		
	}
	
	/**
	 * Generates the make of a Vehicle object randomly from a predefined enumeration, and returns it.
	 * @return make = The make of a Vehicle object
	 */
	private String generateMake() {
		String make = "";
		int caseNumber = randomGenerator.nextInt(5) + 1;
		
		switch (caseNumber) {
		case 1: 
			make = "Chevy";
			break;
		case 2: 
			make = "Ford";
			break;
		case 3: 
			make = "Toyota";
			break;
		case 4: 
			make = "Nissan";
			break;
		case 5: 
			make = "Hyundai";
			break;
		default: 
			make = "";
			break;
		}
		return make;
	}
	
	/**
	 * Generates the model of a Vehicle object randomly from a predefined enumeration, and returns it.
	 * @return model = The model of a Vehicle object
	 */
	private String generateModel() {
		String model = "";
		int caseNumber = randomGenerator.nextInt(6) + 1;
		
		switch (caseNumber) {
		case 1: 
			model = "compact";
			break;
		case 2: 
			model = "intermediate";
			break;
		case 3: 
			model = "fullSized";
			break;
		case 4: 
			model = "van";
			break;
		case 5: 
			model = "suv";
			break;
		case 6: 
			model = "pickup";
			break;
		default: 
			model = "";
			break;
		}
		return model;
	}
	
	/**
	 * Generates the weight of a Vehicle object randomly from a predefined range dependent
	 * upon that Vehicle objects model, and returns it.
	 * @param model = The model of a a Vehicle object used to determine the objects weight
	 * @return weight = The weight of a Vehicle object
	 */
	private double generateWeight(String model) {
		if (model.equals("compact")) {
			return (double) Math.round((1500 + (2000 - 1500) * randomGenerator.nextDouble()) * 100) / 100;
		} else if (model.equals("intermediate")) {
			return (double) Math.round((2000 + (2500 - 2000) * randomGenerator.nextDouble()) * 100) / 100;
		} else {
			return (double) Math.round((2500 + (4000 - 2500) * randomGenerator.nextDouble()) * 100) / 100;
		}
	}
	
	/**
	 * Generates the engine size of a Vehicle object randomly from a predefined range dependent
	 * upon that Vehicle objects model, and returns it.
	 * @param model = The model of a a Vehicle object used to determine the objects engine size
	 * @return engineSize = The size of a Vehicle object's engine in cubic inches
	 */
	private double generateEngineSize(String model) {
		if (model.equals("compact")) {
			return (double) Math.round((90 + (150 - 90) * randomGenerator.nextDouble()) * 100) / 100;
		} else if (model.equals("intermediate")) {
			return (double) Math.round((150 + (250 - 150) * randomGenerator.nextDouble()) * 100) / 100;
		} else {
			return (double) Math.round((250 + (400 - 250) * randomGenerator.nextDouble()) * 100) / 100;
		}
	}
	
	/**
	 * Generates the number of doors a Vehicle object has based
	 * upon that Vehicle objects model, and returns it.
	 * @param model = The model of a a Vehicle object used to determine the objects number of doors
	 * @return numberOfDoors = The number of doors the Vehicle object has
	 */
	private int generateNumberOfDoors(String model) {
		if (model.equals("compact")) {
			return 2;
		} else {
			return 4;
		}
	}
	
	/**
	 * Generates a boolean representation on whether a Vehicle object is imported to the United States 
	 * based upon that Vehicle objects make, and returns it.
	 * @param make = The make of a a Vehicle object used to determine whether it is imported to the United States
	 * @return boolean = The boolean representation of if a Vehicle object is imported to the United States
	 */
	private boolean generateIsImport(String make) {
		if (make.equals("Chevy") || make.equals("Ford"))  {
			return false;
		} else {
			return true;
		}
	}
}
