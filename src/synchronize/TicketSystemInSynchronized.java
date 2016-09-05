package synchronize;

/**
 * 第二部分 多线程 共享数据问题
 * @author wangzhiping wzpmovingday@gmail.com
 * @date 2016/08/31
 *
 */
public class TicketSystemInSynchronized {
	public static void main(String[] args) {
		// 方法1 ：如Thread/demo1 中所示，当传入同一个Runnable实例的时候，可以共享对象。
		
		// 方式2 ：前提－当每个线程执行的代码不一样的时候 可以－将共享数据封装到一个对象中，然后传递给不同的Runnable对象。
		Ticket s = new Ticket(10);
		TicketSeller test1 = new TicketSeller(s, "1");
		TicketSeller test2 = new TicketSeller(s, "2");
		new Thread(test1).start();
		new Thread(test2).start();
		
		/**
		 * 多线程在并行领域必不可少，保证共享数据的可见性则是多线程正确工作的关键
		 * 
		 * 共享变量无非就是让所有的线程使用同一个 对象实例（包含数据），多线程共享数据 包含很多问题：
		 * 1. 可见性 ： 如果一个线程对于共享变量的修改能够及时让其它线程看到，就叫做可见性
		 * 2. 多线程 内存模型：
		 * 		＊ 主内存 ： 保存共享变量原始数据
		 * 		＊ 工作内存 ：保存共享变量数据副本
		 *    一般线程对于共享变量的操作必须在工作内存中操作，具体流程就是：线程1从主内存中读取X到工作内存（缓存）
		 *    －> 线程修改X的值 －> 将X写入主内存 －>线程2读取
		 * 
		 */
		
	}
}

class TicketSeller implements Runnable{
	private Ticket s;
	private String name;
	
	TicketSeller(Ticket s, String name) {
		this.s = s;
		this.name = name;
	}
	
	@Override
	public void run() {
		while (s.getNum() > 0) {
			
			s.sell(name);
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
}

class Ticket {
	private int num;
	Ticket (int num) {
		this.num = num;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	// 没有任何同步措施
//	public void sell (String name) {
//			System.out.println(name + " sell " + num);
//			this.num--;
//	}
	
//	// 利用 synchronized 进行同步
//	public synchronized void sell (String name) {
//		System.out.println(name + " sell " + num);
//		this.num--;
//	}
	
	// 修复隐藏bug
	public synchronized void sell (String name) {
		if (name != null && num > 0) {
			System.out.println(name + " sell " + num);
			this.num--;
		}
	}
}