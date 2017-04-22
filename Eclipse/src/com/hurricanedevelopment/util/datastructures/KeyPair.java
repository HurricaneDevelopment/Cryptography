package com.hurricanedevelopment.util.datastructures;

public class KeyPair<T,E> {
	T key;
	E value;
	
	public KeyPair(T key,E value) {
		this.key = key;
		this.value = value;
	}
	
	public T getKey() {
		return key;
	}
	
	public E getValue() {
		return value;
	}
	
	public String toString() {
		return "[" + key + ": " + value + "]";
	}
}