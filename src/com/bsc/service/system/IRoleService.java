package com.bsc.service.system;

import java.util.List;

import com.bsc.bean.Role;

/**
 * 角色管理服务层
 * @author hanjin7278
 *
 */
public interface IRoleService {
	/**
	 * 查询所有角色信息
	 * @return
	 */
	List<Role> findAll() ;
	
	/**
	 * 增加角色
	 * @param role
	 * @return
	 */
	boolean onCreateRole(Role role);
	
	/**
	 * 删除角色
	 * @param role
	 * @return
	 */
	boolean onDeleteRole(Role role);
	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	boolean onUpdateRole(Role role);
	/**
	 * 查询一个角色信息
	 * @param roleid
	 * @return
	 */
	Role findByid(String roleid);
	/**
	 * 角色id最大记录数
	 * @return
	 */
	int findMaxNum();
}
