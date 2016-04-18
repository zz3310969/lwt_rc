package com.bsc.action.user;

import java.util.TreeMap;

public class ExMethod {

	public static class Authority {

		private static TreeMap<String, String> mapMethod = new TreeMap<String, String>();
		private static Authority authority;

		private Authority() {
			mapMethod.put("01", "insert");// 新建
			mapMethod.put("03", "update");// 修改
			mapMethod.put("02", "delete");// 删除
			mapMethod.put("05", "getModel");// 获取实例信息
			mapMethod.put("04", "queryRecord");// 查询
			mapMethod.put("07", "queryRecordByUser");// 获取用户数据规则权限
			mapMethod.put("editPassword", "editPassword");// 修改密码
			mapMethod.put("initPassword", "initPassword");// 初始化密码
			mapMethod.put("selectResID", "selectResID");// 获取资源信息
			mapMethod.put("auth", "auth");// 授权
			mapMethod.put("userResource", "userResource");// 授权

		}

		public static Authority instance() {
			if (authority == null) {
				authority = new Authority();
			}
			return authority;
		}

		public String getMethodName(String key) {
			String strTemp = "";

			strTemp = (String) mapMethod.get(key);

			if (strTemp == null || strTemp.trim().length() == 0) {
				return key;
			} else {
				return strTemp;
			}
		}

		

	}

}
