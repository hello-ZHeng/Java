package com.qianfeng.week5.day2.cThread;

public class Program1 {
	public static void main(String[] args) {

		Runnable r0 = () -> {
			synchronized ("a") {
				System.out.println(Thread.currentThread().getName() + "获取了锁标记a, 等待锁标记b");
				
				// 使当前线程释放锁标记a，进入等待队列
				try {
					"a".wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				// 唤醒等待队列中的一个线程。
				"a".notify();
				"a".notifyAll();
				
				synchronized ("b") {
					System.out.println(Thread.currentThread().getName() + "同时获取了锁标记a和b");
				}
			}
		};
		
		Runnable r1 = () -> {
			synchronized ("b") {
				System.out.println(Thread.currentThread().getName() + "获取了锁标记b, 等待锁标记a");
				synchronized ("a") {
					System.out.println(Thread.currentThread().getName() + "同时获取了锁标记a和b");
				}
			}
		};
		
		
		
		
		
		
		
		
		
		
		// 实例化两个线程对象
		Thread t0 = new Thread(r0, "t0");
		Thread t1 = new Thread(r1, "t1");
		
		t0.setPriority(1);
		t1.setPriority(10);
		
		// 开启线程
		t0.start();
		t1.start();
	}
}
