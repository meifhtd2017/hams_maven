package com.metime.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

public class CollectionTest {
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();//数据结构数组
		List<Integer> linkedlist = new LinkedList<Integer>();//链表Node<e>
		List<Integer> vector = new Vector<Integer>();
		linkedlist.add(2);
		list.add(1);
		System.out.println(list);
		System.out.println(list.size());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("1", 1);
		 
		Set<Integer> treeset = new TreeSet<Integer>();
		Set<Integer> hashset = new HashSet<Integer>();
		
		Map<String, Integer> hashMap = new HashMap<String, Integer>();
		Map<String, Integer> treeMap = new TreeMap<String, Integer>();
		Map<String, Integer> hashtable = new Hashtable<String, Integer>();
		Map<String, Integer> linkerHashMap = new LinkedHashMap<String, Integer>();
		
	}
	
}
