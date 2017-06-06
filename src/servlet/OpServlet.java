package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.VoteInfoDao;
/**
 * Servlet implementation class OpServlet
 */
@WebServlet("/op")
public class OpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		VoteInfoDao vinfod = new VoteInfoDao();
		
		response.setHeader("refresh", "2;URL=index.jsp");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		if(vinfod.modifyStatus(Integer.parseInt(request.getParameter("item_id")), Integer.parseInt(request.getParameter("status")))!=1)
			out.print(-1);
		else out.print(0);

		
	}

}
