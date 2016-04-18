package com.bsc.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bsc.bean.Functionmodule;
import com.bsc.bean.Patient;
import com.bsc.bean.Role;
import com.bsc.dao.rc.impl.PatientDAOImpl;
import com.bsc.dao.system.impl.DictDAOimpl;
import com.bsc.dao.system.impl.FunctionModelDAOImpl;
import com.bsc.service.system.impl.RoleServiceImpl;
import com.bsc.service.system.impl.WebPageServiceImpl;

import junit.framework.TestCase;

public class TestService extends TestCase {
	
	 String[] locations = {"applicationContext.xml", "applicationContext-service.xml", "applicationContext-dao.xml","applicationContext-action.xml"};
     ApplicationContext ctx = new ClassPathXmlApplicationContext(locations);
	
	/**
	 * 测试webpage服务层查询方法
	 */
	public void testWebpageServiceFindAll(){
		
         WebPageServiceImpl webpageserimpl = (WebPageServiceImpl) ctx.getBean("webpageServiceImpl");
		
	}
	
	/**
	 * 保存Model
	 */
	public void testSaveMode(){
		
		FunctionModelDAOImpl mode = (FunctionModelDAOImpl) ctx.getBean("functionModelDAOImpl");
		try {
			int size = mode.findAll(1, 15).size();
			System.out.println(size);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*Functionmodule fum = new Functionmodule();
		
		fum.setAddTime(new Date());
		fum.setModuleId(UUID.randomUUID().toString());
		fum.setDescription("test");
		fum.setIsPublic("1");
		fum.setModuleLevel("1");
		fum.setModuleName("test");
		try {
			System.out.println(mode.onCreate(fum));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
	public void testDeleteMode(){
		
		FunctionModelDAOImpl mode = (FunctionModelDAOImpl) ctx.getBean("functionModelDAOImpl");
		
		try {
			Functionmodule fm = mode.findById("7eb783a6-18c7-4860-ac89-1085dad5a3bd");
			
			System.out.println(mode.onRemove(fm));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testDictFindType(){
		
		DictDAOimpl dict = (DictDAOimpl) this.ctx.getBean("dictDaoimpl");
		
		try {
			System.out.println(dict.findByIds("06").size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testRole(){
		
		RoleServiceImpl rosser = (RoleServiceImpl) this.ctx.getBean("roleserviceimpl");
		
		//System.out.println(rosser.findAll().size());
		Role role = new Role();
		
		role.setAddTime(new Date());
		role.setRoleId(UUID.randomUUID().toString());
		role.setDescription("test");
		role.setRoleName("test");
		
		//System.out.println(rosser.onCreateRole(role));
		System.out.println(rosser.findByid("e6443f84-939b-4bc8-ab82-a33bf7e7509f").getRoleName());
		System.out.println(rosser.onDeleteRole(rosser.findByid("e6443f84-939b-4bc8-ab82-a33bf7e7509f")));
	}
	
}
