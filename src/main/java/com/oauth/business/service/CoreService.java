package com.oauth.business.service;

import java.util.List;

public interface CoreService<T,I> {
	Boolean save(T object);

	Boolean saveAll(List<T> objectList);

	List<T> findAll();
	T findById(I id);

}