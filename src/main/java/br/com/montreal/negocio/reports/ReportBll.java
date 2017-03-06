package br.com.montreal.negocio.reports;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import br.com.montreal.entidades.Cliente;
import br.com.montreal.fx.dto.ReportDto;
import br.com.montreal.negocio.ClienteBll;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportBll {

	
	public ReportDto gerarReport() throws Exception {

		try {
			ReportDto dto = new ReportDto();
			List<Cliente> lista = new ClienteBll().listar();

			String arquivoJasper = "/reports/exemplo.jasper";
			InputStream jasperFile = getClass().getResourceAsStream(arquivoJasper);

			Map parametros = new HashMap();
			JRBeanCollectionDataSource jrds = new JRBeanCollectionDataSource(lista);
			JasperPrint printer = JasperFillManager.fillReport(jasperFile, parametros, jrds);

			this.removeBlankPage(printer.getPages());

			byte[] reportPdf = JasperExportManager.exportReportToPdf(printer);
			
			int qtdPaginas = printer.getPages().size();
			List<javafx.scene.image.Image> imagesFx = new  ArrayList<javafx.scene.image.Image>();
			for (int i = 0; i < qtdPaginas; i++){
				java.awt.Image imagemReport = JasperPrintManager.printPageToImage(printer, i, 1);
				imagesFx.add(this.createImage(imagemReport));
			}
			
			dto.setReportPdf(reportPdf);
			dto.setReportImage(imagesFx);
			
			return dto;
		} catch (JRException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
	
	public byte[] gerarReportPdf() throws Exception {

		try {
			List<Cliente> lista = new ClienteBll().listar();

			String arquivoJasper = "/com/estudo/reports/exemplo.jasper";
			InputStream jasperFile = getClass().getResourceAsStream(arquivoJasper);

			Map parametros = new HashMap();
			JRBeanCollectionDataSource jrds = new JRBeanCollectionDataSource(lista);
			JasperPrint printer = JasperFillManager.fillReport(jasperFile, parametros, jrds);

			this.removeBlankPage(printer.getPages());

			byte[] reportPdf = JasperExportManager.exportReportToPdf(printer);

			return reportPdf;
		} catch (JRException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
	
	public List<javafx.scene.image.Image> gerarReportImagem() throws Exception {

		try {
			List<javafx.scene.image.Image> imagesFx = new  ArrayList<javafx.scene.image.Image>();
			
			List<Cliente> lista = new ClienteBll().listar();

			String arquivoJasper = "/com/estudo/reports/exemplo.jasper";
			InputStream jasperFile = getClass().getResourceAsStream(arquivoJasper);

			Map parametros = new HashMap();
			JRBeanCollectionDataSource jrds = new JRBeanCollectionDataSource(lista);
			JasperPrint printer = JasperFillManager.fillReport(jasperFile, parametros, jrds);

			this.removeBlankPage(printer.getPages());

			
			int qtdPaginas = printer.getPages().size();
			
			for (int i = 0; i < qtdPaginas; i++){
				java.awt.Image imagemReport = JasperPrintManager.printPageToImage(printer, i, 0);
				imagesFx.add(this.createImage(imagemReport));
			}

			return imagesFx;
		} catch (JRException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
	
	private javafx.scene.image.Image createImage(java.awt.Image image) throws IOException {
	    if (!(image instanceof RenderedImage)) {
	      BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	      Graphics g = bufferedImage.createGraphics();
	      g.drawImage(image, 0, 0, null);
	      g.dispose();
	      image = bufferedImage;
	    }
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ImageIO.write((RenderedImage) image, "png", out);
	    out.flush();
	    ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
	    return new javafx.scene.image.Image(in);
	  }
	
	

	private void removeBlankPage(List<JRPrintPage> pages) {

		for (Iterator<JRPrintPage> i = pages.iterator(); i.hasNext();) {
			JRPrintPage page = i.next();
			if (page.getElements().size() == 0)
				i.remove();
		}
	}

}
