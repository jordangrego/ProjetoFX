package br.com.montreal.fx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.montreal.entidades.Cliente;
import br.com.montreal.fx.controller.enums.EnumRetornoModal;
import br.com.montreal.fx.controller.pattern.AbstractFxModalController;
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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PesquisaClienteModalController extends AbstractFxModalController implements Initializable {

	@FXML
	private TableView<ClienteRowDto> tbvClientes;
	
	private Cliente clienteSelecionado;
	
	
	private ClienteBll clienteBll = new ClienteBll();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.configuraGrid();
	}

	@Override
	protected String getNomeModal() {
		return "PesquisaClienteModal";
	}

	@FXML
	private void btnPesquisarClick(ActionEvent event) {
		this.pesquisarCliente();
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
	
		TableColumn<ClienteRowDto, Integer> colCodCliente = new TableColumn<ClienteRowDto, Integer>("Cod. Cliente");
		colCodCliente.setCellValueFactory(new PropertyValueFactory<ClienteRowDto, Integer>("codCliente"));

		TableColumn<ClienteRowDto, String> colNomeRazao = new TableColumn<ClienteRowDto, String>("Nome / RazaoSocial");
		colNomeRazao.setCellValueFactory(new PropertyValueFactory<ClienteRowDto, String>("nomeRazaoSocial"));
		colNomeRazao.setMinWidth(300);

		TableColumn<ClienteRowDto, String> colCpfCnpj = new TableColumn<ClienteRowDto, String>("CPF / CNPJ");
		colCpfCnpj.setCellValueFactory(new PropertyValueFactory<ClienteRowDto, String>("cpfCnpj"));

		TableColumn<ClienteRowDto, ClienteRowDto> btnAcaoSelecionarCol = new TableColumn<>("");

		btnAcaoSelecionarCol.setCellValueFactory(new Callback<CellDataFeatures<ClienteRowDto, ClienteRowDto>, ObservableValue<ClienteRowDto>>() {
			@Override
			public ObservableValue<ClienteRowDto> call(CellDataFeatures<ClienteRowDto, ClienteRowDto> features) {
				return new ReadOnlyObjectWrapper<ClienteRowDto>(features.getValue());
			}
		});

		btnAcaoSelecionarCol.setCellFactory(new Callback<TableColumn<ClienteRowDto, ClienteRowDto>, TableCell<ClienteRowDto, ClienteRowDto>>() {
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
						button.setText("Selecionar");
						// buttonGraphic.setImage(coffeeImage);

						if (clienteDto != null) {
							setGraphic(button);
							button.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									// CadastroClienteController controller = (CadastroClienteController) abrirNovaAba("cadastroClienteView.fxml", "Editar Cliente", true);
									// controller.carregaCliente(clienteDto.getCodCliente());
									// System.out.println("Clicou em" + clienteDto.getNomeRazaoSocial());
									
									clienteSelecionado = new Cliente();
									clienteSelecionado.setCodCliente(clienteDto.getCodCliente());
									clienteSelecionado.setNomeRazaoSocial(clienteDto.getNomeRazaoSocial());
									clienteSelecionado.setCpfCnpj(clienteDto.getCpfCnpj());
									
									fecharModal(event);
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
		this.tbvClientes.getColumns().add(btnAcaoSelecionarCol);
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}
	
	private void fecharModal(ActionEvent event) {
		Stage stage = this.getStageFromEvent(event);
		stage.close();

		if (this.caller != null) {
			this.caller.executeModalReturn(EnumRetornoModal.OK, this.getNomeModal());
		}
	}

}
