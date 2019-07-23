package jp.co.ulsystems.java8training.optional.work3_2;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 【演習3-2】
 * ファイルの各行に数字が書かれていて、整数値に変換する。<br>
 * ファイルの内容に不備があり数字に変換できない場合は
 * ファイルの読み込みを全て失敗させる。<br>
 * 全ての行の文字列が整数に変換可能な内容であれば成功。<br>
 * @author YutakaOmido
 */
public class Sequence {

	/**
	 * 文字列を整数に変換します。<br>
	 * 変換に成功した場合は変換後の整数をラップしたOptionalが、
	 * 変換に失敗した場合は空のOptionalが返ります。<br>
	 * @param str 変換対象の文字列
	 * @return 整数をラップしたOptional、または空のOptional
	 */
	public static Optional<Integer> toInt(String str) {
		Integer intValue = null;
		try {
			intValue = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			// do nothing
		}
		return Optional.ofNullable(intValue);
	}

	/**
	 * Optional型でラップされたオブジェクトのストリームを受け取り、<br>
	 * それらの内1つでも空のOptionalがあれば空のOptionalを返し、<br>
	 * そうでなければオブジェクトのストリームをOptionalでラップして返す。<br>
	 * @param optStream Optional型でラップされたオブジェクトのストリーム
	 * @return オブジェクトのストリームをOptionalでラップしたもの
	 */
	public static <V> Optional<Stream<V>> toOptionalStream(Stream<Optional<V>> optStream) {
		return optStream.reduce(Optional.of(Stream.empty()),
						 (stream, v) -> stream.flatMap(a -> v.map(b -> Stream.concat(a, Stream.of(b)))),
						 (s1, s2) -> s1.flatMap(_s1 -> s2.map(_s2 -> Stream.concat(_s1, _s2))));
	}

	/**
	 * 文字列のリストを受け取り、それらを数値のリストに変換した上でOptionalでラップして返す。<br>
	 * 1つでも数値に変換できない場合は空のOptionalを返す。<br>
	 * @param strList 文字列のリスト
	 * @return 数値のリストに変換した上でOptionalでラップしたもの
	 */
	public static Optional<List<Integer>> toIntList(List<String> strList) {
		Optional<Stream<Integer>> optionalStream = toOptionalStream(strList.stream().map(s -> toInt(s)));
		return optionalStream.isPresent() ?
				Optional.of(optionalStream.get().collect(Collectors.toList())) : Optional.empty();
	}

	public static void main(String[] args) {
		System.out.println(toIntList(Arrays.asList("123", "456", "789")));
		System.out.println(toIntList(Arrays.asList("123", "4A6", "789")));
	}

}
