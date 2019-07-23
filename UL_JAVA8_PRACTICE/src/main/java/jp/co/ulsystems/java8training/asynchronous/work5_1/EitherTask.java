package jp.co.ulsystems.java8training.asynchronous.work5_1;

import java.util.concurrent.CompletableFuture;

/**
 * 【演習5-1】
 * 以下のような非同期処理を実装
 * ・タスクAとタスクBは同時実行できる
 * ・タスクCはタスクAとタスクBのどちらかが終了したら開始できる
 * ・（タスクAかBの先に終了した結果）+タスクCの結果を求める
 * @author YutakaOmido
 */
public class EitherTask {

	public static void main(String[] args) {
		Integer result =
		CompletableFuture.supplyAsync(EitherTask::taskA)
						 .applyToEitherAsync(CompletableFuture.supplyAsync(EitherTask::taskB),
											 (v) -> v)
						 .thenApply((v) -> v + EitherTask.taskC())
						 .join();
		System.out.println(result);
	}

	private static void wait(int second) {
		try {
			Thread.sleep(1000L * second);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static int taskA() {
		wait(10);
		System.out.println("タスクA = 5");
		return 5;
	}

	public static int taskB() {
		wait(3);
		System.out.println("タスクB = 3");
		return 3;
	}

	public static int taskC() {
		wait(2);
		System.out.println("タスクC = 10");
		return 10;
	}
}
