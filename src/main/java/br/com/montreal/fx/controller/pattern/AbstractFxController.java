package br.com.montreal.fx.controller.pattern;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.Optional;

import br.com.montreal.fx.dto.TabDto;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class AbstractFxController {

	// private final String PATH_VIEW = "br/com/montreal/fx/view/";
	private final String PATH_VIEW = "fx/view/";
	private final String PATH_CSS = "fx/css/";

	/**
	 * Navega para a View e retorna o controller da nova tela
	 * 
	 * @param pathView
	 * @param controle
	 * @return
	 * @throws IOException
	 */
	protected AbstractFxController navegarView(String pathView, Control controle) {
		String pathFxml = PATH_VIEW + pathView;

		try {
			Stage stage = (Stage) controle.getScene().getWindow();
			URL urlView = getClass().getClassLoader().getResource(pathFxml);
			FXMLLoader fxmlLoader = new FXMLLoader(urlView);
			Parent root = (Parent) fxmlLoader.load();
			Scene scene = new Scene(root);
			
			// seta CSS
			// scene.getStylesheets().add(getClass().getClassLoader().getResource(PATH_CSS + "MistSilverSkin.css").toExternalForm());
			
			stage.setScene(scene);
			stage.setMaximized(true);
			stage.setResizable(true);
			stage.show();
			return (AbstractFxController) fxmlLoader.getController();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			this.alertaErro("", "Erro de navegação:", "", e);
		}

		return null;
	}

	/**
	 * Abre a View como modal e retorna o controller da View aberta
	 * 
	 * @param pathView
	 * @param titulo
	 * @return
	 * @throws IOException
	 */
	protected AbstractFxModalController showModal(String pathView, String titulo) {
		String pathFxml = PATH_VIEW + pathView;

		try {

			URL urlView = getClass().getClassLoader().getResource(pathFxml);
			FXMLLoader fxmlLoader = new FXMLLoader(urlView);

			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.DECORATED);
			stage.setTitle(titulo);
			stage.setScene(new Scene(root1));

			stage.show();

			return (AbstractFxModalController) fxmlLoader.getController();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			this.alertaErro("", "Erro de navegação:", "", e);
		}

		return null;
	}
	
	/**
	 * 
	 * @param pathView
	 * @return
	 */
	protected TabDto getConteudoAba(String pathView) {
		try {
			TabDto dto = new TabDto();
			String pathFxml = PATH_VIEW + pathView;
			URL urlView = getClass().getClassLoader().getResource(pathFxml);
			FXMLLoader fxmlLoader = new FXMLLoader(urlView);

			AnchorPane aba = (AnchorPane) fxmlLoader.load();
			aba.setPadding(new Insets(5, 5, 5, 5));
			
			dto.setAbaController((AbstractFxTabController) fxmlLoader.getController());
			dto.setAnchorPane(aba);
			
			return dto;
		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			this.alertaErro("", "Erro ao abrir Aba", "", e);
		}
		
		return null;
	}

	/**
	 * Dialogo de Alerta
	 * 
	 * @param titulo
	 * @param cabecalho
	 * @param conteudo
	 */
	protected void alertaErro(String titulo, String cabecalho, String conteudo, Exception ex) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(titulo);
		alert.setHeaderText(cabecalho);
		alert.setContentText(conteudo);

		// Create expandable Exception.
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("The exception stacktrace was:");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		alert.getDialogPane().setExpandableContent(expContent);

		alert.showAndWait();
	}
	
	/**
	 * Dialogo de Alerta
	 * 
	 * @param titulo
	 * @param cabecalho
	 * @param conteudo
	 */
	protected void alertaSucesso(String titulo, String cabecalho, String conteudo) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(cabecalho);
		alert.setContentText(conteudo);

		alert.showAndWait();
	}
	
	/**
	 * Dialogo de Alerta
	 * 
	 * @param titulo
	 * @param cabecalho
	 * @param conteudo
	 */
	protected void alertaAviso(String titulo, String cabecalho, String conteudo) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(titulo);
		alert.setHeaderText(cabecalho);
		alert.setContentText(conteudo);

		alert.showAndWait();
	}

	/**
	 * Dialogo de confirmação do usuário
	 * 
	 * @param titulo
	 * @param cabecalho
	 * @param conteudo
	 * @return
	 */
	protected boolean confirmacao(String titulo, String cabecalho, String conteudo) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(cabecalho);
		alert.setContentText(conteudo);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Dialogo de escolha de Arquivo.
	 * 
	 * @param stage
	 * @return
	 */
	protected File showFileDialog(Stage stage) {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(stage);
		return file;
	}

	/**
	 * Dialogo de escolha de Pasta
	 * 
	 * @param stage
	 * @return
	 */
	protected String showFolderDialog(Stage stage) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File selectedDirectory = directoryChooser.showDialog(stage);

		if (selectedDirectory != null) {
			return selectedDirectory.getAbsolutePath();
		}

		return null;
	}

	/**
	 * Dialogo de Inserção de texto
	 * 
	 * @param titulo
	 * @param cabecalho
	 * @param conteudo
	 * @return
	 */
	protected String insereTexto(String titulo, String cabecalho, String conteudo) {
		TextInputDialog dialog = new TextInputDialog("walter");
		dialog.setTitle("Text Input Dialog");
		dialog.setHeaderText("Look, a Text Input Dialog");
		dialog.setContentText("Please enter your name:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			return result.get();
		}

		return null;
	}

	/**
	 * Informa o path de execução da aplicação
	 * @return
	 */
	protected String getPathExecucao() {
		return System.getProperty("user.dir");
	}

	/**
	 * Recupera o Stage
	 * @param event
	 * @return
	 */
	protected Stage getStageFromEvent(Event event) {

		if (event.getSource() instanceof MenuItem) {
			return (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
		}

		if (event.getSource() instanceof Control) {
			return (Stage) ((Control) event.getSource()).getScene().getWindow();
		}

		return null;
	}
	
	/**
	 * Recupera o Stage
	 * @param event
	 * @return
	 */
	protected Stage getStageFromControl(Control control) {
		return (Stage) (control).getScene().getWindow();
	}

	/**
	 * Recupera a Scene
	 * @param event
	 * @return
	 */
	protected Scene getSceneFromEvent(Event event) {

		if (event.getSource() instanceof MenuItem) {
			return ((MenuItem) event.getSource()).getParentPopup().getScene();
		}

		if (event.getSource() instanceof Control) {
			return ((Control) event.getSource()).getScene();
		}

		return null;
	}

	/**
	 * Altera o cursor do Mouse.
	 * @param event
	 * @param cursor
	 */
	protected void alterarCursorMouse(Event event, Cursor cursor) {
		this.getSceneFromEvent(event).setCursor(cursor);
	}
}
