package Comun;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import ConnectionHandler.SqlConection;
import Exception.ExHandler;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Setup {
private boolean debug = false;


ExHandler met = new ExHandler();
DirAndPaths dir = new DirAndPaths();

	public void CheckSetup() throws FileNotFoundException {
		
		SetupCarpeta();
		SetupSql();
		// dir.getDB()
		
	}
	
	public void DescargaSQL() {
		
		DescargaPersonas();
		
	}
	
	
	
	public void SetupCarpeta() {
		boolean j = false ;
		File carpetaRoot = new File(dir.getPathProgram());
		if (!carpetaRoot.exists()) {
		try {
		j= new File(this.dir.getPathProgram()).mkdir();
		}catch(Exception ex) {
			System.out.println("Error en la creacion de la carpeta del programa");
			met.ShowException(ex);
		}
		if (j) { 
			System.out.println("carpeta del programa creada");
		}
		}

	}
	public void SetupSql() {
		File tmpDir = new File(dir.getPathSql());
		if (!tmpDir.exists()) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/ConfigSQL.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.showAndWait();
				}catch(Exception ex) {
				  met.ShowException(ex);
				}
		}
	}
	
	
	
	public void DescargaPersonas() {
		
		SqlConection SQL = new SqlConection();
		try {
			SQL.DescargarPersonas();
		} catch (SQLException | IOException e) {
			if (this.debug) { System.out.println("Excepcion al llamar la funcion Descargar Personas de SQL"); } 
			met.ShowException(e);
		}
		
	}
	
}
