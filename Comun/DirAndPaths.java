package Comun;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import Exception.ExHandler;

public class DirAndPaths {
	ExHandler EX = new ExHandler();
	
	private boolean debug = false;
	private String path_Main = "c://POO";	
	private String path_Persona = "c://POO//Persona.txt";
	private String path_SQL	= "c://POO//SqlData.txt";
	private String sqlDB ;



	public boolean debug() { return this.debug; }
	
	public String getPathProgram() {	
		return this.path_Main;
	}
	public String getPathPersona() {
		return this.path_Persona;
	}	
	public String getPathSql() {
		return this.path_SQL;
	}
	
	public String getDB() {
		if (this.debug)System.out.println("Ejecutando getDB");
		File file = new File(this.path_SQL); 
	    Scanner sc;
		try {
			sc = new Scanner(file);
		
		int i=1;  
			    while (sc.hasNextLine()) {
			    	if (i == 4) {
			    		this.sqlDB = sc.nextLine();
			    		if (this.debug)System.out.println(this.sqlDB);
			    	}
			    	sc.nextLine();
			    	i++;
			    }
			    
	    sc.close();
		} catch (FileNotFoundException e) {
			EX.ShowException(e);
		} 
	    return this.sqlDB;
	    /*
	     * TODO: add file verification and error handling
	     */
	}
}
