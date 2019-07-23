package jp.co.ulsystems.java8training.lambda.work1_3;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 【演習1-3】
 * 引数を2つ受け取る関数とその第1引数を受け取ることで、
 * 引数が1つの関数を生成して返す関数curryを作成する。
 * @author YutakaOmido
 */
public class ReturningFunction {

	public static void main(String[] args) {
		Function<Integer, Integer> plus = curry((a, b) -> a + b, 1);
		System.out.println(plus.apply(2));
		System.out.println(plus.apply(30));
		
		Function<String, Boolean> longer = curry((n, str) -> str.length() > n, 5);
		System.out.println(longer.apply("abcde"));
		System.out.println(longer.apply("abcdef"));
	}
	
	private static <T, S, U> Function<S, U> curry(BiFunction<T, S, U> func, T arg1) {
		return arg2 -> func.apply(arg1, arg2);
	}

}
