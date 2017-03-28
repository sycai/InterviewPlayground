package JavaConcurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by sycai on 3/27/2017.
 */
public class ThreadsAndExecutors {
    public static void main(String[] args) {
        //newThreadTest();
        //threadSleepTest();
        executorServiceTest();
    }

    public static void newThreadTest() {
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };

        task.run(); // This is the main thread

        Thread thread = new Thread(task); // Create a new thread
        thread.start();

        System.out.println("Done!");
    }

    public static void threadSleepTest() {
        Runnable runnable = () -> {
            try {
                String name = Thread.currentThread().getName();
                System.out.println("Foo " + name);
                TimeUnit.SECONDS.sleep(1); // Sleep for one second
                System.out.println("Bar " + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    public static void executorServiceTest() {
        // Executors are capable of running asynchronous tasks and typically manage a pool of threads
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);

            // Executors have to be stopped explicitly
            // The following code is an example of shutdown routine
            try {
                System.out.println("attempt to shutdown executor");
                executor.shutdown(); // Wait for currently running tasks to finish ...
                executor.awaitTermination(5, TimeUnit.SECONDS); // ... for five seconds
            } catch (InterruptedException e) {
                System.err.println("tasks interrupted");
            } finally {
                if (!executor.isTerminated()) {
                    System.err.println("cancel non-finished tasks");
                }
                executor.shutdownNow(); // Interrupts all running tasks and shut the executor down immediately.
                System.out.println("shutdown finished");
            }
        });
    }
}
