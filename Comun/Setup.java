package Comun;

import java.io.File;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Setup {
private String mainPath = "c:\\POO";
private String path = "c:POO\\SqlData.txt";
Metodos met = new Metodos();
	
	public void SetupCarpeta() {
		boolean j = false ;
		File carpetaRoot = new File(this.mainPath);
		if (!carpetaRoot.exists()) {
			try {
			j= new File(this.mainPath).mkdir();
			}catch(Exception ex) {
				met.ShowException(ex);
			}
			if (j) {
				System.out.println("carpeta del programa creada");
			}
		}
	}
	public void SetupSql() {
		File tmpDir = new File(mainPath);
		if (!tmpDir.exists()) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/ConfigSQL.fxml&quot"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
				}catch(Exception ex) {
					met.ShowException(ex);
				}
		}
	}
}
