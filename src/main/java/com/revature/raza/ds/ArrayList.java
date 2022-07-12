package com.revature.raza.ds;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {

	private T[] elements; 
	private int size; 
	private int initCapacity = 10; 
	private int factor = 2; 
	
	// accept varies parameters 
	public ArrayList(T...elements) {
		this.elements = elements; 
	}
	
	// no-parameter constructor 
	public ArrayList() {
		elements = (T[]) new Object[initCapacity]; 
	}
	
	public void ensureCapacity(int init) {
		if (init > elements.length) {
			T[] temp = elements; 
			size = size * 2; 
			elements = (T[]) new Object[size]; 
			elements = Arrays.copyOf(temp, size); 
			
		}
	}
	
	@Override
	public void add(T t) {
		// TODO Auto-generated method stub
		ensureCapacity(size + 1); 
		this.elements[size++] = t;
	}

	@Override
	public void add(T t, int index) {
		// TODO Auto-generated method stub
		ensureCapacity(size + 1); 
		for (int i = size - 1; i >=index; i--) {
			this.elements[i + 1] = this.elements[i]; 
		}
		this.elements[index] = t; 
		size++; 
		
	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		if (index >= 0 && index < size) {
			return this.elements[index];  
		}else {
			throw new IndexOutOfBoundsException(); 
		}
	}

	@Override
	public int indexOf(T t) {
		// TODO Auto-generated method stub
		if (t == null) return -1; 
		for (int i = 0; i < size; i++) {
			if (this.elements[i].equals(t)) {
				return i; 
			}
		}
		return -1;
	}

	@Override
	public boolean contains(T t) {
		// TODO Auto-generated method stub
		if (t == null) return false; 
		for (T e: this.elements) {
			if (e.equals(t)) {
				return true; 
			}
		}
		return false;
	}

	@Override
	public T remove(T t) {
		// TODO Auto-generated method stub
		int index = indexOf(t); 
		if (index != -1) {
			return remove(index); 
		}
		return null;
	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		if (index >=0 && index < elements.length) {
			T t = elements[index]; 
			for (int i = index; i < elements.length-1; i++) {
				elements[i] = elements[i + 1]; 
			}
			//empty last element position that was shifted
			elements[elements.length -1] = null; 
			size--; 
			return t; 
		} else {
			throw new IndexOutOfBoundsException(); 
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArrayList other = (ArrayList) obj;
		return Arrays.deepEquals(elements, other.elements) && size == other.size;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("");
		for (int i = 0; i<size; i++) {
			result.append(elements[i] + "\n");
		}
		return result.toString();
	}


}
