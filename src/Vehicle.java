/**
 * @author Timothy McWatters
 * @version 1.0
 * 
 * COP 4027 Advanced Computer Programming
 * Project 1
 * File Name: Vehicle.java
 * 
 * This Program: Uses refactoring to improve structure, works with Derby database
 * and the Java Reflection API, and automates database creation from class definitions
 * and instances.
 */

public class Vehicle {
	private String make; 
	private String model;
	private double weight;
	private double engineSize;
	private int numberOfDoors;
	private boolean isImport;
	
	/**
	 * Parameterized constructor for the Vehicle Class
	 * @param make
	 * @param model
	 * @param weight
	 * @param engineSize
	 * @param numberOfDoors
	 * @param isImport
	 */
	public Vehicle(String make, String model, double weight, double engineSize, int numberOfDoors, boolean isImport) {
		this.make = make;
		this.model = model;
		this.weight = weight;
		this.engineSize = engineSize;
		this.numberOfDoors = numberOfDoors;
		this.isImport = isImport;
	}
	
	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}
	
	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}
	
	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}
	
	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
	
	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		if (weight > 0.0) {
			this.weight = weight;
		}
	}
	
	/**
	 * @return the engineSize
	 */
	public double getEngineSize() {
		return engineSize;
	}
	
	/**
	 * @param engineSize the engineSize to set
	 */
	public void setEngineSize(double engineSize) {
		if (engineSize > 0.0) {
			this.engineSize = engineSize;			
		}
	}
	
	/**
	 * @return the numberOfDoors
	 */
	public int getNumberOfDoors() {
		return numberOfDoors;
	}
	
	/**
	 * @param numberOfDoors the numberOfDoors to set
	 */
	public void setNumberOfDoors(int numberOfDoors) {
		if (numberOfDoors > 0) {
			this.numberOfDoors = numberOfDoors;
		}
	}
	
	/**
	 * @return the isImport
	 */
	public boolean isImport() {
		return isImport;
	}

	/**
	 * @param isImport the isImport to set
	 */
	public void setImport(boolean isImport) {
		this.isImport = isImport;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Vehicle [make = " + make + ", model = " + model + ", weight = " + weight + ", engineSize = " + engineSize
				+ ", numberOfDoors = " + numberOfDoors + ", isImport = " + isImport + "]";
	}
	
}
