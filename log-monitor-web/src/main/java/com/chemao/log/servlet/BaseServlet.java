/**
 * Copyright 2015 chemao.com, Inc. All rights reserved.
 */
package com.chemao.log.servlet;

import javax.servlet.http.HttpServlet;

import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 让servlet支持 spring的注解
 * @author xuhf
 * @since 2015年9月8日 下午2:25:57
 * @version V1.0 
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected Object getBean(String beanName) {
		return WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean(beanName);
    }
}
