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

import cc.co.nunocancelo.im2lazy.sendmymail.exception.InvalidEmailException;
import cc.co.nunocancelo.im2lazy.sendmymail.exception.InvalidHostException;
import cc.co.nunocancelo.im2lazy.sendmymail.model.Email;
import cc.co.nunocancelo.im2lazy.sendmymail.model.EmailAttachment;
import cc.co.nunocancelo.im2lazy.sendmymail.model.contact.Contact;

/**
 * @author Nuno Cancelo (nuno.cancelo@gmail.com)
 *
 */
public class Emailer {
	private Email email;
	public Emailer() {
		this(null);
	}

	public Emailer(Email email) {
		this.email = email;
	}
	/**
	 * Set the Email
	 * @param email
	 */
	public void setEmail(Email email) {
		this.email = email;
	}

	/**
	 * Send the email
	 * @return
	 * @throws InvalidHostException 
	 * @throws InvalidEmailException 
	 * @throws InvalidEmailDetailException 
	 * @throws InvalidEmailHeaderException 
	 */
	public boolean sendEmail() throws InvalidHostException, InvalidEmailException, InvalidEmailDetailException, InvalidEmailHeaderException{
		if (email == null) throw new InvalidEmailException("Email class is null");
		if (email.getHost() == null) throw new InvalidHostException("Host is null");
		if (email.getEmailDetail() == null) throw new InvalidEmailDetailException("Email detail is null");
		if(email.getEmailHeader() == null) throw new InvalidEmailHeaderException("Email Header is null");
		
		boolean r= false;
		int all=0,send=0;
		EmailerWorker mail = new EmailerWorker(email.getHost());
		EmailAttachment ea =email.getEmailAttachment();
		
		// add attachments if any
		if (ea != null)
			for(String pdfAttachment : ea.getFileList())
				mail.addAttachment(pdfAttachment);
		
		//foreach Contact send mail
		for(Contact c : email.getEmailHeader().getTo().getList()){
			r=mail.send(c, email.getEmailDetail().getSubject(), email.getEmailDetail().getBody());
			if(r){
				System.out.println("email sent: "+c.getEmail());
				++send;
			}
			++all;
		}
		return all == send;
	}


}
