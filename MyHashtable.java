package com.test.benchmarks;

import java.util.Hashtable;

public class MyHashtable implements SimpleKV {
	Hashtable<Object, Object> map = new Hashtable<Object, Object>();

	@Override
	public void put(Object k, Object v) {
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
		return 0;
	}
}
