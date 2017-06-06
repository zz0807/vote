package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.VoteService;
import java.util.ArrayList;
import bean.Vote;
/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/main")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		VoteService vs = new VoteService();
		ArrayList<Vote> result = vs.get();
		
		request.setAttribute("result",result);
        request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

}
