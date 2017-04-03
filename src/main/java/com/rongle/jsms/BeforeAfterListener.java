package com.rongle.jsms;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class BeforeAfterListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sctx = sce.getServletContext();

        sctx.setAttribute("a", "b");
    }


    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext sctx = sce.getServletContext();

        sctx.setAttribute("a", null);
    }
}
