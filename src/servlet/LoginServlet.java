package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import logic.UserService;
import logic.UserDao;
import java.io.PrintWriter;


/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		User user = new User();
		
		user.setAccount(request.getParameter("account"));
		user.setPassword(request.getParameter("password"));
		
		UserService us = new UserService();
		
		int result = us.get(user);
		
		if(result == 0){
			
	        HttpSession session = request.getSession();
	        
	        //根据account从数据库获取用户信息
	        UserDao ud = new UserDao();
	        user = ud.get(user.getAccount());
	        
	        session.setAttribute("username", user.getUsername());
	        session.setAttribute("account", user.getAccount());
	        
	        System.out.println("login"+session.getAttribute("account"));

			response.sendRedirect(request.getContextPath()+"/manage");		
		}
		else{		
			response.setHeader("refresh", "2;URL=main");
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print("用户名或密码错误");
			out.flush();
			out.close();
		}
		
	}

}
