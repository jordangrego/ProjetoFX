package br.com.montreal.fx.dto;

import java.util.List;

public class ReportDto {
	private List<javafx.scene.image.Image> reportImage;
	private byte[] reportPdf;
	
	public List<javafx.scene.image.Image> getReportImage() {
		return reportImage;
	}
	public void setReportImage(List<javafx.scene.image.Image> reportImage) {
		this.reportImage = reportImage;
	}
	public byte[] getReportPdf() {
		return reportPdf;
	}
	public void setReportPdf(byte[] reportPdf) {
		this.reportPdf = reportPdf;
	}
}
