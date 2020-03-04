package com.test.benchmarks;

import java.util.Random;

public class Details {
	private static Random generator = new Random();

	public static void main(String[] args) {
		/*
		 * testSimpleKV(new MyLinkedHashMap(), 10000); testSimpleKV(new
		 * MyLinkedHashMap(), 1000000); testSimpleKV(new MyLinkedHashMap(), 10000000);
		 */
		/*
		 * testSimpleKV(new MyHashtable(), 10000); testSimpleKV(new MyHashtable(),
		 * 1000000); testSimpleKV(new MyHashtable(), 10000000);
		 * 
		 */
		testSimpleKV(new RandomHashMap(10), 100000);
		testSimpleKV(new RandomHashMap(100), 100000);
		testSimpleKV(new RandomHashMap(1000), 100000);

		testSimpleKV(new FifoHashMap(10), 100000);
		testSimpleKV(new FifoHashMap(100), 100000);
		testSimpleKV(new FifoHashMap(1000), 100000);
	}

	private static void testSimpleKV(SimpleKV map, int count) {
		long startTime = System.nanoTime();
		int[] keys = new int[count];
		int step = count / 100;
		for (int j = 0; j < count; j += step) {
			for (int i = 0; i < step; i++) {
				keys[j + i] = generator.nextInt();
				map.put(keys[j + i], generator.nextInt());
			}
			int size = map.size();
			for (int i = 0; i < size / 10; i++) {
				map.get(keys[generator.nextInt(size)]);
			}
		}
		long entTime = System.nanoTime();
		long totalTime = (entTime - startTime) / 1000000L;
		System.out.println("Executed in " + totalTime + " ms for count " + String.valueOf(count) + " with capacity "
				+ map.capacity() + " for class " + map.toString());
	}
}
