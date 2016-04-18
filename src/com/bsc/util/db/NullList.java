package com.bsc.util.db;

import java.util.List;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Collection;
import org.apache.log4j.Logger;

/**
 * 空对象单例组合模式NullList实现List接口
 * <p>
 * Dao查询返回null时，将其返回NullList，避免for each 循环时判断空指针，减少代码量
 * @author  詹波
 * @version  1.6<br>
 * Copyright (C), 2003-2008, 北京信息管理科学研究所<br>
 * This program is protected by copyright laws.<br>
 * Date: 2009-10-18
 */
public class NullList<E> implements List<E>
{
	/**
	 * 日志
	 */
	private static final Logger log = Logger.getLogger(NullList.class);
	
	/**
	 * 空对象单例组合模式
	 */
	@SuppressWarnings("unchecked")
	private static final NullList instance = new NullList();
	
	/**
	 * 空对象模式Iterator对象
	 */
	private final NullIterator<E> nullIterator = new NullIterator<E>();
	
	/**
	 * 提示信息
	 */
	private static final String message = "空对象模式中未实现该方法,不能调用";
	
	/**
	 * 获取NullList实例
	 * @param <T> 泛型
	 * @return　NulList实例
	 */
	@SuppressWarnings("unchecked")
	public static <T> NullList<T> getInstance() {
		return instance;
	}
	
	/**
	 * 禁止new NullList()
	 */
	private NullList() {
		
	}
	
	/**
	 * 必须返回0，代表此List中无数据
	 */
    public int size() {
		return 0;
	}
    
    /**
     * 必须返回true，代表此List中无数据
     */
    public boolean isEmpty() {
		return true;
	}
    
    /**
     * for each 会调用此方法
     */
    public Iterator<E> iterator() {
    	return nullIterator;
	}
    
    public boolean contains(Object o) {
    	log.error(message);
    	throw new RuntimeException(message);
	}
    
    private static final Object[] nullObjectArray = new Object[0];
    /**
     * ArrayList.addAll()方法会调此方法
     */
    public Object[] toArray() {
    	return nullObjectArray;
	}
    
    public <T> T[] toArray(T[] a) {
    	log.error(message);
    	throw new RuntimeException(message);
	}
    
    public boolean add(E e) {
    	log.error(message);
    	throw new RuntimeException(message);
	}
    
    public boolean remove(Object o) {
    	log.error(message);
    	throw new RuntimeException(message);
	}
    
    public boolean containsAll(Collection<?> c) {
    	log.error(message);
    	throw new RuntimeException(message);
	}
    
    public boolean addAll(Collection<? extends E> c) {
    	log.error(message);
    	throw new RuntimeException(message);
	}
    
    public boolean addAll(int index, Collection<? extends E> c) {
    	log.error(message);
    	throw new RuntimeException(message);
	}
    
    public boolean removeAll(Collection<?> c) {
    	log.error(message);
    	throw new RuntimeException(message);
	}
    
    public boolean retainAll(Collection<?> c) {
    	log.error(message);
    	throw new RuntimeException(message);
	}
    
    public void clear() {
    	log.error(message);
    	throw new RuntimeException(message);
	}
    
    public E get(int index) {
    	log.error(message);
    	throw new RuntimeException(message);
	}
    
    public E set(int index, E element) {
    	log.error(message);
    	throw new RuntimeException(message);
	}
    
    public void add(int index, E element) {
    	log.error(message);
    	throw new RuntimeException(message);
	}
    
    public E remove(int index) {
    	log.error(message);
    	throw new RuntimeException(message);
	}
    
    public int indexOf(Object o) {
    	log.error(message);
    	throw new RuntimeException(message);
	}
    
    public int lastIndexOf(Object o) {
    	log.error(message);
    	throw new RuntimeException(message);
	}
    
    public ListIterator<E> listIterator() {
    	log.error(message);
    	throw new RuntimeException(message);
	}
    
    public ListIterator<E> listIterator(int index) {
    	log.error(message);
    	throw new RuntimeException(message);
	}
    
    public List<E> subList(int fromIndex, int toIndex) {
    	log.error(message);
    	throw new RuntimeException(message);
	}
    
    // overrides java.lang.Object.equals
//    public boolean equals(Object o) {
//		return false;
//	}
    
    // overrides java.lang.Object.hashCode
//    public int hashCode() {
//		return 0;
//	}
    
    /**
     * 空对象模式内部类实现Iterator
     */
    private class NullIterator<F> implements Iterator<F>
    {
    	/**
    	 * 一定要返回false，for each 循环会调用此方法，如果返回true将调用next()
    	 */
        public boolean hasNext() {
			return false;	// 这里返回false将最终导致for each循环体不被执行
		}
        
        public F next() {
        	log.error(message);
        	throw new RuntimeException(message);
		}
        
        public void remove() {
        	log.error(message);
        	throw new RuntimeException(message);
		}
    }
    
    /**
     * Dao中需要返回NullList类型，用法示例如下
     */
    private static <G> List<G> daoFind(String hql) {
    	List<G> result = NullList.getInstance();
    	return result;
    }
    
    /**
     * 测试方法
     */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List<Object> rawList = NullList.getInstance();
		for (Object o: rawList)
			System.out.println("这句不会执行就对了" + o);
		for (int i=0; i< rawList.size(); i++)
			System.out.println("这句不会执行就对了" + rawList.get(i));
		
		List<Integer> integerList = NullList.getInstance();
		for (Integer o: integerList)
			System.out.println("这句不会执行就对了" + o);
		
		NullList.daoFind("");
		
		
		List otherRawList = NullList.getInstance();		// 不能申名为List<Object> otherRawList，
		List<String> listFromRawList = otherRawList;	// 否则这句就会出错
		System.out.println(listFromRawList);
	}
}









