package br.com.montreal.fx.controller.pattern;

import java.io.IOException;

import javafx.scene.control.Tab;

public interface ITabPaneController {
	void fecharAba(Tab tab);
	AbstractFxTabController geraNovaAba(String view, String titulo) throws IOException;
}
