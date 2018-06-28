package com.najor.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.najor.dao.UserDAO;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap<String,String> mps;
    private String uPwd = "\t";
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String flag = request.getParameter("flag");
		if(flag.equals("sign_in")) {
			String user_name = request.getParameter("user_name");
			String password = request.getParameter("password");
			ServletContext sc = this.getServletContext();
			InputStream is = sc.getResourceAsStream("/WEB-INF/classes/user.txt");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = br.readLine();
			mps = new HashMap<String,String>();
			while(line != null) {
				String[] strs = line.split("=");
				String uname = strs[0];
				String pwd = strs[1];
				mps.put(uname,pwd);
				line = br.readLine();
			}
			int YorN = 0;
			Set<String> keys = mps.keySet();
			for(String key : keys) {
				if(key.equals(user_name) && password.equals(mps.get(key))) {
					YorN = 1;
					break;
				}
			}
			if(YorN == 1) {
				System.out.println("Succeeded!");
				response.getWriter().println("Succeeded!");
			}
			else {
				System.out.println("Failed!");
				response.getWriter().println("Failed!");
			}
		} else if(flag.equals("register")) {
			String user_name = request.getParameter("user_name");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			UserDAO ud = new UserDAO();
			boolean re = ud.regist(user_name,password,email,telephone);
			System.out.println(re);
			request.setAttribute("result", re);
			request.getRequestDispatcher("result.jsp").forward(request, response);
		} else if(flag.equals("validateName")) {
			String user_name = request.getParameter("user_name");
			UserDAO ud = new UserDAO();
			boolean res = ud.validateUserName(user_name);
			String result = "";
			if(res) {
				result = "✅";
			} else {
				result = "❌（用户名已被注册）";
			}
			response.getWriter().write(result);
			response.getWriter().flush();
			response.getWriter().close();
		} else if(flag.equals("testName")) {
			String user_name = request.getParameter("user_name");
			UserDAO ud = new UserDAO();
			boolean res = ud.validateUserName(user_name);
			String result = "";
			if(res) {
				result = "❌（用户名不存在）";
			} else {
				result = "✅";
				uPwd = ud.getPwd(user_name);
			}
			response.getWriter().write(result);
			response.getWriter().flush();
			response.getWriter().close();
		} else if(flag.equals("testPwd")) {
			String password = request.getParameter("password");
			String result = "";
			if(password.equals(uPwd)) {
				result = "✅";
			} else {
				result = "❌（密码错误）";
			}
			response.getWriter().write(result);
			response.getWriter().flush();
			response.getWriter().close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
