package cn.zhaoliugang.guard.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.zhaoliugang.guard.bean.User;
import cn.zhaoliugang.guard.dao.UserDao;
import cn.zhaoliugang.guard.dao.impl.UserDaoImpl;
import cn.zhaoliugang.guard.util.Constant;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		if(action!=null && action.equals("save")){
			save(request,response);
		}else if(action!=null && action.equals("list")){
			list(request,response);
		}else if(action!=null && action.equals("get")){
			get(request,response);
		}else if(action!=null && action.equals("update")){
			update(request,response);
		}else if(action!=null && action.equals("delete")){
			delete(request,response);
		}else if(action!=null && action.equals("show")){
			show(request,response);
		}else if(action!=null && action.equals("export")){
			export(request,response);
		}else{
			
		}
	}
	
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String s_type = request.getParameter("type");
		String s_status = request.getParameter("status");
		String name = request.getParameter("name");
		String idnumber = request.getParameter("idnumber");
		String address = request.getParameter("address");
		String phonenumber = request.getParameter("phonenumber");
		String comments = request.getParameter("comments");
		
		if(username==null || username.equals("")
				||name==null || name.equals("")
				||s_type == null
				||s_status == null
				||name == null){
			String msg = "操作不成功，输入不正确，请重新输入！";
			String msg_n= new String(msg.getBytes("UTF-8"),"UTF-8");
			request.setAttribute("AddMsg", msg_n);
			request.getRequestDispatcher("/view/user.jsp").forward(request, response);
			return;
		}
		
		if(isUserExist(username)){
			String msg = "操作不成功，用户名已存在，请重新输入！";
			String msg_n= new String(msg.getBytes("UTF-8"),"UTF-8");
			request.setAttribute("AddMsg", msg_n);
			request.getRequestDispatcher("/view/user.jsp").forward(request, response);
			return;
		}
		
		int type = Integer.parseInt(s_type);
		int status = Integer.parseInt(s_status);
		
		User usr = new User();
		usr.setUsername(username);
		usr.setPassword(password);
		usr.setType(type);
		usr.setStatus(status);
		usr.setName(name);
		usr.setIdnumber(idnumber);
		usr.setAddress(address);
		usr.setPhonenumber(phonenumber);
		usr.setComments(comments);
		
		UserDao dao = new UserDaoImpl();
		dao.save(usr);
		
		String msg = "用户" + username + "添加成功！";
		String msg_n= new String(msg.getBytes("UTF-8"),"UTF-8");
		request.setAttribute("AddMsg", msg_n);
		
		request.getRequestDispatcher("/view/user.jsp").forward(request, response);
	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		
		List list = null;
		UserDao dao = new UserDaoImpl();
		
		User usr = new User();
		usr.setUsername(username);
		usr.setName(name);
		
		list = dao.list(usr);
		/*
		if((username == null ||username.equals(""))
				&& (name==null || name.equals(""))){
			list = dao.list();
		}
		else{
			User usr = new User();
			usr.setUsername(username);
			usr.setName(name);
			
			list = dao.list(usr);
		}
		*/
		request.setAttribute("USERS",list);
		
		request.setAttribute("username",username);
		request.setAttribute("name",name);
		
		request.getRequestDispatcher("/view/userlist.jsp").forward(request, response);
	}
	

	protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("UserSession");
		if(id==null ||id.equals("") || user.getType() != Constant.TYPE_ADMIN){
			id = String.valueOf(user.getId());
		}
		
		UserDao dao = new UserDaoImpl();
		User bean = dao.get(new Integer(id).intValue());
		request.setAttribute("USER",bean);
		
		request.getRequestDispatcher("/view/useredit.jsp").forward(request, response);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String sid = request.getParameter("id");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int type = Integer.parseInt(request.getParameter("type"));
		int status = Integer.parseInt(request.getParameter("status"));
		String name = request.getParameter("name");
		String idnumber = request.getParameter("idnumber");
		String address = request.getParameter("address");
		String phonenumber = request.getParameter("phonenumber");
		String comments = request.getParameter("comments");
		
		System.out.println("test update");
		
		if(sid==null || sid.equals("")
				||username==null || username.equals("")
				||name==null || name.equals("")){
			String msg = "操作不成功，输入不正确，请重新输入！";
			String msg_n= new String(msg.getBytes("UTF-8"),"UTF-8");
			request.setAttribute("Msg", msg_n);
			request.getRequestDispatcher("/view/useredit.jsp").forward(request, response);
			return;
		}

		int id = Integer.parseInt(sid);
		
		User usr = new User();
		usr.setId(id);
		usr.setUsername(username);
		usr.setPassword(password);
		usr.setType(type);
		usr.setStatus(status);
		usr.setName(name);
		usr.setIdnumber(idnumber);
		usr.setAddress(address);
		usr.setPhonenumber(phonenumber);
		usr.setComments(comments);
		
		UserDao dao = new UserDaoImpl();
		dao.update(usr);
		
		String msg = "用户" + username + "修改成功！";
		String msg_n= new String(msg.getBytes("UTF-8"),"UTF-8");
		request.setAttribute("Msg", msg_n);
		
		request.getRequestDispatcher("/view/useredit.jsp").forward(request, response);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String[] ids = request.getParameterValues("ids");
		if(ids == null || ids.length<=0){
			list(request,response);
			return;
		}
		
		UserDao dao = new UserDaoImpl();
		dao.delete(ids);
		list(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected Boolean isUserExist(String username){

		UserDao dao = new UserDaoImpl();
		User usr = dao.get(username);
		if(usr!=null&&usr.getUsername()!=null){
			return true;
		}
		return false;
	}


	protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		
		List list = null;
		UserDao dao = new UserDaoImpl();
		
		if((username == null ||username.equals(""))
				&& (name==null || name.equals(""))){
			list = dao.list();
		}
		else{
			User usr = new User();
			usr.setUsername(username);
			usr.setName(name);
			
			list = dao.list(usr);
		}
		
		request.setAttribute("USERS",list);

		request.setAttribute("username",username);
		request.setAttribute("name",name);
		
		request.getRequestDispatcher("/view/userreport.jsp").forward(request, response);
	}
	

	protected void export(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		
		List<User> list = null;
		UserDao dao = new UserDaoImpl();
		
		if((username == null ||username.equals(""))
				&& (name==null || name.equals(""))){
			list = dao.list();
		}
		else{
			User usr = new User();
			usr.setUsername(username);
			usr.setName(name);
			
			list = dao.list(usr);
		}
		
		//response.setContentType("application/vnd.ms-excel");
		//response.setContentType("text/comma-separated-values");
		String filename = "UserReport.csv";
		response.setHeader("Content-Disposition", "attachment;filename="+filename);
		
		OutputStream os = response.getOutputStream();
		os.write(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF });
		os.flush();
		OutputStreamWriter out = new OutputStreamWriter(os, "UTF-8");    

		//PrintWriter out = response.getWriter();
		//out.write(new char[] {0xEF, 0xBB, 0xBF });
		//"帐户名"+"\t帐户类型"+"\t帐户状态"+"\t用户姓名"+"\t身份证号"+"\t地址"+"\t手机号"+"\t备注"   
		String sep = ","; 
		/*
		out.print(dao.getTitle(sep));
		out.print("\n");
		for(User u : list){
			out.print(u.getUsername());
			out.print(sep);
			out.print((u.getType()==1)?"管理员":"操作员");
			out.print(sep);
			out.print((u.getStatus()==0)?"启用":"禁用");
			out.print(sep);
			out.print(u.getName());
			out.print(sep);
			out.print(u.getIdnumber());
			out.print(sep);
			out.print(u.getAddress());
			out.print(sep);
			out.print(u.getPhonenumber());
			out.print(sep);
			out.print(u.getComments());
			out.print("\n");
		}
		*/
		out.write(dao.getTitle(sep));
		out.write("\n");
		for(User u : list){
			out.write(u.getUsername());
			out.write(sep);
			out.write((u.getType()==1)?"管理员":"操作员");
			out.write(sep);
			out.write((u.getStatus()==0)?"启用":"禁用");
			out.write(sep);
			out.write(u.getName());
			out.write(sep);
			out.write("\""+u.getIdnumber()+"\"");
			out.write(sep);
			out.write(u.getAddress());
			out.write(sep);
			out.write("\""+u.getPhonenumber()+"\"");
			out.write(sep);
			out.write(u.getComments());
			out.write("\n");
		}
		
		out.flush(); 
	}
	
}
