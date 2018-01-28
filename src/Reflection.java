 import java.lang.reflect.*;

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
	
	public void analyzeInstanceFields(String className) {
		try {
			Class c = Class.forName(className);
			Field f[] = c.getDeclaredFields();
	        System.out.println("fields:");
	        for (int i = 0; i < f.length; i++)
	          System.out.println("  " + f[i].toString());  
		}
	    catch (Throwable e) {
	    	System.err.println(e);
	     }  
	}
	
	public void getInstanceFieldValues(String className, Object object) {
		//Class<?> classType = Class.forName(className);
		//Field privateField = classType.class.getDeclaredField("type");
		
		// get the value of this private field
		//String fieldValue = (String) privateField.get(object);
		//System.out.println("fieldValue before set = " + fieldValue);
	}
	
}
