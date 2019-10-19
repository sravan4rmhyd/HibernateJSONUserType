package org.examples;

import org.examples.domain.AppMode;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, AppMode>{

	@Override
	public AppMode convert(String source) {
		return Enum.valueOf(AppMode.class, source.toUpperCase());
	}

}
