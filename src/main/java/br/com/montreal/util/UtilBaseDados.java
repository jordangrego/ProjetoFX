package br.com.montreal.util;

import java.io.IOException;

import com.thoughtworks.xstream.XStream;

import br.com.montreal.entidades.BaseDados;
import br.com.montreal.entidades.Cliente;
import br.com.montreal.entidades.TipoProduto;

public class UtilBaseDados {
	public static BaseDados recuperaBaseDados() throws IOException {
		// String pathBaseDados = UtilComum.recuperarPropriedadeEstudoApp("pathBaseDados");
		String pathBaseDados = System.getProperty("user.dir") + "\\base.xml";
		String xmlArquivo = UtilComum.recuperarTextoArquivo(pathBaseDados);
		XStream xstream = new XStream();
		BaseDados baseDados = (BaseDados)xstream.fromXML(xmlArquivo); 
		return baseDados;
	}
	
	public static void gravaBaseDados(BaseDados baseDados) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("br.com.montreal.entidades.BaseDados", BaseDados.class); 
		String xml = xstream.toXML(baseDados); 
		// String pathBaseDados = UtilComum.recuperarPropriedadeEstudoApp("pathBaseDados");
		String pathBaseDados = System.getProperty("user.dir") + "\\base.xml";
		UtilComum.gravarArquivo(xml.getBytes(), pathBaseDados);
	}
	
	public static int getMaxCodUsuario() {
		BaseDados base = null;
		try {
			base = recuperaBaseDados();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int codUsuario = 0;
		for(Cliente cliente : base.getListaClientes()) {
			if (cliente.getCodCliente() > codUsuario) {
				codUsuario = cliente.getCodCliente();
			}
		}
		
		return codUsuario;
	}
	
	public static int getMaxCodTipoProduto() {
		BaseDados base = null;
		try {
			base = recuperaBaseDados();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int codTipoProduto = 0;
		if (base.getListaTipoProduto() != null) {			
			for(TipoProduto tipoProduto : base.getListaTipoProduto()) {
				if (tipoProduto.getCodTipoProduto() > codTipoProduto) {
					codTipoProduto = tipoProduto.getCodTipoProduto();
				}
			}
		}
		
		return codTipoProduto;
	}
}
