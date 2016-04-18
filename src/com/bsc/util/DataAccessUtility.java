package com.bsc.util;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class DataAccessUtility extends HibernateDaoSupport {

	public <T> boolean insert(T item) throws Exception {
		HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(item);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null)
				transaction.rollback();
			throw ex;
		} finally {
			if (session != null)
				session.close();
		}

		return true;
	}

	public <T> boolean delete(T item) throws Exception {
		HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();
		try {
			session.delete(item);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null)
				transaction.rollback();
			throw ex;
		} finally {
			if (session != null)
				session.close();
		}
		return true;
	}

	public <T> boolean update(T item) throws Exception {
		this.getHibernateTemplate().update(item);

		return true;
	}

	@SuppressWarnings("unchecked")
	public <T> T getModel(Class<?> clazz, Serializable id) throws Exception {
		Object obj = this.getHibernateTemplate().get(clazz, id);
		return (T) obj;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getList(QueryTerms queryTerms) throws Exception {
		Class<?> persistentClass = queryTerms.getPersistentClass();
		List<Order> orderList = queryTerms.getOrderList();
		List<CriterionAndOrder> criterionAndOrderList = queryTerms
				.getCriterionAndOrderList();
		ProjectionList pList = queryTerms.getProjectionList();
		HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();
		try {
			Criteria crit = null;
			// 第二种查询方式，涉及多表连接查询，及根据不同表中的不同字段排序
			if (criterionAndOrderList.size() != 0) {
				for (int i = 0; i < criterionAndOrderList.size(); i++) {
					if (criterionAndOrderList.get(i).getCriteriaAsName() == null) {
						if (i == 0)
							crit = session.createCriteria(criterionAndOrderList
									.get(i).getCriteriaName());
						else
							crit = crit.createCriteria(criterionAndOrderList
									.get(i).getCriteriaName());
					} else {

						if (i == 0)
							crit = session.createCriteria(criterionAndOrderList
									.get(i).getCriteriaName(),
									criterionAndOrderList.get(i)
											.getCriteriaAsName());
						else
							crit = crit.createAlias(criterionAndOrderList
									.get(i).getCriteriaName(),
									criterionAndOrderList.get(i)
											.getCriteriaAsName(),
									CriteriaSpecification.LEFT_JOIN);
					}

					for (int j = 0; j < criterionAndOrderList.get(i)
							.getCriterionList().size(); j++)
						crit = crit.add(criterionAndOrderList.get(i)
								.getCriterionList().get(j));
					for (int k = 0; k < criterionAndOrderList.get(i)
							.getOrderList().size(); k++)
						crit = crit.addOrder(criterionAndOrderList.get(i)
								.getOrderList().get(k));
				}

			}
			// 第一种查询方式，最简单情况，只涉及单表查询
			else {
				crit = session.createCriteria(persistentClass);
				for (Criterion criterion : queryTerms.getCriterionList())
					crit.add(criterion);
				for (int i = 0; i < orderList.size(); i++) {
					crit.addOrder(orderList.get(i));
				}
			}

			if (pList != null)
				crit.setProjection(pList);
			// crit.setResultTransformer(Transformers.aliasToBean(User.class));
			// 设置要转换成的实体类
			if (criterionAndOrderList.size() != 0
					&& queryTerms.getPersistentClass() != Object.class)
				crit.setResultTransformer(Transformers.aliasToBean(queryTerms
						.getPersistentClass()));
			// 如果PageSize为0，则显示所有的数据
			if (queryTerms.getPageSize() > 0) {
				crit.setFirstResult(queryTerms.getPageIndex()
						* queryTerms.getPageSize());
				crit.setMaxResults(queryTerms.getPageSize());
			}

			List<T> list = crit.list();

			transaction.commit();
			return list;
		} catch (Exception ex) {
			transaction.rollback();
			throw ex;
		} finally {
			if (session != null)
				session.close();
		}

	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getList(String sqlQueryString) throws Exception {
		HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();
		try {
			List<T> list = session.createSQLQuery(sqlQueryString).list();
			transaction.commit();
			return list;
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
	}

	public Long getTotalCount(QueryTerms queryTerms) throws Exception {

		List<CriterionAndOrder> criterionAndOrderList = queryTerms
				.getCriterionAndOrderList();
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();

		Transaction transaction = session.beginTransaction();
		try {
			Criteria crit = session.createCriteria(Object.class);
			// 多表连接查询的情况
			if (criterionAndOrderList.size() != 0) {
				for (int i = 0; i < criterionAndOrderList.size(); i++) {
					if (criterionAndOrderList.get(i).getCriteriaAsName() == null) {
						if (i == 0)
							crit = session.createCriteria(criterionAndOrderList
									.get(i).getCriteriaName());
						else
							crit = crit.createCriteria(criterionAndOrderList
									.get(i).getCriteriaName());
					} else {

						if (i == 0)
							crit = session.createCriteria(criterionAndOrderList
									.get(i).getCriteriaName(),
									criterionAndOrderList.get(i)
											.getCriteriaAsName());
						else
							crit = crit.createAlias(criterionAndOrderList
									.get(i).getCriteriaName(),
									criterionAndOrderList.get(i)
											.getCriteriaAsName(),
									criterionAndOrderList.get(i)
											.getCriteriaSpecification());
					}

					for (int j = 0; j < criterionAndOrderList.get(i)
							.getCriterionList().size(); j++)
						crit = crit.add(criterionAndOrderList.get(i)
								.getCriterionList().get(j));
					for (int k = 0; k < criterionAndOrderList.get(i)
							.getOrderList().size(); k++)
						crit = crit.addOrder(criterionAndOrderList.get(i)
								.getOrderList().get(k));
				}
			}

			// 第一种查询方式，最简单情况，只涉及单表查询
			else {
				crit = session.createCriteria(queryTerms.getPersistentClass());
				for (Criterion criterion : queryTerms.getCriterionList())
					crit.add(criterion);
			}
			// 获取记录总数
			Object o = crit.setProjection(Projections.rowCount())
					.uniqueResult();
			Long recordCount = Long.parseLong(o.toString());
			transaction.commit();
			return recordCount;
		} catch (Exception ex) {
			transaction.rollback();
			throw ex;
		} finally {
			if (session != null)
				session.close();
		}
	}

	public Object getTotalCount(String sqlQueryString) throws Exception {
		HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();
		try {
			Object result = session.createSQLQuery(sqlQueryString)
					.uniqueResult();
			transaction.commit();
			return result;
		} catch (Exception ex) {
			transaction.rollback();
			throw ex;
		} finally {
			if (session != null)
				session.close();
		}
	}

	public Object createSQLQuery(String sqlQueryString) throws Exception {
		HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();
		try {
			SQLQuery qry = session.createSQLQuery(sqlQueryString);
			Object result = qry.executeUpdate();
			transaction.commit();
			return result;
		} catch (Exception ex) {
			transaction.rollback();
			throw ex;
		} finally {
			if (session != null)
				session.close();
		}

	}

}
