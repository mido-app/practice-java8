package jp.co.ulsystems.java8training.stream.work2_5;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import jp.co.ulsystems.java8training.utils.Zipper;

/**
 * 【演習2-5】
 * ISBNのチェックディジットを求める
 * @author YutakaOmido
 */
public class ISBNCheckDigit {

	public static void main(String[] args) {
		Integer cd = calcCheckDigit("9784774142357");
		System.out.println(cd);
	}

	public static Integer calcCheckDigit(String isbn) {
		Integer total = 
		    Zipper.zip(IntStream.range(0, isbn.length()-1).boxed(),
		    		Stream.of(isbn)
				       .flatMap(s -> IntStream.range(0, s.length()-1).boxed()
				    		   		.map(i -> s.charAt(i))
				   ))
				  .map(pair -> (pair.fst % 2) == 0 ? (pair.scd()) * 3 : pair.scd())
				  .collect(Collectors.summingInt(i -> i));
		return (total % 10) == 0 ? 0 : 10 - (total % 10);
	}
}
