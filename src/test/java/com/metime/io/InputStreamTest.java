package com.metime.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamTest {
	
	public static void main(String[] args) throws IOException {
		try {
			InputStreamReader inputStream = new InputStreamReader(new FileInputStream(new File("D://个人工作日报_梅腾.xlsx")));
			int read = inputStream.read();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
