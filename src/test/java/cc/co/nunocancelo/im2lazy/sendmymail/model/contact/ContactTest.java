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

import static org.junit.Assert.*;

import javax.annotation.PreDestroy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cc.co.nunocancelo.im2lazy.sendmymail.exception.InvalidEmailException;

/**
 * @author Nuno Cancelo (nuno.cancelo@gmail.com)
 *
 */
public class ContactTest {
	private Contact _contact;
	private final String GOOD_EMAIL_ADDRESS1="xpto@xpto.com";
	private final String BAD_EMAIL_ADDRESS2="xpto@xpto";
	private final String BAD_EMAIL_ADDRESS3="!xpto@xpto.com";
	private final String GOOD_NAME="José Silva";
	private final String BAD_NAME1="";
	private final String BAD_NAME2=null;
	@Before
	public void prepare(){
		_contact= new Contact();
	}
	@Test
	public void testSetEmail() {
		try {
			_contact.setEmail(GOOD_EMAIL_ADDRESS1);
		} catch (InvalidEmailException e) {
			assertFalse("Exception NOT expected", false);
		}
		try {
			_contact.setEmail(BAD_EMAIL_ADDRESS2);
		} catch (InvalidEmailException e) {
			assertFalse("Exception expected", false);
		}
		try {
			_contact.setEmail(BAD_EMAIL_ADDRESS3);
		} catch (InvalidEmailException e) {
			assertFalse("Exception expected", false);
		}
	}

	@Test
	public void testSetName() {
		_contact.setName(BAD_NAME1);
		assertTrue("Empty Name", _contact.getName().trim().isEmpty());
		_contact.setName(BAD_NAME2);
		assertTrue("Null Name", _contact.getName() == null);
		_contact.setName(GOOD_NAME);
		assertTrue("Diferent Name",_contact.getName().equals(GOOD_NAME));
	}

	@Test
	public void testGetEmail() {
		try {
			_contact.setEmail(GOOD_EMAIL_ADDRESS1);
		} catch (InvalidEmailException e) {}
		assertTrue("Diferent Email",_contact.getEmail().equals(GOOD_EMAIL_ADDRESS1));
	}

	@Test
	public void testGetName() {
		_contact.setName(GOOD_NAME);
		assertTrue("Diferent Name",_contact.getName().equals(GOOD_NAME));
	}

	@After
	public void destroy(){/*There's nothing to destroy*/}

}
