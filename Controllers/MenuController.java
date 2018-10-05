package Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

import Comun.Biblioteca;
import Comun.Libro;

public class MenuController implements Observer{
	private Biblioteca biblioteca;
	@FXML private Button btnExit;
	@FXML private Button bAdd;
	@FXML private MenuItem libro;
	
	public void initialize() {
		biblioteca = new Biblioteca();
	}
	
	public void exit(ActionEvent event) throws Exception {
		Platform.exit();
	}
	
	public void agregarLibro(ActionEvent event) throws Exception{
		if(biblioteca.equals(null)) {
			initialize();
		}
		
		System.out.println(biblioteca.getListaLibros().size());
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/Views/AddLibro.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(loader.load());
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
		AddBookController controller = loader.getController();
		biblioteca.addObserver(controller);
		controller.setBiblioteca(biblioteca);

	}
	
	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

	@Override
	public void update(Observable arg0, Object biblioteca) {
		this.biblioteca = (Biblioteca) biblioteca;
		System.out.println(this.biblioteca.getListaLibros().size());
		ListIterator<Libro> itr = this.biblioteca.getListaLibros().listIterator();
		btnExit.setText(itr.next().getTitulo());
	}
}
