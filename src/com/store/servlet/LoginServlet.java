package com.store.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.store.dao.UserDAO;
import com.store.dao.TrolleyDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        System.out.println("LoginServlet()");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		UserDAO ud = new UserDAO();
		TrolleyDAO td = new TrolleyDAO();
		HttpSession session = request.getSession();
		
		String flag = request.getParameter("flag");
		if(flag.equals("register")) {
			String uname = request.getParameter("uname");
			String upwd = request.getParameter("upwd");
			long uphone = Long.parseLong(request.getParameter("uphone"));
			long uqq = Long.parseLong(request.getParameter("uqq"));
			
			boolean result = ud.register(uname, upwd, uphone, uqq);
			System.out.println(result);
			
			if(result) {
				request.setAttribute("result", "注册成功！");
				request.getRequestDispatcher("loginresult.jsp").forward(request, response);
			} else {
				request.setAttribute("result", "注册失败！");
				request.getRequestDispatcher("loginresult.jsp").forward(request, response);
			}
		} else if(flag.equals("cUname")) {
			String tpuname = request.getParameter("tpuname");
			boolean result = ud.validateUname(tpuname);
			String rText = "";
			if(result) {
				rText = "用户名可用!";
			} else {
				rText = "用户名已被注册!";
			}
			response.getWriter().write(rText);
			response.getWriter().flush();
			response.getWriter().close();
		} else if(flag.equals("sign_in")) {
			String uname = request.getParameter("uname");
			String upwd = request.getParameter("upwd");
			boolean result = ud.validateUname(uname);
			if(result) {
				request.setAttribute("result", "登录失败！");
				request.getRequestDispatcher("loginresult.jsp").forward(request, response);
			} else {
				String dUpwd = ud.getUpwd(uname);
				if(upwd.equals(dUpwd)) {
					int uid = ud.getUid(uname);
					int trolleySum = td.getTrolleySum(uid);
					session.setAttribute("uid", uid);
					session.setAttribute("trolleySum", trolleySum);
					request.setAttribute("result", "登录成功！");
					request.getRequestDispatcher("loginresult.jsp").forward(request, response);
				} else {
					request.setAttribute("result", "登录失败！");
					request.getRequestDispatcher("loginresult.jsp").forward(request, response);
				}
			}
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
