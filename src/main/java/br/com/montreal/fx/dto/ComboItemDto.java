package br.com.montreal.fx.dto;

public class ComboItemDto {
	
	private String displayText;
	private long idValue;

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public long getIdValue() {
		return idValue;
	}

	public void setIdValue(long idValue) {
		this.idValue = idValue;
	}

	@Override
	public String toString() {
		return this.displayText;
	}
}
