package jp.co.ulsystems.java8training.stream.work2_3;

/**
 * 3つの整数を格納するクラス。
 * @author YutakaOmido
 */
public class TripleNumber {
	private int a;
	private int b;
	private int c;
	
	public TripleNumber(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	public int getC() {
		return c;
	}
	
	public String toString() {
		return String.format("(%3d, %3d, %3d)", a, b, c);
	}
}
