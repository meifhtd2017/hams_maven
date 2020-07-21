package com.metime.reflect;

import java.lang.reflect.Field;

import com.metime.test.Student;

public class ReflectTest {
	
	public static void main(String[] args) {
		try {
			Class<?> class1 = Student.class;
			Class<? extends Student> class2 = new Student().getClass();
			Class<?> forName = Class.forName("com.metime.test.Student");
			Field[] declaredFields = forName.getDeclaredFields();
			for (int i = 0; i < declaredFields.length; i++) {
				System.out.println(declaredFields[i]);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
