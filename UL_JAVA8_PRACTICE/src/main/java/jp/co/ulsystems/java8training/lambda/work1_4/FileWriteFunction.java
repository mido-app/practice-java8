package jp.co.ulsystems.java8training.lambda.work1_4;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileWriteFunction<T> {
	public void write(BufferedWriter writer, T obj) throws FileNotFoundException, IOException;
}
