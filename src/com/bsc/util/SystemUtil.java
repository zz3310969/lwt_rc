package com.bsc.util;

import java.util.Iterator;
import java.util.Set;

import com.bsc.bean.Systemuser;
import com.bsc.bean.Userrole;
import com.opensymphony.xwork2.ActionContext;

public class SystemUtil {

	/**
	 * 获得当前登陆用户信息
	 * @return
	 */
	public static Systemuser getSystemuser(){
		
		Systemuser systemuser = null;
		
		systemuser = (Systemuser) ActionContext.getContext().getSession().get("sysuser");
		
		return systemuser;
	}
	
	/**
	 * 判断当前登陆用户是否为管理员
	 * @return
	 */
	public static boolean isAdmin(){
		
		boolean flag = false;
		
		Systemuser user = getSystemuser();
		
		if(user != null){
			
			Set<Userrole> set = user.getUserroles();
			
			Iterator<Userrole> iterator = set.iterator();
			
			while(iterator.hasNext()){
				
				Userrole role = iterator.next();
				
				if("1".equals(role.getRoleId())){
					
					flag = true;
				}
			}
		}
		
		return flag;
	}
}
