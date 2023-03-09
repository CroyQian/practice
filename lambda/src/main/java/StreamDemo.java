import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * stream并不是某种数据结构，它只是数据源的一种视图。这里的数据源可以是一个数组，Java容器或I/O channel等。
 * 正因如此要得到一个stream通常不会手动创建，而是调用对应的工具方法，比如：
 * 1)调用Collection.stream()或者Collection.parallelStream()方法
 * 2)调用Arrays.stream(T[] array)方法
 *
 * 有4种stream接口继承自BaseStream（IntStream,LongStream,DoubleStream,Stream）
 * 对应三种基本类型(int ,long , double 不是包装类型) ，stream对应所有剩余类型的stream视图
 * 为不同数据类型设置不同stream接口，可以1.提高性能，2.增加特定接口函数。
 *
 * 虽然大部分情况下stream是容器调用Collection.stream()方法得到的，但stream和collections有以下不同：
 * 无存储。stream不是一种数据结构，它只是某种数据源的一个视图，数据源可以是一个数组，Java容器或I/O channel等。
 * 为函数式编程而生。对stream的任何修改都不会修改背后的数据源，比如对stream执行过滤操作并不会删除被过滤的元素，而是会产生一个不包含被过滤元素的新stream。
 * 惰式执行。stream上的操作并不会立即执行，只有等到用户真正需要结果的时候才会执行。
 * 可消费性。stream只能被“消费”一次，一旦遍历过就会失效，就像容器的迭代器那样，想要再次遍历必须重新生成。
 */
public class StreamDemo {
    /**
     * 中间操作 concat() distinct() filter() flatMap() limit() map() peek() skip() sorted() parallel() sequential() unordered()
     * 结束操作 allMatch() anyMatch() collect() count() findAny() findFirst() forEach() forEachOrdered() max() min() noneMatch() reduce() toArray()
     * 返回值是stream的大都是中间操作，否则是结束操作
     */
    private static void test() {
//        Stream<String> stream = list.stream();
        Stream<String> stream = Stream.of("I", "love", "you", "too");

        /**
         * forEach
         */
        //同stream.forEach(str -> System.out.println(str));
//        stream.forEach(System.out::println);

        /**
         * filter
         */
//        stream.filter(str -> str.length() == 3).forEach(System.out::println);

        /**
         * distinct 去重
         */
//        stream.distinct().forEach(System.out::println);

        /**
         * sorted
         */
//        stream.sorted((s1, s2) -> s1.length() - s2.length()).forEach(System.out::println);

        /**
         * map 返回一个对当前所有元素执行mapper之后的结果，元素的个数不会改变，元素的类型取决于转换之后的类型
         */
        stream.map(String::toUpperCase).forEach(System.out::println);

        /**
         * flatMap() 对每个元素执行mapper指定的操作，并用所有mapper返回的Stream中的元素组成一个新的Stream作为最终返回结果
         * 通俗的讲flatMap()的作用就相当于把原stream中的所有元素都"摊平"之后组成的Stream，转换前后元素的个数和类型都可能会改变
         * flatMap() 需要返回stream
         */
        Stream<List<Integer>> streamEx = Stream.of(Arrays.asList(1,2), Arrays.asList(3, 4, 5));
        streamEx.flatMap(list -> list.stream()).forEach(System.out::println);
    }

    private static void test2() {
        /**
         * reduce 从一堆值中生成一个值(collect 生成集合)
         *
         * 举例：找出最长的单词
         * Optional是（一个）值的容器，使用它可以避免null值的麻烦
         */
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Optional<String> longest = stream.reduce((s1, s2) -> s1.length() >= s2.length() ? s1 : s2);
        System.out.println(longest.get());

        /**
         * 求一组单词长度之和
         */
        Integer lengthSum = stream.reduce(0,  //初始值
                (sum, str) -> sum + str.length(),  //累加器
                (a, b) -> a + b);  //部分和拼接器
        //等价于
        stream.mapToInt(str -> str.length()).sum();
    }

    public static void main(String[] args) {
//        test();
    }

    private static List<String> list = Arrays.asList("I", "love", "you", "too");
}
