package com.order.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ModelDriven;
import com.order.entity.User;

/**
 * 过滤器
 * 
 * @author lt
 *
 */
public class SessionFilter implements Filter {
	@Override
	public void init(FilterConfig cfg) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// * 请求 http://127.0.0.1:8080/webApp/home.jsp?&a=1&b=2 时
		// * request.getRequestURL()： http://127.0.0.1:8080/webApp/home.jsp
		// * request.getContextPath()： /webApp
		// * request.getServletPath()：/home.jsp
		// * request.getRequestURI()： /webApp/home.jsp
		// * request.getQueryString()：a=1&b=2
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.addHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-store");
		response.addHeader("Cache-Control", "must-revalidate");
		String servletPath = request.getServletPath();
		String contentType = request.getContentType();
		if (null != contentType && contentType.contains("form-data")) {
			chain.doFilter(req, res);
			return;
		}
//		if (servletPath.endsWith("applyShop.json")
//				|| servletPath.endsWith("modShopLogo.json")
//				|| servletPath.endsWith("addProduct.json")
//				|| servletPath.endsWith("applyAd.json")
//				|| servletPath.endsWith("modProduct.json")
//				|| servletPath.endsWith("modUserPic.json")) {
//			chain.doFilter(req, res);
//			return;
//		}
		if (servletPath.endsWith("json")
				&& "POST".equalsIgnoreCase(request.getMethod())) {
			String token = request.getParameter("token");
			String checkToken = (String) request.getSession().getAttribute(
					"token");
			if (null == token || !token.equals(checkToken)) {
				json.accumulate("code", 500);
				json.accumulate("errMsg", "csrf");
				response.getWriter().print(json.toString());
				return;
			}
		}
		if (servletPath.equals("/index.html")) {
			User sessionObj = (User) request.getSession().getAttribute("user");
			String token = CreateUUID.createUUID();
			request.getSession().setAttribute("token", token);
			if (sessionObj == null) {
				response.getWriter().print(
						"<script>window.PARKNSHOP_CONFIG={IS_LOGIN:false,"
								+ "TOKEN:'" + token + "'}</script>");
			} else {
				String username = sessionObj.getUsername();
				response.getWriter().print(
						"<script>window.PARKNSHOP_CONFIG={IS_LOGIN:true,"
								+ "TOKEN:'" + token + "'," + "USER_NAME:'"
								+ username + "',LOGO:'" + sessionObj.getNotice()
										+ "'}</script>");
			}
		}
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {

	}
}