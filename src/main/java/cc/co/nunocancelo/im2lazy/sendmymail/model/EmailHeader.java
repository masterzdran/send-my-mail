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

import java.util.HashSet;
import java.util.Set;

import cc.co.nunocancelo.im2lazy.sendmymail.exception.InvalidEmailException;

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
	public boolean setFrom(Contact contact){
		if(contact == null) return false;
		if(contact.getEmail().trim().isEmpty()) return false;
		try {
			from.setEmail(contact.getEmail());
		} catch (InvalidEmailException e) {
			return false;
		}
		from.setName(contact.getName());
		return true;
	}
	public void setBcc(ContactList bcc) {
		this.bcc = bcc;
	}
	public void setCc(ContactList cc) {
		this.cc = cc;
	}
	public void setTo(ContactList to) {
		this.to = to;
	}
	public ContactList getBcc() {
		return bcc;		
	}
	public ContactList getCc() {
		return cc;
	}
	public ContactList getTo() {
		return to;
	}
	public Contact getFrom() {
		return from;
	}
}
