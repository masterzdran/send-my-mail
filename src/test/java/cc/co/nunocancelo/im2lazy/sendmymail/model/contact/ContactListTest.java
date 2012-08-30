/**
 * 
 */
package cc.co.nunocancelo.im2lazy.sendmymail.model.contact;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cc.co.nunocancelo.im2lazy.sendmymail.exception.InvalidEmailException;

/**
 * @author masterzdran
 *
 */
public class ContactListTest {

	private ContactList contactList;
	
	
	private Contact getContactFor(String name,String email){
		Contact c1 = new Contact();
		try {
			c1.setEmail(email);
			c1.setName(name);
		} catch (InvalidEmailException e) {
			return null;
		}
		return c1;
	}
	
	private void addContactsToList(){
		//9 items
		contactList.add(getContactFor("nuno cancelo", "test.test@test.ts"));
		contactList.add(getContactFor("nunocancelo", "test.test@test.ts"));
		contactList.add(getContactFor("nunoancelo", "test.test@test.ts"));
		contactList.add(getContactFor("nuno ncelo", "test.test@test.ts"));
		contactList.add(getContactFor("nuno elo", "test.test@test.ts"));
		contactList.add(getContactFor("nuno aelo", "test.test@test.ts"));
		contactList.add(getContactFor("nuno caelo", "test.test@test.ts"));
		contactList.add(getContactFor("nuno celo", "test.test@test.ts"));
		contactList.add(getContactFor("nuno ccelo", "test.test@test.ts"));
		
	}
	@Test
	public void testConstructor(){
		contactList = new ContactList();
		Assert.assertTrue(contactList != null);
	}
	
	@Before
	public void create(){
		contactList = new ContactList();
		
	}
	/**
	 * Test method for {@link cc.co.nunocancelo.im2lazy.sendmymail.model.contact.ContactList#add(cc.co.nunocancelo.im2lazy.sendmymail.model.contact.Contact)}.
	 */
	@Test
	public void testAdd() {
		Contact c1 = this.getContactFor("Nuno Cancelo", "test@test.ts");
		Assert.assertTrue(contactList.add(c1));
		Assert.assertTrue(contactList.getSize() == 1);
	}

	/**
	 * Test method for {@link cc.co.nunocancelo.im2lazy.sendmymail.model.contact.ContactList#remove(cc.co.nunocancelo.im2lazy.sendmymail.model.contact.Contact)}.
	 */
	@Test
	public void testRemove() {
		Contact c1 = this.getContactFor("Nuno Cancelo", "test@test.ts");
		contactList.add(c1);
		
		Assert.assertTrue(contactList.remove(c1));
		Assert.assertTrue(contactList.getSize() == 0);
	}

	/**
	 * Test method for {@link cc.co.nunocancelo.im2lazy.sendmymail.model.contact.ContactList#getList()}.
	 */
	@Test
	public void testGetList() {
		this.addContactsToList();
		
		Iterable<Contact> list = contactList.getList();
		Assert.assertTrue(list != null);
		int i=0;
		for(Contact c : list){
			System.out.println(c.getName());
			i++;
		}
		Assert.assertTrue(i==9);
		
	}

	/**
	 * Test method for {@link cc.co.nunocancelo.im2lazy.sendmymail.model.contact.ContactList#getContactList()}.
	 */
	@Test
	public void testGetContactList() {
		this.addContactsToList();
		Iterable<Contact> list = contactList.getList();
		Assert.assertTrue(list != null);
		Assert.assertTrue(contactList.getSize()==9);
	}


}
