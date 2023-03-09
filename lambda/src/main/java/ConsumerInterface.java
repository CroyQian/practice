/**
 * @author Croy Qian
 * @createDate 2023/2/10
 */
@FunctionalInterface
public interface ConsumerInterface<T> {
    void accept(T t);
}
