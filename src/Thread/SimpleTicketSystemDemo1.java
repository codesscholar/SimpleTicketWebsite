package Thread;

/**
 * simple ticket system , the aim is to learn how to use thread
 * @author wangzhiping wzpmovingday@gmail.com
 * @date 2016/08/30 16:40
 *
 */
public class SimpleTicketSystemDemo1 {
	public static void main(String[] args) {
		/**
		 * 第一部分 ：了解实现 多线程 的两种基本方式，并了解各自的优劣
		 * 
		 * 多线程的实现方式包括两种 ：继承 Thread 类，或者 实现 Runnable 接口，以下有几个注意点：
		 * 1. start() 和 run()方法 
		 * 		start() : 最终调用本地方法（native），启动一个新线程， 新线程会自动执行；
		 * 		run() :  只会在当前的线程中运行相应对象的run方法，并不会启动新线程，所以大多表现出串行的结果。
		 * 2. 两种形式的优缺点，以及选择方式：
		 * 		个人觉得区别还是很明显的，首先从编程思想的角度来说，如果仅仅只是重写run方法还完全没有达到继承Thread类的要求。其次：
		 * 	         （1）：避免 java 单继承特性带来麻烦
		 * 			 （2）：实现 Runnable 接口可以实现资源共享（多线程数据共享，下面会做详细分析）
		 * 
		 * 预告 ： 关于多线程资源共享的方式探讨将会在第二部分开始
		 */
			
//		// 方法1 ：继承 Thread 实现
//		new MyTicketThread("A").start();
//		new MyTicketThread("B").start();
//		new MyTicketThread("C").start();
//		new MyTicketThread("D").start();
//		
//		// 方法2 ：实现 Runnable 接口
//		new Thread(new MyTicketThreadInRunnable("A")).start();
//		new Thread(new MyTicketThreadInRunnable("B")).start();
//		new Thread(new MyTicketThreadInRunnable("c")).start();
		
		// 实现Runnable接口实现多线程资源共享展示
		MyTicketThreadInRunnable myRunnable = new MyTicketThreadInRunnable("wang");
		new Thread(myRunnable).start();
		new Thread(myRunnable).start();
		new Thread(myRunnable).start();
		
	}		
}
