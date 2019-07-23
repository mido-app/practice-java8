package jp.co.ulsystems.java8training.stream.work2_2;

import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 【演習2-2】
 * 単語の登場数を数える関数wc
 * @author YutakaOmido
 */
public class WordCount {

	public static void main(String[] args) {
		wc(Stream.of("aaa bbb ccc", "kkk ccc bbb", "aaa kkk aaa", "ccc ttt"))
			.forEach(s -> System.out.println("単語: " + s.getKey() + " 回数: " + s.getValue()));
	}

	public static Set<Entry<String, Integer>> wc(Stream<String> sentences) {
		return sentences.flatMap(s -> Stream.of(s.split(" ")))
				 		.collect(Collectors.groupingBy(s -> s))
				 		.entrySet()
				 		.stream()
				 		.collect(Collectors.toMap(k -> k.getKey(), k -> k.getValue().size()))
				 		.entrySet();
	}
}
