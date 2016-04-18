package com.bsc.util.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bsc.util.db.tx.AtomOperation;
import com.bsc.util.db.tx.JdbcTransactionWrapper;
import com.bsc.util.db.tx.TransactionWrapper;

/**
 * 使用Hibernate操作数据库的工具类
 * @author  韩进城
 * @version  1.6<br>
 * Copyright (C), 2003-2008, 北京信息管理科学研究所<br>
 * This program is protected by copyright laws.<br>
 * Date: 2009-9-9
 */
@SuppressWarnings("unchecked")
public class Dao
{
	
	private static SessionFactory sessionFactory;
	private static DataSource dataSource;
	private static Logger log = Logger.getLogger(Dao.class);
	
	/**
	 * 构造方法
	 */
	public Dao(SessionFactory sessionFactory, DataSource dataSource) {
		Dao.sessionFactory = sessionFactory;
		Dao.dataSource = dataSource;
	}
	
	/**
	 * 增
	 * @param entity
	 * @return 被持久化成功的实体(需要类型转换)，实体中已经产生了主键值
	 */
	public static Serializable save(Object entity) {
		Session session = null;
		Transaction tx = null;
		Serializable result = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			result = session.save(entity);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.error(e);
		} finally {
			session.close();
		}
		return result;
	}
	
	/**
	 * 删
	 * @param entity 待删除的实体，其实体中包含主键即可
	 */
	public static void delete(Object entity) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.delete(entity);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.error(e);
		} finally {
			session.close();
		}
	}
	
	/**
	 * 改
	 * @param entity 待更新的实体
	 */
	public static void update(Object entity) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(entity);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.error(e);
		} finally {
			session.close();
		}
	}
	
	/**
	 * 查<br>
	 * 示例:<br>
	 * Dao.findOne("from Homepage h order by h.date desc");<br>
	 * 联接查询示例:<br>
	 * Dao.findOne("select new Student(id, name) from Student s inner join Course c where s.id = c.studentId");   注:本联接查询并未测试过<br>
	 * Dao.findOne("select new Family(mother, mate, offspr) from DomesticCat as mother join mother.mate as mate left join mother.kittens as offspr"); 注：本联接查询来自Hibernate文档
	 * @param hql 查询语句
	 * @return 查询结果
	 */
	public static <T> List<T> find(String hql) {
		Session session = null;
		Transaction tx = null;
		List<T> result = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			result = session.createQuery(hql).list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.error(e);
		} finally {
			session.close();
		}
		
		return result;
	}
	
	/**
	 * 查询并指定返回实体的最大数目
	 * @param hql 查询语句
	 * @param maxResults 记录最大返回数目
	 * @return 查询结果
	 */
	public static <T> List<T> find(String hql, int maxResults) {
		Session session = null;
		Transaction tx = null;
		List<T> result = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			result = session.createQuery(hql).setMaxResults(maxResults).list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.error(e);
		} finally {
			session.close();
		}
		
		return result;
	}
	
	/**
	 * 查询一个对象
	 * @param hql 查询语句
	 * @return 查询结果
	 */
	public static <T> T findOne(String hql) {
		List<T> result = find(hql, 1);
		if (result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}
	
	/**
	 * Return the persistent instance of the given entity class</br> 
	 * with the given identifier, or null if there is no such</br>
	 * persistent instance. (If the instance, or a proxy for</br>
	 * the instance, is already associated with the session,</br>
	 * return that instance or proxy.)
	 * @param clazz - a persistent class
	 * @param id - an identifier
	 * @return a persistent instance or null
	 */
	public static <T> T get(Class<T> clazz, Serializable id) {
		Session session = null;
		Transaction tx = null;
		T result = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			result = (T)session.get(clazz, id);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.error(e);
		} finally {
			session.close();
		}
		return result;
	}
	
	/**
	 * 保存或修改实体
	 * @param entity
	 */
	public static void saveOrUpdate(Object entity) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(entity);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.error(e);
		} finally {
			session.close();
		}
	}
	
	/**
	 * 批量删除和批量更新<br>
	 * 示例：<br>
	 * Dao.executeUpdate("delete Log where id in (1, 2, 3, 8, 100)");<br>
	 * 注意:<br>
	 * 语句中不能包含as和别名如："delete Log as l where l.id in (1, 2, 3, 8, 100)";
	 * @param hql 更新语句
	 * @return 被更新的记录数
	 */
	public static int executeUpdate(String hql) {
		Session session = null;
		Transaction tx = null;
		int result = 0;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			result = session.createQuery(hql).executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.error(e);
		} finally {
			session.close();
		}
		return result;
	}
	
	/**
	 * 执行SQL更新<br>
	 * 示例：<br>
	 * Dao.executeSqlUpdate("insert into role_right(role_id, right_id)values(16, 88)");
	 * Dao.executeSqlUpdate("update user set name='zhanbo' where name='goodman'");<br>
	 * 例如：删除一条记录并需要得知是否删除成功(Dao.delete(Object)返回为void所以无法得知操作结果)
	 * int result = Dao.executeSqlUpdate("delete from user where id=0");
	 * result > 0 则删除成功
	 * 注:<br>
	 * 此方法可以执行纯粹的sql语句，为某些情况下更新与删除操作提供了便利，特别是往多对多关联表插入数据
	 * @param sql 更新或删除sql语句
	 * @return 被更新或删除的记录数
	 */
	@Deprecated public static int executeSqlUpdate_old_hibernate_version(String sql) {
		Session session = null;
		Transaction tx = null;
		int result = 0;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			result = session.createSQLQuery(sql).executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.error(e);
		} finally {
			session.close();
		}
		return result;
	}
	
	/**
	 * 执行SQL更新<br>
	 * 示例：<br>
	 * Dao.executeSqlUpdate("insert into role_right(role_id, right_id)values(16, 88)");
	 * Dao.executeSqlUpdate("update user set name='zhanbo' where name='goodman'");<br>
	 * 例如：删除一条记录并需要得知是否删除成功(Dao.delete(Object)返回为void所以无法得知操作结果)
	 * int result = Dao.executeSqlUpdate("delete from user where id=0");
	 * result > 0 则删除成功
	 * 注:<br>
	 * 此方法可以执行纯粹的sql语句，为某些情况下更新与删除操作提供了便利，特别是往多对多关联表插入数据
	 * @param sql 更新或删除sql语句
	 * @return 被更新或删除的记录数
	 */
	public static int executeSqlUpdate(String sql) {
		Connection conn = null;
		Statement st = null;
		int result = 0;
		try {
			conn = getJdbcConnection();
			conn.setAutoCommit(false);
			st = conn.createStatement();
			result = st.executeUpdate(sql);
			conn.commit();
		} catch (Exception e) {
			try {conn.rollback(); } catch (Exception e1) {log.error(e1); }
			log.error(e);
		} finally {
			if (conn != null) {
				try {conn.setAutoCommit(true); } catch (Exception e) {log.error(e); }
				try {st.close(); } catch (Exception e) {log.error(e); }
				try {conn.close(); } catch (Exception e) {log.error(e); }
			}
			else {
				log.error("获取Jdbc连接失败");
			}
		}
		return result;
	}
	
	/**
	 * 执行SQL查询<br>
	 * 示例：<br>
	 * Dao.executeSqlQuery("select * from user where age>20");<br>
	 * 注:<br>
	 * 可以利用调试查看结果返回的类型，以便转换查询结果的数据类型
	 * @param sql 查询语句
	 * @return 查询结果
	 */
	@Deprecated public static <T> List<T> executeSqlQuery_old_hibernate_version(String sql) {
		Session session = null;
		Transaction tx = null;
		List<T> result = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			result = session.createSQLQuery(sql).list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.error(e);
		} finally {
			session.close();
		}
		
		return result;
	}
	
	/**
	 * 执行SQL查询<br>
	 * 示例：<br>
	 * Dao.executeSqlQuery("select * from user where age>20");<br>
	 * 注:<br>
	 * 可以利用调试查看结果返回的类型，以便转换查询结果的数据类型
	 * @param sql 查询语句
	 * @return 查询结果
	 */
	public static <T> List<T> executeSqlQuery(String sql) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List result = new ArrayList();
		try {
			conn = getJdbcConnection();
			conn.setAutoCommit(false);
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			int colAmount = rs.getMetaData().getColumnCount();
			
			if (colAmount > 1) {
				while (rs.next()) {
					Object[] temp = new Object[colAmount];
					for (int i=0; i<colAmount; i++)
						temp[i] = rs.getObject(i + 1);
					result.add(temp);
				}
			}
			else if(colAmount == 1) {
				while (rs.next()) {
					result.add(rs.getObject(1));
				}
			}
			
			conn.commit();
		} catch (Exception e) {
			try {conn.rollback(); } catch (Exception e1) {log.error(e1); }
			log.error(e);
		} finally {
			if (conn != null) {
				try {conn.setAutoCommit(true); } catch (Exception e) {log.error(e); }
				try {rs.close(); } catch (Exception e) {log.error(e); }
				try {st.close(); } catch (Exception e) {log.error(e); }
				try {conn.close(); } catch (Exception e) {log.error(e); }
			}
			else {
				log.error("获取Jdbc连接失败");
			}
		}
		
		return result;
	}
	
	/**
	 * 获得org.hibernate.Session实例<br>
	 * 当Dao类中现有方法无法满足需求时可以获得Session以获得更大地自由度<br>
	 * 当需要在多个数据库操作序列中使用事务处理时，必须调用此方法获得Session，再开启事务<br>
	 * 警告：调用本方法获得到的Session必须自行进行关闭，否则将造成数据库连接不能及时回收
	 * @return org.hibernate.Session实例
	 */
	public static Session getHibernateSession() {
		return sessionFactory.openSession();
	}
	
	/**
	 * 执行批量查询，目的是为了提高多次查询的效率<br>
	 * 示例：<br>
	 * String[] hqls = {"from User where id > 10",
	 *                  "from Student where id < 100"}<br>
	 * List[] results = {lista, listb};<br>
	 * bolean allSuccess = Dao.doBatchQuery(hqls, results);<br>
	 * List users = results[0];<br>
	 * List students = results[1]; <br>
	 * 说明：如果某一个查询语句执行发生异常，不影响其它查询的结果
	 * @param hqls 待执行的查询语句数组
	 * @param results 查询成功后返回的查询结果
	 * @return true 全部操作都成功执行，否则返回false
	 */
	public static boolean doBatchQuery(String[] hqls, List[] results) {
		if (hqls == null || hqls.length <= 0 || results == null || results.length != hqls.length) {
			results = null;
			return false;
		}
		
		boolean allSuccess = true;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		for (int i=0; i<hqls.length; i++)
			try {
				results[i] = session.createQuery(hqls[i]).list();
			} catch (Exception e) {
				allSuccess = false;
				log.error(e);
			}
			
		tx.commit();
		session.close();
		return allSuccess;
	}
	
	/**
	 * 在事务中批量执行数据库操作<br>
	 * @param operations 待执行的数据库原子操作列表
	 * @return true 全部操作都成功执行，否则返回false并且所有已执行成功的操作全部回滚
	 */
	public static boolean doInTransaction(List<AtomOperation> operations) {
		if (operations == null || operations.size() <= 0)
			return false;
		
		boolean allSuccess = true;
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			int operationAmount = operations.size();
			for (int i=0; i<operationAmount && allSuccess == true; i++)
				allSuccess = operations.get(i).execute(session);
			
			if (allSuccess == true)
				tx.commit();
			else
				tx.rollback();
		} catch (Exception e) {
			allSuccess = false;
			tx.rollback();
			log.error(e);
		} finally {
			session.close();
		}
		return allSuccess;
	}
	
	/**
	 * 在事务中批量执行数据库操作（警告：尚未测试）<br>
	 * @param transactionWrapper 事务操作包装类
	 * @return true 全部操作都成功执行，否则返回false并且所有已执行成功的操作全部回滚
	 */
	public static boolean doInTransaction(TransactionWrapper transactionWrapper) {
		boolean allSuccess = true;
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			allSuccess = transactionWrapper.execute(session);
			
			if (allSuccess == true)
				tx.commit();
			else
				tx.rollback();
		} catch (Exception e) {
			allSuccess = false;
			tx.rollback();
			log.error(e);
		} finally {
			session.close();
		}
		return allSuccess;
	}
	
	/**
	 * 在事务中批量执行Jdbc数据库操作（警告：尚未测试）<br>
	 * @param jdbcTransactionWrapper Jdbc事务操作包装类
	 * @return true 全部操作都成功执行，否则返回false并且所有已执行成功的操作全部回滚
	 */
	public static boolean doInJdbcTransaction(JdbcTransactionWrapper jdbcTransactionWrapper) {
		boolean allSuccess = false;	// doInTransaction()方法中也应改成boolean allSuccess = false;
		Connection conn = null;
		try {
			conn = getJdbcConnection();
			conn.setAutoCommit(false);
			allSuccess = jdbcTransactionWrapper.execute(conn);
			
			if (allSuccess == true)
				conn.commit();
			else
				conn.rollback();
		} catch (Exception e) {
			allSuccess = false;
			try {conn.rollback(); } catch (Exception e2) {log.error(e2); }
			log.error(e);
		} finally {
			if (conn != null) {
				try {conn.setAutoCommit(true); } catch (Exception e3) {log.error(e3); }
				try {conn.close(); } catch (Exception e4) {log.error(e4); }
			}
		}
		return allSuccess;
	}
	
	/**
	 * @param entity
	 */
	public static void merge(Object entity){		
		Session session = sessionFactory.openSession();						
		Transaction tx = null;		
		try {
			tx = session.beginTransaction();
			session.merge(entity);
			session.flush();
			session.clear();
			tx.commit();
		} catch (Exception e) {
			if(tx != null)tx.rollback();	
			log.error(e);
		}
		finally{
			if(session!=null)session.close();
		}
	}
 
	
	
	
	/**
	 * 获得JDBC连接
	 * @return JDBC Connection
	 */
//	public static Session getJdbcConnection() {
//		return null;	// Spring DataSource 中获取JDBC连接
//	}
	
	/**
	 * 获取java.sql.Connection
	 * hibernate可以调用session.createSQLQuery(sql).list();来执行纯sql语句，<br>
	 * 但查出的结果有时不正确，所以当需要执行sql时使用jdbc直接查，故提供本方法
	 * 詹波添加于2010-03-11 
	 */
	public static Connection getJdbcConnection() {
		try {
			return dataSource.getConnection();
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}
	
	/**
	 * 测试入口
	 */
	public static void main(String[] args) {
		// String hql = "select new Users(username, password) from Users u";
//		String hql = "from CodeProvince";
//		List <CodeProvince> result = Dao.find(hql);
//		for (CodeProvince codeProvince:result) {
//			System.out.println(codeProvince.getProvinceName());
//		}
	}

}





