import java.io.*;
import java.util.Scanner;

/**
 * @author Timothy McWatters
 * @version 1.0
 * 
 * COP 4027 Advanced Computer Programming
 * Project 1
 * File Name: Log.java
 * 
 * This Program: Uses reflection to improve structure, works with Derby database
 * and the Java Reflection API, and automates database creation from class definitions
 * and instances.
 */

public class Log {
	private String logName;
	private PrintWriter outputStream = null;
	private Scanner inputStream = null;
	
	/**
	 * Constructor for Log class
	 * @param logName = the name of the log
	 */
	public Log(String logName) {
		this.logName = logName;
	}

	/**
	 * @return the logName
	 */
	public String getLogName() {
		return logName;
	}

	/**
	 * @param logName = the name of the log to set
	 */
	public void setLogName(String logName) {
		this.logName = logName;
	}
	
	/**
	 * Creates a log
	 */
	public void createLog() {
		try {
			//attempt to open the file
			outputStream = new PrintWriter(new FileOutputStream(logName));
		}
		catch (FileNotFoundException e) {
			System.out.println("WARNING: Log file not found.");
			System.exit(0);
		}
	}
	
	/**
	 * Creates a log entry
	 * @param logEntry = The log entry to write to the file
	 */
	public void createLogEntry(String logEntry) {
		outputStream.println(logEntry);
	}
	
	/**
	 * Prints log 
	 * @param logName = The log files name to read/print
	 */
	public void printLog(String logName) {
		try {
			inputStream = new Scanner(new FileInputStream(logName));
		}
		catch (FileNotFoundException e) {
			System.out.println("WARNING: Log file not found.");
			System.exit(0);
		}
		while (inputStream.hasNextLine()) {
			System.out.println(inputStream.nextLine());
		}
		inputStream.close();
	}
	
	/**
	 * Closes the outputStream
	 */
	public void closeOutputStream() {
		outputStream.close();
	}
	
}
