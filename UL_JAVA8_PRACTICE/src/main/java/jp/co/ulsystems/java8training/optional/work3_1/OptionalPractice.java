package jp.co.ulsystems.java8training.optional.work3_1;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

/**
 * 【演習3-1】
 * Optional型の練習
 * @author YutakaOmido
 */
public class OptionalPractice {

	public static void main(String[] args) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("a", 80);
		map.put("b", 55);
		map.put("c", -92);

		// 2-1: キーに対応する値があればコンソールに出力、ない場合はNothingを出力
		Optional<Integer> aValue = optGet(map, "a");
		System.out.println(aValue.isPresent() ? aValue.get() : "Nothing");
		Optional<Integer> dValue = optGet(map, "d");
		System.out.println(dValue.isPresent() ? aValue.get() : "Nothing");

		// 2-2: キーに対応する値が正ならば2倍し、負なら0にして取得する
		Optional<Integer> bValue = optGet(map, "b");
		System.out.println(bValue.filter(e -> e >= 0).map(e -> 2 * e).orElse(0));
		Optional<Integer> cValue = optGet(map, "c");
		System.out.println(cValue.filter(e -> e >= 0).map(e -> 2 * e).orElse(0));

		// 3: optGet2
		Optional<Integer> abValue = optGet2(map, "a", "b", (a, b) -> a + b);
		System.out.println(abValue.isPresent() ? abValue.get() : "Nothing");
		Optional<Integer> cdValue = optGet2(map, "c", "d", (c, d) -> c + d);
		System.out.println(cdValue.isPresent() ? cdValue.get() : "Nothing");
	}

	/**
	 * マップとキーを渡すと、対応する値をOptionalでラップして返す。
	 * 対応する値がない場合、空のOptionalを返す。
	 * @param map 値を取得するマップ
	 * @param key 値を取得するためのキー
	 * @return 対応する値がある場合、その値をOptionalでラップしたもの。
	 *         対応する値がない場合、空のOptionalを返す。
	 */
	public static <K, V> Optional<V> optGet(Map<K, V> map, K key) {
		return map.containsKey(key) ? Optional.of(map.get(key)) : Optional.empty();
	}

	/**
	 * 2つのキーで取得した値を、引数で与えた関数で結合し、Optionalでラップして返す。
	 * 値が存在しない場合は空のOptionalを返す。
	 * @param map 値を取得するマップ
	 * @param key1 値を取得するためのキー1
	 * @param key2 値を取得するためのキー2
	 * @param func key1とkey2で取得した値それぞれを結合する関数
	 * @return key1とkey2で取得した値それぞれを結合した値
	 */
	public static <K, V> Optional<V> optGet2(Map<K, V> map, K key1, K key2, BiFunction<V, V, V> func) {
		V value1 = map.get(key1);
		V value2 = map.get(key2);
		if ((value1 == null) || (value2 == null)) {
			return Optional.empty();
		}
		return optGet(map, key1).flatMap(v1 -> optGet(map, key2).map(v2 -> func.apply(v1, v2)));
	}

}
