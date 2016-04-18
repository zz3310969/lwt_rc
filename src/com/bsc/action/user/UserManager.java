package com.bsc.action.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.bsc.bean.Functionmodule;
import com.bsc.bean.Loginlog;
import com.bsc.bean.Role;
import com.bsc.bean.Roleright;
import com.bsc.bean.Systemuser;
import com.bsc.bean.Userrole;
import com.bsc.service.system.IUserLoginService;
import com.bsc.service.system.impl.UserLoginServiceImpl;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionContext;

public class UserManager {

	private String msg; // 返回提示信息

	private Systemuser sysUser; // 用户实体信息

	private String randCode;// 验证码

	private Map<String, HashMap<String, String>> map;

	private IUserLoginService userlogin;

	/**
	 * 用户登录Action
	 * 
	 * @return JSON
	 */
	@SuppressWarnings("unchecked")
	public String login() {

		String method = ServletActionContext.getRequest().getMethod();
		if (method.equals("GET"))
			return null;

		if (checkDate()) { // 如果填写正确

			Systemuser sys = userlogin.loginService(sysUser); // 验证此用户是否存在

			if (sys == null) {

				this.msg = "用户名或密码错误！";

			} else {

				this.msg = "SUCCESS";

				Loginlog log = userlogin.logService(sys); // 保存用户登录日志

				ActionContext.getContext().getSession().put("sysuser", sys);

				ActionContext.getContext().getSession().put("log", log);
			}

		}

		return "json";
	}

	/**
	 * 取得所有权限
	 * 
	 * @return
	 */
	public String getRightMenus() {

		return "lift";
	}

	/**
	 * 取得该角色下的菜单
	 * 
	 * @param systemuser
	 * @return
	 */
	private void getRolesMenus(Systemuser systemuser) {

		List<String> list = new ArrayList<String>();

		Set<Userrole> roles = systemuser.getUserroles(); // 取得所有角色

		Iterator<Userrole> iterator = roles.iterator();

		while (iterator.hasNext()) {

			Userrole userrole = iterator.next(); // 取得每个角色下的

			Role role = userrole.getRole();

			Set<Roleright> rolerights = role.getRolerights(); // 取得该角色下的权限

			if (!rolerights.isEmpty()) {

				Iterator<Roleright> rIterator = rolerights.iterator();

				while (rIterator.hasNext()) {

					Roleright rr = rIterator.next();

					Functionmodule functionmodule = rr.getFunctionmodule(); // 获得功能模块

				}

			}
		}
	}

	/**
	 * 检查用户名和验证码是否正确或填写
	 * 
	 * @return
	 */
	private boolean checkDate() {
		
		boolean flag = false;

		if (sysUser == null) {

			this.msg = "用户名或密码不能为空！";

			return flag;

		} else if (sysUser.getUserName() == null
				|| sysUser.getPassword() == null) {

			this.msg = "用户名或密码不能为空！";

			return flag;

		} else if (randCode == null && "".equals(randCode)) {

			this.msg = "验证码不能为空！";

			return flag;
		}

		String code = (String) ActionContext.getContext().getSession()
				.get("systemRandomCode");

		if (!randCode.equalsIgnoreCase(code)) {

			this.msg = "验证码错误！";

			return flag;
		}

		flag = true;

		return flag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Systemuser getSysUser() {
		return sysUser;
	}

	public void setSysUser(Systemuser sysUser) {
		this.sysUser = sysUser;
	}

	public String getRandCode() {
		return randCode;
	}

	public void setRandCode(String randCode) {
		this.randCode = randCode;
	}

	@JSON(serialize = false)
	public IUserLoginService getUserlogin() {
		return userlogin;
	}

	public void setUserlogin(IUserLoginService userlogin) {
		this.userlogin = userlogin;
	}

	public Map<String, HashMap<String, String>> getMap() {
		return map;
	}

	public void setMap(Map<String, HashMap<String, String>> map) {
		this.map = map;
	}
}
