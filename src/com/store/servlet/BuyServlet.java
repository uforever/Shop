package com.store.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.dao.AddressDAO;
import com.store.dao.OrderDAO;
import com.store.dao.ProductDAO;
import com.store.dao.TrolleyDAO;
import com.store.dao.UserDAO;
import com.store.entity.Address;
import com.store.entity.Order;
import com.store.entity.Product;
import com.store.entity.Trolley;

@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BuyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		UserDAO ud = new UserDAO();
		TrolleyDAO td = new TrolleyDAO();
		ProductDAO pd = new ProductDAO();
		AddressDAO ad = new AddressDAO();
		OrderDAO od = new OrderDAO();
		int uid = (int)request.getSession().getAttribute("uid");
		String flag = request.getParameter("flag");
		int umoney = ud.getUmoney(uid);
		if(flag.equals("tobuy")) {
			int moneySum = 0;
			int pprice = 0;
			int tnum = 0;
			ArrayList<Address> addressList = ad.getAddressList(uid);
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
			request.setAttribute("umoney", umoney);
			request.setAttribute("moneySum", moneySum);
			request.setAttribute("addressList", addressList);
			request.setAttribute("trolleyList", trolleyList);
			request.setAttribute("productList", productList);
			request.getRequestDispatcher("pay.jsp").forward(request, response);
		} else if(flag.equals("dobuy")) {
			int oaid = Integer.parseInt(request.getParameter("oaid"));
			int omoney = Integer.parseInt(request.getParameter("omoney"));
			String ogoods = request.getParameter("ogoods");
			boolean result = od.createOrder(oaid, uid, omoney, ogoods);
			if(result) {
				int tempmoney = ud.getUmoney(uid);
				tempmoney -= omoney;
				boolean tluser = ud.updateUmoney(uid, tempmoney);
				if(tluser) {
					ArrayList<Order> orderList = od.getOrderList(uid);
					ArrayList<Address> addressList = new ArrayList<Address>();
					for(Order order : orderList) {
						Address address = ad.getTheAddress(order.getOaid());
						addressList.add(address);
					}
					request.setAttribute("orderList", orderList);
					request.setAttribute("addressList", addressList);
					request.getRequestDispatcher("porder.jsp").forward(request, response);
				}
			}
		} else if(flag.equals("getOrder")) {
			ArrayList<Order> orderList = od.getOrderList(uid);
			ArrayList<Address> addressList = new ArrayList<Address>();
			for(Order order : orderList) {
				Address address = ad.getTheAddress(order.getOaid());
				addressList.add(address);
			}
			request.setAttribute("orderList", orderList);
			request.setAttribute("addressList", addressList);
			request.getRequestDispatcher("porder.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
