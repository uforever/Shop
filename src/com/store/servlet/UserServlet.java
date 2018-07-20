package com.store.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.dao.AddressDAO;
import com.store.dao.GameAreaDAO;
import com.store.dao.HouseDAO;
import com.store.dao.ProductDAO;
import com.store.dao.UserDAO;
import com.store.entity.Address;
import com.store.entity.House;
import com.store.entity.Product;
import com.store.entity.User;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserServlet() {
        super();
        System.out.println("UserServlet()");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		UserDAO ud = new UserDAO();
		GameAreaDAO gad = new GameAreaDAO();
		HouseDAO hd = new HouseDAO();
		ProductDAO pd = new ProductDAO();
		AddressDAO ad = new AddressDAO();
		String flag = request.getParameter("flag");
		int uid = (int)request.getSession().getAttribute("uid");
		if(flag.equals("getInfom")) {
			User theUser = new User();
			theUser = ud.getTheUser(uid);
			int gaid = theUser.getUgaid();
			String ganame = gad.getGaname(gaid);
			String ganet = gad.getGanet(gaid);
			request.setAttribute("theUser", theUser);
			request.setAttribute("ganame", ganame);
			request.setAttribute("ganet", ganet);
			request.getRequestDispatcher("pinfom.jsp").forward(request, response);
		} else if(flag.equals("changeInfom")) {
			long uphone = Long.parseLong(request.getParameter("uphone"));
			long uqq = Long.parseLong(request.getParameter("uqq"));
			int ugaid = Integer.parseInt(request.getParameter("ugaid"));
			boolean result = ud.updateInfom(uid, uphone, uqq, ugaid);
			if(result) {
				User theUser = new User();
				theUser = ud.getTheUser(uid);
				String ganame = gad.getGaname(ugaid);
				String ganet = gad.getGanet(ugaid);
				request.setAttribute("theUser", theUser);
				request.setAttribute("ganame", ganame);
				request.setAttribute("ganet", ganet);
				request.getRequestDispatcher("pinfom.jsp").forward(request, response);
			} else {
				Writer writer = response.getWriter();
				writer.write("修改失败!");
				writer.flush();
				writer.close();
			}
		} else if(flag.equals("getHouse")) {
			ArrayList<House> houseList = hd.getHouseList(uid);
			ArrayList<Product> productList = new ArrayList<>();
			ArrayList<String> pscnameList = new ArrayList<String>();
			for(House house : houseList) {
				Product product = new Product();
				int hpid = house.getHpid();
				product = pd.getTheProduct(hpid);
				productList.add(product);
				pscnameList.add(pd.getScname(pd.getPscid(hpid)));
			}
			request.setAttribute("houseList", houseList);
			request.setAttribute("productList", productList);
			request.setAttribute("pscnameList", pscnameList);
			request.getRequestDispatcher("phouse.jsp").forward(request, response);
		} else if(flag.equals("delHouse")) {
			int hid = Integer.parseInt(request.getParameter("hid"));
			boolean result = hd.delHouse(hid);
			if(result) {
				ArrayList<House> houseList = hd.getHouseList(uid);
				ArrayList<Product> productList = new ArrayList<>();
				ArrayList<String> pscnameList = new ArrayList<String>();
				for(House house : houseList) {
					Product product = new Product();
					int hpid = house.getHpid();
					product = pd.getTheProduct(hpid);
					productList.add(product);
					pscnameList.add(pd.getScname(pd.getPscid(hpid)));
				}
				request.setAttribute("houseList", houseList);
				request.setAttribute("productList", productList);
				request.setAttribute("pscnameList", pscnameList);
				request.getRequestDispatcher("phouse.jsp").forward(request, response);
			} else {
				Writer writer = response.getWriter();
				writer.write("删除失败!");
				writer.flush();
				writer.close();
			}
		} else if(flag.equals("addHouse")) {
			int huid = (int)request.getSession().getAttribute("uid");
			int hpid = Integer.parseInt(request.getParameter("hpid"));
			boolean result = hd.addHouse(huid, hpid);
			if(result) {
				System.out.println(uid+"号用户收藏了"+hpid+"号商品");
			} else {
				Writer writer = response.getWriter();
				writer.write("收藏失败!");
				writer.flush();
				writer.close();
			}
		} else if(flag.equals("getMoney")) {
			int umoney = ud.getUmoney(uid);
			request.setAttribute("umoney", umoney);
			request.getRequestDispatcher("paccount.jsp").forward(request, response);
		} else if(flag.equals("addMoney")) {
			int umoney = ud.getUmoney(uid);
			umoney += 5;
			boolean result = ud.updateUmoney(uid, umoney);
			if(result) {
				System.out.println(uid+"号用户偷了5元");
				request.setAttribute("umoney", umoney);
				request.getRequestDispatcher("paccount.jsp").forward(request, response);
			} else {
				Writer writer = response.getWriter();
				writer.write("偷钱失败!");
				writer.flush();
				writer.close();
			}
		} else if(flag.equals("getAddress")) {
			ArrayList<Address> addressList = ad.getAddressList(uid);
			request.setAttribute("addressList", addressList);
			request.getRequestDispatcher("paddress.jsp").forward(request, response);
		} else if(flag.equals("delAddress")) {
			int aid = Integer.parseInt(request.getParameter("aid"));
			boolean result = ad.delTheAddress(aid);
			if(result) {
				ArrayList<Address> addressList = ad.getAddressList(uid);
				request.setAttribute("addressList", addressList);
				request.getRequestDispatcher("paddress.jsp").forward(request, response);
			} else {
				Writer writer = response.getWriter();
				writer.write("删除收获地址失败!");
				writer.flush();
				writer.close();
			}
		} else if(flag.equals("addAddress")) {
			String awho = request.getParameter("awho");
			long aphone = Long.parseLong(request.getParameter("aphone"));
			String aname = request.getParameter("aname");
			boolean result = ad.addAAddress(uid, awho, aphone, aname);
			if(result) {
				ArrayList<Address> addressList = ad.getAddressList(uid);
				request.setAttribute("addressList", addressList);
				request.getRequestDispatcher("paddress.jsp").forward(request, response);
			} else {
				Writer writer = response.getWriter();
				writer.write("新建收获地址失败!");
				writer.flush();
				writer.close();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
