package ali_guidence;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class ConcurrentTest {

    @Test
    /**
     * 线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，
     * 这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
     */
    public void executeThread() {
        BlockingQueue<Runnable> bQueue = new ArrayBlockingQueue<>(16);
        ThreadFactory factory = new ThreadFactory(){

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
            
        };
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 10L, TimeUnit.MINUTES, 
            bQueue, factory, new ThreadPoolExecutor.AbortPolicy());
        threadPoolExecutor.execute(new Task());
        threadPoolExecutor.execute(new Task());
    }

    static class Task implements Runnable{

        @Override
        public void run() {
            IntStream.range(1, 20).forEach(System.out::println);
        }
    }

    @Test
    /**
     * ThreadLocal 在使用之后需要remove，
     * 否则在线程池中，会被下一次线程所使用
     */
    public void threadLocal() {
        ThreadLocal<Integer> tl = new ThreadLocal<>();
        tl.set(7);
        Thread t1 = new Thread(new LocalTask(tl));
        t1.start();
        Thread t2 = new Thread(new LocalTask(tl));
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {}
    }


    static class LocalTask implements Runnable{
        ThreadLocal<Integer> tl = new ThreadLocal<Integer>();

        public LocalTask(ThreadLocal<Integer> tl) {
            this.tl = tl;
        }

        @Override
        public void run() {
            tl.set(12);
            for (int i = 0; i < 20; i++) {
                tl.set(tl.get() + i);
                System.out.println(Thread.currentThread().getName() 
                    + " - "
                    + tl.get() );
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {}
            }
            tl.remove();
        }
    }

    @Test
    /**
     * lock不能放在try块当中，也不能在lock和try之间有任何可能发生异常的代码
     * 防止lock无法解锁，和unlock一个未上锁的进而引发异常
     */
    public void releaseLock() {
        ReentrantLock lock = new ReentrantLock();
        lock.tryLock();
        try {
            System.out.println( "do something" );
        } finally {
            lock.unlock();
        }
    }
}
