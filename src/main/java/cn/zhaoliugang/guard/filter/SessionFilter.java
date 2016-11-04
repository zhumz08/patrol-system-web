package cn.zhaoliugang.guard.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.zhaoliugang.guard.bean.User;
import cn.zhaoliugang.guard.util.Constant;

/**
 * Servlet Filter implementation class SessionFilter
 */
@WebFilter("/view/*")
public class SessionFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public SessionFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("UserSession");

		System.out.println("SessionFilter " + request.getServletContext() + req.getRequestURI());

		String path = req.getContextPath();
		List excludedPages = new ArrayList();
		excludedPages.add(path + "/action/LoginServlet");

		String uri = req.getRequestURI();
		if ((user == null || user.getName() == null || user.getName().equals(""))
				&& !excludedPages.contains(uri)) {
			String msg = "您还未登录，请登录后再操作！";
			String msg_n = new String(msg.getBytes("UTF-8"), "UTF-8");
			request.setAttribute("Msg", msg_n);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		if (user != null && user.getType() != Constant.TYPE_ADMIN) {
			List adminPages = new ArrayList();
			adminPages.add(path + "/view/user.jsp");
			adminPages.add(path + "/view/userlist.jsp");
			adminPages.add(path + "/view/guardsite.jsp");
			adminPages.add(path + "/view/guardsiteedit.jsp");
			adminPages.add(path + "/tool/GuardTourSystemTool-dist.zip");
			adminPages.add(path + "/view/userreport.jsp");

			if (adminPages.contains(uri)) {
				String msg = "您不是管理员，无法操作！";
				String msg_n = new String(msg.getBytes("UTF-8"), "UTF-8");
				request.setAttribute("Msg", msg_n);
				request.getRequestDispatcher("/view/index.jsp").forward(request, response);
				return;
			}

		} 
	
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
