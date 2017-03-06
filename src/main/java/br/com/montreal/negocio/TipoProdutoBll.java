package br.com.montreal.negocio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.montreal.entidades.BaseDados;
import br.com.montreal.entidades.TipoProduto;
import br.com.montreal.util.UtilBaseDados;

public class TipoProdutoBll {
	public List<TipoProduto> listar() throws IOException {
		BaseDados base = UtilBaseDados.recuperaBaseDados();
		List<TipoProduto> listaClientes = base.getListaTipoProduto();
		return listaClientes;
	}
	
	public void incluir(TipoProduto tipoProduto) throws IOException {
		BaseDados base = UtilBaseDados.recuperaBaseDados();
		tipoProduto.setCodTipoProduto((UtilBaseDados.getMaxCodTipoProduto() + 1));
		
		if (base.getListaTipoProduto() == null) {
			base.setListaTipoProduto(new ArrayList<TipoProduto>());
		}
		
		base.getListaTipoProduto().add(tipoProduto);
		UtilBaseDados.gravaBaseDados(base);
	}
	
	public void atualiza(TipoProduto tipoProduto) throws IOException {
		BaseDados base = UtilBaseDados.recuperaBaseDados();
		//update
		for(TipoProduto tipo : base.getListaTipoProduto()) {
			if (tipo.getCodTipoProduto() == tipoProduto.getCodTipoProduto()) {
				tipo.setNomeTipoProduto(tipoProduto.getNomeTipoProduto());
			}
		}
		
		UtilBaseDados.gravaBaseDados(base);
	}
	
	public void excluir(int codTipoProduto) throws IOException {
		BaseDados base = UtilBaseDados.recuperaBaseDados();
		
		int indexTipoProduto = 0;
		for (int i = base.getListaClientes().size() - 1; i != 0; i--) {
			if (base.getListaTipoProduto().get(i).getCodTipoProduto() == codTipoProduto) {
				indexTipoProduto = i;
			}
		}
		
		base.getListaTipoProduto().remove(indexTipoProduto);
		
		UtilBaseDados.gravaBaseDados(base);
	}

	public void salvar(TipoProduto tipoProduto) throws IOException {
		if (tipoProduto.getCodTipoProduto() == 0) {
			this.incluir(tipoProduto);
		}
		else{
			this.atualiza(tipoProduto);
		}
	}
}
