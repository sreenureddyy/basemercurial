package com.sree.base.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.sree.base.domain.User;

@Repository("baseDao")
@SuppressWarnings({ "unchecked" })
public class BaseDao implements IBaseDao {

	private static final Logger log = Logger.getLogger(BaseDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sree.base.dao.IBaseDao#getSession()
	 */
	@Override
	public Session getSession() {
		return (Session) entityManager.getDelegate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sree.base.dao.IBaseDao#save(java.lang.Object)
	 */
	@Override
	public void save(Object object) {
		getSession().saveOrUpdate(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sree.base.dao.IBaseDao#update(java.lang.Object)
	 */
	@Override
	public void update(Object object) {
		entityManager.merge(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sree.base.dao.IBaseDao#delete(java.lang.Object)
	 */
	@Override
	public void delete(Object object) {
		object = entityManager.merge(object);
		entityManager.remove(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sree.base.dao.IBaseDao#find(java.lang.String, java.lang.Object)
	 */
	@Override
	public List<?> find(String queryName, Object... objects) {
		if (queryName == null) {
			throw new IllegalArgumentException("queryName should not be null");
		}
		Query query = null;
		try {
			query = entityManager.createNamedQuery(queryName);

			if (objects != null && objects.length > 0) {
				for (int i = 0; i < objects.length; i++) {
					query.setParameter(i + 1, objects[i]);
				}
			}
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sree.base.dao.IBaseDao#find(java.lang.String)
	 */
	@Override
	public List<?> find(String queryName) {
		return find(queryName, new Object[] {});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sree.base.dao.IBaseDao#find(int, int, java.lang.String, boolean,
	 * java.lang.Class)
	 */
	@Override
	public List<User> find(int firstRow, int numberOfRows, String sortField,
			boolean descending, Class<?> clazz) {
		log.info("FirstRow :: " + firstRow + " NumberOfRows :: " + numberOfRows
				+ " SortField :: " + sortField + " Descending :: " + descending);
		Criteria criteria = getSession().createCriteria(clazz);
		criteria.setFirstResult(firstRow);
		criteria.setMaxResults(numberOfRows);
		if (descending)
			criteria.addOrder(Order.desc(sortField));
		else
			criteria.addOrder(Order.asc(sortField));

		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sree.base.dao.IBaseDao#find(java.lang.Class, java.lang.Long)
	 */
	@Override
	public Object find(Class<?> clazz, Long id) {
		return getSession().get(clazz, id);
	}

}
