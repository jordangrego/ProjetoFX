package br.com.montreal.fx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.plaf.synth.SynthSeparatorUI;

import br.com.montreal.fx.controller.enums.EnumRetornoModal;
import br.com.montreal.fx.controller.pattern.AbstractFxTabController;
import br.com.montreal.fx.controller.pattern.IModalCaller;
import br.com.montreal.fx.dto.ReportDto;
import br.com.montreal.negocio.reports.ReportBll;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class GraficoController extends AbstractFxTabController implements Initializable, IModalCaller {

	@FXML
	private PieChart grfPizza;
	
	@FXML
	private Button btnExecutarProgresso;
	
	@FXML
	private Button btnOk;
	
	@FXML
	private AnchorPane anpContauto;
	
	@FXML
	private VBox vbContauto;
	
	@FXML
	private ProgressBar pgExecucao;
	
	private PdfModalController pdfModalController;
	
	@FXML
	private void btnOkClick(ActionEvent event) {
		this.pdfModalController = (PdfModalController) this.showModal("pdfModalView.fxml", "Pdf");
		this.pdfModalController.setCaller(this);
		
		try {
			
			ReportDto reportDto = new ReportBll().gerarReport();			
			this.pdfModalController.apresentaPdf(reportDto);
			
		} catch (Exception e) {
			this.alertaErro("", "", "Erro no Jasper", e);
		}
	}
	
	@FXML
	private void btnExecutarProgressoClick(ActionEvent event) {
		
		for(int i = 1; i <= 100; i++) {
			
			for(int j = 1; j <= 9999; j++) {
				System.out.println("-->");
			}
			// double v = i / 100;
			this.pgExecucao.setProgress(i);
		}
		
		this.alertaSucesso("", "", "Execução Finalizada");
	}
	
	public void executeModalReturn(EnumRetornoModal retornoModal, String nomeModal) {

	}

	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<PieChart.Data> pieChartData =
				FXCollections.observableArrayList(
						new PieChart.Data("Grapefruit", 13),
						new PieChart.Data("Oranges", 25),
						new PieChart.Data("Plums", 10),
						new PieChart.Data("Pears", 22),
						new PieChart.Data("Apples", 100));
		grfPizza.setData(pieChartData);
		grfPizza.setTitle("Imported Fruits");
		
		this.btnOk.setDefaultButton(true);
	}

}
