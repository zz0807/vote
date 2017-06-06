package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList; 
import java.util.Date;
import java.text.SimpleDateFormat;

import bean.Vote;
import bean.Item;
import logic.VoteService;
/**
 * Servlet implementation class NewVoteServlet
 */
@WebServlet("/newvote")
public class NewVoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewVoteServlet() {
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
        HttpSession session = request.getSession();

        //vote对象创建
		Vote vote = new Vote();
		
		vote.setTheme(request.getParameter("theme"));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		vote.setStartDate(df.format(new Date()));
		vote.setEndDate(request.getParameter("end_date"));
		vote.setOwner((String)session.getAttribute("username"));
		vote.setType(request.getParameter("type"));
		vote.setAccount((String)session.getAttribute("account"));
		
		System.out.println("session"+session.getAttribute("account"));
		System.out.println("session"+session.getAttribute("username"));

		
		String temp[] = request.getParameterValues("item");
		ArrayList<Item> items = new ArrayList<Item>();
		//去掉空字符串
		for(String str:temp){     
			if(str!=null&&str!=""){
				Item item = new Item();
				item.setItem(str);	
				items.add(item);
			}
		    } 
		vote.setItem(items);
		
		VoteService vs = new VoteService();
		int result = vs.add(vote);
		System.out.println("add result"+result);
		response.setHeader("refresh", "2;"+request.getContextPath()+"/manage");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if(result == -1){
			out.print("系统错误");
		}
		else{
			out.print("发起成功");
		}
		out.flush();
		out.close();
		
		
	}

}
