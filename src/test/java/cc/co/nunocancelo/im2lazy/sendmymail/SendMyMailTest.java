package cc.co.nunocancelo.im2lazy.sendmymail;

import org.junit.Test;

import cc.co.nunocancelo.im2lazy.sendmymail.exception.InvalidEmailException;
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
		Host h = new Host("smtp.gmail.com", 465);
		h.setUsername("");
		h.setPassword("");
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
		c.add(getContactFor("username", "email@email.com"));
		c.add(getContactFor("username", "email@email.com"));
		c.add(getContactFor("username", "email@email.com"));
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
		s.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi sapien lectus, porttitor vitae tempus at, lacinia ut tellus. Maecenas tristique, tortor eu cursus luctus, augue nisi commodo nisi, at mattis felis velit sed arcu. Aenean et massa enim. In elementum ligula quis neque ullamcorper id facilisis nulla adipiscing. Integer cursus porta accumsan. Etiam semper consequat diam, quis aliquam sapien aliquet a. Integer tortor odio, volutpat quis lacinia nec, tristique et odio. Duis porta faucibus lectus, ac rutrum quam posuere sit amet. Sed imperdiet justo ac tellus vulputate at fringilla lectus sollicitudin. Vivamus non elit a eros sollicitudin faucibus. Curabitur ornare, diam sit amet vulputate lacinia, mi tellus congue elit, sed laoreet turpis libero in ipsum. Proin eu iaculis tortor. Pellentesque interdum feugiat ligula vel aliquet. Fusce felis massa, molestie id porta vitae, lacinia vel est.");
		return s.toString();
	}

	private EmailAttachment getAttchments(){
		EmailAttachment e = new EmailAttachment();
		e.addAttachment("path2file1");
		e.addAttachment("path2file2");
		e.addAttachment("path2file3");
		return e;
	}
	@Test
	public void test() {
		Email e = getEmail();
		Emailer sendEmail = new Emailer();
		sendEmail.setEmail(e);
		sendEmail.sendEmail();
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
