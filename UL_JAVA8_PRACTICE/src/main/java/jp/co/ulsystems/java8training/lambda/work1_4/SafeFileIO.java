package jp.co.ulsystems.java8training.lambda.work1_4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * 【演習1-4】
 * 本質的な処理だけをラムダ式に書くことで、
 * 任意の型を入出力に扱うことのできるファイル入出力メソッドを作成する。
 * @author YutakaOmido
 */
public class SafeFileIO {

	public static void main(String[] args) {
		// ファイルへの書き込みテスト
		// 整数の配列を受け取り、各要素を2倍した値を書き込む
		String w_filepath = "./src/main/resources/work1-4_write.txt";
		List<Integer> list = Arrays.asList(1,3,4,5,7,9);
		fileWrite(w_filepath, list, (w, l) -> {
			for (Integer e : l) {
				Integer e2 = e * 2;
				w.write(e2.toString());
				w.newLine();
			}
		});
		
		// ファイルの読み込みテスト
		System.out.println("----- file read -----");
		String r_filepath = "./src/main/resources/work1-4_read.txt";
		List<String> result1 = fileRead(r_filepath, reader -> {
			String line = null;
			List<String> lines = new ArrayList<>();
			while((line = reader.readLine()) != null) {
				lines.add(line);
			}
			return lines;
		});
		result1.forEach(System.out::println);
		
		// ファイル読み込みリスト出力テスト
		System.out.println("----- file reads -----");
		List<String[]> result2 = fileReads(r_filepath, line -> line.split("、"));
		result2.forEach(array -> {
			for(String s : array) {
				System.out.println(s);
			}
		});
	}

	/**
	 * ファイルに任意のオブジェクトを書き込む関数。
	 * @param filepath 書き込み先のファイルパス
	 * @param obj ファイルに書き込むオブジェクト
	 * @param func
	 * 		引数としてBufferedWriterと書き込みたいオブジェクトを受け取り、
	 * 		ファイルにどのように書き込むかを定義する関数
	 */
	public static <T> Boolean fileWrite(String filepath, T obj, FileWriteFunction<T> func) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))){
			func.write(writer, obj);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * ファイルを読み込み、その結果を任意の型のオブジェクトに変換して返す関数。
	 * @param filepath 読み込むファイルのパス
	 * @param func 引数としてBufferedReaderを受け取り、読み込み処理を行う関数
	 * @return 読み込んだ結果生成されたオブジェクト
	 */
	public static <T> T fileRead(String filepath, FileReadFunction<T> func) {
		T obj = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
			obj = func.read(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj; 
	}
	
	/**
	 * ファイルを読み込み、その結果を任意の型のリストとして返す関数。
	 * @param filepath 読み込むファイルのパス
	 * @param func
	 * 		引数として読み込んだ1行の文字列を受け取り、
	 * 		リストの要素となるオブジェクトを返す関数
	 * @return 読み込んだ結果生成されたリスト
	 */
	public static <T> List<T> fileReads(String filepath, Function<String, T> func) {
		List<T> list = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
			String line = null;
			while((line = reader.readLine()) != null) {
				list.add(func.apply(line));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}
	
}
