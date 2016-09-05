package Thread;

class MyTicketThreadInRunnable implements Runnable{
	private String name;
	private int num = 5;
	MyTicketThreadInRunnable (String name) {
		this.name = name;
	}
	@Override
	public void run() {
		while (num > 0) {
			System.out.println(name + " buy " + num--);
		}
	}
}
