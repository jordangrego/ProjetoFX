package br.com.montreal.fx.dto;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ClienteRowDto {
	private SimpleIntegerProperty  codCliente;
	private SimpleStringProperty  nomeRazaoSocial;
	private SimpleStringProperty  cpfCnpj;
	
	public ClienteRowDto() {
		codCliente = new SimpleIntegerProperty();
		nomeRazaoSocial = new SimpleStringProperty();
		cpfCnpj = new SimpleStringProperty();
	}
	
	public Integer getCodCliente() {
		return codCliente.get();
	}
	public void setCodCliente(Integer codCliente) {
		this.codCliente.set(codCliente);
	}
	public String getNomeRazaoSocial() {
		return nomeRazaoSocial.get();
	}
	public void setNomeRazaoSocial(String nomeRazaoSocial) {
		this.nomeRazaoSocial.set(nomeRazaoSocial);
	}
	public String getCpfCnpj() {
		return cpfCnpj.get();
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj.set(cpfCnpj);
	}
}
