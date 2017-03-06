package br.com.montreal.fx.controller;

import java.io.IOException;

import br.com.montreal.entidades.TipoProduto;
import br.com.montreal.fx.controller.enums.EnumRetornoModal;
import br.com.montreal.fx.controller.pattern.AbstractFxModalController;
import br.com.montreal.negocio.TipoProdutoBll;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroTipoProdutoModalController extends AbstractFxModalController {

	@FXML
	private TextField txtTipoProduto;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private void btnSalvarClick(ActionEvent event) {
		
		try {
			TipoProduto tipoProduto = new TipoProduto();
			tipoProduto.setNomeTipoProduto(this.txtTipoProduto.getText());
			TipoProdutoBll tipoProdutoBll = new TipoProdutoBll();
			tipoProdutoBll.incluir(tipoProduto);
			Stage stage = this.getStageFromEvent(event);
			stage.close();
			this.caller.executeModalReturn(EnumRetornoModal.OK, this.getNomeModal());
		} catch (IOException e) {
			this.alertaErro("", "Erro ao Salvar", "", e);
		}
	}
	
	@Override
	protected String getNomeModal() {
		return "Cadastro Tipo Produto";
	}

}
