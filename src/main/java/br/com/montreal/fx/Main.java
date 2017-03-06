package br.com.montreal.fx;

import java.io.IOException;

import javax.swing.JOptionPane;

import br.com.montreal.entidades.BaseDados;
import br.com.montreal.util.UtilBaseDados;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			this.verificaBase();
			
			BorderPane root = new BorderPane();
			// tamanho da tela
			Scene scene = new Scene(root, 640, 480);
			
			primaryStage.setScene(scene);
			
			//nao permite mudar tamanho janela
			primaryStage.setResizable(false);

			primaryStage.setTitle("Projeto JavaFX");
			primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResource("icons/icone.png").toExternalForm()));
			
			primaryStage.setMaximized(false); // <---- seta para maximizado. pode
												// usar setFullScreen para nao
												// mostrar os icones de fechar e
												// afins...
			primaryStage.show();

			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(getClass().getClassLoader().getResource("fx/view/loginView.fxml"));
			
			AnchorPane mainView = (AnchorPane) loader.load();
			mainView.getStylesheets().add(getClass().getClassLoader().getResource("fx/css/application.css").toExternalForm());
			
			// centraliza a tela
			root.setCenter(mainView);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
	}

	private void verificaBase() throws IOException {
		try {
			UtilBaseDados.recuperaBaseDados();
		} catch (IOException e) {
			
			BaseDados base = new BaseDados();
			UtilBaseDados.gravaBaseDados(base);
		}
	}
	
}
