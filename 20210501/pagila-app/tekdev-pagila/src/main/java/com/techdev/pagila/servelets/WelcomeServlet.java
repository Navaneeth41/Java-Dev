package com.techdev.pagila.servelets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "WelcomeServlet", urlPatterns = {"/welcome"}
    )
public class WelcomeServlet  extends HttpServlet {

	private static final long serialVersionUID = 8605549760582614954L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.write("welcome Pagila app...!!!".getBytes());
        out.flush();
        out.close();
    }
}
