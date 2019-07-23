package jp.co.ulsystems.java8training.lambda.work1_4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileReadFunction<T> {
	public T read(BufferedReader reader) throws FileNotFoundException, IOException;
}
