package utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 发送邮件的工具类:
 * @author admin
 *
 */
public class MailUtils {

	public static void sendMail(String to,String code){
		
		try {
			// 获得连接:
			Properties props = new Properties();
			Session session = Session.getInstance(props, new Authenticator() {

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("service@store.com", "111");
				}
				
			});
			// 构建邮件:
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("service@store.com"));
			// 设置收件人:
			// TO:收件人   CC:抄送   BCC:暗送,密送.
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// 主题:
			message.setSubject("来自天虎官方商城的激活邮件!");
			// 正文:
			message.setContent("<h1>来自天虎官方商城的激活邮件:请点击下面链接激活!</h1><h3><a href='http://localhost:8899/userServlet?method=active&code="+code+"'>http://localhost:8899/userServlet?method=active&code="+code+"</a></h3>", "text/html;charset=UTF-8");
		
			// 发送邮件:
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	/*public static void main(String[] args) {
		MailUtils.sendMail("a@store.com", "123sdfjklsdkljrsiduoi1123");
	}*/
}
