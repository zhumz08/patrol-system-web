package cn.zhaoliugang.guard.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.zhaoliugang.guard.bean.GuardSite;
import cn.zhaoliugang.guard.bean.User;
import cn.zhaoliugang.guard.dao.GuardSiteDao;
import cn.zhaoliugang.guard.dao.UserDao;
import cn.zhaoliugang.guard.dao.impl.GuardSiteDaoImpl;
import cn.zhaoliugang.guard.dao.impl.UserDaoImpl;

/**
 * Servlet implementation class GuardSiteServlet
 */
@WebServlet("/GuardSiteServlet")
public class GuardSiteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GuardSiteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		if (action != null && action.equals("save")) {
			save(request, response);
		} else if (action != null && action.equals("list")) {
			list(request, response);
		} else if (action != null && action.equals("get")) {
			get(request, response);
		} else if (action != null && action.equals("update")) {
			update(request, response);
		} else if (action != null && action.equals("delete")) {
			delete(request, response);
		} else {

		}
	}

	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String guardsiteid = request.getParameter("guardsiteid");
		String guardsitename = request.getParameter("guardsitename");
		String cameraid = request.getParameter("cameraid");
		String cameraname = request.getParameter("cameraname");
		String presetid = request.getParameter("presetid");

		if (guardsiteid == null || guardsiteid.equals("") || guardsitename == null || guardsitename.equals("")
				|| cameraid == null || cameraid.equals("")) {
			String msg = "操作不成功，输入不正确，请重新输入！";
			String msg_n = new String(msg.getBytes("UTF-8"), "UTF-8");
			request.setAttribute("Msg", msg_n);
			request.getRequestDispatcher("/view/guardsite.jsp").forward(request, response);
			return;
		}

		if (isExist(guardsiteid, guardsitename)) {
			String msg = "操作不成功，巡更站点已存在，请重新输入！";
			String msg_n = new String(msg.getBytes("UTF-8"), "UTF-8");
			request.setAttribute("Msg", msg_n);
			request.getRequestDispatcher("/view/guardsite.jsp").forward(request, response);
			return;
		}

		GuardSite bean = new GuardSite();
		bean.setGuardsiteid(guardsiteid);
		bean.setGuardsitename(guardsitename);
		bean.setCameraid(cameraid);
		bean.setCameraname(cameraname);
		bean.setPresetid(presetid);

		GuardSiteDaoImpl dao = new GuardSiteDaoImpl();
		dao.save(bean);

		String msg = "巡更站点" + guardsiteid + "添加成功！";
		String msg_n = new String(msg.getBytes("UTF-8"), "UTF-8");
		request.setAttribute("Msg", msg_n);

		request.getRequestDispatcher("/view/guardsite.jsp").forward(request, response);
	}

	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String guardsiteid = request.getParameter("guardsiteid");
		String guardsitename = request.getParameter("guardsitename");
		String cameraid = request.getParameter("cameraid");
		String cameraname = request.getParameter("cameraname");
		String presetid = request.getParameter("presetid");

		List list = null;
		GuardSiteDao dao = new GuardSiteDaoImpl();

		if ((guardsiteid == null || guardsiteid.equals("")) 
				&& (guardsitename == null || guardsitename.equals(""))) {
			list = dao.list();
		} else {
			GuardSite bean = new GuardSite();
			bean.setGuardsiteid(guardsiteid);
			bean.setGuardsitename(guardsitename);
			bean.setCameraid(cameraid);
			bean.setCameraname(cameraname);
			bean.setPresetid(presetid);

			list = dao.list(bean);
		}

		request.setAttribute("GUARDSITES", list);
		
		request.setAttribute("guardsiteid",guardsiteid);
		request.setAttribute("guardsitename",guardsitename);

		request.getRequestDispatcher("/view/guardsitelist.jsp").forward(request, response);
	}

	protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String id = request.getParameter("id");//id is guardsiteid from jsp
		if (id == null || id.equals("")) {
			request.getRequestDispatcher("/view/guardsitelist.jsp").forward(request, response);
			return;
		}

		GuardSiteDao dao = new GuardSiteDaoImpl();
		GuardSite bean = dao.getbyid(id);
		request.setAttribute("GUARDSITE", bean);

		request.getRequestDispatcher("/view/guardsiteedit.jsp").forward(request, response);
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String guardsiteid = request.getParameter("guardsiteid");
		String guardsitename = request.getParameter("guardsitename");
		String cameraid = request.getParameter("cameraid");
		String cameraname = request.getParameter("cameraname");
		String presetid = request.getParameter("presetid");

		if (guardsiteid == null || guardsiteid.equals("") || guardsitename == null || guardsitename.equals("")
				|| cameraid == null || cameraid.equals("")) {
			String msg = "操作不成功，参数不正确，请重新输入！";
			String msg_n = new String(msg.getBytes("UTF-8"), "UTF-8");
			request.setAttribute("Msg", msg_n);
			request.getRequestDispatcher("/view/guardsiteedit.jsp").forward(request, response);
			return;
		}

		if (isUpdataExist(guardsiteid, guardsitename)) {
			String msg = "操作不成功，巡更站点名称已存在，请重新输入！";
			String msg_n = new String(msg.getBytes("UTF-8"), "UTF-8");
			request.setAttribute("Msg", msg_n);
			request.getRequestDispatcher("/view/guardsiteedit.jsp").forward(request, response);
			return;
		}

		GuardSite bean = new GuardSite();
		bean.setGuardsiteid(guardsiteid);
		bean.setGuardsitename(guardsitename);
		bean.setCameraid(cameraid);
		bean.setCameraname(cameraname);
		bean.setPresetid(presetid);

		GuardSiteDaoImpl dao = new GuardSiteDaoImpl();
		dao.update(bean);

		String msg = "巡更站点" + guardsiteid + "更新成功！";
		String msg_n = new String(msg.getBytes("UTF-8"), "UTF-8");
		request.setAttribute("Msg", msg_n);

		request.getRequestDispatcher("/view/guardsiteedit.jsp").forward(request, response);
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String[] ids = request.getParameterValues("ids");
		if (ids == null || ids.length <= 0) {
			list(request, response);
			return;
		}

		GuardSiteDao dao = new GuardSiteDaoImpl();
		dao.delete(ids);
		list(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected Boolean isExist(String guardsiteid, String guardsitename) {

		GuardSiteDao dao = new GuardSiteDaoImpl();
		if (dao.getbyid(guardsiteid) != null || dao.getbyname(guardsitename) != null) {
			return true;
		}
		return false;
	}
	
	protected Boolean isUpdataExist(String guardsiteid, String guardsitename) {

		GuardSiteDao dao = new GuardSiteDaoImpl();
		GuardSite rec = dao.getbyname(guardsitename);
		if (rec!= null&&!rec.getGuardsiteid().equals(guardsiteid)) {
			return true;
		}
		return false;
	}

}
