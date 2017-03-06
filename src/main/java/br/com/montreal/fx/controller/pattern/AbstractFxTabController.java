package br.com.montreal.fx.controller.pattern;

import java.io.IOException;

import javafx.scene.control.Tab;

public abstract class AbstractFxTabController extends AbstractFxController {
	private ITabPaneController tabCaller;
	private Tab tab;
	
	public ITabPaneController getTabCaller() {
		return tabCaller;
	}
	
	public void setTabCaller(ITabPaneController tabCaller) {
		this.tabCaller = tabCaller;
	}
	
	public Tab getTab() {
		return tab;
	}

	public void setTab(Tab tab) {
		this.tab = tab;
	}
	
	protected void fecharEstaAba() {
		this.getTabCaller().fecharAba(this.tab);
	}
	
	protected AbstractFxTabController abrirNovaAba(String view, String titulo) {
		try {
			return this.getTabCaller().geraNovaAba(view, titulo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.alertaErro("", "Erro ao abrir a Aba:" + view, "Erro ao abrir a aba.", e);
		}
		
		return null;
	}
}
