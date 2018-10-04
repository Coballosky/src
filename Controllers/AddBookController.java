package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import Comun.Biblioteca;
import Comun.Libro;

public class AddBookController {
	/* Variables de la segunda ventana */
	@FXML private Label lbl_Id;
	@FXML private TextField txt_Titulo;
	@FXML private TextField txt_Autor;
	@FXML private Label lbl_Tema;
	@FXML private ChoiceBox<String> cb_Estado;
	@FXML private ChoiceBox<String> cb_Disp; //Importancia del libro (Por ej: libros pregrado, tesis, etc.)
	
	public Libro nuevoLibro() {
		
		Libro nuLibro = new Libro(lbl_Id.getText());
		nuLibro.setTitulo(txt_Titulo.getText());
		nuLibro.setAutor(txt_Autor.getText());
		nuLibro.setTema(lbl_Tema.getText());
		if(cb_Disp.getValue().equals("Postgrado")) {
			nuLibro.setDias(3);
		}
		else if(cb_Disp.getValue().equals("Tesis")) {
			nuLibro.setDias(1);
		}
		else {
			nuLibro.setDias(7);
		}
		if(cb_Estado.getValue().equals("Nuevo")) {
			nuLibro.setEstado("Nuevo");
		}
		else if(cb_Estado.getValue().equals("Mal Estado")) {
			nuLibro.setEstado("Mal estado");
		}
		else {
			nuLibro.setEstado("Buen estado");
		}
		
		return nuLibro;
	}
	
	public void setLibro(Biblioteca biblioteca) {
		biblioteca.agregarLibro(nuevoLibro());
	}
	
}