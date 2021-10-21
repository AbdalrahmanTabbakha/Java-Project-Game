package project5;


public class Test extends Thread {
	@Override
	public synchronized void run() {
		System.out.println("Hello, World!");
		try {
			Thread.sleep(3000);
			
		} catch (Exception e) {}
		System.out.println("ends here..");
	}
	
	public static void main(String[] args) {
		Thread t = new Test();
		Thread t1 = new Test();
		t.start();
		t1.start();
		System.out.println(1);
	}
}
