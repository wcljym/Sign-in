package com.stu.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stu.dao.IAdmin;
import com.stu.dao.impl.AdminImpl;
import com.stu.dto.Admin;

/**
 * Servlet implementation class AdminAction
 */
@WebServlet("/AdminAction")
public class AdminAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		if(method==null||method.equals("")){
			return ;
		}else if(method.equals("login")){
			try {
				doLogin(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		IAdmin adimpl=new AdminImpl();
		String name=request.getParameter("name");
		String pass=request.getParameter("pass");
		Admin admin=new Admin();
		admin.setName(name);
		admin.setPass(pass);
		if(adimpl.login(admin)){
			response.sendRedirect("StudentAction?operate=listStuYes");
		}else{
			request.setAttribute("msg", "µÇÂ½Ê§°Ü");
			request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
		}
	}

}
