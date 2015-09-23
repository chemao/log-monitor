package com.chemao.log.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chemao.log.monitor.util.MailUtil;

public class WeixinNotify extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(WeixinNotify.class);
	private static final String email = "huangx@chemao.com.cn";
	private static final String subject = "故障反馈";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ignore useless signature checking from weixin
		resp.getWriter().write(req.getParameter("echostr"));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String msg = req.getReader().readLine();
		try {
			MailUtil.sendMail(email, subject, msg);
		} catch (Exception e) {
			logger.error("mail error", e);
		}
	}

}
