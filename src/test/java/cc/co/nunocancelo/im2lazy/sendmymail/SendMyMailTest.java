package cc.co.nunocancelo.im2lazy.sendmymail;

import org.junit.Test;

import cc.co.nunocancelo.im2lazy.sendmymail.exception.InvalidEmailDetailException;
import cc.co.nunocancelo.im2lazy.sendmymail.exception.InvalidEmailException;
import cc.co.nunocancelo.im2lazy.sendmymail.exception.InvalidEmailHeaderException;
import cc.co.nunocancelo.im2lazy.sendmymail.exception.InvalidHostException;
import cc.co.nunocancelo.im2lazy.sendmymail.mail.Emailer;
import cc.co.nunocancelo.im2lazy.sendmymail.model.Email;
import cc.co.nunocancelo.im2lazy.sendmymail.model.EmailAttachment;
import cc.co.nunocancelo.im2lazy.sendmymail.model.EmailDetail;
import cc.co.nunocancelo.im2lazy.sendmymail.model.EmailHeader;
import cc.co.nunocancelo.im2lazy.sendmymail.model.Host;
import cc.co.nunocancelo.im2lazy.sendmymail.model.contact.Contact;
import cc.co.nunocancelo.im2lazy.sendmymail.model.contact.ContactList;

public class SendMyMailTest {

	private Host getHost(){
		Host h = new Host(Messages.getString("SendMyMailTest.1"), 465);
		h.setUsername(Messages.getString("SendMyMailTest.2"));
		h.setPassword(Messages.getString("SendMyMailTest.3"));
		return h;
	}
	
	private Contact getContactFor(String name,String email){
		Contact c1 = new Contact();
		try {
			c1.setEmail(email);
			c1.setName(name);
		} catch (InvalidEmailException e) {
			return null;
		}
		return c1;
	}
	private ContactList getContactList(){
		ContactList c = new ContactList();
		c.add(getContactFor("Nuno Alexandre Cancelo", "nuno.cancelo@gmail.com"));
		c.add(getContactFor("Nuno Cancelo", "nu.no.cancelo@gmail.com"));
		c.add(getContactFor("Nuno Alexandre dos Santos Cancelo", "nu.no.can.celo@gmail.com"));
		return c;
	}
	private EmailHeader getHeader(){
		EmailHeader e = new EmailHeader();
		e.setTo(getContactList());
		return e;
	}
	
	private EmailDetail getDetail(){
		EmailDetail e = new EmailDetail();
		e.setSubject("mysubject");
		e.setBody(getBodyMessage());
		return e;
	}
	
	private String getBodyMessage() {
		StringBuilder s = new StringBuilder();
		s.append("Exmo Sr.\n");
		s.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi sapien lectus, porttitor vitae tempus at, lacinia ut tellus. Maecenas tristique, tortor eu cursus luctus, augue nisi commodo nisi, at mattis felis velit sed arcu. Aenean et massa enim. In elementum ligula quis neque ullamcorper id facilisis nulla adipiscing. Integer cursus porta accumsan. Etiam semper consequat diam, quis aliquam sapien aliquet a. Integer tortor odio, volutpat quis lacinia nec, tristique et odio. Duis porta faucibus lectus, ac rutrum quam posuere sit amet. Sed imperdiet justo ac tellus vulputate at fringilla lectus sollicitudin. Vivamus non elit a eros sollicitudin faucibus. Curabitur ornare, diam sit amet vulputate lacinia, mi tellus congue elit, sed laoreet turpis libero in ipsum. Proin eu iaculis tortor. Pellentesque interdum feugiat ligula vel aliquet. Fusce felis massa, molestie id porta vitae, lacinia vel est."); //$NON-NLS-1$
		return s.toString();
	}

	private EmailAttachment getAttchments(){
		EmailAttachment e = new EmailAttachment();
		e.addAttachment("/home/masterzdran/Desktop/CV/Europass-CV-120723-Cancelo.pdf");
		e.addAttachment("/home/masterzdran/Desktop/CV/Europass-CV-120730-Silva.pdf");
		e.addAttachment("/home/masterzdran/Downloads/Dropbox/Dropbox/CV/CartadeApresentacao.pdf");
		return e;
	}
	@Test
	public void test() {
		Email e = getEmail();
		Emailer sendEmail = new Emailer();
		sendEmail.setEmail(e);
		try {
			sendEmail.sendEmail();
		} catch (InvalidHostException e1) {
			e1.printStackTrace();
		} catch (InvalidEmailException e1) {
			e1.printStackTrace();
		} catch (InvalidEmailDetailException e1) {
			e1.printStackTrace();
		} catch (InvalidEmailHeaderException e1) {
			e1.printStackTrace();
		}
	}

	private Email getEmail() {
		Email email = new Email();
		//Setting the Host
		email.setHost(getHost());
		//Setting the Header
		email.setEmailHeader(getHeader());
		//Setting the Detail
		email.setEmailDetail(getDetail());
		//Setting the Attachment
		email.setEmailAttachment(getAttchments());
		return email;
	}

}
