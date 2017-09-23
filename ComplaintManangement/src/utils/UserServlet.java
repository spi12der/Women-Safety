package utils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import admin.AdminUtils;
import complain.ComplainUtils;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		LoginUtils obj=new LoginUtils();
		JSONObject user=obj.checkCredentials(username, password);
		String type=(String)user.get("type");
		String userId=(String)user.get("userId");
		if(type.equalsIgnoreCase("admin"))
		{
			AdminUtils aob=new AdminUtils();
			JSONObject message=aob.getAdminDetails();
			request.setAttribute("message", message.toJSONString());
			request.getRequestDispatcher("JSP/OFFICER/AdminHome.jsp").forward(request, response);	
		}
		else
		{
			ComplainUtils cobj=new ComplainUtils();
			JSONObject message=cobj.getComplains(userId);
			request.setAttribute("message", message.toJSONString());
			request.getRequestDispatcher("JSP/OFFICER/Complaints.jsp").forward(request, response);	
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
