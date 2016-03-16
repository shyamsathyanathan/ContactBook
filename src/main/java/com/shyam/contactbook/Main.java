package com.shyam.contactbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.shyam.contactbook.controller.ContactBookController;

public class Main {

	private static final String MESSAGE_INVALID_CHOICE = "Invalid choice!";
	
	public static void main(String[] args) throws IOException {
		
		boolean repeat = true;
		
		ContactBookController controller = new ContactBookController();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int choice = 0;
		
		do{
			showMainMenu();
			try {
				choice = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				System.out.println(MESSAGE_INVALID_CHOICE);
				break;
			}
			
			switch(choice) {
			
			case 1:
				controller.add();
				break;
			
			case 2:
				controller.list();
				break;
			
			case 3:
				controller.delete();
				break;
			
			case 4:
				controller.search();
				break;
			
			case 5:
				controller.update();
				break;
			
			case 6:
				repeat = false;
				break;
			
			default:
				System.out.println(MESSAGE_INVALID_CHOICE);
				break;
			}
			
		} while(repeat);
		
	}
	
	public static void showMainMenu() {
		System.out.println("========= Main Menu ========");
		System.out.println("1. Create Contact");
		System.out.println("2. List Contacts");
		System.out.println("3. Delete Contact");
		System.out.println("4. Search Contact");
		System.out.println("5. Update Contact");
		System.out.println("6. Exit");
		
	}
	
}
