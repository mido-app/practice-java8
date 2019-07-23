package jp.co.ulsystems.java8training.stream.work2_1;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 【演習2-1】 生徒の成績一覧に対してStream APIを用いて様々な操作を行う。
 * @author YutakaOmido
 */
public class ScoreAnalizer {

	public static void main(String[] args) {
		List<Score> scores = Score.testData();

		// 各生徒の平均点を求める
		System.out.println("--- 各生徒平均点 -----");
		scores.stream()
			  .forEach(s -> System.out.printf("%-15s : %3.1f\n", s.getName(), s.ave()));
		
		// 合計点がもっとも高い生徒名を出力
		System.out.println("----- 最高点 -----");
		scores.stream()
			  .sorted((s1, s2) -> s1.sum() < s2.sum() ? 1 : -1)
		   	  .limit(1)
			  .forEach(s -> System.out.printf("%s : %d\n", s.getName(), s.sum()));
		
		// 理科の成績上位3名を出力
		System.out.println("----- 理科TOP3 -----");
		scores.stream()
			  .sorted((s1, s2) -> s1.getSciense() < s2.getSciense() ? 1 : -1)
			  .limit(3)
			  .forEach(s -> System.out.printf("%-15s : %d\n", s.getName(), s.getSciense()));;
			
		// 合計点が200点以上と200点未満のグループに分けて、
		// それぞれの生徒の名前・合計点を出力
		System.out.println("----- 200点以上・未満のグループ分け -----");
		Map<Boolean, List<Score>> groupBy200Map =
				scores.stream()
					  .collect(Collectors.partitioningBy(s -> s.sum() >= 200));
		System.out.println("[200点以上]");
		groupBy200Map.get(true).forEach(s -> System.out.printf("%-15s : %d\n", s.getName(), s.sum()));
		System.out.println("[200点未満]");		
		groupBy200Map.get(false).forEach(s -> System.out.printf("%-15s : %d\n", s.getName(), s.sum()));
		
		// 国語の点数が50点以上の生徒の数学の平均点
		System.out.println("----- 国語50点以上の生徒の数学の平均点 -----");
		Integer sumOfMath =
				scores.stream()
					  .filter(s -> s.getJapanese() >= 50)
					  .collect(Collectors.summingInt(s -> s.getMath()));
		System.out.println("平均 : " + sumOfMath / 4.0);
		
		// 0点を取った生徒がいるかどうか
		System.out.println("----- 0点を取った生徒がいるか -----");
		boolean anyZero = 
				scores.stream()
				      .flatMap(s -> Stream.of(s.getJapanese(), s.getEnglish(), s.getMath(), s.getSciense()))
				      .anyMatch(s -> s == 0);
		System.out.println(anyZero);
	}

}
