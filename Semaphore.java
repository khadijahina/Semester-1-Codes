package eggFarm;

public class Semaphore {

	public int value;
	public Object lock;

	public Semaphore(Object lock) { // binary semaphore
		this.value = 1;
		this.lock = lock;
	}

	public Semaphore(int sem, Object lock) { // general semaphore
		this.value = sem;
		this.lock = lock;
	}

	public void down() {
		synchronized (lock) {
			while (value == 0) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			value--;
		}
	}

	public void up() {
		synchronized (lock) {
			value++;
			lock.notifyAll(); // notify all threads waiting on this semaphore
		}
	}

}
