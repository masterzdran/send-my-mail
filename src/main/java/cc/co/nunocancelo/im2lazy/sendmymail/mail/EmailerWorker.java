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
package cc.co.nunocancelo.im2lazy.sendmymail.mail;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import cc.co.nunocancelo.im2lazy.sendmymail.model.Host;
import cc.co.nunocancelo.im2lazy.sendmymail.model.contact.Contact;

import com.sun.mail.smtp.SMTPTransport;

/**
 * @author Nuno Cancelo (nuno.cancelo@gmail.com)
 * 
 */
final class EmailerWorker {
	private static final String PROTOCOL = "smtps";
	private Host host;
	private Session session;
	private Message msg;
	private MimeMultipart multipart;

	public EmailerWorker(Host host) {
		this.host = new Host(host.getHost(), host.getPort());
		this.host.setPassword(host.getPassword());
		this.host.setUsername(host.getUsername());
		setHost();
		multipart = new MimeMultipart();
	}

	public void addAttachment(String pdfAttachment) {

		if (!isValid(pdfAttachment))
			return;

		try {
			File file = new File(pdfAttachment);
			if (!file.exists() || !file.isFile() || file.length() == 0)
				return;
			int idx = file.getName().lastIndexOf('.');
			if (idx == 0)
				return;
			String ext = file.getName().substring(idx + 1).toLowerCase();
			if (!ext.equals("pdf"))
				return;

			multipart.addBodyPart(getMimeBodyPart(file));
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public boolean send(Contact destination, String subject, String message) {
		boolean ret = setEmailMessage(destination, subject, message);
		if (ret) {
			ret = sendMail();
		}
		return ret;
	}

	// Private Methods
	private MimeBodyPart getMimeBodyPart(File file) throws MessagingException {
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		DataSource dataSource = getPDFDatasource(file);
		mimeBodyPart.setDataHandler(new DataHandler(dataSource));
		mimeBodyPart.setFileName(file.getName());
		mimeBodyPart.setHeader("Content-Transfer-Encoding", "base64");
		mimeBodyPart.setDisposition(Part.ATTACHMENT);
		return mimeBodyPart;
	}

	private DataSource getPDFDatasource(File file) {
		DataSource dataSource = new FileDataSource(file) {
			public String getContentType() {
				return "application/pdf";
			}
		};
		return dataSource;
	}

	private void setMultipart(String message) {
		
		MimeBodyPart textBodyPart = new MimeBodyPart();
		try {
			textBodyPart.setText(message);
			multipart.addBodyPart(textBodyPart);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private void setHost() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		session = Session.getDefaultInstance(props, null);
		session.setDebug(true);
	}

	private boolean setEmailMessage(Contact destination, String subject,String message) {
		msg = new MimeMessage(session);

		try {
			msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(destination.getEmail(), false));
			msg.setSubject(subject);
			setMultipart(message);
			msg.setText("text/html");
		} catch (AddressException e1) {
			return false;
		} catch (MessagingException e1) {
			return false;
		}
		return true;
	}

	private boolean sendMail() {
		try {
			msg.setContent(multipart);
		} catch (MessagingException e1) {
			return false;
		}
		if (!isValid(host.getUsername()) || !isValid(host.getPassword()))
			return false;

		SMTPTransport t = null;
		try {
			t = (SMTPTransport) session.getTransport(PROTOCOL);
			t.connect(host.getHost(), host.getUsername(), host.getPassword());
			t.sendMessage(msg, msg.getAllRecipients());
		} catch (NoSuchProviderException e) {
			return false;
		} catch (MessagingException e) {
			return false;
		} finally {
			try {
				if (t != null)
					t.close();
			} catch (MessagingException e) {
				return false;
			}
		}
		return true;
	}

	private boolean isValid(String value) {
		return (value != null && !value.trim().isEmpty());
	}
}
