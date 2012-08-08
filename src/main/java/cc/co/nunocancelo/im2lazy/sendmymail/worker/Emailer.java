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

import cc.co.nunocancelo.im2lazy.sendmymail.model.Email;

/**
 * @author Nuno Cancelo (nuno.cancelo@gmail.com)
 *
 */
public class Emailer {
	private Email email;
	private boolean sendEach;
	
	public Emailer() {
		this(null,false);
	}

	public Emailer(Email email, boolean sendEach) {
		this.email = email;
		this.sendEach = sendEach;
	}
	public void setEmail(Email email) {
		this.email = email;
	}
	public void setSendEach(boolean sendEach) {
		this.sendEach = sendEach;
	}
	public void sendEmail(){
		if (email == null) return;
	}
	
	private void emailer(){
		
	}

}
