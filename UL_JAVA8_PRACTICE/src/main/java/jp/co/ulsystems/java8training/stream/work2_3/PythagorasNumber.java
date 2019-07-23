package jp.co.ulsystems.java8training.stream.work2_3;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 【演習2-3】
 * 100番目のピタゴラス数を求める
 * @author YutakaOmido
 */
public class PythagorasNumber {

	public static void main(String[] args) {
		Stream.iterate(1, c -> c + 1)
			  .flatMap(c -> IntStream.range(1, c).boxed()
					  .flatMap(b -> IntStream.range(1, b).boxed()
							  .filter(a -> isRelativelyPrime(a, b, c))
							  .filter(a -> isPythagorasNumber(a, b, c))
							  .map(a -> new TripleNumber(a, b, c))
					  )
			  )
			  .skip(99)
			  .limit(1)
			  .forEach(System.out::println);
	}
	
	/**
	 * ピタゴラス数であるかどうかを返す。
	 * @param a 整数
	 * @param b 整数
	 * @param c 整数
	 * @return ピタゴラス数ならtrue
	 */
	public static boolean isPythagorasNumber (int a, int b, int c) {
		return c * c == a * a + b * b;
	}	
	/**
	 * 受け取った配列要素がが互いに素であるかどうかを調べる
	 * （最大公約数が1であるかどうか調べる）
	 * @param a 整数の配列
	 * @return 互いに素であればtrue
	 */
	public static boolean isRelativelyPrime(int...a) {
		return IntStream.of(a).reduce((x, y) -> gcd(x, y)).getAsInt() == 1;
	}
	
	/**
	 * 最大公約数を求める
	 * @param a 整数
	 * @param b 整数
	 * @return aとbの最大公約数
	 */
	public static int gcd(int a, int b) {
		if (a < b) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		int r = a % b;
		return r == 0 ? b : gcd(b, r);
	}

}
