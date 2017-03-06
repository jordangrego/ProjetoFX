package br.com.montreal.fx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.montreal.fx.controller.pattern.AbstractFxTabController;
import br.com.montreal.fx.dto.ComboItemDto;
import br.com.montreal.fx.support.ComboSupport;
import br.com.montreal.negocio.TipoProdutoBll;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class CadastroProdutoController extends AbstractFxTabController implements Initializable {
	@FXML
	private ComboBox<ComboItemDto> cbTipoProduto;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			this.cbTipoProduto.setPromptText("Selecione");
			this.cbTipoProduto.setItems(ComboSupport.geraComboDataSource(new TipoProdutoBll().listar(), "nomeTipoProduto", "codTipoProduto"));
		} catch (Exception ex) {
			this.alertaErro("", "", "Erro", ex);
		}
		
	}
	
	@FXML
	private void btnSalvarClick(ActionEvent event) {
		if (this.cbTipoProduto.getSelectionModel().getSelectedIndex() > -1) {
			this.alertaAviso("", "", "Tipo de Produto Selecionado = " + this.cbTipoProduto.getSelectionModel().getSelectedItem().getIdValue());
		} else {
			this.alertaAviso("", "", "Nenhum tipo de produto selecionado");
		}
	}
	
}
