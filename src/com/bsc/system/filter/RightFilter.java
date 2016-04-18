package com.bsc.system.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsc.bean.Systemuser;
import com.bsc.service.system.impl.LinkMenuWebPageServiceImpl;
import com.bsc.util.ResultModel;

public class RightFilter implements Filter {
	private LinkMenuWebPageServiceImpl webPageService;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub、

		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		HttpSession session = ((HttpServletRequest) request).getSession();
		Systemuser user = null;
		if (session != null) {
			user = (Systemuser) session.getAttribute("sysuser");
			if (user != null) {
				// 下面是控制页面访问权限的代码，先注释掉，以防影响测试开发工作
				// ArrayList<String> urlList =
				// getUrlListByUser(user.getUserId());
				// String targetUrl = request.getRequestURI();
				// targetUrl = targetUrl.substring(7);
				// if (urlList.contains(targetUrl))
				arg2.doFilter(request, response);
				return;
			}
		}
		request.setAttribute("url", "/util/global/noRight.jsp");
		request.getRequestDispatcher("/util/forward.jsp").forward(request,
				response);
		return;

	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> getUrlListByUser(String userId) {

		ArrayList<String> list = new ArrayList<String>();
		String sql = "select g.url from systemuser a left join userrole b on a.userId=b.userId left join role c on b.roleId=c.roleId left join roleright d on c.roleId=d.roleId left join functionmodule e on d.functionModuleId=e.moduleId left join functionpage f on e.moduleId=f.functionModuleId left join webpage g on f.webPageId=g.pageId where e.isPublic='0'";
		sql += " or a.userId='" + userId + "';";
		ResultModel result = webPageService.getStringList(sql, false);
		list = (ArrayList<String>) result.getData();

		return list;
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("SessionFilter正在启动....");
	}

	public LinkMenuWebPageServiceImpl getWebPageService() {
		return webPageService;
	}

	public void setWebPageService(LinkMenuWebPageServiceImpl webPageService) {
		this.webPageService = webPageService;
	}

}
