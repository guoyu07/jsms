package com.rongle.jsms;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.codec.binary.Base64;

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

        rsp.setContentType("application/json");

        String token = req.getParameter("token");
        if (StringUtils.isBlank(token)) {
            rsp.getWriter().write("{\"error_no\":\"401\",\"error_msg\":\"absence of parameter\"}");
            return;
        }
        String phone = req.getParameter("phone");
        if (StringUtils.isBlank(phone)) {
            rsp.getWriter().write("{\"error_no\":\"401\",\"error_msg\":\"absence of parameter\"}");
            return;
        }
        String code = req.getParameter("code");
        if (StringUtils.isBlank(code)) {
            rsp.getWriter().write("{\"error_no\":\"401\",\"error_msg\":\"absence of parameter\"}");
            return;
        }

        String smsToken = (String)sctx.getAttribute("sms-token");
        if (! smsToken.equals(token)) {
            rsp.getWriter().write("{\"error_no\":\"402\",\"error_msg\":\"invalid token\"}");
            return;
        }

        SendMsg sms = (SendMsg) sctx.getAttribute("sms-sender");
        if (! sms.send(phone, new String(Base64.decodeBase64(code)))) {
            rsp.setStatus(503);
            return;
        }

        rsp.getWriter().write("{\"error_no\":\"200\",\"error_msg\":\"success\"}");

        return;
    }
}