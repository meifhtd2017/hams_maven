package com.metime.collection;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
	
	private static int a;
	
	public static void main(String[] args) {
		if(1>a){
			System.out.println("haha");
		}
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("zhangsan", "张三");*/
		int n = 0 - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println(n);
	}
	
}
