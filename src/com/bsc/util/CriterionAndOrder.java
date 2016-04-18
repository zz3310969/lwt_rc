package com.bsc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public class CriterionAndOrder {
	private String criteriaName;
	private String criteriaAsName;
	private int criteriaSpecification;
	// 查询条件列表
	private List<Criterion> criterionList = new ArrayList<Criterion>();
	// 排序字段列表
	private List<Order> orderList = new ArrayList<Order>();

	public String getCriteriaName() {
		return criteriaName;
	}

	public void setCriteriaName(String criteriaName) {
		this.criteriaName = criteriaName;
	}

	public List<Criterion> getCriterionList() {
		return criterionList;
	}

	public void setCriterionList(List<Criterion> criterionList) {
		this.criterionList = criterionList;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public String getCriteriaAsName() {
		return criteriaAsName;
	}

	public void setCriteriaAsName(String criteriaAsName) {
		this.criteriaAsName = criteriaAsName;
	}

	public int getCriteriaSpecification() {
		return criteriaSpecification;
	}

	public void setCriteriaSpecification(int criteriaSpecification) {
		this.criteriaSpecification = criteriaSpecification;
	}

}