package uo.mp.util.collections.impl;

import java.util.Iterator; 
import java.util.NoSuchElementException;

import uo.mp.util.check.ArgumentChecks;

public class LinkedList<T> extends AbstractList<T>{
    
	private Node head;
	
	
	private void addFirst(T element) {
		head = new Node(element, head);
		numberOfElements++;
	}

	
	@Override
	public void clear() { 
		// TODO REDEFINIR EN SUBCLASES

	}

	@Override
	public T get(int index) {
		return  getNode(index).element;
	}

	private Node getNode(int index) {
		//CheckIndex
		int position = 0;
		Node actual = head;
		while(position < index) {
			actual = actual.next;
			position++;
		}
		return actual;
	}

	@Override
	public T set(int index, T element) {
		Node actual = getNode(index);
		T old = actual.element;
		actual.element = element;
		return old;	
	}

	@Override
	public void add(int index, T element) {
		ArgumentChecks.isTrue(index >= 0 && index < size(), 
				String.format("Index out of bounds (0,%d)", size() - 1));
		ArgumentChecks.isNotNull(element, "The element can not be null");
		
		if(isEmpty()) {
			addFirst(element);
		}else {
			Node previusNode = getNode(index - 1);
			previusNode.next = new Node(element, previusNode.next);
			numberOfElements++;
		}
	}

	@Override
	public T remove(int index) {
		ArgumentChecks.isTrue(index >= 0 && index < size(), 
				String.format("Index out of bounds (0,%d)", size() - 1));
		
		T old;
		if(index == 0) {
			old = head.element;
			head = head.next;
		}else {
			Node previousNode = getNode(index - 1);
			old = previousNode.next.element;
			previousNode.next = previousNode.next.next;
		}
		numberOfElements--;
		return old;
	}

	@Override
	public int indexOf(Object element) {
		if(element == null) {
			return -1;
		}
		if(size() == 0) {
			return -1;
		}
		Node each = head;
		for(int i = 0; i < size(); i++) {
			if(each.element.equals(element)) {
				return i;
			}
			each = each.next;
		}
		return -1;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Iterator<T> iterator() {
		return (Iterator<T>) new LinkedListIterator();
	}
	
	
	private class LinkedListIterator implements Iterator<Object>{
		
		private Node next;
		private Node actual;
		private int nextIndex;

		
		public LinkedListIterator() {
			nextIndex = 0;
			next = head;
		}

		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public T next() {
			if(hasNext()) {
				actual = next;
				next = next.next;
				nextIndex++;
				return actual.element;
			}else {
				throw new NoSuchElementException("No hay siguiente elemento");
			}
		}
	}
	
	
	private class Node {
		T element;
		Node next;
		
		Node(T element, Node next){
			this.element = element;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [element=" + element + ", next=" + next + "]";
		}	
	}
}