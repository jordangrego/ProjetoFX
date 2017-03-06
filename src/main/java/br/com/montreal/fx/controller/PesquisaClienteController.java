package br.com.montreal.fx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.montreal.fx.controller.pattern.AbstractFxTabController;
import br.com.montreal.fx.dto.ClienteRowDto;
import br.com.montreal.fx.support.RowSupport;
import br.com.montreal.negocio.ClienteBll;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class PesquisaClienteController extends AbstractFxTabController implements Initializable {
	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnNovoCliente;

	@FXML
	private TableView<ClienteRowDto> tbvClientes;
	
	private ClienteBll clienteBll = new ClienteBll();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.configuraControles();
	}

	@FXML
	private void btnPesquisarClick(ActionEvent event) {
		this.pesquisarCliente();
	}

	@FXML
	private void btnNovoClienteClick(ActionEvent event) throws IOException {
		CadastroClienteController cadastroClienteController = (CadastroClienteController) this.abrirNovaAba("cadastroClienteView.fxml", "Novo Cliente");
	}

	private void configuraControles() {
		this.configuraGrid();
	}
	
	private void pesquisarCliente() {
		try {
			List<ClienteRowDto> dataSource = RowSupport.gerarClienteDataSource(clienteBll.listar());
			ObservableList<ClienteRowDto> data = FXCollections.observableArrayList(dataSource);
			tbvClientes.setItems(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.alertaErro("", "", "Erro", e);
		}
	}

	private void configuraGrid() {
		// abre com 2 cliques
		this.tbvClientes.setRowFactory(tv -> {
			TableRow<ClienteRowDto> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					ClienteRowDto rowData = row.getItem();
					CadastroClienteController controller = (CadastroClienteController) this.abrirNovaAba("cadastroClienteView.fxml", "Editar Cliente");
					controller.carregaCliente(rowData.getCodCliente());
				}
			});
			return row;
		});

		TableColumn<ClienteRowDto, Integer> colCodCliente = new TableColumn<ClienteRowDto, Integer>("Cod. Cliente");
		colCodCliente.setCellValueFactory(new PropertyValueFactory<ClienteRowDto, Integer>("codCliente"));

		TableColumn<ClienteRowDto, String> colNomeRazao = new TableColumn<ClienteRowDto, String>("Nome / RazaoSocial");
		colNomeRazao.setCellValueFactory(new PropertyValueFactory<ClienteRowDto, String>("nomeRazaoSocial"));
		colNomeRazao.setMinWidth(300);

		TableColumn<ClienteRowDto, String> colCpfCnpj = new TableColumn<ClienteRowDto, String>("CPF / CNPJ");
		colCpfCnpj.setCellValueFactory(new PropertyValueFactory<ClienteRowDto, String>("cpfCnpj"));

		TableColumn<ClienteRowDto, ClienteRowDto> btnAcaoEditarCol = new TableColumn<>("");

		btnAcaoEditarCol.setCellValueFactory(new Callback<CellDataFeatures<ClienteRowDto, ClienteRowDto>, ObservableValue<ClienteRowDto>>() {
			@Override
			public ObservableValue<ClienteRowDto> call(CellDataFeatures<ClienteRowDto, ClienteRowDto> features) {
				return new ReadOnlyObjectWrapper<ClienteRowDto>(features.getValue());
			}
		});

		btnAcaoEditarCol.setCellFactory(new Callback<TableColumn<ClienteRowDto, ClienteRowDto>, TableCell<ClienteRowDto, ClienteRowDto>>() {
			@Override
			public TableCell<ClienteRowDto, ClienteRowDto> call(TableColumn<ClienteRowDto, ClienteRowDto> btnCol) {
				return new TableCell<ClienteRowDto, ClienteRowDto>() {
					// final ImageView buttonGraphic = new ImageView();
					final Button button = new Button();
					{
						// button.setGraphic(buttonGraphic);
						// button.setMinWidth(10);
					}

					public void updateItem(final ClienteRowDto clienteDto, boolean empty) {
						super.updateItem(clienteDto, empty);
						button.setText("Editar");
						// buttonGraphic.setImage(coffeeImage);

						if (clienteDto != null) {
							setGraphic(button);
							button.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									CadastroClienteController controller = (CadastroClienteController) abrirNovaAba("cadastroClienteView.fxml", "Editar Cliente");
									controller.carregaCliente(clienteDto.getCodCliente());
									// System.out.println("Clicou em" + clienteDto.getNomeRazaoSocial());
								}
							});
						} else {
							setGraphic(null);
						}
					}
				};
			}
		});
		
		TableColumn<ClienteRowDto, ClienteRowDto> btnAcaoExcluirCol = new TableColumn<>("");

		btnAcaoExcluirCol.setCellValueFactory(new Callback<CellDataFeatures<ClienteRowDto, ClienteRowDto>, ObservableValue<ClienteRowDto>>() {
			@Override
			public ObservableValue<ClienteRowDto> call(CellDataFeatures<ClienteRowDto, ClienteRowDto> features) {
				return new ReadOnlyObjectWrapper<ClienteRowDto>(features.getValue());
			}
		});

		btnAcaoExcluirCol.setCellFactory(new Callback<TableColumn<ClienteRowDto, ClienteRowDto>, TableCell<ClienteRowDto, ClienteRowDto>>() {
			@Override
			public TableCell<ClienteRowDto, ClienteRowDto> call(TableColumn<ClienteRowDto, ClienteRowDto> btnCol) {
				return new TableCell<ClienteRowDto, ClienteRowDto>() {
					// final ImageView buttonGraphic = new ImageView();
					final Button button = new Button();
					{
						// button.setGraphic(buttonGraphic);
						// button.setMinWidth(10);
					}

					public void updateItem(final ClienteRowDto clienteDto, boolean empty) {
						super.updateItem(clienteDto, empty);
						button.setText("Excluir");
						// buttonGraphic.setImage(coffeeImage);

						if (clienteDto != null) {
							setGraphic(button);
							button.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									
									if(confirmacao("", "", "Confima excluir Cliente?")) {
										try {
											clienteBll.excluir(clienteDto.getCodCliente());
											pesquisarCliente();
										} catch (IOException e) {
											alertaErro("", "", "Erro na Exclusão.", e);
										}
									}
								}
							});
						} else {
							setGraphic(null);
						}
					}
				};
			}
		});

		this.tbvClientes.getColumns().add(colCodCliente);
		this.tbvClientes.getColumns().add(colNomeRazao);
		this.tbvClientes.getColumns().add(colCpfCnpj);
		this.tbvClientes.getColumns().add(btnAcaoEditarCol);
		this.tbvClientes.getColumns().add(btnAcaoExcluirCol);
	}

	// Caso queira colocar uma imagem no botão
	// private final Image coffeeImage = new Image("http://icons.iconarchive.com/icons/archigraphs/collection/48/Coffee-icon.png");

}
