package completableFuture;

import java.util.concurrent.*;

/**
 * @author Croy Qian
 * @createDate 2023/2/9
 */
public class Demo {
    private static void test1(){
        /**
         * new ThreadPoolExecutor(num, num, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(num), new ThreadPoolExecutor.AbortPolicy());
         * 不建议使用 Executors 的最重要的原因是：Executors 提供的很多方法默认使用的都是无界的 LinkedBlockingQueue，高负载情境下，无界队列很容易导致 OOM，而 OOM 会导致所有请求都无法处理，这是致命问题。所以强烈建议使用有界队列。
         */
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletableFuture<String> f0 = CompletableFuture
                /**
                 * 启动一个异步流程
                 * 也可以用 .supplyAsync(() -> "Hello world")
                 * 默认情况下 CompletableFuture 会使用公共的 ForkJoinPool 线程池，这个线程池默认创建的线程数是 CPU 的核数（也可以通过 JVM option:-Djava.util.concurrent.ForkJoinPool.common.parallelism 来设置 ForkJoinPool 线程池的线程数）。如果所有 CompletableFuture 共享一个线程池，那么一旦有任务执行一些很慢的 I/O 操作，就会导致线程池中所有线程都阻塞在 I/O 操作上，从而造成线程饥饿，进而影响整个系统的性能。所以，强烈建议你要根据不同的业务类型创建不同的线程池，以避免互相干扰。
                 */
                .supplyAsync(() -> "Hello world", executorService)
                /**
                 * 串行执行
                 * thenApply   返回CompletionStage<R>
                 * thenRun     返回CompletionStage<Void>
                 * 并行执行
                 * thenApplyAsync
                 * thenRunAsync
                 */
                .thenApply(s -> s + " QQ")
                .thenApply(String::toUpperCase)
                ;
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            f0.thenApplyAsync(s -> {
                System.out.println("异步执行" + finalI);
                return s + " QP" + finalI;
            });
        }
        System.out.println(f0.join());
    }

    private static void test2() {
        CompletableFuture<Integer> f0 = CompletableFuture.supplyAsync(() -> (7/ 0)).thenApply(r -> r + 10)
                .exceptionally(e -> {
                    System.out.println(e.toString());
                    return 0;
                });
        ;
        System.out.println(f0.join());
    }

    private static void test3() {
        //任务1：洗水壶->烧开水
        CompletableFuture<Void> f1 =
                CompletableFuture.runAsync(()->{
                    System.out.println("T1:洗水壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T1:烧开水...");
                    sleep(15, TimeUnit.SECONDS);
                });
        //任务2：洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(()->{
                    System.out.println("T2:洗茶壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T2:洗茶杯...");
                    sleep(2, TimeUnit.SECONDS);

                    System.out.println("T2:拿茶叶...");
                    sleep(1, TimeUnit.SECONDS);
                    return "龙井";
                });
        //任务3：任务1和任务2完成后执行：泡茶
        CompletableFuture<String> f3 =
                f1.thenCombine(f2, (__, tf)->{
                    System.out.println("T1:拿到茶叶:" + tf);
                    System.out.println("T1:泡茶...");
                    return "上茶:" + tf;
                });
        //等待任务3执行结果
        System.out.println(f3.join());
    }

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    private static void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        } catch (InterruptedException e) {

        }
    }
}
