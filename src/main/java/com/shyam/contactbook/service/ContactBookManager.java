package com.shyam.contactbook.service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.shyam.contactbook.model.Contact;
import com.shyam.contactbook.persistance.FileManager;

public class ContactBookManager {
	
	private static final String CONTACT_STORE_DIRECTORY = "";
	
	private File contactBook;
	private Gson gson;
	private FileManager fileManager;
	
	public ContactBookManager(String contactBookName) {
		this.contactBook = new File(CONTACT_STORE_DIRECTORY + contactBookName);
		this.gson = new Gson();
		this.fileManager = new FileManager(contactBook);
	}
	
	/**
	 * Add contact into ContactBook
	 * @throws IOException 
	 */
	public int addContact(Contact contact) throws IOException {
		List<Contact> contacts = getAll();
		
		if(contacts == null) 
			contacts = new ArrayList<Contact>();
		
		contacts.add(contact);
		
		fileManager.write(gson.toJson(contacts));
		
		return contact.getId();
	}
	
	/**
	 * Get one using id
	 */
	public Contact getOne(int id) {
		List<Contact> contactList = getAll();
		for(Contact contact: contactList) {
			if(contact.getId() == id)
				return contact;
		}
		return null;
	}
	
	/**
	 * Remove contacts using id from ContactBook
	 */
	public boolean deleteContact(int id) {
		List<Contact> contactList = getAll();
		boolean deleted = false;
		for(Contact contact: contactList) {
			if(contact.getId() == id) {
				contactList.remove(contact);
				deleted = true;
			}
		}
		try {
			fileManager.write(gson.toJson(contactList));
		} catch (IOException e) {
			deleted = false;
			return deleted;
		}
		return deleted;
	}
	
	/**
	 * Search for strings/regex in any field of the Contacts
	 * @param string
	 * @return
	 */
	public List<Contact> searchFor(String string) {
		List<Contact> contactList = getAll();
		List<Contact> searchResult = new ArrayList<Contact>();
		try {
			for(Contact contact: contactList) {
				if(
					Pattern.matches(string, contact.getAddress())
					|| Pattern.matches(string, contact.getEmail())
					|| Pattern.matches(string, contact.getFirstName())
					|| Pattern.matches(string, contact.getLastName())
					|| Pattern.matches(string, contact.getPhoneNumber())
				) {
					searchResult.add(contact);
				}
			}
		} catch(PatternSyntaxException e) {
			System.out.println("Invalid regex pattern");
			return null;
		}
		return searchResult;
	}
	
	public boolean update(Contact contactUpdate) {
		List<Contact> contactList = getAll();
		for(int i = 0; i < contactList.size(); i++) {
			Contact contact = contactList.get(i);
			if(contact.getId() == contactUpdate.getId()) {
				if(!contactUpdate.getAddress().isEmpty()) {
					contact.setAddress(contactUpdate.getAddress());
				}
				if(!contactUpdate.getEmail().isEmpty()) {
					contact.setEmail(contactUpdate.getEmail());
				}
				if(!contactUpdate.getFirstName().isEmpty()) {
					contact.setFirstName(contactUpdate.getFirstName());
				}
				if(!contactUpdate.getLastName().isEmpty()) {
					contact.setLastName(contactUpdate.getLastName());
				}
				if(!contactUpdate.getPhoneNumber().isEmpty()) {
					contact.setPhoneNumber(contactUpdate.getPhoneNumber());
				}
				contactList.set(i, contact);
			}
		}
		try {
			fileManager.write(gson.toJson(contactList));
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	/**
	 * List all contacts from ContactBook
	 */
	public List<Contact> getAll() {
		Type type = new TypeToken<List<Contact>>(){}.getType();
		List<Contact> contacts = new ArrayList<Contact>();
		try {
			contacts = gson.fromJson(fileManager.getReader(), type);
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		return contacts;
	}

}
