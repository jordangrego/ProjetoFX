package br.com.montreal.fx.support;

import java.util.ArrayList;
import java.util.List;

import br.com.montreal.entidades.Cliente;
import br.com.montreal.entidades.TipoProduto;
import br.com.montreal.fx.dto.ClienteRowDto;
import br.com.montreal.fx.dto.TipoProdutoRowDto;

public class RowSupport {
	public static List<ClienteRowDto> gerarClienteDataSource(List<Cliente> lista) {
		List<ClienteRowDto> listaDto = new ArrayList<ClienteRowDto>();
		for (Cliente cliente : lista) {
			ClienteRowDto dto = new ClienteRowDto();
			dto.setCodCliente(cliente.getCodCliente());
			dto.setCpfCnpj((cliente.getCpfCnpj()));
			dto.setNomeRazaoSocial(cliente.getNomeRazaoSocial());
			listaDto.add(dto);
		}
		
		return listaDto;
	}
	
	public static List<TipoProdutoRowDto> gerarTipoProdutoDataSource(List<TipoProduto> lista) {
		List<TipoProdutoRowDto> listaDto = new ArrayList<TipoProdutoRowDto>();
		for (TipoProduto tipoProduto : lista) {
			TipoProdutoRowDto dto = new TipoProdutoRowDto();
			dto.setCodTipoProduto(tipoProduto.getCodTipoProduto());
			dto.setNomeTipoProduto(tipoProduto.getNomeTipoProduto());
			listaDto.add(dto);
		}
		
		return listaDto;
	}
}
