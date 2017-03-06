package br.com.montreal.fx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.montreal.fx.controller.enums.EnumRetornoModal;
import br.com.montreal.fx.controller.pattern.AbstractFxTabController;
import br.com.montreal.fx.controller.pattern.IModalCaller;
import br.com.montreal.fx.dto.TipoProdutoRowDto;
import br.com.montreal.fx.support.RowSupport;
import br.com.montreal.negocio.TipoProdutoBll;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PesquisaTipoProdutoController extends AbstractFxTabController implements Initializable, IModalCaller {
	
	private TipoProdutoBll tipoProdutoBll = null;
	
	@FXML
	private TableView<TipoProdutoRowDto> tbvTipoProduto;
	
	@FXML
	private TextField txtTipoProduto;
	
	@FXML
	private Button btnPesquisar;
	
	@FXML
	private Button btnNovoTipoProduto;
	
	@FXML
	private void btnPesquisarClick(ActionEvent event) {
		this.pesquisarTipoProduto();
	}
	
	@FXML
	private void btnNovoTipoProdutoClick(ActionEvent event) {
		CadastroTipoProdutoModalController cadastroTipoProdutoModalController = (CadastroTipoProdutoModalController)this.showModal("cadastroTipoProdutoView.fxml", "Cadastro Tipo Produto");
		cadastroTipoProdutoModalController.setCaller(this);
	}

	private void pesquisarTipoProduto() {
		try {
			List<TipoProdutoRowDto> dataSource = RowSupport.gerarTipoProdutoDataSource(this.tipoProdutoBll.listar());
			ObservableList<TipoProdutoRowDto> data = FXCollections.observableArrayList(dataSource);
			tbvTipoProduto.setItems(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.alertaErro("", "", "Erro", e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.tipoProdutoBll = new TipoProdutoBll();
		this.configuraGrid();
	}
	
	private void configuraGrid() {
		
		TableColumn<TipoProdutoRowDto, Integer> colCodTipoProduto = new TableColumn<TipoProdutoRowDto, Integer>("Cod. Tipo Produto");
		colCodTipoProduto.setCellValueFactory(new PropertyValueFactory<TipoProdutoRowDto, Integer>("codTipoProduto"));

		TableColumn<TipoProdutoRowDto, String> colNomeTipoProduto = new TableColumn<TipoProdutoRowDto, String>("Tipo Produto");
		colNomeTipoProduto.setCellValueFactory(new PropertyValueFactory<TipoProdutoRowDto, String>("nomeTipoProduto"));
		colNomeTipoProduto.setMinWidth(300);

		TableColumn<TipoProdutoRowDto, TipoProdutoRowDto> btnAcaoSelecionarCol = new TableColumn<>("");

		btnAcaoSelecionarCol.setCellValueFactory(new Callback<CellDataFeatures<TipoProdutoRowDto, TipoProdutoRowDto>, ObservableValue<TipoProdutoRowDto>>() {
			@Override
			public ObservableValue<TipoProdutoRowDto> call(CellDataFeatures<TipoProdutoRowDto, TipoProdutoRowDto> features) {
				return new ReadOnlyObjectWrapper<TipoProdutoRowDto>(features.getValue());
			}
		});

		btnAcaoSelecionarCol.setCellFactory(new Callback<TableColumn<TipoProdutoRowDto, TipoProdutoRowDto>, TableCell<TipoProdutoRowDto, TipoProdutoRowDto>>() {
			@Override
			public TableCell<TipoProdutoRowDto, TipoProdutoRowDto> call(TableColumn<TipoProdutoRowDto, TipoProdutoRowDto> btnCol) {
				return new TableCell<TipoProdutoRowDto, TipoProdutoRowDto>() {
					// final ImageView buttonGraphic = new ImageView();
					final Button button = new Button();
					{
						// button.setGraphic(buttonGraphic);
						// button.setMinWidth(10);
					}

					public void updateItem(final TipoProdutoRowDto tipoProdutoDto, boolean empty) {
						super.updateItem(tipoProdutoDto, empty);
						button.setText("Selecionar");
						// buttonGraphic.setImage(coffeeImage);

						if (tipoProdutoDto != null) {
							setGraphic(button);
							button.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									// CadastroClienteController controller = (CadastroClienteController) abrirNovaAba("cadastroClienteView.fxml", "Editar Cliente", true);
									// controller.carregaCliente(clienteDto.getCodCliente());
									// System.out.println("Clicou em" + clienteDto.getNomeRazaoSocial());
									/*
									clienteSelecionado = new Cliente();
									clienteSelecionado.setCodCliente(tipoProdutoDto.getCodCliente());
									clienteSelecionado.setNomeRazaoSocial(tipoProdutoDto.getNomeRazaoSocial());
									clienteSelecionado.setCpfCnpj(tipoProdutoDto.getCpfCnpj());
									*/
									// fecharModal(event);
								}
							});
						} else {
							setGraphic(null);
						}
					}
				};
			}
		});

		this.tbvTipoProduto.getColumns().add(colCodTipoProduto);
		this.tbvTipoProduto.getColumns().add(colNomeTipoProduto);
		this.tbvTipoProduto.getColumns().add(btnAcaoSelecionarCol);
	}

	@Override
	public void executeModalReturn(EnumRetornoModal retornoModal, String nomeModal) {
		if (retornoModal == EnumRetornoModal.OK) {
			
			this.alertaSucesso("", "", "Tipo de Produto salvo com Sucesso");
			this.pesquisarTipoProduto();
		}
	}
}
