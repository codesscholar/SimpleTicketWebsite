package synchronize;

/**
 * 用Volatile 来解决多线程同步问题
 * @author wangzhiping wzpmovingday@gmail.com
 * @date 2016/09/05 16:03
 *
 */
public class TicketSystemInVolatile {
	public static void main(String[] args) {
		TicketTwo ticketTwo = new TicketTwo(10);
		TicketSellerTwo ts1 = new TicketSellerTwo(ticketTwo, "1");
		TicketSellerTwo ts2 = new TicketSellerTwo(ticketTwo, "2");
		new Thread(ts1).start();
		new Thread(ts2).start();
		
		/**
		 * 锁提供了两种主要特性：互斥和可见性。互斥指的是同一时间只有一个线程获得特定的锁，
		 * 
		 * Volatile 变量具有 synchronized 的可见性特性，但是不具备原子特性。所以我们只能在一些有限的情况下使用Volatile来代替锁，要想使Volatile提供线程安全，需要：
		 * 		1. 对变量的写操作不依赖当前值
		 * 		2. 该变量没有包含在其它变量的不变式中
		 */
		
		// 所以Volatile 变量并不符合本次的买票系统，因为卖出一张票时需要依赖当前的票的数量
	}

}

class TicketSellerTwo implements Runnable {
	private TicketTwo ticketTwo;
	private String name;
	TicketSellerTwo (TicketTwo ticketTwo, String name) {
		this.ticketTwo = ticketTwo;
		this.name = name;
	}
	@Override
	public void run() {
		while (ticketTwo.getNum() > 0) {
			ticketTwo.sell(name);
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


class TicketTwo {
	volatile private int num;
	TicketTwo (int num) {
		this.num = num;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public void sell(String name) {
		if (name != null && num > 0) {
			System.out.println(name + " sell " + num-- );
		}
	}
}