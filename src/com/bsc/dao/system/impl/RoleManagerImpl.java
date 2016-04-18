package com.bsc.dao.system.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bsc.bean.Role;
import com.bsc.dao.system.IRoleManager;
/**
 * 角色管理
 * @author 韩进城
 *
 */
public class RoleManagerImpl extends HibernateDaoSupport implements IRoleManager {
	/**
	 * 查询所有角色
	 */
	public List<Role> findAll() throws Exception {
		// TODO Auto-generated method stub
		
		List<Role> roles = this.getHibernateTemplate().find("SELECT new Role(roleId,roleName,addTime,updateTime,description) FROM Role"); 
		
		return roles;
	}
	/**
	 * 根据ID查询角色信息
	 */
	public Role findById(String roleId) throws Exception {
		// TODO Auto-generated method stub
		
		String sql = "FROM Role WHERE roleId = ?";
		
		List<Role> roles = this.getHibernateTemplate().find(sql,roleId);
		
		if(!roles.isEmpty()){
			
			return roles.get(0);
			
		}
		
		return null;
	}
	/**
	 * 增加角色
	 */
	public boolean onCreateRole(Role role) throws Exception {
		// TODO Auto-generated method stub
		
		Serializable ser = this.getHibernateTemplate().save(role);
		
		if(ser != null){
			
			return true;
		}
		
		return false;
	}
	/**
	 * 删除角色
	 */
	public boolean onRemove(Role role) throws Exception {
		// TODO Auto-generated method stub
		
		this.getHibernateTemplate().delete(role);
		
		return true;
	}
	/**
	 * 更新角色
	 */
	public boolean onUpdate(Role role) throws Exception {
		// TODO Auto-generated method stub
		
		this.getHibernateTemplate().update(role);
		
		return true;
	}
	/**
	 * 查询角色id最大值
	 */
	public int findMaxNumber() throws Exception {
		// TODO Auto-generated method stub
		
		String num = this.getHibernateTemplate().find("SELECT max(roleId) FROM Role").get(0).toString();
		
		int temp = Integer.parseInt(num);
		
		return temp;
	}

}
