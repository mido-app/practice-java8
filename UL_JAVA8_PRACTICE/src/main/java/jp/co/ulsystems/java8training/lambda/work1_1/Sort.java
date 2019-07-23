package jp.co.ulsystems.java8training.lambda.work1_1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 【演習1-1 Sort】
 * 文字列のリストに対して、文字列の文字数の少ない順にリストをソートする。
 * ソートはラムダ式を利用して実装する
 * @author YutakaOmido
 */
public class Sort {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("tanaka", "1", "0022");		
		Collections.sort(list, (e1, e2) -> (e1.length() < e2.length()) ? -1 : 1);
		list.forEach(System.out::println);
	}

}
