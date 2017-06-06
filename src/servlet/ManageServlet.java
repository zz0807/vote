package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.VoteInfoDao;
import java.sql.ResultSet;
import java.util.ArrayList;
import bean.Vote;


/**
 * Servlet implementation class ManageServlet
 */
@WebServlet("/manage")
public class ManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        
        VoteInfoDao vinfod = new VoteInfoDao();
               
        ArrayList<Vote> a = vinfod.get((String)session.getAttribute("account"));
        
        request.setAttribute("result", a);
        request.setAttribute("username", session.getAttribute("username"));
        request.getRequestDispatcher("/WEB-INF/manage.jsp").forward(request, response);

		
	}

	

}
