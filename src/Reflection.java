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
	
	public ArrayList<String> analyzeInstanceFields(String className) {
		String word;
		String type;
		String instanceField;
		String instanceFieldType;
		ArrayList<String> instanceFields = new ArrayList<String>();
		try {
			Class c1 = Class.forName(className);
			Field f[] = c1.getDeclaredFields();
	        for (int i = 0; i < f.length; i++) {
	        	type = f[i].getType().getName();
	        	instanceFieldType = type.substring(type.lastIndexOf('.') + 1);
	        	instanceFields.add(instanceFieldType);
	        	
	        	word = f[i].toString();
	        	instanceField = word.substring(word.lastIndexOf('.') + 1);
	        	instanceFields.add(instanceField);
	        }
		}
	    catch (Throwable e) {
	    	System.err.println(e);
	     } 
		return instanceFields;
	}
	
	public ArrayList<String> getInstanceFieldValues(Object object) {
		String type;
		String field;
		String fieldValue = "";
		String className = object.getClass().getName();
		ArrayList<String> instanceFields = analyzeInstanceFields(className);
		ArrayList<String> instanceFieldValues = new ArrayList<String>();
		try {
			for (int index = 0; index < instanceFields.size()/2; index++) {
				field = instanceFields.get(index * 2 + 1);
				type = instanceFields.get(index * 2);

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
				else {
					//what do i wanna code here?
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