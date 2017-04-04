package com.rongle.jsms;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class BeforeAfterListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sctx = sce.getServletContext();

        String token = sctx.getInitParameter("sms-token");
        if (StringUtils.isBlank(token)) {
            token = "rongle_sms317s";
        }
        String username = sctx.getInitParameter("sms-username");
        if (StringUtils.isBlank(username)) {
            username = "rlkj";
        }
        String password = sctx.getInitParameter("sms-password");
        if (StringUtils.isBlank(password)) {
            password = "rlkj123";
        }
        String epid = sctx.getInitParameter("sms-epid");
        if (StringUtils.isBlank(epid)) {
            epid = "121366";
        }
        String subcode = sctx.getInitParameter("sms-subcode");
        if (StringUtils.isBlank(subcode)) {
            subcode = "317";
        }

        sctx.setAttribute("sms-token", token);
        sctx.setAttribute("sms-sender", new SendMsg(username, password, epid, subcode));
    }


    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext sctx = sce.getServletContext();

        sctx.setAttribute("sms-sender", null);
    }
}
