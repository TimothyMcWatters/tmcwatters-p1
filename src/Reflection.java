 import java.lang.reflect.*;
import java.util.ArrayList;

/**
 * @author Timothy McWatters
 * @version 1.0
 * 
 * COP 4027 Advanced Computer Programming
 * Project 1
 * File Name: Reflection.java
 * 
 * This Program: Uses reflection to improve structure, works with Derby database
 * and the Java Reflection API, and automates database creation from class definitions
 * and instances.
 */

public class Reflection {
	
	/**
	 * Creates a list of instance fields and their Types using reflect from a Class name
	 * @param className = The name of the class to use reflection on 
	 * @param log = The log to log all SQL operations or failure to process an unsupported field Type
	 * @return instanceFields = An ArrayList<String> of the Class' instance fields and their Types
	 */
	public ArrayList<String> analyzeInstanceFields(String className, Log log) {
		String word;
		String type;
		String instanceField = "";
		String instanceFieldType;
		ArrayList<String> instanceFields = new ArrayList<String>();
		try {
			Class c1 = Class.forName(className);
			Field f[] = c1.getDeclaredFields();
	        for (int i = 0; i < f.length; i++) {
	        	type = f[i].getType().getName();
	        	instanceFieldType = type.substring(type.lastIndexOf('.') + 1);
        		word = f[i].toString();
        		instanceField = word.substring(word.lastIndexOf('.') + 1);
	        	if (instanceFieldType.equalsIgnoreCase("String") || instanceFieldType.equalsIgnoreCase("int") 
	        				|| instanceFieldType.equalsIgnoreCase("double") || instanceFieldType.equalsIgnoreCase("boolean")) {
	        		instanceFields.add(instanceFieldType);
	        		instanceFields.add(instanceField);
	        	} else {
	        		log.createLogEntry("WARNING: The Instance Field: " + instanceField + " could not be processed because it is of Type: " + instanceFieldType);
	        	}
	        }
		}
	    catch (Throwable e) {
	    	System.err.println(e);
	     } 
		return instanceFields;
	}
	
	/**
	 * Creates a list of instance field values and their Types using reflection on an object
	 * @param object = The object to use reflection on 
	 * @param log = The log to log all SQL operations or failure to process an unsupported field Type
	 * @return instanceFieldValues = An ArrayList<String> of the Class' instance field values and their Types
	 */
	public ArrayList<String> getInstanceFieldValues(Object object, Log log) {
		String type;
		String field;
		String fieldValue = "";
		String className = object.getClass().getName();
		ArrayList<String> instanceFields = analyzeInstanceFields(className, log);
		ArrayList<String> instanceFieldValues = new ArrayList<String>();
		try {
			for (int index = 0; index < instanceFields.size()/2; index++) {
				type = instanceFields.get(index * 2);
				field = instanceFields.get(index * 2 + 1);

				Field privateField = object.getClass().getDeclaredField(field);
				privateField.setAccessible(true);
			
				if (type.equalsIgnoreCase("String")) {
					fieldValue = (String) privateField.get(object);
				}
				else if (type.equalsIgnoreCase("int")) {
					fieldValue = String.valueOf(privateField.get(object));
				}
				else if (type.equalsIgnoreCase("double")) {
					fieldValue = String.valueOf(privateField.get(object));
				}
				else if (type.equalsIgnoreCase("boolean")) {
					fieldValue = String.valueOf(privateField.get(object));
				}
				instanceFieldValues.add(type);
				instanceFieldValues.add(fieldValue);
			}
		}
		catch (Throwable e) {
			System.err.println(e);
		} 
		return instanceFieldValues;
	}
}