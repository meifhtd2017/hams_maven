package com.metime.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;


public class ReadTest {
	
	public static void main(String[] args) {
		
		Reader reader = new Reader() {
			
			@Override
			public int read(char[] cbuf, int off, int len) throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public void close() throws IOException {
				// TODO Auto-generated method stub
				
			}
		};
		
		Writer writer = new Writer() {
			
			@Override
			public void write(char[] cbuf, int off, int len) throws IOException {
				
			}
			
			@Override
			public void flush() throws IOException {
				
			}
			
			@Override
			public void close() throws IOException {
				
			}
		};
		
		InputStream inputStream = new InputStream() {
			@Override
			public int read() throws IOException {
				return 0;
			}
		};
		
		OutputStream outputStream = new OutputStream() {
			
			@Override
			public void write(int b) throws IOException {
				
			}
		};
		
		
	}
	
}
