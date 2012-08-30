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
package cc.co.nunocancelo.im2lazy.sendmymail.model;

import cc.co.nunocancelo.im2lazy.sendmymail.exception.InvalidEmailException;
import cc.co.nunocancelo.im2lazy.sendmymail.model.contact.Contact;
import cc.co.nunocancelo.im2lazy.sendmymail.model.contact.ContactList;

/**
 * @author Nuno Cancelo (nuno.cancelo@gmail.com)
 *
 */
public class EmailHeader {
	private Contact from;
	private ContactList to;
	private ContactList cc;
	private ContactList bcc;
	
	public EmailHeader() {
		this.from = new Contact();
		this.to = new ContactList();
		this.cc = new ContactList();
		this.bcc = new ContactList();
	}
	/**
	 * The From email user
	 * @param contact
	 * @return
	 */
	public boolean setFrom(Contact contact){
		if(contact == null || contact.getEmail().trim().isEmpty()) return false;
		try {
			from.setEmail(contact.getEmail());
			from.setName(contact.getName());
		} catch (InvalidEmailException e) {
			return false;
		}
		return true;
	}

	

	/**
	 * Set the TO contactList
	 * @param to
	 */
	public void setTo(ContactList to) {
		this.to = to;
	}
	
	ContactList getBcc() {
		return bcc;		
	}
	ContactList getCc() {
		return cc;
	}
	void setBcc(ContactList bcc) {
		this.bcc = bcc;
	}
	void setCc(ContactList cc) {
		this.cc = cc;
	}
	/**
	 * Get the Contact List
	 * @return
	 */
	public ContactList getTo() {
		return to;
	}
	/**
	 * Get the From Contact
	 * @return
	 */
	public Contact getFrom() {
		return from;
	}
}
