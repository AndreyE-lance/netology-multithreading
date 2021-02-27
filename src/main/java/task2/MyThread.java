package task2;

import java.util.concurrent.Callable;

public class MyThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        int i = 0;
        long randomExecutionTime = (long) (System.currentTimeMillis() + Math.random() * 10000);
        String name = Thread.currentThread().getName();
        while (System.currentTimeMillis() < randomExecutionTime) {
            Thread.sleep(2000);
            System.out.println("Это поток " + name + ". Всем привет!");
            i++;
        }
        return "Поток " + name + " выполнился " + i + " раз(а).";
    }
}
