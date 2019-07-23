package jp.co.ulsystems.java8training.utils;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Zip
 */
public class Zipper {

    /** 2つのストリームを1つに組み合わせる */
    public static <T,U,R> Stream<R> zip(Stream<T> s1, Stream<U> s2, BiFunction<T,U,R> f){

        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new Iterator<R>(){
            private Iterator<T> i1 = s1.iterator();
            private Iterator<U> i2 = s2.iterator();
            @Override
            public boolean hasNext() {
                return i1.hasNext() && i2.hasNext();
            }
            @Override
            public R next() {
                return f.apply(i1.next(), i2.next());
            }
        }, Spliterator.IMMUTABLE | Spliterator.NONNULL | Spliterator.ORDERED), false);
    }

    public static <T,U> Stream<Pair<T,U>> zip(Stream<T> s1, Stream<U> s2){
        return zip(s1, s2, Pair::of);
    }

    public static class Pair<T,U> {
        /** 1番目 */
        public final T fst;
        /** 2番目 */
        public final U scd;

        public Pair(T fst, U scd) {
            this.fst = fst;
            this.scd = scd;
        }

        public static <T,U> Pair<T,U> of(T fst, U scd) {
            return new Pair<>(fst, scd);
        }

        public T fst(){
            return fst;
        }
        public U scd(){
            return scd;
        }
        @Override
        public String toString(){
            return "(" + fst + "," + scd + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (!fst.equals(pair.fst)) return false;
            if (!scd.equals(pair.scd)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = fst.hashCode();
            result = 31 * result + scd.hashCode();
            return result;
        }
    }


}
