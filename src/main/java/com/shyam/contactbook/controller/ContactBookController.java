package com.shyam.contactbook.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.shyam.contactbook.model.Contact;
import com.shyam.contactbook.service.ContactBookManager;
import com.shyam.contactbook.util.IdGenerator;

public class ContactBookController {
	
	private static final String BOOK_NAME = "myContactBook";
	
	private BufferedReader br;
	private ContactBookManager contactBook;
	private IdGenerator idGenerator;
	
	public ContactBookController() {
		this.br = new BufferedReader(new InputStreamReader(System.in));
		this.contactBook = new ContactBookManager(BOOK_NAME);
		this.idGenerator = new IdGenerator(contactBook);
	}
	
	public void add() throws IOException {
		System.out.println("======= New Contact =======");
		System.out.print("Enter first name: ");
		String firstName = br.readLine();
		System.out.print("Enter last name: ");
		String lastName = br.readLine();
		System.out.print("Enter phone: ");
		String phone = br.readLine();
		System.out.print("Enter address: ");
		String address = br.readLine();
		System.out.print("Enter email: ");
		String email = br.readLine();
		
		Contact contact = new Contact();
		contact.setId(idGenerator.generate());
		contact.setAddress(address);
		contact.setEmail(email);
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setPhoneNumber(phone);
		
		if(contactBook.addContact(contact) > 0)
			System.out.println("Created!");
		
		else 
			System.out.println("Failed");
		
	}
	
	public void delete() {
		System.out.println("Enter id: ");
		int id = 0;
		try {
			id = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(contactBook.deleteContact(id))
			System.out.println("Deleted");
		else
			System.out.println("Invaid entry");
		
	}
	
	public void search() {
		System.out.println("Enter search string (try regexes): ");
		try {
			String searchQuery = br.readLine();
			System.out.println(contactBook.searchFor(searchQuery));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() throws IOException {
		System.out.println("======= Update Contact =======");
		System.out.println("Enter Contact id: ");
		int id = Integer.parseInt(br.readLine());
		System.out.println("Leave blank if no change is required");
		System.out.print("Enter first name: ");
		String firstName = br.readLine();
		System.out.print("Enter last name: ");
		String lastName = br.readLine();
		System.out.print("Enter phone: ");
		String phone = br.readLine();
		System.out.print("Enter address: ");
		String address = br.readLine();
		System.out.print("Enter email: ");
		String email = br.readLine();
		
		Contact contact = new Contact();
		contact.setId(id);
		contact.setAddress(address);
		contact.setEmail(email);
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setPhoneNumber(phone);
		
		if(contactBook.update(contact))
			System.out.println("Updated!");
		
		else 
			System.out.println("Failed");
		
	}
	
	public void list() {
		System.out.println(contactBook.getAll());
	}

}
