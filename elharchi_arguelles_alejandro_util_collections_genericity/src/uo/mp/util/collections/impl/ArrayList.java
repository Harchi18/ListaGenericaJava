package uo.mp.util.collections.impl;

import java.util.Iterator; 
import java.util.NoSuchElementException;

import uo.mp.util.check.ArgumentChecks;
import uo.mp.util.collections.List;

public class ArrayList<T> extends AbstractList<T> implements List<T>{
	
	private static final int DEFAULT_CAPACITY = 10;
	private T[] elements;
	
	@SuppressWarnings("unchecked")
	public ArrayList() {
		this.elements = (T[]) new Object[DEFAULT_CAPACITY];
		this.numberOfElements = 0;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList(int initialCapacity) {
		this.elements = (T[]) new Object[initialCapacity];
		this.numberOfElements = 0;
	}
	
	@Override
	public void clear() {
		for(int i = 0; i < elements.length; i++) {
			elements[i] = null;
		}
		numberOfElements = 0;
	}

	@Override
	public T get(int index) {
		ArgumentChecks.isTrue(index >= 0 && index < size(), 
				String.format("Index out of bounds (0,%d)", size() - 1));
		return elements[index];
	}

	@Override
	public T set(int index, T element) {
		ArgumentChecks.isTrue(index >= 0 && index < size(), 
				String.format("Index out of bounds (0,%d)", size() - 1));
		ArgumentChecks.isNotNull(element, "The element can not be null");
		T old = elements[index];
		elements[index] = element;
		return old;
	}

	@Override
	public void add(int index, T element) {
		ArgumentChecks.isTrue(index >= 0 && index < size(), 
				String.format("Index out of bounds (0,%d)", size() - 1));
		ArgumentChecks.isNotNull(element, "The element can not be null");
		if(numberOfElements == elements.length) {
			@SuppressWarnings("unchecked")
			T[] newElementsList = (T[]) new Object[elements.length * 2 ];
			for (int i = 0; i < elements.length; i++) {
				newElementsList[i] = elements[i];
            	}
            elements = newElementsList;
        	}
        for (int i = numberOfElements; i > index; i--) {
            elements[i] = elements[i - 1];
        	}
        elements[index] = element;
        numberOfElements++;
	}

	@Override
	public T remove(int index) {
		ArgumentChecks.isTrue(index >= 0 && index < size(), 
				String.format("Index out of bounds (0,%d)", size() - 1));
		T removedElement = elements[index];
		for (int i = index; i < numberOfElements - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--numberOfElements] = null;
        return removedElement;
	}

	@Override
	public int indexOf(Object element) {
		if(element == null) {
			return -1;
		}
		
		if(size() == 0) {
			return -1;
		}
		
		for(int i = 0; i < size(); i++) {
			if(elements[i].equals(element)) {
				return i;
			}
		}
		return -1;
	}

	@Override
    @SuppressWarnings("unchecked")
	public boolean add(Object element) {
	    ArgumentChecks.isNotNull(element, "El elemento no puede ser nulo");
	    if (numberOfElements == elements.length) {
	        T[] newElementsList = (T[]) new Object[elements.length * 2];
	        for (int i = 0; i < elements.length; i++) {
	            newElementsList[i] = elements[i];
	        }
	        elements = newElementsList;
	    }
	    elements[numberOfElements] = (T) element;
	    numberOfElements++;
	    return true;}

	@Override
	public boolean remove(Object o) {
		int index = indexOf(o);
		if(index == -1) {
			return false;
		}
		for(int i = index; i < numberOfElements; i++) {
			elements[i] = elements[i + 1];
		}
		elements[--numberOfElements] = null;
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Iterator<T> iterator() {
		return (Iterator<T>) new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator<Object>{
		
		private int index = 0;
		
		@Override
		public boolean hasNext() {
			return index < size();
		}

		@Override
		public T next() {
			if(hasNext()) {
				
				return elements[index++];
			}else {
				throw new NoSuchElementException("No hay siguiente elemento");
			}
		}
	
	}
}
