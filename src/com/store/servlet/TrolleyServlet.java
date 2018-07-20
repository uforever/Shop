package com.store.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.store.dao.ProductDAO;
import com.store.dao.TrolleyDAO;
import com.store.entity.Product;
import com.store.entity.Trolley;

@WebServlet("/TrolleyServlet")
public class TrolleyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TrolleyServlet() {
        super();
        System.out.println("TrolleyServlet()");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ProductDAO pd = new ProductDAO();
		TrolleyDAO td = new TrolleyDAO();
		String flag = request.getParameter("flag");
		
		if(flag.equals("gotoTrolley")) {
			int moneySum = 0;
			int pprice = 0;
			int tnum = 0;
			int uid = (int)request.getSession().getAttribute("uid");
			ArrayList<Trolley> trolleyList = td.getTrolleyList(uid);
			ArrayList<Product> productList = new ArrayList<Product>();
			for(Trolley trolley:trolleyList) {
				Product product = new Product();
				product = pd.getTheProduct(trolley.getTpid());
				productList.add(product);
				pprice = pd.getPprice(trolley.getTpid());
				tnum = trolley.getTnum();
				moneySum += pprice * tnum;
			}
			request.setAttribute("moneySum", moneySum);
			request.setAttribute("trolleyList", trolleyList);
			request.setAttribute("productList", productList);
			request.getRequestDispatcher("trolley.jsp").forward(request, response);
		} else if(flag.equals("addTrolley")) {
			int tuid = (int)request.getSession().getAttribute("uid");
			int tpid = Integer.parseInt(request.getParameter("tpid"));
			int tnum = Integer.parseInt(request.getParameter("tnum"));
			boolean result =  td.addTrolley(tuid, tpid, tnum);
			if(result) {
				int trolleySum = (int)request.getSession().getAttribute("trolleySum");
				trolleySum += tnum;
				request.getSession().setAttribute("trolleySum", trolleySum);
			} else {
				Writer writer = response.getWriter();
				writer.write("加入购物车失败!");
				writer.flush();
				writer.close();
			}
		} else if(flag.equals("delTrolley")) {
			int tid = Integer.parseInt(request.getParameter("tid"));
			boolean result = td.delTheTrolley(tid);
			if(result) {
				int trolleySum = (int)request.getSession().getAttribute("trolleySum");
				int tNum = td.getTnum(tid);
				trolleySum -= tNum;
				int moneySum = 0;
				int pprice = 0;
				int tnum = 0;
				int uid = (int)request.getSession().getAttribute("uid");
				ArrayList<Trolley> trolleyList = td.getTrolleyList(uid);
				ArrayList<Product> productList = new ArrayList<Product>();
				for(Trolley trolley:trolleyList) {
					Product product = new Product();
					product = pd.getTheProduct(trolley.getTpid());
					productList.add(product);
					pprice = pd.getPprice(trolley.getTpid());
					tnum = trolley.getTnum();
					moneySum += pprice * tnum;
				}
				request.getSession().setAttribute("trolleySum", trolleySum);
				request.setAttribute("moneySum", moneySum);
				request.setAttribute("trolleyList", trolleyList);
				request.setAttribute("productList", productList);
				request.getRequestDispatcher("trolley.jsp").forward(request, response);
			} else {
				Writer writer = response.getWriter();
				writer.write("删除失败!");
				writer.flush();
				writer.close();
			}
		} else if(flag.equals("test")) {
			System.out.println("chenggong");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
