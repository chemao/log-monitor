/**
 * Copyright 2015 chemao.com, Inc. All rights reserved.
 */
package com.chemao.log.monitor.util;

import org.apache.commons.mail.SimpleEmail;

/**
 * 发送邮件
 * @author xuhf
 * @since 2015年9月2日 下午2:58:37
 * @version V1.0 
 */
public class MailUtil {
	public static void sendMail(String toEmail, String subject, String content) throws Exception {
    	 SimpleEmail email = new SimpleEmail();
    	 // 接收人
         email.addTo (toEmail); 
    	 // 标题
         email.setSubject (subject);
         // 邮件内容
         email.setMsg (content);
         // smtp host
         email.setHostName ( "smtp.yeah.net" );
         // 登陆邮件服务器的用户名和密码，发件箱用户名和密码
         email.setAuthentication ( "fengkar", "xxxx" );
         // 发送人
         email.setFrom ( "fengkar@yeah.net", "chemao dev notify" );		  
         // 发送
         email.send();
    }

}
