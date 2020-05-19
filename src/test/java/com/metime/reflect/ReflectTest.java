package com.metime.reflect;

import java.lang.reflect.Field;

import com.metime.test.Student;

public class ReflectTest {
	
	public static void main(String[] args) {
		try {
			Class<?> forName = Class.forName("com.metime.test.Student");
			Field[] declaredFields = forName.getDeclaredFields();
			for (int i = 0; i < declaredFields.length; i++) {
				System.out.println(declaredFields[i]);
			}
			Student student = Student.class.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
