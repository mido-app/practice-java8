package jp.co.ulsystems.java8training.lambda.work1_2;

/**
 * 【演習1-2 Unless】
 * if文と逆の動きをするメソッドを作成する。
 * @author YutakaOmido
 */
public class Unless {

	public static void main(String[] args) {
		String empty = "";
		unless(empty.isEmpty(),
			   () -> welcome(empty),
			   () -> raiseError());

		String name = "Yutaka Omido";
		unless(name.isEmpty(),
			   () -> welcome(name),
			   () -> raiseError());
	}

	public static void unless(boolean condition, ExecuteFunction onFalse, ExecuteFunction onTrue) {
		if (condition) {
			onFalse.exec();
		} else {
			onTrue.exec();
		}
	}

	private static void welcome(String name) {
		System.out.println("Welcome!" + name);
	}

	private static void raiseError() {
		throw new RuntimeException("name is empty");
	}

}
