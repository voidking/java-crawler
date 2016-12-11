package com.voidking.crawler2;

public class Lock{

	public static void main(String[] args) {
		// 定义三个售票窗口
		TicketWindow tw1 = new TicketWindow();
		// TicketWindow tw2=new TicketWindow();
		// TicketWindow tw3=new TicketWindow();

		// 使用三个线程同时启动
		Thread t1 = new Thread(tw1);
		Thread t2 = new Thread(tw1);
		Thread t3 = new Thread(tw1);

		t1.start();
		t2.start();
		t3.start();

	}
}

class TicketWindow implements Runnable {

	// 一共两千张票
	private int num = 2000;

	// private Dog dog=new Dog();

	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			// 一秒钟出一张
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// this是对象锁，也可以换成其他对象，如dog
			synchronized (this) {
				// 先判断是否还有票
				if (num > 0) {
					// 显示售票信息
					System.out.println(Thread.currentThread().getName()
							+ "在售出第" + num + "张票");
					num--;
				} else
					break;
			}
		}

	}

}