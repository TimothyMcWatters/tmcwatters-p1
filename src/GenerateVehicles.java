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
	
	public void populateVehicleList() {
		for (int i = 0; i < VEHICLE_LIST_SIZE; i++) {
			vehicleList[i] = createNewVehicle();
		}
	}
	
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
	
	private double generateWeight(String model) {
		if (model.equals("compact")) {
			return (double) Math.round((1500 + (2000 - 1500) * randomGenerator.nextDouble()) * 100) / 100;
		} else if (model.equals("intermediate")) {
			return (double) Math.round((2000 + (2500 - 2000) * randomGenerator.nextDouble()) * 100) / 100;
		} else {
			return (double) Math.round((2500 + (4000 - 2500) * randomGenerator.nextDouble()) * 100) / 100;
		}
	}
	
	private double generateEngineSize(String model) {
		if (model.equals("compact")) {
			return (double) Math.round((90 + (150 - 90) * randomGenerator.nextDouble()) * 100) / 100;
		} else if (model.equals("intermediate")) {
			return (double) Math.round((150 + (250 - 150) * randomGenerator.nextDouble()) * 100) / 100;
		} else {
			return (double) Math.round((250 + (400 - 250) * randomGenerator.nextDouble()) * 100) / 100;
		}
	}
	
	private int generateNumberOfDoors(String model) {
		if (model.equals("compact")) {
			return 2;
		} else {
			return 4;
		}
	}
	
	private boolean generateIsImport(String make) {
		if (make.equals("Chevy") || make.equals("Ford"))  {
			return false;
		} else {
			return true;
		}
	}
}
