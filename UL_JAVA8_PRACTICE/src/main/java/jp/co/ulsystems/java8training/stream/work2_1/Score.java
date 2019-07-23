package jp.co.ulsystems.java8training.stream.work2_1;

import java.util.ArrayList;
import java.util.List;

/**
 * 生徒の成績を格納するクラス。
 * @author YutakaOmido
 */
public class Score {
	
	private final String name;
	private final int japanese;
	private final int english;
	private final int math;
	private final int sciense;
	
	public Score(String name, int japanese, int english, int math, int sciense) {
		super();
		this.name = name;
		this.japanese = japanese;
		this.english = english;
		this.math = math;
		this.sciense = sciense;
	}
	
	public int sum() {
		return japanese + english + math + sciense;
	}
	
	public double ave() {
		return sum() / 4.0;
	}
	
	/**
	 * テストデータを取得します。
	 */
	public static List<Score> testData() {
		List<Score> testData = new ArrayList<>();
		testData.add(new Score("YutakaOmido",     30, 75,  55,  39));
		testData.add(new Score("YukiOshima",      40, 51, 100,  92));
		testData.add(new Score("RyoheiNoda",      62, 39,  93, 100));
		testData.add(new Score("KojiroYamaguchi", 36, 28,  81,  43));
		testData.add(new Score("HirokoKubota",    80, 58,  41,  97));
		testData.add(new Score("HeiwaRyuto",       0, 34,  89,  60));
		testData.add(new Score("TaikiKono",       98, 56,  15,  31));
		return testData;
	}

	public String getName() {
		return name;
	}

	public int getJapanese() {
		return japanese;
	}

	public int getEnglish() {
		return english;
	}

	public int getMath() {
		return math;
	}

	public int getSciense() {
		return sciense;
	}
	
}
