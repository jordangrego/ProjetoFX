package br.com.montreal.fx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import br.com.montreal.fx.controller.pattern.AbstractFxModalController;
import br.com.montreal.fx.controller.pattern.AbstractFxTabController;
import br.com.montreal.fx.controller.pattern.ITabPaneController;
import br.com.montreal.fx.dto.ReportDto;
import br.com.montreal.fx.dto.TabDto;
import br.com.montreal.negocio.reports.ReportBll;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MultiViewController extends AbstractFxModalController implements ITabPaneController {
	@FXML
	TabPane tabMaster;
	
	@FXML
	private MenuItem btnSair;
	
	@FXML
	private MenuItem miNovoCliente;
	
	@FXML
	private MenuItem miPesquisarClientes;
	
	@FXML
	private MenuItem miNovoPedido;
	
	@FXML
	private MenuItem miPesquisaPedido;
	
	@FXML
	private MenuItem miProduto;
	
	@FXML
	private MenuItem miTipoProduto;
	
	@FXML
	private MenuItem miGrafico;
	
	@FXML
	private MenuItem miRelatorioPdf;
	
	@Override
	protected String getNomeModal() {
		return "MultiView";
	}
	
	@FXML
	private void miNovoClienteClick(ActionEvent event) {
		CadastroClienteController cadastroClienteController = (CadastroClienteController) this.geraNovaAba("cadastroClienteView.fxml", "Novo Cliente");
	}
	
	@FXML
	private void miGraficoClick(ActionEvent event) {
		GraficoController graficoController = (GraficoController) this.geraNovaAba("graficoView.fxml", "Grafico Pizza");
	}
	
	@FXML
	private void miRelatorioPdfClick(ActionEvent event) {
		
		try {
			ReportDto reportDto = new ReportBll().gerarReport();			
			PdfTabController pdfTabController = (PdfTabController) this.geraNovaAba("pdfTabView.fxml", "Relatório Tab");
			pdfTabController.apresentaPdf(reportDto);
		} catch (Exception e) {
			this.alertaErro("", "", "Erro no Jasper", e);
		}
		
	}
	
	@FXML
	private void miNovoPedidoClick(ActionEvent event) {
		CadastroPedidoController cadastroPedidoController = (CadastroPedidoController) this.geraNovaAba("cadastroPedidoView.fxml", "Novo Pedido");
	}
	
	@FXML
	private void imPesquisaPedidoClick(ActionEvent event) {
		PesquisaPedidoController pesquisaPedidoController = (PesquisaPedidoController) this.geraNovaAba("pesquisaPedidoView.fxml", "Pesquisa Pedidos");
	}
	
	@FXML
	private void miPesquisarClientesClick(ActionEvent event) {
		PesquisaClienteController pesquisaClienteController = (PesquisaClienteController) this.geraNovaAba("pesquisaCliente.fxml", "Pesquisa de Cliente");
	}
	
	@FXML
	private void miTipoProdutoClick(ActionEvent event) {
		PesquisaTipoProdutoController pesquisaTipoProdutoController = (PesquisaTipoProdutoController) this.geraNovaAba("pesquisaTipoProdutoView.fxml", "Tipo de Produto");
	}
	
	@FXML
	private void miProdutoClick(ActionEvent event) {
		CadastroProdutoController cadastroProdutoController = (CadastroProdutoController) this.geraNovaAba("cadastroProdutoView.fxml", "Cadastro Produto");
	}
	
	@FXML
	private void btnSairClick(ActionEvent event) throws IOException {
		if (this.confirmacao("Confirmação", "", "Confirma fechar o sistema?")) {
			System.exit(0);			
		}
	}
	
	public AbstractFxTabController geraNovaAba(String view, String titulo) {
		try {
			Tab novaTab = new Tab();
			novaTab.setClosable(true);
			novaTab.setText(titulo);
			ScrollPane scrollPane = new ScrollPane();
			
			TabDto abaDto = this.getConteudoAba(view);
			
			// executa a ligação entro objetos.
			AbstractFxTabController controller = (AbstractFxTabController) abaDto.getAbaController();
			controller.setTab(novaTab);
			controller.setTabCaller(this);
			
			scrollPane.setContent(abaDto.getAnchorPane());
			scrollPane.setPadding(new Insets(5, 5, 5, 5));
			scrollPane.setFitToWidth(true);
			scrollPane.setFitToHeight(true);
			
			novaTab.setContent(scrollPane);
			tabMaster.getTabs().add(novaTab);
			tabMaster.getSelectionModel().select(novaTab);
			
			return controller;
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			this.alertaErro("", "Erro ao abrir Aba", "", e);
		}
		
		return null;
	}

	@Override
	public void fecharAba(Tab tab) {
		tabMaster.getTabs().remove(tab);
	}
}
