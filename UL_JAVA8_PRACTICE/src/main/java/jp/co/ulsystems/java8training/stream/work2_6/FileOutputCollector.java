package jp.co.ulsystems.java8training.stream.work2_6;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

import jp.co.ulsystems.java8training.lambda.work1_4.SafeFileIO;

public abstract class FileOutputCollector<T> implements Collector<T, List<T>, Void> {

	@Override
	public Supplier<List<T>> supplier() {
		return ArrayList::new;
	}

	@Override
	public BiConsumer<List<T>, T> accumulator() {
		return (l, e) -> l.add(e);
	}

	@Override
	public BinaryOperator<List<T>> combiner() {
		return (l1, l2) -> {
			l1.addAll(l2);
			return l1;
		};
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return EnumSet.noneOf(Characteristics.class);
	}

	public static FileOutputCollector<String> write(String filepath) {
		return new FileOutputCollector<String>() {

			@Override
			public Function<List<String>, Void> finisher() {
				return list -> {
					SafeFileIO.fileWrite(filepath, list, (w, l) -> {
						for (String s : l) {
							w.write(s);
							w.newLine();
						}
					});
					return null;
				};
			}
		};
	}

	public static <T> FileOutputCollector<T> write(String filepath, Function<T, String> func) {
		return new FileOutputCollector<T>() {

			@Override
			public Function<List<T>, Void> finisher() {
				return list -> {
					SafeFileIO.fileWrite(filepath, list, (w, l) -> {
						for (T s : l) {
							w.write(func.apply(s));
							w.newLine();
						}
					});
					return null;
				};
			}
		};
	}

	public static void main(String[] args) {
		Stream.of("aaa", "vvv", "bbb", "ccc", "kkk")
			  .collect(FileOutputCollector.write("./src/main/resources/work2-6_write_string.txt"));

		Stream.of(1, 3, 5, 7, 9, 11)
			  .collect(FileOutputCollector.write("./src/main/resources/work2-6_write_with_lambda.txt",
					   e -> String.valueOf(e + 10)));
	}

}
