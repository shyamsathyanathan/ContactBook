package com.shyam.contactbook.util;

import java.util.List;

import com.shyam.contactbook.model.Contact;
import com.shyam.contactbook.service.ContactBookManager;


public class IdGenerator {
	
	private ContactBookManager contactBook;
	
	public IdGenerator(ContactBookManager contactBook) {
		this.contactBook = contactBook;
	}
	
	public int generate() {
		List<Contact> contactList = contactBook.getAll();
		if(contactList == null) {
			return 1;
		}
		Contact lastContact =  (Contact) contactList.get(contactList.size() - 1);
		return lastContact.getId() + 1;		
	}
}