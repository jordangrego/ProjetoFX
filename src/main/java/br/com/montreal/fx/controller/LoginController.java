package br.com.montreal.fx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.montreal.fx.controller.pattern.AbstractFxController;
import br.com.montreal.fx.support.ValidationFields;
import br.com.montreal.negocio.AcessoLdap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginController extends AbstractFxController implements Initializable {
	@FXML
	private TextField txtUsuario;
	@FXML
	private PasswordField ptxtSenha;
	@FXML
	private Button btnLogin;

	@FXML
	private void btnLoginClick(ActionEvent event) {

		boolean autenticado = false;

		try {

			if (ValidationFields.checkEmptyFields(txtUsuario) && ValidationFields.checkEmptyFields(ptxtSenha)) {

				this.alterarCursorMouse(event, Cursor.WAIT);
				autenticado = AcessoLdap.authenticateJndi(this.txtUsuario.getText(), this.ptxtSenha.getText());
				this.alterarCursorMouse(event, Cursor.DEFAULT);
				
				// System.out.println(autenticado);

				String username = System.getenv("USERNAME");
				// System.out.println("Usuario logado:" + username);

				final String dir = System.getProperty("user.dir");
				// System.out.println("current dir = " + dir);

				String os = System.getProperty("os.name");
				// System.out.println("OS:" + os);

				/*
				 * System.out.println("TUDO ###########################");
				 * 
				 * Map<String, String> env = System.getenv(); for (String
				 * envName : env.keySet()) { System.out.format("%s=%s%n",
				 * envName, env.get(envName)); }
				 */
				
				
				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.alertaErro("Usuário não autenticado", "Login", "Usuário não autenticado", new Exception("Usuário não autenticado."));
		}

		if (autenticado) {
			Stage stage = (Stage) btnLogin.getScene().getWindow();
			Boolean isMaximized = stage.isMaximized();
			this.navegarView("multiView.fxml", this.btnLogin);

		} else {
			this.alertaErro("Usuário não autenticado", "Login", "Usuário não autenticado", new Exception("Usuário não autenticado."));
		}
	}

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.btnLogin.setDefaultButton(true);
		/*this.btnLogin.defaultButtonProperty().bind(this.btnLogin.focusedProperty());*/
		
		
		
	}

}
