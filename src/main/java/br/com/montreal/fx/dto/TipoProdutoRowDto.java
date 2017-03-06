package br.com.montreal.fx.dto;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TipoProdutoRowDto {
	private SimpleIntegerProperty  codTipoProduto;
	private SimpleStringProperty  nomeTipoProduto;
	
	public TipoProdutoRowDto() {
		this.codTipoProduto = new SimpleIntegerProperty();
		this.nomeTipoProduto = new SimpleStringProperty();
	}

	public Integer getCodTipoProduto() {
		return codTipoProduto.get();
	}

	public void setCodTipoProduto(Integer codTipoProduto) {
		this.codTipoProduto.set(codTipoProduto);
	}

	public String getNomeTipoProduto() {
		return nomeTipoProduto.get();
	}

	public void setNomeTipoProduto(String nomeTipoProduto) {
		this.nomeTipoProduto.set(nomeTipoProduto);
	}
}
