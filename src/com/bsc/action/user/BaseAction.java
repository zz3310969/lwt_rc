package com.bsc.action.user;

import java.lang.reflect.Method;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	private String action;
	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
		if (action==null||action.isEmpty()) {
			return null;
		}
		
		String methodName=ExMethod.Authority.instance().getMethodName(action);
		
		Method mi =this.getClass().getMethod(methodName);
		
		if (mi== null) {
			mi = this.getClass().getMethod(action);
		}
		if (mi != null) {
			mi.invoke(this);
		}
		return "json";
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
