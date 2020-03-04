package eggFarm;

/**
 * A bounded buffer class: condition synchronization is replaced by semaphore
 * functionality.
 *
 */
public class TheNest implements Buffer {

	protected Object[] buf;
	protected int in = 0;
	protected int out = 0;
	protected int size;
	private static Object lock = new Object();

	protected Semaphore full; // counts number of items
	protected Semaphore empty; // counts number of spaces

	public TheNest(int size) {
		this.size = size;
		this.buf = new Object[size];
		this.full = new Semaphore(0, lock);
		this.empty = new Semaphore(size, lock);
	}

	public void put(Object obj) {
		synchronized (lock) {
			empty.down();
			buf[in] = obj;
			in = (in + 1) % size;
			full.up();
		}
	}

	public Object get() {
		synchronized (lock) {
			full.down();
			Object obj = buf[out];
			buf[out] = null;
			out = (out + 1) % size;
			empty.up();
			return (obj);
		}
	}

}
