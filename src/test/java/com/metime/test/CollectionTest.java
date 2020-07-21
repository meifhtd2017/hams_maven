package com.metime.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import org.junit.Test;

public class CollectionTest {
	
	@Test
	public void test01(){
		int[] aar = {1,2,3};
		System.out.println(aar.length);
		String string = "abcd";
		string.length();
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(4);
		list2.add(5);
		list2.add(6);
		list2.add(7);
		//list.addAll(list2);//[1, 2, 3, 4, 5, 6, 7]
		//System.out.println(list);
		//list.addAll(0, list2);//[5, 6, 7, 1, 2, 3, 4]
		//System.out.println(list);
		//list.removeAll(list2);
		//System.out.println(list);//[1, 2, 3]
		
	}
	@Test
	public void test02(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(4);
		System.out.println(list.indexOf(5));
	}
	
	@Test
	public void test03(){
		List<Object> list = new ArrayList<Object>();
		list.add(1);
		list.add("2");
		list.add('A');
		list.remove('A');
		System.out.println(list);
	}
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();// 数据结构数组
		List<Integer> linkedlist = new LinkedList<Integer>();// 链表Node<e>
		List<Integer> vector = new Vector<Integer>();// 线程安全的。
		linkedlist.add(2);
		list.add(1);
		System.out.println(list);
		System.out.println(list.size());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("1", 1);
		map.put(null, null);
		map.put(null, null);
		System.out.println(map);

		Set<Integer> treeset = new TreeSet<Integer>();
		Set<Integer> hashset = new HashSet<Integer>();

		Map<String, Integer> treeMap = new TreeMap<String, Integer>();
		Map<String, Integer> hashtable = new Hashtable<String, Integer>();
		Map<String, Integer> linkerHashMap = new LinkedHashMap<String, Integer>();
		Iterator<Integer> iterator = list.iterator();
	}

	void testList() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		for (Integer integer : list) {
			list.remove(integer);
		}
		System.out.println(list);

		for (int i = 0; i < list.size(); i++) {

			list.remove(i);

		}

		for (int i = list.size() - 1; i >= 0; i--) {

			list.remove(i);

		}

		int size = list.size();

		for (int i = size - 1; i > -1; i--) {

			list.remove(i);

		}

		for (Object i : list) {

			list.remove(i);// 如果list中存在多个Object互相equals时,此方法仍然有效.注意list.remove(Object)内部使用了遍历操作,并使用equals来比较对象并删除.

		}

		Iterator it = list.iterator();
		while (it.hasNext()) {

			it.next();

			it.remove();

		}
	}
}
