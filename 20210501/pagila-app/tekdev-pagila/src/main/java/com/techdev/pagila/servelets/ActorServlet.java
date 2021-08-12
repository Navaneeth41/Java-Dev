package com.techdev.pagila.servelets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import com.techdev.pagila.dto.Actor;

@WebServlet(name = "ActorServlet", urlPatterns = { "/actor/*" })
public class ActorServlet extends HttpServlet {
	private static final long serialVersionUID = -611490833373789459L;
	private static final Logger logger = LoggerFactory.getLogger(ActorServlet.class);
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
			Actor a = dao.getActorid(Long.parseLong(idPart));
			if (a == null) {
				returnMessage(response, "No Actor is available.");
				return;
			}
			// returnMessage(response, c.toString());
			request.setAttribute("actor", a);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/actorinfo.jsp");
		rd.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		Actor a = null;
		String[] pathParts = pathInfo != null ? pathInfo.split("/") : null;
		if (pathParts != null && pathParts.length > 1) {
			String idPart = pathParts[1];
			System.out.println("*********** " + pathInfo);
			System.out.println("*********** " + pathParts);
			System.out.println("*********** " + idPart);
			a = dao.getActorid(Long.parseLong(idPart));
			if (a == null) {
				returnMessage(response, "No Actor is available.");
				return;
			}
		}
	}
		@Override
		protected void doDelete(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Actor a =null;
			PreparedStatement st;
			ResultSet rs;
			try
			{
			st= a.prepareStatement("delete from data where name='bipul'");
			int i = st.executeUpdate();
			if(i!=0)
			pw.println("Deleting row...");
			else if (i==0)
			{
			pw.println("<br>Row has been deleted successfully.");
			}
			}
			catch(SQLException sx)
			{
			pw.println(sx);
			}
			catch(ClassNotFoundException cx)
			{
			pw.println(cx);
			} 
			
			
				
			

		if (a == null) {
			a = new Actor();
		}
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		
		a.setFirstname(firstName);
		a.setLastname(lastName);
		
	
		if (a.getActorid() == null) {
			Long actorid = dao.saveActor(a);
			response.sendRedirect("/actor/" + actorid);
	}
			else {
		dao.updateActor(a);
		request.setAttribute("Actor", a);
			RequestDispatcher rd = request.getRequestDispatcher("/actorinfo.jsp");
		rd.forward(request, response);
		}

	}
}
       

