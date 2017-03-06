package br.com.montreal.fx.support;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import br.com.montreal.fx.dto.ComboItemDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ComboSupport {
	public static <T> ObservableList<ComboItemDto> geraComboDataSource(List<T> lista, String displayText, String idValue) throws NoSuchFieldException, SecurityException, NumberFormatException, IllegalArgumentException, IllegalAccessException {
		List<ComboItemDto> listaComboItem = new ArrayList<ComboItemDto>();
		
		for(Object item : lista) {
			ComboItemDto dto = new ComboItemDto();
			Field fieldDisplayText = item.getClass().getDeclaredField(displayText);			
			Field fieldIdValue = item.getClass().getDeclaredField(idValue);
			fieldDisplayText.setAccessible(true);
			fieldIdValue.setAccessible(true);
			
			dto.setDisplayText(fieldDisplayText.get(item).toString());
			dto.setIdValue(Long.parseLong(fieldIdValue.get(item).toString()));
			
			listaComboItem.add(dto);
		}
		
		ObservableList<ComboItemDto> listaComboItemObervable = FXCollections.observableArrayList(listaComboItem);
		
		return listaComboItemObervable;
	}
}
