package cn.zhaoliugang.guard.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.zhaoliugang.guard.bean.User;
import cn.zhaoliugang.guard.dao.UserDao;
import cn.zhaoliugang.guard.dao.impl.UserDaoImpl;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			String action = request.getParameter("action");
			if (action != null && action.equals("login")) {
				login(request, response);
			} else if (action != null && action.equals("logout")) {
				logout(request, response);
			}else if (action != null && action.equals("loginRemote")) { //第三方系统登录
	 			loginRemote(request,response);
			}else if (action != null && action.equals("auto")) {
				auto(request, response);
			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void loginRemote(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UserDao dao = new UserDaoImpl();
		User bean = dao.get(username);
		
		String message = null;
		if(!(bean!=null && bean.getId()>0)){
			message = "error";
		}else{
			JSONObject json = new JSONObject(bean);
			message = json.toString();
			message = message.replaceAll("\"", "'");
		}
		
		PrintWriter writer = response.getWriter();
		writer.print(message);
		writer.flush();
		writer.close();
		
	}

	public void auto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Cookie[] cs = request.getCookies();
		if(cs!=null && cs.length>0){
			for(int i=0;i<cs.length;i++){
				Cookie c = cs[i];
				System.out.println("@@@cookie name:"+c.getName()+" value:"+c.getValue());
				
				if(c.getName().equals("username")){
					String value=c.getValue();
					request.setAttribute("un", value);
				}
				
				if(c.getName().equals("password")){
					String value=c.getValue();
					request.setAttribute("pw", value);
				}
			}
		}

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if((username == null || username.equals(""))
				|| (password==null || password.equals(""))){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		login(request, response);
	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if((username == null || username.equals(""))
				|| (password==null || password.equals(""))){
			String msg = "登录不成功，用户名密码不能为空，请重新输入！";
			String msg_n= new String(msg.getBytes("UTF-8"),"UTF-8");
			request.setAttribute("Msg", msg_n);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		UserDao dao = new UserDaoImpl();
		User bean = dao.get(username);
		if(bean == null
				||bean.getPassword()==null
				||!bean.getPassword().equals(password)){
			String msg = "登录不成功，用户名或密码错误，请重新输入！";
			String msg_n= new String(msg.getBytes("UTF-8"),"UTF-8");
			request.setAttribute("Msg", msg_n);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		if(bean.getStatus()==1){
			String msg = "登录不成功，用户已禁用，请联系管理员！";
			String msg_n= new String(msg.getBytes("UTF-8"),"UTF-8");
			request.setAttribute("Msg", msg_n);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		
		
		String msg = "登录成功！";
		String msg_n= new String(msg.getBytes("UTF-8"),"UTF-8");
		request.setAttribute("Msg", msg_n);
		
		String auto = request.getParameter("auto");
		if(auto!=null&&auto.equals("1")){
			System.out.println(request.getContextPath()+" Set cookie:"+" usrname:"+username+" password:"+password);
			Cookie u = new Cookie("username",username);
			Cookie p = new Cookie("password",password);
			
			u.setPath(request.getContextPath());
			p.setPath(request.getContextPath());
			
			u.setMaxAge(60*60*24*7);
			p.setMaxAge(60*60*24*7);
			
			response.addCookie(u);
			response.addCookie(p);
		}else{
			Cookie u = new Cookie("username","");
			Cookie p = new Cookie("password","");

			u.setPath(request.getContextPath());
			p.setPath(request.getContextPath());
			
			u.setMaxAge(60*60*24*7);
			p.setMaxAge(60*60*24*7);
			
			response.addCookie(u);
			response.addCookie(p);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("UserSession", bean);

		request.getRequestDispatcher("/view/index.jsp").forward(request, response);
		
		return;
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.removeAttribute("UserSession");
		
		Cookie u = new Cookie("username","");
		Cookie p = new Cookie("password","");

		u.setPath(request.getContextPath());
		p.setPath(request.getContextPath());
		
		u.setMaxAge(60*60*24*7);
		p.setMaxAge(60*60*24*7);
		
		response.addCookie(u);
		response.addCookie(p);

/*		String msg = "注销成功！";
		String msg_n= new String(msg.getBytes("UTF-8"),"UTF-8");
		request.setAttribute("Msg", msg_n);
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);*/
		
		response.sendRedirect("index.jsp");
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
