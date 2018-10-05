package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ThreadLocalRandom;
import Comun.Biblioteca;
import Comun.Libro;

public class AddBookController implements Observer{
	private Biblioteca biblioteca;
	/* Variables de la ventana */
	@FXML private Label lblAlfa;
	@FXML private Label lblNum;
	@FXML private TextField txtTitulo;
	@FXML private TextField txtAutor;
	@FXML private Label lblTema;
	@FXML private TextArea areaInfo;
	@FXML private ComboBox<String> cbTema;
	@FXML private ComboBox<String> cbEstado;
	@FXML private ComboBox<String> cbDisp; //Importancia del libro (Por ej: libros pregrado, tesis, etc.)

	@Override
	public void update(Observable o, Object biblioteca) {
		this.biblioteca = (Biblioteca) biblioteca;
		
	}
	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	/*public void sendBiblioteca() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/Views/Main.fxml"));
		MenuController menu = loader.getController();
		menu.setBiblioteca(biblioteca);
	}*/
	
	/*** METODOS ESTETICOS DE LA VENTANA ***/
	public void updCodeTema(ActionEvent event) throws Exception {
		lblAlfa.setText(cbTema.getSelectionModel().getSelectedItem());
		updTema();
		lblNum.setText(generateNumCode());
	}
	
	public void updTema() {
		if(lblAlfa.getText().equals("MAT")) {
			lblTema.setText("Matematicas");
		}
		else if(lblAlfa.getText().equals("FIS")) {
			lblTema.setText("Fisica");
		}
		else if(lblAlfa.getText().equals("QUI")) {
			lblTema.setText("Quimica");
		}
		else if(lblAlfa.getText().equals("LEN")) {
			lblTema.setText("Lenguaje");
		}
		else if(lblAlfa.getText().equals("ING")) {
			lblTema.setText("Ingles");
		}
		else if(lblAlfa.getText().equals("INF")) {
			lblTema.setText("Informatica");
		}
		else if(lblAlfa.getText().equals("MEC")) {
			lblTema.setText("Mecanica");
		}
		else if(lblAlfa.getText().equals("ELE")) {
			lblTema.setText("Electronica");
		}
		else if(lblAlfa.getText().equals("ECO")) {
			lblTema.setText("Economia");
		}
		else if(lblAlfa.getText().equals("OTR")) {
			lblTema.setText("Otro");
		}
	}
	
	/***/
	
	public String generateNumCode(/*Biblioteca biblioteca*/) {
		String numCode = new String();
		//do {
			numCode = "";
			for(int i=0;i<4;i++) {
				numCode += Integer.toString(ThreadLocalRandom.current().nextInt(0, 10));
			}
		//}while(biblioteca.buscarLibro(Integer.parseInt(numCode)));
		
		return numCode;
	}
	
	public Libro nuevoLibro() {
		
		String code = lblAlfa.getText() + "-" + lblNum.getText();
		Libro nuLibro = new Libro(code);
		nuLibro.setTitulo(txtTitulo.getText());
		nuLibro.setAutor(txtAutor.getText());
		nuLibro.setTema(lblTema.getText());
		if(cbDisp.getValue().equals("Pregrado")) {
			nuLibro.setDias(5);
		}
		else if(cbDisp.getValue().equals("Tesis")) {
			nuLibro.setDias(2);
		}
		else if(cbDisp.getValue().equals("Postgrado")) {
			nuLibro.setDias(3);
		}
		else if(cbDisp.getValue().equals("Otro")) {
			nuLibro.setDias(7);
		}
		//----
		if(cbEstado.getValue().equals("Nuevo")) {
			nuLibro.setEstado("Nuevo");
		}
		else if(cbEstado.getValue().equals("Buen Estado")) {
			nuLibro.setEstado("Buen Estado");
		}
		else {
			nuLibro.setEstado("Decente");
		}
		
		return nuLibro;
	}
	
	public boolean setLibro() {
		Libro nuLibro = nuevoLibro();
		if(biblioteca.agregarLibro(nuLibro)) {
			nuLibro.mostrarInfo(areaInfo);
			return true;
		}
		return false;
	}
	
	public void agregarLibro(ActionEvent event) throws Exception {
		if(setLibro()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Exito");
			alert.setHeaderText("El libro ha sido añadido correctamente!");
			alert.showAndWait();
			System.out.println(biblioteca.getListaLibros().size());
			//sendBiblioteca();
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Hubo un error al añadir el libro");
			alert.setContentText("Porfavor asegurese que los datos hayan sido ingresados correctamente.");
			alert.showAndWait();
		}
	}	
}