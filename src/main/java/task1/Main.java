package task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("myGroup");
        MyThread thread1 = new MyThread(threadGroup, "1");
        MyThread thread2 = new MyThread(threadGroup, "2");
        MyThread thread3 = new MyThread(threadGroup, "3");
        MyThread thread4 = new MyThread(threadGroup, "4");
        List<MyThread> threadList = new ArrayList<MyThread>(Arrays.asList(thread1, thread2, thread3, thread4));
        threadList.forEach(Thread::start);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadGroup.interrupt();
    }
}
