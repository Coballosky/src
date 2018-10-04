package ConnectionHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import Comun.DirAndPaths;

import Exception.ExHandler;

/**
 * @author Nicolas
 *	
 *
 **/


public class SqlConection {
	private boolean debug = false;
	
	ExHandler met = new ExHandler(); 
	
	private String url,username,password;	
	private Connection con; 
	public Statement st;
	private String dbPersonas = "Persona";

	
	//
	DirAndPaths dirs = new DirAndPaths();
	String dbName = dirs.getDB();
	
	// -------------------------------------------------------------- //
	
	public void LoadConData() throws IOException {
		if (dirs.debug()) {System.out.println("Cargando sql data");}
		
		String fileName = dirs.getPathSql();
		
		FileReader fileReader =   new FileReader(fileName);
	    BufferedReader bufferedReader =  new BufferedReader(fileReader);
		this.url = bufferedReader.readLine();
		this.username = bufferedReader.readLine();
		this.password = bufferedReader.readLine();
		

		bufferedReader.close();fileReader.close();
		if (this.debug) {
			System.out.println(this.url);
			System.out.println(this.username);
			System.out.println(this.password);
		}
		
		/*
		 * TODO: take out that filthy throws declaration, and use a try/catch instead!
		 */
	}
	
	public boolean CreateConnection (){
		try {
			LoadConData();
			} catch (IOException ex) {		
				met.ShowException(ex);
				return false;
			}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.con = DriverManager.getConnection (this.url,this.username,this.password);
			st = this.con.createStatement();
			}catch(Exception ex){
				met.ShowException(ex);
				return false;
			}
		return true;
	}
	
	public void DescargarPersonas() throws SQLException, IOException {
		CreateConnection();
		String dataBase = dirs.getDB();
		
		accionSql ("use "+dataBase);
		String sql = "Select * from "+dbPersonas;
		
		/*
		 * TODO: not finished just **working** reed re-work
		 */
		
		
		ResultSet rs = preguntaSql(sql);
		if(dirs.debug()) {System.out.println("in: SqlCon / DescargaPersonas : Exito  ");}
		String mail,nombre,apellido,nv,sexo;
		int rut;
		PrintWriter writer = new PrintWriter("c://POO//Persona.txt");
		while (rs.next()) 
		{
			nombre = rs.getString("Nombre");
			apellido = rs.getString("Apellido");
			rut = rs.getInt("Rut");
			nv = rs.getString("NumVer");
			sexo = rs.getString("Sexo");
			mail = rs.getString("Mail");
			writer.println(rut+"-"+nv+","+nombre+","+apellido+","+sexo+","+mail);
			
		}
		CloseConnection();	writer.close();
	}
	
	public boolean TestSqlCon() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if (this.url == null | this.username == null | this.password == null) { LoadConData(); }
			this.con = DriverManager.getConnection (this.url,this.username,this.password);
			st = this.con.createStatement();
			this.con.close();
			return true;
		}catch (Exception ex) {
			met.ShowException(ex);
			return false;
		}

	}

	public void CloseConnection() {
		try {
		this.con.close();
		}catch (Exception ex) {
			met.ShowException(ex);
		}
	}
	public ResultSet preguntaSql (String s) {
		try {
		return st.executeQuery(s);
		}catch (Exception ex) {
			met.ShowException(ex);
			return null;
		}
	}
	public int accionSql (String s){
		try {
			return st.executeUpdate(s);
		} catch (SQLException ex) {
			met.ShowException(ex);
			return 0;
		}		
	}
	

}

