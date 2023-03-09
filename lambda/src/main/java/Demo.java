import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author Croy Qian
 * @createDate 2023/2/10
 */
public class Demo {
    private static void test1(){
        new Thread(() -> System.out.println("Thread run()")).start();
        new Thread(() -> {
            System.out.print("Hello");
            System.out.println(" world");
        }).start();

        Collections.sort(list, (s1, s2) -> {
            if (s1 == null)
                return -1;
            if (s2 == null) {
                return 1;
            }
            return s1.length() - s2.length();
        });

//        ActionListener listener = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        };
        ActionListener listener = e -> {
            System.out.println("button clicked");
        };

        BinaryOperator<Long> add = (x, y) -> x + y;

        ConsumerInterface<String> consumerInterface = str -> System.out.println(str);
    }

    private static void test2(){
        /**
         * -------------------------------list.forEach----------------------------------------------
         */
//        list.forEach(new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//
//            }
//        });
        List<String> listEx = new ArrayList<>();
        List<String> listEx1 = new ArrayList<>();
        list.forEach(s -> {
//            if (s.length() > 3) {
//                System.out.println(s);
//            }
            List<String> tempListEx = new ArrayList<>();
            tempListEx.add(s);
            listEx1.addAll(tempListEx);
        });

        listEx1.forEach(System.out::println);

        /**
         * -------------------------------list.removeIf(删除所有满足条件的)----------------------------------------------
         */
//        list.removeIf(new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return s.length() > 3;
//            }
//        });
        list.removeIf(s -> s.length() > 3);

        /**
         * -------------------------------list.replaceAll----------------------------------------------
         */
        list.replaceAll(s -> {
            if (s.length() > 3) {
                return s.toUpperCase();
            }
            return s;
        });

        /**
         * -------------------------------list.sort----------------------------------------------
         */
        list.sort((s1, s2) -> s1.length() - s2.length());

        /**
         * -------------------------------Map.forEach----------------------------------------------
         */
        map.forEach((k, v) -> {
            System.out.println("key=" + k + ",value=" + v);
        });

        /**
         * -------------------------------Map.getOrDefault----------------------------------------------
         * -------------------------------Map.putIfAbsent----------------------------------------------
         * ------------Map.remove(Objetct key, Object value)只有在map中key正好映射到value才删除------------------
         * ------------Map.replace(K key, V value)只有在map中key存在时用value替换原来的值------------------
         * ------------Map.replace(K key, V oldValue，V newValue)只有在map中key存在时且等于oldValue时，用newValue替换原来的值------------------
         * ------------Map.computeIfPresent 仅当key存在时才调用转换(如果新值为null则删去旧值，不为null则put) ----------------------
         * ------------Map.compute key的值不存在，value的参数为null ----------------------
         *
         */
        list.forEach(k -> {
            map.putIfAbsent(k, "0");
            map.computeIfPresent(k, (key, value) -> value + "0");
        });
        list.forEach(k -> map.compute(k, (key, value) -> {
            System.out.println("dsadsa");
            return value != null ? value + "0" : "0";
        }));

        /**
         * -------------------------------Map.replaceAll----------------------------------------------
         */
        map.replaceAll((k, v) -> v.toUpperCase());

        /**
         * -------------------------------Map.merge----------------------------------------------
         * merge(K key, V value, BiFunction<? super V,? super V,? extends V> remappingFunction)
         * 如果Map中key对应的映射不存在或者为null，则将value（不能是null）关联到key上；
         * 否则执行remappingFunction，如果执行结果非null则用该结果跟key关联，否则在Map中删除key的映射
         */

    }

    public static void main(String[] args) {
        test2();
    }

    private static List<String> list = Arrays.asList("I", "love", "you", "too");

    private static Map<String, String> map = new HashMap<>();
}
