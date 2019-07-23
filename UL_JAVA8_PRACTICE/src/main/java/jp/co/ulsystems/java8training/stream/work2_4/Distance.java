package jp.co.ulsystems.java8training.stream.work2_4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jp.co.ulsystems.java8training.utils.Zipper;

/**
 * 【演習2-4】
 * チェックポイントのスタートからの距離が格納されたリストから
 * 各チェックポイント間の距離を求める
 * @author YutakaOmido
 */
public class Distance {

	public static void main(String[] args) {
		List<Integer> distFromStart = Arrays.asList(10, 15, 24, 30, 45);
		distPtoP(distFromStart).forEach(System.out::println);
	}
	
	public static List<Integer> distPtoP(List<Integer> distFromStart) {
		return Zipper.zip(distFromStart.stream().limit(distFromStart.size()-1),
				   distFromStart.stream().skip(1))
			  .map(pair -> pair.scd() - pair.fst())
			  .collect(Collectors.toList());
	}

}
