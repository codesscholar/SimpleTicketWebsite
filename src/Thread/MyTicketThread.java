package Thread;

class MyTicketThread extends Thread{
	private int num = 5;
	private String name;
	MyTicketThread(String name) {
		this.name = name;
	}
	@Override
	public void run() {
		while (num > 0) {
			System.out.println(name + " buy " + num--);
		}
	}
}
