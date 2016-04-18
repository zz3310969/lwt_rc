package com.bsc.dao.system;

import java.util.List;

import com.bsc.bean.Role;
/**
 * 角色管理
 * @author 韩进城
 *
 */
public interface IRoleManager {
	/**
	 * 添加角色
	 * @param role
	 * @return
	 * @throws Exception
	 */
	boolean onCreateRole(Role role) throws Exception;
	/**
	 * 删除角色
	 * @param role
	 * @return
	 * @throws Exception
	 */
	boolean onRemove(Role role) throws Exception;
	/**
	 * 更新角色
	 * @param role
	 * @return
	 * @throws Exception
	 */
	boolean onUpdate(Role role) throws Exception;
	/**
	 * 根据ID查询角色
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	Role findById(String roleId) throws Exception;
	/**
	 * 取得所有角色
	 * @return
	 * @throws Exception
	 */
	List<Role> findAll() throws Exception;
	/**
	 * 查询出角色id最大的数
	 * @return
	 * @throws Exception
	 */
	int findMaxNumber() throws Exception;
}
