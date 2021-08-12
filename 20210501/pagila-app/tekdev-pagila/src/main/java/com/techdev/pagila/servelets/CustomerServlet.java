package com.techdev.pagila.servelets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.techdev.pagila.dao.PagilaDao;
import com.techdev.pagila.dao.PagilaDaoImpl;
import com.techdev.pagila.dto.Customer;

@WebServlet(name = "CustomerServlet", urlPatterns = { "/customer/*" })
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = -611490866673789459L;
	private static final Logger logger = LoggerFactory.getLogger(CustomerServlet.class);

	PagilaDao dao = new PagilaDaoImpl();

	private void returnMessage(HttpServletResponse resp, String message) throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		out.write(message.getBytes());
		out.flush();
		out.close();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		System.out.println("............ " + pathInfo);
		String[] pathParts = pathInfo != null ? pathInfo.split("/") : null;
		if (pathParts != null && pathParts.length > 1) {
			String idPart = pathParts[1];
			Customer c = dao.getCustomerId(Long.parseLong(idPart));
			if (c == null) {
				returnMessage(response, "No customer is available.");
				return;
			}
			//returnMessage(response, c.toString());
			request.setAttribute("customer", c);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/customerinfo.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		Customer c = null;
		String[] pathParts = pathInfo != null ? pathInfo.split("/") : null;
		if (pathParts != null && pathParts.length > 1) {
			String idPart = pathParts[1];
			System.out.println("*********** " + pathInfo);
			System.out.println("*********** " + pathParts);
			System.out.println("*********** " + idPart);
			c = dao.getCustomerId(Long.parseLong(idPart));
			if (c == null) {
				returnMessage(response, "No customer is available.");
				return;
			}
		}
		if (c == null) {
			c = new Customer();
		}
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String storeId = request.getParameter("storeId");
		String addressId = request.getParameter("addressId");

		c.setFirstName(firstName);
		c.setLastName(lastName);
		c.setStoreId(Long.parseLong(storeId));
		c.setAddressId(Long.parseLong(addressId));
		if (c.getCustomerId() == null) {
			Long customerId = dao.saveCustomer(c);
			response.sendRedirect("/customer/" + customerId);
		} else {
			dao.updateCustomer(c);
			request.setAttribute("customer", c);
			RequestDispatcher rd = request.getRequestDispatcher("/customerinfo.jsp");
			rd.forward(request, response);
		}
	}
}
