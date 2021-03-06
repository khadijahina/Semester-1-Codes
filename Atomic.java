package br.com.leonardoz.features.atomics;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {
		static class AtomicCounter {
		private AtomicInteger atomicInteger = new AtomicInteger(0);

		public void increment() {
			atomicInteger.incrementAndGet();
		}

		public void decrement() {
			atomicInteger.decrementAndGet();
		}

		public int get() {
			return atomicInteger.get();
		}
	
	}
		public static void main(String[] args) throws InterruptedException {
		AtomicCounter counter = new AtomicCounter();
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10_000; i++) {
			cachedThreadPool.execute(() -> counter.increment());
		}
		cachedThreadPool.shutdown();
		cachedThreadPool.awaitTermination(4000, TimeUnit.SECONDS);
		System.out.println("Result shound be 10000: Actual result is: " + counter.get());
	}

	}


