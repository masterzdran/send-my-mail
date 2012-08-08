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

/**
 * @author Nuno Cancelo (nuno.cancelo@gmail.com)
 *
 */
public class EmailDetail {
	private String subject;
	private String body;
	public String getBody() {
		return body;
	}
	public String getSubject() {
		return subject;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
}
