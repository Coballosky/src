package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class AddController{
	@FXML private TabPane tabAdd;
	@FXML private Tab tAddUser;
	@FXML private Tab tAddBook;
	@FXML private Tab tAddOther;
	@FXML private AddBookController addBookController;

	@FXML
	private void AgregarLibro(ActionEvent event) throws Exception {
		addBookController.agregarLibro(event);
	}

}
