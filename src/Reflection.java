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
	//public ArrayList<String> instanceFieldTypes = new ArrayList<String>();
	
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
	
	public void getInstanceFieldValues(String fieldName, Object object) {
		try {
			System.out.println("\nInstance Field Values Are: ");
			
			Class c2 = object.getClass();
			Field privateField = c2.getDeclaredField(fieldName);
			
			String name = privateField.getName();
			privateField.setAccessible(true);
//String fieldValue = (String) privateField.get(object);
			//System.out.println(fieldValue);
		}
	    catch (Throwable e) {
	    	System.err.println(e);
	     } 
	}
	
}
/***************************************************
	public void getInstanceFieldValues(String className, Object object) {
		try {
			System.out.println("\nInstance Field Values Are: ");
			Class c2 = Class.forName(className);
			Field privateField = c2.getDeclaredField("make");
			String name = privateField.getName();
			privateField.setAccessible(true);
			String fieldValue = (String) privateField.get(object);
			System.out.println("fieldValue before set = " + fieldValue);
		}
	    catch (Throwable e) {
	    	System.err.println(e);
	     } 
	}
*********************************************************/
