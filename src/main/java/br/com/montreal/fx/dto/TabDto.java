package br.com.montreal.fx.dto;

import br.com.montreal.fx.controller.pattern.AbstractFxController;
import javafx.scene.layout.AnchorPane;

public class TabDto {
	private AnchorPane anchorPane;
	private AbstractFxController abaController;

	public AnchorPane getAnchorPane() {
		return anchorPane;
	}

	public void setAnchorPane(AnchorPane anchorPane) {
		this.anchorPane = anchorPane;
	}

	public AbstractFxController getAbaController() {
		return abaController;
	}

	public void setAbaController(AbstractFxController abaController) {
		this.abaController = abaController;
	}

	
}
