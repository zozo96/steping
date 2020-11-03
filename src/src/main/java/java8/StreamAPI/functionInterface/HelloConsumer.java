package java8.StreamAPI.functionInterface;

import java.util.Objects;

/**
 * test functional interface
 *
 * @author : Sonya
 * @date : 2020/7/9 21:02
 */
@FunctionalInterface
public interface HelloConsumer<T> {
    /**
     * test say
     * @param anything any
     */
    void sayAnything(T anything);

    /**
     * why
     * @param after
     * @return
     */
    default HelloConsumer<T> andThen(HelloConsumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T mes) -> {
            sayAnything(mes);
            after.sayAnything(mes);
        };
    }
}
