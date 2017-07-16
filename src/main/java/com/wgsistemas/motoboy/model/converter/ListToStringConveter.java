package com.wgsistemas.motoboy.model.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.apache.commons.lang.StringUtils;

@Converter
public class ListToStringConveter implements AttributeConverter<List<String>, String> {
	@Override
	public String convertToDatabaseColumn(List<String> attribute) {
		if (attribute == null || attribute.isEmpty()) {
			return "";
		}
		return attribute.stream().filter(StringUtils::isNotBlank).collect(Collectors.joining(","));
	}

	@Override
	public List<String> convertToEntityAttribute(String dbData) {
		if (dbData == null || dbData.trim().length() == 0) {
			return new ArrayList<String>();
		}

		String[] data = dbData.split(",");
		return Arrays.asList(data);
	}
}