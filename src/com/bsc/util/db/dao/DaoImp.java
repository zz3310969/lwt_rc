package com.bsc.util.db.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class DaoImp extends HibernateDaoSupport implements Dao {
	
	private static Logger log = Logger.getLogger(DaoImp.class);
	public Serializable save(Object entity) {
		return getHibernateTemplate().save(entity);
	}
	
	public void delete(Object entity) {
         getHibernateTemplate().delete(entity);
	}
	
	public void update(Object entity) {
		getHibernateTemplate().merge(entity);
	}
	
	public void saveOrUpate(Object entity) {

		getHibernateTemplate().saveOrUpdate(entity);
	}
	
	public int executeUpdate(String hql) {
		return getHibernateTemplate().bulkUpdate(hql);
	}
	
	public <T> List<T> find(String hql) {
		List<T> find = getHibernateTemplate().find(hql);
		return find;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> executeSqlQuery(String sql) {
		Connection conn = null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			conn = getJdbcConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			int colCount = rs.getMetaData().getColumnCount();
			if (colCount > 1) {
				while (rs.next()) {
					Object[] temp = new Object[colCount];
					for (int i=0; i<colCount; i++)
						temp[i] = rs.getObject(i + 1);
					list.add(temp);
				}
			}
			else if(colCount == 1) {
				while (rs.next()) {
					list.add(rs.getObject(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e);
		}finally{
			if(conn != null){
				if(rs !=null)try {rs.close();} catch (SQLException e) {log.error(e);}
				if(pstm !=null)try {pstm.close();} catch (SQLException e) {log.error(e);}
				try {conn.close();} catch (SQLException e) {log.error(e);}
			}
		}
		return list;
	}
	
	public <T> T findOne(String hql) {
		getHibernateTemplate().setMaxResults(1);
		List<T> list = getHibernateTemplate().find(hql);
        if(list.isEmpty()){
        	return null;
        }
		return list.get(0);
	}
	public <T> T get(Class<T> cla, Serializable id) {
		T entity = (T) getHibernateTemplate().get(cla, id);
		return entity;
	}

	public Integer findListCount(final String hql){
		Integer result = 0;
		result = (Integer)getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				return query.uniqueResult();
			}
			
		});
		return result;
	}
	
	public <T> List<T> findListForPage(final String hql,final int offset,final int length) {

		List list = getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult(offset);
				query.setMaxResults(length);
				return query.list();
			}
			
		});
		return list;
	}

	public Connection getJdbcConnection() {
		Connection conn = null;
		try {
			conn=DriverManager.getConnection("proxool.healthsys");
		} catch (SQLException e) {
			log.error(e);
		}
		return conn;
	}

	public Session getHibernateSession() {
		return getHibernateTemplate().getSessionFactory().openSession();
	}

	
	
	
	


	

}
