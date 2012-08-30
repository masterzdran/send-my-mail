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
public class Email {
	private Host host;
	private EmailHeader emailHeader;
	private EmailDetail emailDetail;
	private EmailAttachment emailAttachment;
	
	/**
	 * Get the Email detail (The Body/Subject of the email message)
	 * @return
	 */
	public EmailDetail getEmailDetail() {
		return emailDetail;
	}
	/**
	 * Get the Email header(Destination and From conctats )
	 * @return
	 */
	public EmailHeader getEmailHeader() {
		return emailHeader;
	}
	/**
	 * Get the Host server
	 * @return
	 */
	public Host getHost() {
		return host;
	}
	/**
	 * Get The attachments
	 * @return
	 */
	public EmailAttachment getEmailAttachment() {
		return emailAttachment;
	}
	/**
	 * Set the email detail (The Body/Subject of the email message)
	 * @param emailDetail
	 */
	public void setEmailDetail(EmailDetail emailDetail) {
		this.emailDetail = emailDetail;
	}
	/**
	 * Set the email header  (Destination and From conctats )
	 * @param emailHeader
	 */
	public void setEmailHeader(EmailHeader emailHeader) {
		this.emailHeader = emailHeader;
	}
	/**
	 * Set the mail server host
	 * @param host
	 */
	public void setHost(Host host) {
		this.host = host;
	}
	/**
	 * Set the attachments
	 * @param emailAttachment
	 */
	public void setEmailAttachment(EmailAttachment emailAttachment) {
		this.emailAttachment = emailAttachment;
	}
}
