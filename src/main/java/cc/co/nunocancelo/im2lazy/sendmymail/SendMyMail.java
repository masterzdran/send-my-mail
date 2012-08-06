/**
 *  This file is part of SendMyMail.
 *  
 *   SendMyMail is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   SendMyMail is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with SendMyMail.  If not, see <http://www.gnu.org/licenses/>.
 */
package cc.co.nunocancelo.im2lazy.sendmymail;


//based in http://www.oracle.com/technetwork/java/javamail/faq-135477.html#gmail
//based in "I'll illustrate the proper configuration using the demo programs that come with JavaMail - msgshow.java and smtpsend.java"
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

/**
 * @author Nuno Cancelo (nuno.cancelo@gmail.com)
 *
 */
public class SendMyMail {

	private static final String MESSAGE = "ups";
	private static final String username = "";
	private static final String password = "";
	private static final String host = "smtp.gmail.com";
	private static final String subject = "TESTE";
	
	private static void send1(){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {

					return new PasswordAuthentication(username,password);
				}
			});
 session.setDebug(true);
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(username));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler," +
					"\n\n No spam to my email, please!");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
	private static void send2(){
		
	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    
	    Session session = Session.getDefaultInstance(props, null);
	    session.setDebug(true);

	    
	    Message msg = new MimeMessage(session);
	    try {
			msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(username, false));
			
			msg.setSubject(subject);
			msg.setText(MESSAGE);
		} catch (AddressException e1) {
			e1.printStackTrace();
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
	    SMTPTransport t=null;
	    try {
		     t =(SMTPTransport)session.getTransport("smtps");
		     t.connect(host, username, password);
		     t.sendMessage(msg, msg.getAllRecipients());
	    } catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} finally {
	    	try {
				t.close();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
	    }

	}
	
	public static void main(String[] args) {
		send2();
	}

}
