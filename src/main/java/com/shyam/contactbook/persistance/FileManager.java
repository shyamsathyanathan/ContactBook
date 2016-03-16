package com.shyam.contactbook.persistance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class FileManager {
	
	private File file;
	
	public FileManager(File file) {
		this.file = file;
	}
	
	public void write(String json) throws IOException {
		FileWriter writer = new FileWriter(file, false);
		writer.write(json);
		writer.close();
	}
	
	public Reader getReader() {
		try {
			return new FileReader(file);
		} catch (FileNotFoundException e) {
		}
		return null;
	}

}
