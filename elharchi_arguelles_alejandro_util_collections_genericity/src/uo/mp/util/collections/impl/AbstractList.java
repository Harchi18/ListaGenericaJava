package uo.mp.util.collections.impl;

import java.util.Objects; 

import uo.mp.util.check.ArgumentChecks;
import uo.mp.util.collections.List;

public abstract class AbstractList<T> implements List<T> {
	
	protected int numberOfElements = 0;
	
	@Override
	public int size() {
		return numberOfElements;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	@Override
	public boolean add(T element) {
		ArgumentChecks.isNotNull(element);
		add(size(), element);
		return true;
	}

	@Override
	public boolean remove(Object o) {
		int index = indexOf(o);
		if(index == -1) {
			return false;
		}
		remove(index);
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numberOfElements);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractList<?> other = (AbstractList<?>) obj;
		return numberOfElements == other.numberOfElements;
	}
	
	
	

}
