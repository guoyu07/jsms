package com.rongle.jsms;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SendCodeServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse rsp)
        throws ServletException, IOException {
        ServletContext sctx = this.getServletContext();
        rsp.getWriter().write("hello" + sctx.getAttribute("a"));
    }
}