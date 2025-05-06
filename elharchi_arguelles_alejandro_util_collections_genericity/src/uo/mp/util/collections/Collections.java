package uo.mp.util.collections;

import java.util.Comparator;

import uo.mp.util.collections.impl.LinkedList;


public class Collections {

	/**
	 * Ordena una lista recibida como par�metro
	 * El criterio de ordenaci�n est� implementado en el m�todo compareTo que deben
	 * tener los objetos de la lista 
	 * 
	 * @param list la lista a ordenar
	 */
	public static <T> void sort(List<T> list) {
		List<T> sorted = new LinkedList<>();
		for (T element:list) {
			int index = getPositionInSorted(element, sorted);
			sorted.add(index, element);
		}
		makeCopy(sorted, list); // El paso de par�metros en Java es por copia luego es necesario 
		                        // que la lista recibida quede ordenada
	}


	/**
	 * Busca la posici�n en la que se debe incluir el elemento en la lista ordenada para que esta siga 
	 * estando ordenada.
	 * El criterio de ordenaci�n viene definido en el m�todo compareTo de los elementos a ordenar
	 * 
	 * @param element el elemnto a a�adir en la lista ordenada
	 * @param sorted la lista ordenada
	 * @return el �ndice a colocar el elemento
	 */

	@SuppressWarnings("unchecked")
	private static <T> int getPositionInSorted(T element, List<T> sorted) {
		
		for(int i = 0; i < sorted.size(); i++) {
			T o1 = sorted.get(i);
			if(((Comparable <T>) o1).compareTo(element) > 0) {
				return i;
			}
		}
		return sorted.size();
	}
	
	/**
	 * Copia en la lista destino el contenido de la lista origen
	 * @param origin la lista ordenada
	 * @param destination la lista inicial 
	 */
	private static <T> void makeCopy(List<T> origin, List<T> destination) {
		destination.clear();
		for (T element: origin) {
			destination.add(element);
		}		
	}

/************************************************************************/
	
	
	public static <T> void sort(List<T> list, Comparator<T> comparator) {
		List<T> sorted = new LinkedList<>();
		for (T element:list) {
			int index = getPositionInSorted(element, sorted, comparator);
			sorted.add(index, element);
		}
		makeCopy(sorted, list);
	}

	private static <T> int getPositionInSorted(T element, List<T> sorted, Comparator<T> comparator) {
		
		for(int i = 0; i < sorted.size(); i++) {
			T o1 = sorted.get(i);
			if(comparator.compare(o1,element) > 0) {
				return i;
			}
		}
		return sorted.size();
	}

	
	
	
}
