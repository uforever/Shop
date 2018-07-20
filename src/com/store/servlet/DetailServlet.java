package com.store.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.dao.ProductDAO;

@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DetailServlet() {
        super();
        System.out.println("DetailServlet()");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ProductDAO pd = new ProductDAO();
//		HttpSession session = request.getSession();
		
		int pid = Integer.parseInt(request.getParameter("pid"));
		String pname = pd.getPname(pid);
		int pprice = pd.getPprice(pid);
		String ppicpath = pd.getPpicpath(pid);
//		session.setAttribute("pname", pname);
//		session.setAttribute("pprice", pprice);
//		session.setAttribute("ppicpath", ppicpath);
		request.setAttribute("pid", pid);
		request.setAttribute("pname", pname);
		request.setAttribute("pprice", pprice);
		request.setAttribute("ppicpath", ppicpath);
		request.getRequestDispatcher("detail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
