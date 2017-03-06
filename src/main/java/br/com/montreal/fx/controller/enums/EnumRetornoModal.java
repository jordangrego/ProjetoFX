package br.com.montreal.fx.controller.enums;

public enum EnumRetornoModal {
	OK(1), 
	CANCELAR(2), 
	SIM(3), 
	NAO(4);
	
	private final int valor;
	EnumRetornoModal(int valorOpcao){
		valor = valorOpcao;
	}
	public int getValor(){
		return valor;
	}
}
