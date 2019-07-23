package jp.co.ulsystems.java8training.interfaceex;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;

/**
 * 【演習4-1】
 * anyMatch、filter、mapメソッドをデフォルトメソッドとしてもつ
 * FListインターフェースの作成
 * @author YutakaOmido
 */
public interface FList<T> extends List<T> {

	/**
	 * このリスト上に条件を満たす要素があるかどうか調べる。<br>
	 * @param predicate 条件
	 * @return 条件を満たす要素が1つでもあればtrue
	 */
	default boolean anyMatch(Predicate<T> predicate) {
		System.out.println(this.getClass().getName());
		return this.stream().anyMatch(predicate);
	}

	/**
	 * このリスト上の条件を満たす要素のみを集めたリストを返します。<br>
	 * @param predicate 条件
	 * @return 条件に当てはまる要素のみのリスト
	 */
	@SuppressWarnings("unchecked")
	default FList<T> filter(Predicate<T> predicate) {
		System.out.println(this.getClass().getName());
		return this.stream()
				   .filter(predicate)
				   .collect(Collector.of(() -> { return this.getInstance(); },
									  	 (l, e) -> { l.add(e); },
									  	 (l1, l2) -> { l1.addAll(l2); return l1; }));
	}

	/**
	 * このリストの各要素にマッピング関数を適用した結果のリストを返します。<br>
	 * @param function マッピング関数
	 * @return マッピング関数を適用した結果のリスト
	 */
	@SuppressWarnings("unchecked")
	default <R> FList<R> map(Function<T, R> function) {
		System.out.println(this.getClass().getName());
		return this.stream()
				   .map(function)
				   .collect(Collector.of(() -> { return this.getInstance(); },
										 (l, e) -> { l.add(e); },
										 (l1, l2) -> { l1.addAll(l2); return l1; }));
	}

	@SuppressWarnings("rawtypes")
	default FList getInstance() {
		try {
			return this.getClass().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return new FArrayList<>();
		}
	}
}
