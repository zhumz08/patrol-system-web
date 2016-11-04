package cn.zhaoliugang.guard.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import cn.zhaoliugang.guard.bean.GuardRecord;
import cn.zhaoliugang.guard.bean.User;
import cn.zhaoliugang.guard.dao.GuardRecordDao;
import cn.zhaoliugang.guard.dao.impl.GuardRecordDaoImpl;

/**
 * Servlet implementation class GuardRecordServlet
 */
@WebServlet("/GuardRecordServlet")
public class GuardRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuardRecordServlet() {
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		

        String filename = "";

		int i_uid = 0;
       
		
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try
        {
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> itr = items.iterator();
            
            Map<String,byte[]> itemMap = new HashMap<String,byte[]>();
            
            while (itr.hasNext())
            {
                FileItem item = (FileItem) itr.next();
                if (item.isFormField())//重新获取form数据
                {
                	String p_name = item.getFieldName();
                	itemMap.put(p_name, item.get());
                }
            }
            
            String uid = new String(itemMap.get("uid"));//获取的数据均为null，上传图片使用的特殊form格式，下面会重新获取数据
            String username = new String(itemMap.get("username"));
    		String name = new String(itemMap.get("name"),"UTF-8");
//    		String guardpicture = itemMap.get("guardpicture");
    		String guardaddress = new String(itemMap.get("guardaddress"));
    		String comments = "comment" ; // new String(itemMap.get("comments"));
    		
    		String guardtime = null;
    		if(guardtime==null || guardtime.equals("")){
    			guardtime = getDataTimeString(false);
    		}
            
            
            if(uid==null||uid.equals("")){
            	HttpSession session = request.getSession();
        		User usr = (User)session.getAttribute("UserSession");
        		if(usr!=null){
            		uid = Integer.toString(usr.getId());
        		}
            }
            
            if(uid==null || uid.equals("")
    				||guardaddress==null || guardaddress.equals("")){
    			String msg = "操作不成功，输入不正确，请重新输入！";
    			String msg_n= new String(msg.getBytes("UTF-8"),"UTF-8");
    			request.setAttribute("Msg", msg_n);
    			request.getRequestDispatcher("/view/guardrecord.jsp").forward(request, response);
    			return;
    		}
            

    		if(uid!=null&&!uid.equals("")){
    			i_uid = Integer.parseInt(uid);
    		}
    		User usr = new User();
    		usr.setId(i_uid);
    		usr.setName(name);
    		if(isRepeatPost(usr)){
    			String msg = "刚刚已经提交，请不要重复提交！";
    			String msg_n= new String(msg.getBytes("UTF-8"),"UTF-8");
    			request.setAttribute("Msg", msg_n);
    			request.getRequestDispatcher("/view/guardrecord.jsp").forward(request, response);
    			return;
    		}
    		
    		byte[] imgArr = itemMap.get("imgFile");
    		
    		Integer rdm = new Random().nextInt(10000);
    		
        	ServletContext servletContext = this.getServletContext();
        	String realpath = servletContext.getRealPath("/");
        	
            String path = "";
            path = getdir(realpath,uid);
            filename = path + getDataTimeString(true) + rdm.toString() + ".jpg";
            
            File imgFile =  new File(realpath,filename);
            System.out.println(imgFile.getAbsolutePath());
            FileUtils.writeByteArrayToFile(imgFile, imgArr);
            
            
            String guardpicture = filename;
    		
    		GuardRecord bean = new GuardRecord();
    		bean.setUid(i_uid);
    		bean.setUsername(username);
    		bean.setName(name);
    		bean.setGuardtime(guardtime);
    		bean.setGuardpicture(guardpicture);
    		bean.setGuardaddress(guardaddress);
    		bean.setComments(comments);
    		
    		GuardRecordDaoImpl dao = new GuardRecordDaoImpl();
    		dao.save(bean);
    		
    		String msg = "巡更记录添加成功，站点"+guardaddress+"！";
    		String msg_n= new String(msg.getBytes("UTF-8"),"UTF-8");
    		request.setAttribute("Msg", msg_n);
    		
    		System.out.println(msg);
            
            /*itr = items.iterator();
            while (itr.hasNext())
            {
                FileItem item = (FileItem) itr.next();
                if (!item.isFormField())//为图像数据
                {

                	ServletContext servletContext = this.getServletContext();
                	
                	String realpath = servletContext.getRealPath("/");
                	
                    String path = "";
                    path = getdir(realpath,uid);
                    
                    if (item.getName() != null && !item.getName().equals(""))
                    {
                    	System.out.println("程序的目录:" + realpath);
                    	
                        System.out.println("上传文件的大小:" + item.getSize());
                        System.out.println("上传文件的类型:" + item.getContentType());
                        System.out.println("上传文件的名称:" + item.getName());

                        File tempFile = new File(item.getName());
                        Integer rdm = new Random().nextInt(10000);
                        
                        filename = path + getDataTimeString(true) + rdm.toString() 
                        	+ tempFile.getName().substring(tempFile.getName().indexOf('.'));
                        
                        String writefilename = realpath + filename;
                        File file = new File(writefilename);
                        item.write(file);
                        
                        System.out.println("保存的上传文件:" + file.getPath());
                    }
                    else
                    {
                        String msg = "巡更记录添加不成功，没有选择巡更照片文件！";
                		String msg_n= new String(msg.getBytes("UTF-8"),"UTF-8");
                		request.setAttribute("Msg", msg_n);

                		request.getRequestDispatcher("/view/guardrecord.jsp").forward(request, response);
                		return;
                    }
                    
                    break;//只处理第一个文件
                }
            }*/
            
        }
        catch (FileUploadException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        
       

		request.getRequestDispatcher("/view/guardrecord.jsp").forward(request, response);
	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		
		List list = null;
		GuardRecordDao dao = new GuardRecordDaoImpl();
		
		GuardRecord bean = new GuardRecord();

		bean.setUsername(username);
		bean.setName(name);
		bean.setStarttime(starttime);
		bean.setEndtime(endtime);
		
		list = dao.list(bean);
		
		request.setAttribute("GUARDRECORDS",list);

		request.setAttribute("username",username);
		request.setAttribute("name",name);
		request.setAttribute("startTime",starttime);
		request.setAttribute("endTime",endtime);
		
		request.getRequestDispatcher("/view/guardrecordlist.jsp").forward(request, response);
	}
	

	protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String id = request.getParameter("id");
		if(id==null||id.equals("")){
			request.getRequestDispatcher("/view/guardrecord.jsp").forward(request, response);
			return;
		}
		
		GuardRecordDao dao = new GuardRecordDaoImpl();
		GuardRecord bean = dao.get(new Integer(id).intValue());
		request.setAttribute("GUARDRECORD",bean);
		
		request.getRequestDispatcher("/view/guardrecord.jsp").forward(request, response);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int id = Integer.parseInt(request.getParameter("id"));
		int uid = Integer.parseInt(request.getParameter("uid"));
		String name = request.getParameter("name");
		String guardtime = request.getParameter("guardtime");
		String guardpicture = request.getParameter("guardpicture");
		String guardaddress = request.getParameter("guardaddress");
		String comments = request.getParameter("comments");
		
		if(guardpicture==null || guardpicture.equals("")
				||guardaddress==null || guardaddress.equals("")){
			String msg = "操作不成功，参数不正确，请重新输入！";
			String msg_n= new String(msg.getBytes("UTF-8"),"UTF-8");
			request.setAttribute("Msg", msg_n);
			request.getRequestDispatcher("/view/guardrecord.jsp").forward(request, response);
			return;
		}
		
		GuardRecord bean = new GuardRecord();
		bean.setUid(uid);
		bean.setName(name);
		bean.setGuardtime(guardtime);
		bean.setGuardpicture(guardpicture);
		bean.setGuardaddress(guardaddress);
		bean.setComments(comments);
		
		GuardRecordDaoImpl dao = new GuardRecordDaoImpl();
		dao.update(bean);
		
		String msg = "巡更记录" + guardaddress +" "+ guardtime + "添加成功！";
		String msg_n= new String(msg.getBytes("UTF-8"),"UTF-8");
		request.setAttribute("Msg", msg_n);
		
		request.getRequestDispatcher("/view/guardrecord.jsp").forward(request, response);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String[] ids = request.getParameterValues("ids");
		if(ids == null || ids.length<=0){
			list(request,response);
			return;
		}
		
		GuardRecordDao dao = new GuardRecordDaoImpl();
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
	
	private String getdir(String realpath,String uid)
    {
		if(uid == null || uid.equals("")){
			return null;
		}
		
        String path = "upload/" + getDataString() + "/" + uid + "/";

        try
        {
            java.io.File file = new java.io.File(realpath + path);
            if (!file.exists())
            {
                if (!file.mkdirs())
                {
                    return "";
                }
            }
            return path;
        }
        catch (Exception ex)
        {
            return "";
        }
        finally
        {

        }
    }

    /*
     * 获取当前时间
     */
    private static String getDataTimeString(Boolean isfilename)
    {
        try
        {
            SimpleDateFormat formatter = null;
            if (!isfilename)
            {
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
            else
            {
                formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            }
            Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
            return formatter.format(curDate);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return "";
        }
    }

    /*
     * 获取当前日期
     */
    private static String getDataString()
    {
        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
            return formatter.format(curDate);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return "";
        }
    }
    
    private static String getDataString(int second)
    {
        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date curDate = new Date(System.currentTimeMillis() + second*1000);// 获取当前时间
            return formatter.format(curDate);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return "";
        }
    }
    
    private static String getDataTimeString(Boolean isfilename, int second)
    {
        try
        {
            SimpleDateFormat formatter = null;
            if (!isfilename)
            {
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
            else
            {
                formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            }
            Date curDate = new Date(System.currentTimeMillis() + second*1000);// 获取当前时间
            return formatter.format(curDate);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return "";
        }
    }
    
    private Boolean isRepeatPost(User usr){
		List list = null;
		GuardRecordDao dao = new GuardRecordDaoImpl();
		
		GuardRecord bean = new GuardRecord();
		
		bean.setUid(usr.getId());
		bean.setName(usr.getName());
		bean.setStarttime(getDataTimeString(false,-10));
		bean.setEndtime(getDataTimeString(false));
		list = dao.list(bean);
		if (list!=null && list.size()>0){
			return true;
		}
		else{
			return false;
		}
    }


	protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		
		List list = null;
		GuardRecordDao dao = new GuardRecordDaoImpl();
		
		GuardRecord bean = new GuardRecord();

		bean.setUsername(username);
		bean.setName(name);
		bean.setStarttime(starttime);
		bean.setEndtime(endtime);
		
		list = dao.list(bean);
		
		request.setAttribute("GUARDRECORDS",list);

		request.setAttribute("username",username);
		request.setAttribute("name",name);
		request.setAttribute("startTime",starttime);
		request.setAttribute("endTime",endtime);
		
		request.getRequestDispatcher("/view/guardrecordreport.jsp").forward(request, response);
	}
	

	protected void export(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		
		List<GuardRecord> list = null;
		GuardRecordDao dao = new GuardRecordDaoImpl();
		
		GuardRecord bean = new GuardRecord();

		bean.setUsername(username);
		bean.setName(name);
		bean.setStarttime(starttime);
		bean.setEndtime(endtime);
		
		list = dao.list(bean);
		
		String filename = "GuardReport.csv";
		response.setHeader("Content-Disposition", "attachment;filename="+filename);
		
		OutputStream os = response.getOutputStream();
		os.write(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF });
		os.flush();
		OutputStreamWriter out = new OutputStreamWriter(os, "UTF-8");    

		//"帐户名"+sep+"巡更人员"+sep+"巡更时间"+sep+"巡更图片"+sep+"巡更地点"+sep+"备注信息"  
		String sep = ",";
		out.write(dao.getTitle(sep));
		out.write("\n");
		for(GuardRecord u : list){
			out.write(u.getUsername());
			out.write(sep);
			out.write(u.getName());
			out.write(sep);
			out.write(u.getGuardtime());
			out.write(sep);
			out.write(u.getGuardpicture().equals("")?"无":"有");
			out.write(sep);
			out.write(u.getGuardaddress());
			out.write(sep);
			out.write(u.getComments());
			out.write("\n");
		}
		
		out.flush(); 
		
	}
	
}
