package com.bsc.system.interceptor;

import com.bsc.bean.Systemuser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub
		System.out.println("拦截器正在启动....");
	}

	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		String action = arg0.getProxy().getMethod();
		
		if("login".equals(action)){
			
			arg0.invoke();
		}else{
			
			Systemuser sysuser = (Systemuser) ActionContext.getContext().getSession().get("sysuser");
			
			if(sysuser == null){
				
				return "error";
				
			}else{
				
				arg0.invoke();
				
			}
		}
		
		
		return null;
	}

}
