package lds.com.medicalsystem;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {
    @Test
    public void testThreadLocalSetAndGet(){
        // 提供一个ThreadLocal对象
        ThreadLocal tl = new ThreadLocal();

        // 开启两个线程：
        new Thread(()->{
            tl.set("白月魁");
            System.out.println(Thread.currentThread().getName()+"："+tl.get());
            System.out.println(Thread.currentThread().getName()+"："+tl.get());
            System.out.println(Thread.currentThread().getName()+"："+tl.get());
        },"线程一").start();

        new Thread(()->{
            tl.set("马克");
            System.out.println(Thread.currentThread().getName()+tl.get());
            System.out.println(Thread.currentThread().getName()+tl.get());
            System.out.println(Thread.currentThread().getName()+tl.get());
        },"线程二").start();
    }
}
