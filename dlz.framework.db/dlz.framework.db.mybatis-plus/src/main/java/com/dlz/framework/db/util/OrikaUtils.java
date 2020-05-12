package com.dlz.framework.db.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class OrikaUtils {

	private static MapperFacade MAPPER = null;

	public static MapperFacade getMapperFacade() {
		if (MAPPER == null) {
			MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
			MAPPER = mapperFactory.getMapperFacade();
		}
		return MAPPER;
	}

	public static <S, D> D map(S sourceObject, Class<D> destinationClass) {
		return getMapperFacade().map(sourceObject, destinationClass);
	}

	public static <S, D> D copy(S sourceObject, Class<D> destinationClass) {
		return getMapperFacade().map(sourceObject, destinationClass);
	}

	public static <S, D> void copy(S sourceObject, D destinationClass) {
		getMapperFacade().map(sourceObject, destinationClass);
	}
}
