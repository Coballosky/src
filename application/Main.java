package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.sql.SQLException;

import Exception.ExHandler;
import Comun.Setup;
import Controllers.MainController;

/**
 * 
 * @author Nicolas
 *
 *
 * Este programa corre con base de datos MYSQL externa, para comunicar hacia tal database se requiere
 * del uso del puerto 3306 por defecto, desconosco si a la hora de entrega arreglamos ese tema cambiando el puerto 
 * o con otra solucion. porfavor tener en cuenta.
 *
 *
 */

public class Main extends Application {
	ExHandler met = new ExHandler();
	
	@Override
	public void start(Stage primaryStage) throws IOException, SQLException {
		Setup set = new Setup();
		set.CheckSetup();			//Se revisan las carpetas del programa y sus archivos
		set.DescargaSQL();			// se descarga la base de datos para hacer uso de archivos ( requerimiento entrega 1 ).
		

		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			} catch(Exception ex) {
				met.ShowException(ex);
			}
			
	}
	
	public void closeLogin(Stage primaryStage) {
		primaryStage.close();
	}
	
	

}
