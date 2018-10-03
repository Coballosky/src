package Comun;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import ConnectionHandler.SqlConection;
import Exception.ExHandler;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Setup {
	
private String mainPath = "c://POO",path = "c://POO//SqlData.txt";
private String path_Persona = "c://POO//Persona.txt";

ExHandler met = new ExHandler();

	public void CheckSetup() {
		
		SetupCarpeta();
		SetupSql();
		
		
	}
	public void SetupCarpeta() {
		boolean j = false ;
		File carpetaRoot = new File(this.mainPath);
		if (!carpetaRoot.exists()) {
		try {
		j= new File(this.mainPath).mkdir();
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
		File tmpDir = new File(this.path);
		if (tmpDir.exists()) {
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
	
	
	
	
	public void DescargaSQL() {
		SqlConection SQL = new SqlConection();
		File Per = new File(this.path_Persona);
		if (Per.exists() ) {
			try {
				SQL.DescargarPersonas();
			} catch (SQLException | IOException ex) {
				met.ShowException(ex);
			}
		}else {
			
			System.out.println("No se encontro el archivo");
			try {
				SQL.DescargarPersonas();
			} catch (SQLException | IOException ex) {
				met.ShowException(ex);
			}
		}
	}
}
