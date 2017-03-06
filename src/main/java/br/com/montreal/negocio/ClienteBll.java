package br.com.montreal.negocio;

import java.io.IOException;
import java.util.List;

import br.com.montreal.entidades.BaseDados;
import br.com.montreal.entidades.Cliente;
import br.com.montreal.util.UtilBaseDados;

public class ClienteBll {
	
	public List<Cliente> listar() throws IOException {
		BaseDados base = UtilBaseDados.recuperaBaseDados();
		List<Cliente> listaClientes = base.getListaClientes();
		return listaClientes;
	}
	
	public void incluir(Cliente cliente) throws IOException {
		BaseDados base = UtilBaseDados.recuperaBaseDados();
		cliente.setCodCliente((UtilBaseDados.getMaxCodUsuario() + 1));
		base.getListaClientes().add(cliente);
		UtilBaseDados.gravaBaseDados(base);
	}
	
	public void atualiza(Cliente cliente) throws IOException {
		BaseDados base = UtilBaseDados.recuperaBaseDados();
		//update
		for(Cliente cli : base.getListaClientes()) {
			if (cli.getCodCliente() == cliente.getCodCliente()) {
				cli.setNomeRazaoSocial(cliente.getNomeRazaoSocial());
				cli.setCpfCnpj(cliente.getCpfCnpj());
			}
		}
		
		UtilBaseDados.gravaBaseDados(base);
	}
	
	public void excluir(int codCliente) throws IOException {
		BaseDados base = UtilBaseDados.recuperaBaseDados();
		
		int indexCliente = 0;
		for (int i = base.getListaClientes().size() - 1; i != 0; i--) {
			if (base.getListaClientes().get(i).getCodCliente() == codCliente) {
				indexCliente = i;
			}
		}
		
		base.getListaClientes().remove(indexCliente);
		
		UtilBaseDados.gravaBaseDados(base);
	}

	public void salvar(Cliente cliente) throws IOException {
		if (cliente.getCodCliente() == 0) {
			this.incluir(cliente);
		}
		else{
			this.atualiza(cliente);
		}
	}
}
