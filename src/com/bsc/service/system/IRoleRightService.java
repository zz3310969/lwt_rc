package com.bsc.service.system;

import com.bsc.bean.Roleright;

public interface IRoleRightService {
	/**
	 * 增加权限
	 * @param roleright
	 * @return
	 */
	boolean onCreateRoleRight(Roleright roleright);
	/**
	 * 删除角色权限
	 * @param roleright
	 * @return
	 */
	boolean onDeleteRoleRight(Roleright roleright);
}
