package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
public class IndexController implements Initializable{
	@FXML
	private Button loginGame_btn;
	@FXML
	private Button loadGame_btn;
	@FXML
	private Button exitGame_btn;
	
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	public void loginGame() {
		new GameStage();
	}
	public void loadGame() {
		
	}
	public void exitGame() {
		System.exit(1);
	}
}
