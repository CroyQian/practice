package semaphore;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author Croy Qian
 * @createDate 2022/3/7
 * @Description 信号量
 */
public class ObjectPoolBaseOnSemaphore<T,R> {
    final List<T> pool;
    final Semaphore semaphore;

    ObjectPoolBaseOnSemaphore(int size, T t) {
        pool = new Vector<T>();
        for (int i = 0; i < size; i++) {
            pool.add(t);
        }
        semaphore = new Semaphore(size);
    }

    //利用对象池的对象，调用func
    R exec(Function<T,R> function) throws InterruptedException {
        T t = null;
        semaphore.acquire();
        try{
            //为了体现出效果，等待2秒
            TimeUnit.SECONDS.sleep(2);
            t = pool.remove(0);
            return function.apply(t);
        } finally {
            pool.add(t);
            semaphore.release();
        }
    }

    static class TestThread implements Runnable{
        ObjectPoolBaseOnSemaphore<Long, String> pool = null;
        TestThread(ObjectPoolBaseOnSemaphore<Long, String> pool) {
            this.pool = pool;
        }
        @Override
        public void run() {
            try {
                pool.exec(t -> {
                    System.out.println(t + "  " + pool.pool.size());
                    return t.toString();
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ObjectPoolBaseOnSemaphore<Long, String> pool = new ObjectPoolBaseOnSemaphore<Long, String>(10, 2L);
        for (int i = 0; i < 100; i++) {
            TestThread testThread = new TestThread(pool);
            new Thread(testThread).start();
        }

    }
}
