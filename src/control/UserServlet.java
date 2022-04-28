package control;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.Page;
import model.Sex;
import model.Type;
import model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import util.DateUtil;
import util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	@Override
		protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
		
		String method = request.getParameter("method");
			if("login".equals(method)) {
				login(request,response);
			}else if("logout".equals(method)) {
				logout(request,response);
			}else if("getAll".equals(method)) {
				getAll(request,response);
			}else if("preAdd".equals(method)) {
				preAdd(request,response);
			}else if("save".equals(method)) {
				save(request,response);
			}else if("delUserById".equals(method)) {
				delUserById(request,response);
			}else if("save1".equals(method)) {
				save1(request,response);
			}
			
		}
	
 
	protected void save1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建磁盘工厂对象   创建一个指向硬盘的对象
		DiskFileItemFactory df = new DiskFileItemFactory();
		// 创建上传对象  把工厂加进去   上传对象指向硬盘
		ServletFileUpload upload = new ServletFileUpload(df);
		User user = new User();
		
		try {
			// 解析表单数据
			List<FileItem>  items = upload.parseRequest(request);
			for (FileItem its : items) {
				if(its.isFormField()) {
					// 文本域
					String name = its.getFieldName();
					if("username".equals(name)) {
						user.setUsername(its.getString("utf-8"));
					}else if("password".equals(name)) {
						user.setPassword(its.getString("utf-8"));
					}else if("realname".equals(name)) {
						user.setRealname(its.getString("utf-8"));
					}else if("sex".equals(name)) {
						user.setSex(its.getString("utf-8"));
					}else if("birthday".equals(name)) {
						user.setBirthday(DateUtil.stringToDate(its.getString("utf-8"), "yyyy-MM-dd"));
					}else if("tel".equals(name)) {
						user.setTel(its.getString("utf-8"));
					}else if("address".equals(name)) {
						user.setAddress(its.getString("utf-8"));
					}else if("type".equals(name)) {
						user.setType(its.getString("utf-8"));
					}
					
				}else {
					// 文件域
					// 文件域   简历.doc  19998888.doc
					String fileName = its.getName();
					String fileType = fileName.substring(fileName.lastIndexOf("."));
					// 取当前时间距离 1970年 的 毫秒数
					long currentTime = System.currentTimeMillis();
					// 指定文件 上传后 放置的位置
					String path = 
						request.getRealPath("/fileupload");
//					String path = "D:/kj01/fileupload";
					
					//System.out.println("path="+path);
					// path=D:\apache-tomcat-8.0.43\wtpwebapps\kj01\fileupload
					// 如果目录不存在  那么自动创建
					System.out.println(path);
					File f = new File(path);
					if(!f.exists()) {
						f.mkdir();
					}
					File file = new File(path,currentTime+fileType);
					its.write(file);
					user.setPhoto("fileupload/"+currentTime+fileType);	
					UserDao dao = new UserDaoImpl();
					boolean res = dao.saveUser(user);
					if(res) {
						response.sendRedirect("user?method=getAll");
					}
					
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	protected void delUserById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		UserDao dao = new UserDaoImpl();
		boolean result = dao.delUserById(id);
		if(result) {
			response.sendRedirect("user?method=getAll"); 
		}
		
	}
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String realname = request.getParameter("realname");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		String type = request.getParameter("type");
		User user = new User();
		user.setUsername(username);
		user.setRealname(realname);
		user.setPassword(password);
		user.setSex(sex);
		user.setBirthday(DateUtil.stringToDate(birthday, "yyyy-MM-dd"));
		user.setTel(tel);
		user.setAddress(address);
		user.setType(type);
		UserDao dao = new UserDaoImpl();
		boolean result = dao.saveUser(user);
		if(result) {
			response.sendRedirect("user?method=getAll");
		}		
	}
	protected void preAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao dao = new UserDaoImpl();
		List<Type> types = dao.getAllType();
		List<Sex> sexs = dao.getAllSex();
		request.setAttribute("types", types); 
		request.setAttribute("sexs", sexs); 
		request.getRequestDispatcher("userAdd.jsp").forward(request, response);		
	}
	
	protected void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 模糊查询 
		String realname = request.getParameter("realname");
    	Map map = new HashMap();
    	map.put("realname", realname);
		
		
		
		UserDao dao = new UserDaoImpl();
		// 默认是第1页
		int currentPage = 1;
		// 获取页面请求需要展现的页号
		String pageNo =  request.getParameter("pageNo");
		if(!StringUtil.empty(pageNo)) {
			currentPage =  Integer.parseInt(pageNo) ;
		}
		// 总条数
		int totalSize = dao.getAllCount(map);
		Page page = new Page();
		page.setPageSize(2);
		page.setCurrentPage(currentPage);
		page.setTotalSize(totalSize);
		page.setTotalPage(totalSize);
		
		List<User> users = dao.getAll(page,map);
		request.setAttribute("page", page);
		request.setAttribute("users", users); 
		request.setAttribute("realname", realname); 
		
		request.getRequestDispatcher("userList.jsp").forward(request, response);
		
	}
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		UserDao dao = new UserDaoImpl();
		User user = dao.login(u);
		if(user!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			response.sendRedirect("login.jsp");
		}
		
	}
 
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("login.jsp");
		
	}

}
