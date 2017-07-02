package com.wgsistemas.motoboy.model.converter;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanConverter implements AttributeConverter<Boolean, Integer> {
	@Override
	public Integer convertToDatabaseColumn(Boolean aBoolean) {
		if (TRUE.equals(aBoolean)) {
			return 1;
		} else {
			return -1;
		}
	}
	@Override
	public Boolean convertToEntityAttribute(Integer value) {
		if (value == null) {
			return FALSE;
		} else {
			if (value == 1) {
				return TRUE;
			} else {
				return FALSE;
			}
		}
	}
}