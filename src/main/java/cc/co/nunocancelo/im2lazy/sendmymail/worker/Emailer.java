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
package cc.co.nunocancelo.im2lazy.sendmymail.worker;

import cc.co.nunocancelo.im2lazy.sendmymail.model.Contact;
import cc.co.nunocancelo.im2lazy.sendmymail.model.Email;
import cc.co.nunocancelo.im2lazy.sendmymail.model.EmailAttachment;

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
	public void setEmail(Email email) {
		this.email = email;
	}

	public boolean sendEmail(){
		if (email == null) return false;
		boolean r= false;
		int all=0,send=0;
		EmailerWorker mail = new EmailerWorker(email.getHost());
		
		for(String pdfAttachment : email.getEmailAttachment().getFileList())
			mail.addAttachment(pdfAttachment);
		
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
