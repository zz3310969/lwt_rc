package com.bsc.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;

public class QueryTerms {

	public QueryTerms() {

	}

	// 实体类，要序列化成的类
	private Class<?> persistentClass = Object.class;
	// 查询条件列表
	private List<Criterion> criterionList = (List<Criterion>) new ArrayList<Criterion>();
	// 涉及多表连接查询时，根据不同表中的不同字段排序
	private List<CriterionAndOrder> criterionAndOrderList = new ArrayList<CriterionAndOrder>();
	// 排序字段列表
	private List<Order> orderList = new ArrayList<Order>();
	//要查询的字段
	private ProjectionList projectionList=null;
	private int pageIndex = 0;
	private int pageSize = 10;

	public Class<?> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<?> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public List<Criterion> getCriterionList() {
		return criterionList;
	}

	public void setCriterionList(List<Criterion> criterionList) {
		this.criterionList = criterionList;
	}

	public List<CriterionAndOrder> getCriterionAndOrderList() {
		return criterionAndOrderList;
	}

	public void setCriterionAndOrderList(
			List<CriterionAndOrder> criterionAndOrderList) {
		this.criterionAndOrderList = criterionAndOrderList;
	}
		
	public ProjectionList getProjectionList() {
		return projectionList;
	}

	public void setProjectionList(ProjectionList projectionList) {
		this.projectionList = projectionList;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	
	
}
