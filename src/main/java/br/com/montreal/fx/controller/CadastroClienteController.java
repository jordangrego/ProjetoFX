package br.com.montreal.fx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.montreal.entidades.Cliente;
import br.com.montreal.fx.controller.pattern.AbstractFxTabController;
import br.com.montreal.fx.support.ValidationFields;
import br.com.montreal.negocio.ClienteBll;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastroClienteController extends AbstractFxTabController implements Initializable {

	private Cliente cliente;
	
	private ClienteBll clienteBll = new ClienteBll();
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Label lblCodCliente;
	
	@FXML
	private Button btnCancelar;
	
	@FXML
	private TextField txtNomeRazao;
	
	@FXML
	private TextField txtCpfCnpj;
	
	@FXML
	private ComboBox<String> cbTipoPessoa;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.cliente = new Cliente();
		this.cbTipoPessoa.getItems().add("Selecione");
		this.cbTipoPessoa.getItems().add("PF - Pessoa Física");
		this.cbTipoPessoa.getItems().add("PJ - Pessoa Jurídica");
		this.cbTipoPessoa.getSelectionModel().select(0);
	}
	
	public void carregaCliente(int codCliente) {
		try {
			for (Cliente cliente : this.clienteBll.listar()) {
				if (cliente.getCodCliente() == codCliente) {
					this.cliente = cliente;
					this.carregaTela();
				}
			}
		} catch (IOException e) {
			alertaErro("", "", "Erro ao carregar Cliente", e);
		}
	}
	
	private void carregaTela() {
		this.lblCodCliente.setText(String.valueOf(this.cliente.getCodCliente()));
		this.txtNomeRazao.setText(this.cliente.getNomeRazaoSocial());
		this.txtCpfCnpj.setText(this.cliente.getCpfCnpj());
	}

	@FXML
	private void btnSalvarClick(ActionEvent event) {
		
		if (this.cbTipoPessoa.getSelectionModel().getSelectedIndex() != 0) {			
			try {
				if (this.confirmacao("Confirmação", "", "Confirma salvar Cliente?") && ValidationFields.checkEmptyFields(txtNomeRazao) && ValidationFields.checkEmptyFields(txtCpfCnpj)) {			
					this.cliente.setNomeRazaoSocial(this.txtNomeRazao.getText());
					this.cliente.setCpfCnpj(this.txtCpfCnpj.getText());
					this.clienteBll.salvar(this.cliente);
					
					this.alertaSucesso("", "", "Editado com sucesso");
					this.fecharEstaAba();
				}
			} catch (IOException e) {
				this.alertaErro("", "", "Erro", e);
			}
		} else {
			alertaAviso("", "", "Formulário inválido.");
		}
		
	}
	
	@FXML
	private void btnCancelarClick(ActionEvent event) {
		if (this.confirmacao("Confirmação", "", "Confirma cancelar o cadastro?")) {
			this.fecharEstaAba();		
		}
	}
}
