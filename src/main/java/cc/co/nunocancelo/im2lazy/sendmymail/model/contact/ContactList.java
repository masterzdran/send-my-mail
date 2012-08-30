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
package cc.co.nunocancelo.im2lazy.sendmymail.model.contact;

import java.util.HashSet;
import java.util.Set;

import cc.co.nunocancelo.im2lazy.sendmymail.exception.InvalidEmailException;

/**
 * @author Nuno Cancelo (nuno.cancelo@gmail.com)
 *
 */
public class ContactList {
	private Set<Contact> contactList;
	public ContactList() {
		contactList= new HashSet<Contact>();
	}
	/**
	 * Add new Contact to the 'Repository'
	 * @param contact
	 * @return : Return false if theres is a empty,invalid email or even if the contact is null, otherwise is an sucessfull action.
	 */
	public boolean add(Contact contact)
	{
		if(contact == null) return false;
		if(contact.getEmail().trim().isEmpty()) return false;
		Contact c = new Contact();
		try {
			c.setEmail(contact.getEmail());
		} catch (InvalidEmailException e) {
			return false;
		}
		c.setName(contact.getName());
		return contactList.add(c);
	}
	/**
	 * Remove an Contact from the repository
	 * @param contact
	 * @return : Return true if the contact is removed, false otherwise
	 */
	public boolean remove(Contact contact){
		if(contact == null) return false;
		return contactList.remove(contact);
	}
	/**
	 * 
	 * @return : An iterable data structure with the contacts
	 */
	public Iterable<Contact> getList() {
		return contactList;
	}

	/**
	 * Return the size of the 'Repository'
	 * @return
	 */
	public int getSize(){
		return contactList.size();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contactList == null) ? 0 : contactList.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactList other = (ContactList) obj;
		if (contactList == null) {
			if (other.contactList != null)
				return false;
		} else if (!contactList.equals(other.contactList))
			return false;
		return true;
	}
	
}
