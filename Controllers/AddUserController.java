package Controllers;

import java.awt.TextField;

import Comun.Biblioteca;
import Comun.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

public class AddUserController {
	Usuario usuario = new Usuario();
	
	@FXML private TextField lbl_Nombre;
	@FXML private TextField lbl_Apellido;
	@FXML private TextField lbl_Rut;
	@FXML private TextField lbl_Mail;
	// @FXML private TextField lbl_Direccion;
	@FXML private RadioButton SexoH,SexoM,SexoNA;

	
	
	public void AgregarPersona(	 ) {

		usuario.setNombres (this.lbl_Nombre.getText());
		usuario.setApellidos( this.lbl_Apellido.getText());
		usuario.setRut( this.lbl_Rut.getText());
		usuario.setMail( this.lbl_Mail.getText());
		
		if (!SexoNA.isSelected() ) {
			if ( SexoH.isSelected() ) {
				usuario.setSexo('H');
			}else {
				usuario.setSexo('M');
			}
		}else {
			usuario.setSexo('O');
		}
		
		/*
		 * TODO: Añadir usuario a la biblioteca
		 */
		
		
	}

	
	
	
}
