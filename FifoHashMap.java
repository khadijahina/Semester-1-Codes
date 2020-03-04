package com.test.benchmarks;

import java.util.LinkedHashMap;

public class FifoHashMap implements SimpleKV {
	private LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
	private int capacity;

	public FifoHashMap(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public void put(Object k, Object v) {
		if(map.size() > capacity)
			map.remove(map.entrySet().iterator().next());
		map.put(k, v);
	}

	@Override
	public Object get(Object k) {
		return map.get(k);
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public int capacity() {
		return capacity;
	}
}
