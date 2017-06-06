package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.VoteItemDao;
import logic.VoteService;

/**
 * Servlet implementation class PlusServlet
 */
@WebServlet("/plus")
public class PlusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlusServlet() {
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
		VoteItemDao vitemd = new VoteItemDao();
		
		response.setHeader("refresh", "2;URL=main");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if(request.getParameter("select_item")!=null){
			if(vitemd.plus(Integer.parseInt(request.getParameter("select_item")))==1)
				out.print("投票成功,2秒后跳转");
			else out.print("系统错误");
		}
		if(request.getParameterValues("select_items")!=null){
			String[] items = request.getParameterValues("select_items");
			VoteService vs = new VoteService();
			if(vs.plus(items)==1)
				out.print("投票成功,2秒后跳转");
			else out.print("系统错误");
		}
	}
	
}
