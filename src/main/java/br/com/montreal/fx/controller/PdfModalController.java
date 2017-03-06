package br.com.montreal.fx.controller;

import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.Sides;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import br.com.montreal.fx.controller.pattern.AbstractFxModalController;
import br.com.montreal.fx.dto.ReportDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;

public class PdfModalController extends AbstractFxModalController implements Initializable {

	private ReportDto reportDto;

	@FXML
	private Button btnAnterior;

	@FXML
	private Button btnProxima;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnImprimir;

	@FXML
	private ComboBox cbPaginas;

	@FXML
	private Label lblTotalPaginas;

	@FXML
	private ImageView imgPage;

	@FXML
	private void btnAnteriorClick(ActionEvent event) {
		int pageIndex = (int) cbPaginas.getSelectionModel().getSelectedItem();
		if (pageIndex > 1 && pageIndex <= this.reportDto.getReportImage().size()) {
			pageIndex--;
			this.cbPaginas.setValue(pageIndex);
			this.setPaginaAtual(pageIndex);
		}
	}

	@FXML
	private void btnProximaClick(ActionEvent event) {
		int pageIndex = (int) cbPaginas.getSelectionModel().getSelectedItem();
		if (pageIndex > 0 && pageIndex <= (this.reportDto.getReportImage().size() - 1)) {
			pageIndex++;
			this.cbPaginas.setValue(pageIndex);
			this.setPaginaAtual(pageIndex);
		}
	}

	@FXML
	private void btnSalvarClick(ActionEvent event) {

		try {

			DirectoryChooser directoryChooser = new DirectoryChooser();
			File selectedDirectory = directoryChooser.showDialog(imgPage.getScene().getWindow());

			Date agora = new Date();
			String arquivo = "Export_" + agora.getDay() + agora.getMonth() + agora.getYear() + agora.getHours()
					+ agora.getMinutes() + agora.getSeconds() + ".pdf";

			byte[] buffer = null;
			File targetFile = new File(selectedDirectory.getAbsolutePath() + "\\" + arquivo);
			/*
			if (this.reportDto.getReportPdf() != null && !PDFfile.equals("")) {
				FileInputStream fis = new FileInputStream(PDFfile);
				buffer = new byte[fis.available()];
				fis.read(buffer);
				fis.close();
			} else if (this.reportDto.getReportPdf() != null && this.reportDto.getReportPdf().length > 0) {
			}
			*/
			buffer = this.reportDto.getReportPdf();

			OutputStream outStream = new FileOutputStream(targetFile);
			outStream.write(buffer);
			outStream.close();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("");
			alert.setHeaderText("");
			alert.setContentText("Arquivo exportado: " + selectedDirectory.getAbsolutePath() + "\\" + arquivo);
			alert.showAndWait();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void btnImprimirClick(ActionEvent event) {
		try {
			
			PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			pras.add(OrientationRequested.PORTRAIT);
			pras.add(new Copies(1));
			pras.add(Sides.ONE_SIDED);

			PrintService myPrintService = PrintServiceLookup.lookupDefaultPrintService();

			PDDocument pdf = null;
			/*
			if (PDFfile != null && !PDFfile.equals("")) {
				FileInputStream fis = new FileInputStream(PDFfile);
				pdf = PDDocument.load(fis);
				fis.close();
			} else if (PDFfileData != null && PDFfileData.length > 0) {
			}
			*/
			
			pdf = PDDocument.load(this.reportDto.getReportPdf());

			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPrintService(myPrintService);
			job.setPageable(new PDFPageable(pdf));
			job.print(pras);
			pdf.close();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("");
			alert.setHeaderText("");
			alert.setContentText("Arquivo enviado para impressora.");
			alert.showAndWait();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void cbPaginasSelectedItemChanged(ActionEvent event) {
		int pageIndex = (int) cbPaginas.getSelectionModel().getSelectedItem();
		this.setPaginaAtual(pageIndex);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void apresentaPdf(ReportDto dto) {
		try {
			this.reportDto = dto;
			this.carregaDados();
		} catch (Exception ex) {
			this.alertaErro("", "", "Erro", ex);
		}
	}

	private void setConteudoPaginas() {
		int i = 0;
		for (Image imagem : this.reportDto.getReportImage()) {
			i++;
			this.cbPaginas.getItems().add(i);
		}

		this.cbPaginas.setValue(1);
	}

	@Override
	protected String getNomeModal() {
		return "PdfModalController";
	}

	public ReportDto getReportDto() {
		return reportDto;
	}

	public void setReportDto(ReportDto reportDto) {
		this.reportDto = reportDto;
	}

	private void carregaDados() {
		this.lblTotalPaginas.setText(" / " + this.reportDto.getReportImage().size());
		this.setConteudoPaginas();
		this.setPaginaAtual(1);
	}

	public void setPaginaAtual(int pageIndex) {
		if (pageIndex > 0 && pageIndex <= this.reportDto.getReportImage().size()) {
			imgPage.setImage(this.reportDto.getReportImage().get(pageIndex - 1));
		}
	}
}
