package com.sree.base.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sree.base.dao.IBaseDao;
import com.sree.base.domain.User;
import com.sree.common.exception.ApplicationServiceException;
import com.sree.common.exception.BaseException;
import com.sree.common.exception.DomainValidationException;
import com.sree.common.exception.IntegrationServiceException;


@Transactional(rollbackFor = { ApplicationServiceException.class,
		IntegrationServiceException.class, DomainValidationException.class,
		BaseException.class })
@Service("baseService")
@SuppressWarnings("rawtypes")
public class BaseService implements IBaseService {
	@Autowired
	private IBaseDao baseDao;

	public IBaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void delete(Object object) {
		baseDao.delete(object);
	}

	public void save(Object obj) {
		baseDao.save(obj);
	}

	public List find(String queryName, Object... objects) {
		return baseDao.find(queryName, objects);
	}

	public Session getSession() {
		return baseDao.getSession();
	}

	@Override
	public List<User> find(int firstRow, int numberOfRows, String sortField,
			boolean descending, Class clazz) {
		return baseDao.find(firstRow, numberOfRows, sortField, descending, clazz);
	}

	@Override
	public Object find(Class clazz, Long id) {
		return baseDao.find(clazz, id);
	}
}
