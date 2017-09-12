package com.stu.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stu.dto.Student;
import com.stu.service.StudentService;

/**
 * Servlet implementation class StudentAction
 */
@WebServlet("/StudentAction")
public class StudentAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String operate = request.getParameter("operate");
		if (operate == null || operate.equals("")) {
			return;
		} else {
			if (operate.equals("sign")) {
				doSign(request, response);
				// } else if (operate.equals("updateStatus")) {
				// updateStatus(request, response);
			} else if (operate.equals("listStuYes")) {
				listStuYes(request, response);
			} else if (operate.equals("listStuNo")) {
				listStatusNo(request, response);
			}
		}
	}

	private void listStatusNo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<Student> aList = null;
		StudentService as = null;
		int pageNo = 1, pageSize = 6, count = 0;
		Student stu = new Student();
		try {
			as = new StudentService();
			if (request.getParameter("pageNo") != null && !request.getParameter("pageNo").equals(""))
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			stu.setStatus(0);
			count = as.getCount(stu);
			int totalPage = (int) Math.ceil((float) count / pageSize);
			if (pageNo < 1)
				pageNo = 1;
			if (pageNo > totalPage)
				pageNo = totalPage;
			aList = as.listStuNo(pageNo, pageSize);
			request.setAttribute("aList", aList);
			request.setAttribute("count", count);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("totalPage", totalPage);
			request.getRequestDispatcher("listStuNo.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void listStuYes(HttpServletRequest request, HttpServletResponse response) {

		// TODO Auto-generated method stub
		List<Student> aList = null;
		StudentService as = null;
		int pageNo = 1, pageSize = 6, count = 0;
		Student stu = new Student();
		try {
			as = new StudentService();
			if (request.getParameter("pageNo") != null && !request.getParameter("pageNo").equals(""))
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			stu.setStatus(1);
			count = as.getCount(stu);
			int totalPage = (int) Math.ceil((float) count / pageSize);
			if (pageNo < 1)
				pageNo = 1;
			if (pageNo > totalPage)
				pageNo = totalPage;
			aList = as.listStuYes(pageNo, pageSize);
			request.setAttribute("aList", aList);
			request.setAttribute("count", count);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("totalPage", totalPage);
			request.getRequestDispatcher("listStuYes.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void doSign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stuName = request.getParameter("stuName");
		String stuNum = request.getParameter("stuNum");
		String sessionName=request.getParameter("sessionName");
		StringBuffer sbf = null;
		StudentService as = null;
		String ip = getIpAddr(request);
		if (!ip.equals("218.23.237.240")) {
			request.setAttribute("msg", ip + "禁止签到！因为检测到你不在集训队！想要签到请到2517插上你的网线再访问本页面！");
			goJsp(request, response);
			return;
		}
		try {
			as = new StudentService();
			sbf = new StringBuffer();
			if (stuName == null || stuName.equals("")) {
				sbf.append("姓名不能为空！");
			}
			if (stuNum == null || stuNum.equals("")) {
				sbf.append("学号不能为空！");
			}
			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				int result=as.searchStatus(stuNum);
				if (result==1) {
					request.setAttribute("msg", "你已经签到了，不可重复签到！！");
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				} else {
					Student stu = as.sign(stuName, stuNum);
					if(sessionName==null||sessionName.equals("")&&stu!=null){
						if(stu.getStuName()!=null||stu.getStuName()!=""){
							HttpSession session=request.getSession();
							session.setAttribute("stuName", stu.getStuName());	
						}
					
					}
					if (stu != null) {
						stu.setStatus(1);
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date d = new Date();
						String strd = dateFormat.format(d);
						System.out.println(strd + "------");
						int time = subStringToInt(strd);
						System.out.println(time + "----");
						if (time < 1850 || time > 1910) {
							request.setAttribute("msg", "请在18:50到19:10之间签到！");
							goJsp(request, response);
							return;
						}
						stu.setTime(strd);
						stu.setSessionName(sessionName);
						if (as.updateStatus(stu)) {
							request.setAttribute("msg", "签到成功！ ");
							request.getRequestDispatcher("login.jsp").forward(request, response);

						} else {
							request.setAttribute("msg", "用户名或密码错误，或者用户不存在 ");
							System.out.println(stu);
							request.getRequestDispatcher("login.jsp").forward(request, response);
						}

					} else {
						request.setAttribute("msg", "用户名或密码错误，或者用户不存在 ");
						request.getRequestDispatcher("login.jsp").forward(request, response);

					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int subStringToInt(String s) {
		StringBuffer sb = new StringBuffer();
		char ch = s.charAt(11);
		char ch1 = s.charAt(12);
		char ch2 = s.charAt(14);
		char ch3 = s.charAt(15);
		sb.append(ch).append(ch1).append(ch2).append(ch3);
		int time = Integer.parseInt(sb.toString());
		return time;
	}

	private void goJsp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);

	}

	private String getIpAddr(HttpServletRequest request) { // 获取外网ip
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
