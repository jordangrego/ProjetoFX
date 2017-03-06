package br.com.montreal.fx.controller.pattern;

import br.com.montreal.fx.controller.enums.EnumRetornoModal;

public interface IModalCaller {
	void executeModalReturn(EnumRetornoModal retornoModal, String nomeModal);
}
