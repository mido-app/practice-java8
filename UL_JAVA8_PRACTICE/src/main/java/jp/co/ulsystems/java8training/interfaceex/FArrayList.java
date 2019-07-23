package jp.co.ulsystems.java8training.interfaceex;

import java.util.ArrayList;

public class FArrayList<T> extends ArrayList<T> implements FList<T> {
	
	public static void main(String[] args) {
		FList<String> list = new FArrayList<>();
		list.add("123");
		list.add("1345");
		list.add("2345");
		
		FList<Integer> list2 = list.map(Integer::parseInt)
								   .filter(n -> n > 1000);
		
		System.out.println(list2.size());
		System.out.println(list2.anyMatch(n -> n > 2000));
		System.out.println(list2.anyMatch(n -> n > 3000));
	}
}
