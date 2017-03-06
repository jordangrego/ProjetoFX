package br.com.montreal.fx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.montreal.entidades.Cliente;
import br.com.montreal.entidades.Pedido;
import br.com.montreal.fx.controller.enums.EnumRetornoModal;
import br.com.montreal.fx.controller.pattern.AbstractFxTabController;
import br.com.montreal.fx.controller.pattern.IModalCaller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CadastroPedidoController extends AbstractFxTabController implements Initializable, IModalCaller {

	private PesquisaClienteModalController pesquisaClienteModalController;
	private Pedido pedido = new Pedido();
	
	@FXML
	private Button btnSelecionarCliente;
	
	@FXML
	private TextField txtNomeCliente;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
	}
	
	@FXML
	private void btnSelecionarClienteClick(ActionEvent event) {
		this.pesquisaClienteModalController = (PesquisaClienteModalController) this.showModal("pesquisaClienteModalView.fxml", "Selecione o Cliente");
		this.pesquisaClienteModalController.setCaller(this);
	}

	@Override
	public void executeModalReturn(EnumRetornoModal retornoModal, String nomeModal) {
		if (retornoModal == EnumRetornoModal.OK) {
			Cliente cliente = this.pesquisaClienteModalController.getClienteSelecionado();
			pedido.setCliente(cliente);
			this.txtNomeCliente.setText(cliente.getNomeRazaoSocial());
		}
	}

}
