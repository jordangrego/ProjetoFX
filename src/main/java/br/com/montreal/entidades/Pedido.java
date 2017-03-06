package br.com.montreal.entidades;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private Cliente cliente;
	private List<Produto> listaProdutos;
	
	public Pedido(){
		this.listaProdutos = new ArrayList<Produto>();
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
}
