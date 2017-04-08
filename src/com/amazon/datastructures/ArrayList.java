package com.amazon.datastructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class ArrayList {

	private Object[] items;
	private int size = 0;

	private static final int DEFAULT_CAPACITY = 10;

	private static final Object[] EMPTY_ITEMS = {};

	private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;

	public ArrayList() {
		items = new Object[DEFAULT_CAPACITY];
	}

	public ArrayList(int initialCapacity) {
		if (initialCapacity == 0) {
			items = EMPTY_ITEMS;
		} else if (initialCapacity > 0) {
			items = new Object[initialCapacity];
		} else
			throw new IllegalArgumentException();
	}
	
	public int size() { return size; }
	
	public boolean isEmpty() { return size == 0; }

	private void ensureCapacity(int minCapacity) {
		int oldCapacity = items.length;
		if (oldCapacity >= minCapacity)
			return;
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		// at least minCapacity
		newCapacity = Math.max(newCapacity, minCapacity);
		// don't exceed VM limitation
		newCapacity = Math.min(newCapacity, MAX_CAPACITY);
		// grow array
		items = Arrays.copyOf(items, newCapacity);
	}

	public void add(Object item) {
		checkItem(item);
		ensureCapacity(size + 1);
		items[size++] = item;
	}

	public void add(int index, Object item) {
		checkRangeForAdd(index);
		checkItem(item);
		ensureCapacity(size + 1);
		// before 0 1 2 3
		// *
		// after 0 1 * 3 4
		System.arraycopy(items, index, items, index + 1, size - index);
		items[index] = item;
		++size;
	}

	public Object remove(Object item) {
		checkItem(item);
		int index = indexOf(item);
		return remove(index);
	}

	public Object remove(int index) {
		checkRange(index);
		Object value = items[index];
		// before 0 1 * 3 4
		// after 0 1 2 3
		System.arraycopy(items, index + 1, items, index, size - index - 1);
		--size;
		return value;
	}

	public int indexOf(Object item) {
		for (int i = 0; i < items.length; i++) {
			if (items[i].equals(item))
				return i;
		}
		return -1;
	}

	public Object get(int index) {
		checkRange(index);
		return items[index];
	}

	private void checkRangeForAdd(int index) {
		if (index < 0 || index > size)
			throw new ArrayIndexOutOfBoundsException();
	}

	private void checkRange(int index) {
		if (index < 0 || index >= size)
			throw new ArrayIndexOutOfBoundsException();
	}

	private void checkItem(Object item) {
		if (item == null)
			throw new IllegalArgumentException();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("c=").append(items.length).append(" ");
		sb.append("[ ");
		for(int i=0; i<size; i++)
			sb.append(items[i] + " ");
		for(int i=size; i<items.length; i++)
			sb.append("- ");
		sb.append("]");
		sb.append("\n");
		return sb.toString();
	}

	public static ArrayList importFromFile(String path) {

		try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
			ArrayList list = new ArrayList();
			String line = "";
			while ((line = reader.readLine()) != null) {
				list.add(Integer.valueOf(line));
			}
			return list;
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
}
