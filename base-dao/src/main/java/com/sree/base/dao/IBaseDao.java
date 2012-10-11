package com.sree.base.dao;

import java.util.List;

import org.hibernate.Session;

import com.sree.base.domain.User;

public interface IBaseDao {

	public abstract Session getSession();

	public abstract void save(Object object);

	public abstract void update(Object object);

	public abstract void delete(Object object);

	public abstract List<?> find(String queryName, Object... objects);

	public abstract List<?> find(String queryName);

	public abstract List<User> find(int firstRow, int numberOfRows,
			String sortField, boolean descending, Class<?> clazz);

	public abstract Object find(Class<?> clazz, Long id);

}