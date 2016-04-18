package com.bsc.action.rc;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.bsc.bean.Consultation;
import com.jspsmart.upload.Request;

/**
 * 
 * @author 
 * @version 1.0
 * 
 */
public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 2450102559616913758L;

	private static final String ENCODING = "utf-8";

	private final String stateXMLversion = "<?xml version=\"1.0\" encoding=\""
			+ ENCODING + "\" ?>";

	private final String startXml = "<result>";

	private final String endXml = "</result>";

	// 最大上传文件大小为100MB
	private final Long maxFileSize = 1024L * 1024L * 100L;

	/**
	 * 构造请求
	 */
	public UploadServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 文件上传服务,请求参数需用urlencode进行编码,编码方式为utf-8 
	 * 请求参数 datatype: 指定返回的数据格式json|xml,默认返回json格式 
	 *         callbackurl: 将上传的结果返回的地址,当此参数为空的时候,直接输出结果到页面(json格式)
	 *         cfunction: javascript回调的函数,当此参数为空的时候,系统默认为函数名为callback 
	 *         username: 用户名
	 *         password: 密码
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		String dataType = null;
		StringBuilder sb = new StringBuilder();
		Param param = null;
		//.初始化
		com.jspsmart.upload.SmartUpload su = new com.jspsmart.upload.SmartUpload();
		JspFactory jsp1 = JspFactory.getDefaultFactory();
		PageContext pageContext = jsp1.getPageContext(this, request, response,
				null, true, 8192, true);
		su.initialize(pageContext);
		   //文件大小设置为100MB
		su.setMaxFileSize(maxFileSize);
		try {
			su.upload();   
			com.jspsmart.upload.File file = su.getFiles().getFile(0);
			Uploader up = new Uploader(pageContext, request);
			up.SetOriFileName(false);
			up.setM_userFilename(null);
			up.SaveFile(file);
			if (null != dataType && dataType.equals("xml")) {
				sb.append(stateXMLversion);
				sb.append(startXml);
				getSuccessXml(sb, up.GetFilePathRel());
				sb.append(endXml);
			} else {
				getSuccessJson(sb, up.GetFilePathRel());
			}

		} catch (SecurityException se) {
			if (null != dataType && dataType.equals("xml")) {
				sb.append(stateXMLversion);
				sb.append(startXml);
				getMaxSizeFailXml(sb);
				sb.append(endXml);
			} else {
				getMaxSizeFailJson(sb);
			}

		} catch (Exception se) {
			if (null != dataType && dataType.equals("xml")) {
				sb.append(stateXMLversion);
				sb.append(startXml);
				getOtherErrorXml(sb);
				sb.append(endXml);
			} else {
				getOtherErrorJson(sb);
			}
		} finally {
			//解码
			param = this.warpParam(su.getRequest());
			dataType = param.getDataType();
			pageContext.getOut().clear();
			pageContext.pushBody();
			// 1.验证用户和密码
			if (!chkUser(param.getUserName(), param.getPassword())) {

				if (null != dataType && dataType.equals("xml")) {
					sb.append(stateXMLversion);
					sb.append(startXml);
					getValideFailXml(sb);
					sb.append(endXml);
					this.writeToPageXml(sb.toString(), response);
				} else {
					getValideFailJson(sb);
					this.writeToPageJson(sb.toString(), response);
				}
				return;
			} 
		}
		// 3.返回结果
		String callbackurl = param.getCallBackUrl();
		if(null == callbackurl || callbackurl.isEmpty())
		{
			if (null != dataType && dataType.equals("xml")) {
				sb.append(stateXMLversion);
				writeToPageXml(sb.toString(),response);
			} else {
				writeToPageJson(sb.toString(),response);
			}
			return;
		}
		  //urlencode
		String res = java.net.URLEncoder.encode(sb.toString(),ENCODING);
		String cfunction = null == param.getCFunction() || param.getCFunction().isEmpty()?"callback":param.getCFunction();
		response.sendRedirect(callbackurl+"?data="+res+"&callback="+ cfunction);
		

	}

	private void getValideFailJson(StringBuilder sb) {
		sb.append("{\"flag\":\"2\",\"msg\":\"用户验证失败\", url:\"\"}");
	}

	private void getValideFailXml(StringBuilder sb) {
		sb.append("<![CDATA[2|").append("用户验证失败|").append("]]>");
	}

	private void getSuccessJson(StringBuilder sb, String url) {
		sb.append("{\"flag\":\"1\",\"msg\":\"上传成功\", url:\"" + url + "\"}");
	}

	private void getSuccessXml(StringBuilder sb, String url) {
		sb.append("<![CDATA[1|上传成功|").append(url).append("]]");
	}

	private void getMaxSizeFailJson(StringBuilder sb) {
		sb.append("{\"flag\":\"-1\",\"msg\":\"文件大小超过限制\", url:\"\"}");
	}

	private void getMaxSizeFailXml(StringBuilder sb) {
		sb.append("<![CDATA[-1|文件大小超过限制|").append("]]");
	}

	private void getOtherErrorJson(StringBuilder sb) {
		sb.append("{\"flag\":\"0\",\"msg\":\"上传失败,请稍后重试\", url:\"\"}");
	}

	private void getOtherErrorXml(StringBuilder sb) {
		sb.append("<![CDATA[0|上传失败,请稍后重试|").append("]]");
	}

	/**
	 * 输出json结果到页面
	 * 
	 * @param data
	 *            数据
	 * @param response
	 *            reponse
	 */
	private void writeToPageJson(String data, HttpServletResponse response) {
		response.setContentType("text/html;charset=" + ENCODING);
		writeToPage(response, data);
	}

	private void writeToPageXml(String data, HttpServletResponse response) {
		response.setContentType("text/xml");
		writeToPage(response, data);
	}

	private void writeToPage(HttpServletResponse response, String data) {
		response.setCharacterEncoding(ENCODING);
		try {
			OutputStreamWriter osw = new OutputStreamWriter(response
					.getOutputStream(), ENCODING);
			osw.write(data);
			osw.flush();
			osw.close();
		} catch (UnsupportedEncodingException ue) {

		} catch (IOException ie) {

		}
	}

	public void init() throws ServletException {
		// TODO 初始化
	}

	/**
	 * 验证请求
	 * 
	 * @param u
	 *            u
	 * @param p
	 *            p
	 * @return
	 */
	private static boolean chkUser(String u, String p) {
		return true;
	}

	private Param warpParam(Request request) {
		String[] p = new String[5];
		// 解码
		p[0] = request.getParameter("datatype");
		p[1] = request.getParameter("callbackurl");
		p[2] = request.getParameter("cfunction");
		p[3] = request.getParameter("username");
		p[4] = request.getParameter("password");
		// 解码
		urlDecode(p);
		return new Param(p[0], p[1], p[2], p[3], p[4]);
	}

	private static void urlDecode(String... str) {
		for (int i = 0; i < str.length; i++) {
			String v = str[i];
			if (null == v || v.isEmpty()) {
				continue;
			}
			try {
				str[i] = java.net.URLDecoder.decode(v, "utf-8");
			} catch (UnsupportedEncodingException ue) {
				ue.printStackTrace();
			}
		}
	}

	/**
	 * 参数
	 * 
	 * @author cbai
	 * 
	 */
	class Param {
		private String dataType;
		private String callBackUrl;
		private String cFunction;
		private String userName;
		private String password;

		/**
		 * 
		 * @param dataType
		 *            数据类型
		 * @param callBackUrl
		 *            返回的url
		 * @param cFunction
		 *            回调的java函数
		 * @param userName
		 *            用户名
		 * @param password
		 *            密码
		 */
		public Param(String dataType, String callBackUrl, String cFunction,
				String userName, String password) {
			this.dataType = dataType;
			this.callBackUrl = callBackUrl;
			this.cFunction = cFunction;
			this.userName = userName;
			this.password = password;
		}

		public String getDataType() {
			return dataType;
		}

		public String getCallBackUrl() {
			return callBackUrl;
		}

		public String getCFunction() {
			return cFunction;
		}

		public String getUserName() {
			return userName;
		}

		public String getPassword() {
			return password;
		}
	}

}
