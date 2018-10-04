package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

public class AddController {
	@FXML private TabPane tabAdd;
	@FXML private Tab tAddUser;
	@FXML private Tab tAddBook;
	@FXML private Tab tAddOther;

	/*  Variables correspondientes a la primera pestaña de la ventana  */
	@FXML private TextField lbl_Nombre, lbl_Apellido,lbl_Rut,lbl_Mail,lbl_Direccion;
	
	public void AgregarPersona(ActionEvent event) {
		
		System.out.println(this.lbl_Nombre.getText());
				
	}
	public void AgregarLibro(ActionEvent event) {
		
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
