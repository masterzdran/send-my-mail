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

public class Host {
	private String host;
	private Integer port;
	private String username;
	private String password;

	public Host(String host, Integer port) {
		this.host = host;
		this.port = port;
	}
	/**
	 * Get the host address
	 * @return
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Set the host address
	 * @param host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Get the Port
	 * @return
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * Set the port
	 * @param port
	 */
	public void setPort(Integer port) {
		this.port = port;
	}
	/**
	 * Get the passpowd in PLAIN TEXT.
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Get the username
	 * @return
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Set the password in PLAIN TEXT
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Set the username
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}
