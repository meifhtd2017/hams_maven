package com.metime.test;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.metime.dir.vo.DirVo;

public class Test1 implements Test2<String>{
	
	
	
	@Test
	public void test1(){
		Test1 test1 = new Test1();
		System.out.println(test1.getClass());
		List<DirVo> list = new ArrayList<>();
		list.stream().collect(Collectors.groupingBy(DirVo::getfDirId));
	}
	
}
