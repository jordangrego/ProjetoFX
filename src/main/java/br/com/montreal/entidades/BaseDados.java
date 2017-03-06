package br.com.montreal.entidades;

import java.util.ArrayList;
import java.util.List;

public class BaseDados {
	private List<Pedido> listaPedido;
	private List<Cliente> listaClientes;
	private List<TipoProduto> listaTipoProduto;
	
	public BaseDados(){
		this.listaPedido = new ArrayList<Pedido>();
		this.listaClientes = new ArrayList<Cliente>();
		this.listaTipoProduto = new ArrayList<TipoProduto>();
	}

	public List<Pedido> getListaPedido() {
		return listaPedido;
	}

	public void setListaPedido(List<Pedido> listaPedido) {
		this.listaPedido = listaPedido;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<TipoProduto> getListaTipoProduto() {
		return listaTipoProduto;
	}

	public void setListaTipoProduto(List<TipoProduto> listaTipoProduto) {
		this.listaTipoProduto = listaTipoProduto;
	}
}
