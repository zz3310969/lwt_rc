package com.bsc.util.db.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import org.hibernate.Session;

public interface Dao {
	/**
	 * 增
	 * @param entity
	 * @return 被持久化成功的实体(需要类型转换)，实体中已经产生了主键值
	 */
	public abstract Serializable save(Object entity);
	/**
	 * 删
	 * @param entity 待删除的实体，其实体中包含主键即可
	 */
	public abstract void delete(Object entity);
	/**
	 * 改
	 * @param entity 待更新的实体
	 */
	public abstract void update(Object entity);
	/**
	 * 查<br>
	 * @param hql 查询语句
	 * @return 查询结果
	 */
	public abstract <T> List<T> find(String hql); 
	/**
	 * 执行SQL查询<br>
	 * @param sql 查询语句
	 * @return 查询结果
	 */
	public abstract <T> List<T> executeSqlQuery(String sql);
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
	public abstract <T> T get(Class<T> cla,Serializable id);
	/**
	 * 查询并指定返回实体的最大数目
	 * @param hql 查询语句
	 * @param maxResults 记录最大返回数目
	 * @return 查询结果
	 */
//	public abstract <T> List<T> find(String hql,int maxResults);
	/**
	 * 查询一个对象
	 * @param hql 查询语句
	 * @return 查询结果
	 */
	public abstract <T> T findOne(String hql);
	/**
	 * 保存或修改实体
	 * @param entity
	 */
	public abstract void saveOrUpate(Object entity);
	/**
	 * 执行删除和修改hql语句<br>
	 * @param hql 更新语句
	 * @return 被更新的记录数
	 */
	public abstract int executeUpdate(String hql);
	
	/**
	 * 计算满足hql条件的记录数<br>
	 * @param hql 要执行的sql语句
	 * @return 满足hql语句的数据库记录数，
	 * */
	public abstract Integer findListCount(final String hql);
	
	/**
	 * 根据hql查询数据库，返回第offset条记录以后的数据记录，并且返回的记录最多为length
	 * @param hql 要执行的查询语句
	 * @param offset 起始记录数
	 * @param length 允许返回的最大记录数
	 * @return list 满足条件的记录
	 * */
	public abstract <T> List<T> findListForPage(final String hql,final int offset,final int length);
	/**
	 * 从数据库连接池获得JDBC连接
	 * @return conn 如果获取失败返回 null
	 * */
	public abstract Connection getJdbcConnection();
	/**
	 * 获得org.hibernate.Session实例<br>
	 * @return org.hibernate.Session实例
	 */
	public abstract Session getHibernateSession();
}
