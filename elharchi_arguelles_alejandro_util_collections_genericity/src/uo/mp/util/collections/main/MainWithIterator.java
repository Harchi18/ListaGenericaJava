/**
 * 
 */
package uo.mp.util.collections.main;

import java.util.Iterator; 

import uo.mp.util.collections.List;
import uo.mp.util.collections.impl.ArrayList;
import uo.mp.util.collections.impl.LinkedList;

/**
 * @author 
 *
 */
public class MainWithIterator {
	public static void main(String[] args) {
		new MainWithIterator().run();
	}

	/*
	 * Uso de las operaciones del iterador para una LinkedList y una ArrayList
	 *   recorrido con un iterador 
	 *   borrado de elementos con un iterador 
	 *   recorrido con un bucle foreach 
	 * 
	 */
	void run() {
		List<Object> list = new LinkedList<Object>();
		list.add("Uno");
		list.add("dos");
		list.add("tres");
		
		displayIteratingWithFor(list, "LinkedList");
		displayIteratingWithForeach(list, "LinkedList");
		displayIteratingWithIterator(list, "LinkedList");
		
		removeTwoFirst(list);
		displayIteratingWithFor(list, "LinkedList después del borrado");
		
		list = new ArrayList<Object>();
		list.add("cuatro");
		list.add("cinco");
		list.add("seis");
		
		displayIteratingWithFor(list, "ArrayList");
		displayIteratingWithForeach(list, "ArrayList");
		displayIteratingWithIterator(list, "ArrayList");
		
		removeTwoFirst(list);
		displayIteratingWithFor(list, "ArrayList después del borrado");		
		
		
	}

	private void displayIteratingWithFor(List<Object> list, String listType) {
		System.out.println("Recorrido con bucle for.....para " + listType);
		for (int i=0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	private void displayIteratingWithForeach(List<Object> list, String listType) {
		System.out.println("Recorrido con foreach.....para " + listType);
		for (Object element:list) {
			System.out.println(element);
		}		
	}

	private void displayIteratingWithIterator(List<Object> list, String listType) {
		System.out.println("Recorrido con iterador.....con " + listType);
		Iterator<Object> it = list.iterator();
		while (it.hasNext()) {			
			System.out.println( it.next());
		}	
	}
	
	private void removeTwoFirst(List<Object> list) {
		Iterator<Object> it = list.iterator();
		int cont = 0;
		while (it.hasNext() && cont < 2) {		
			it.next();
			it.remove();
			cont++;
		}
	}
}
