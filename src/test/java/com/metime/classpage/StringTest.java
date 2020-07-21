package com.metime.classpage;

public class StringTest {
	public static void main(String[] args) {
		String string = "abc";
		StringBuffer sBuffer = new StringBuffer("abc");
		StringBuilder sBuilder = new StringBuilder();
		StringBuffer reverse = sBuffer.reverse();
		System.out.println(reverse);
	}
}
