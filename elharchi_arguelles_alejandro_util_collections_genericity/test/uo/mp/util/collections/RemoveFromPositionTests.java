package uo.mp.util.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import uo.mp.util.collections.impl.ArrayList;
import uo.mp.util.collections.impl.LinkedList;

/*
 * ESCENARIOS
 * 	1 Remove de la posición 0 de una lista con un elemento devuelve el elemento eliminado y la lista queda vacía
 *  2 Remove de la posición 0 de una lista no vacía devuelve el elemento eliminado, quita el elemento de la lista y  mueve el resto de elementos una posición a la izquierda
 *  3 Remove de una posición existente, devuelve el elemento borrado, se quita el elemento de la lista y mueve los elementos de la derecha una posición a la izquierda
 *  4 Remove de la última posición, devuelve el elemento borrado y quita el lemento de la lista
 *  5 Intentar realizar remove en la posición -1 lanza IndexOutOfBoundsException
 *  6 Intentar realizar remove en la posición 0 de una lista vacía, lanza IndexOutOfBoundsException
 *  7 Intentar realizar remove en la posición size() de una lista vacía, lanza IndexOutOfBoundsException
 *  8 Intentar realizar remove en la posición size() de una lista no vacía, lanza IndexOutOfBoundsException
 */
public class RemoveFromPositionTests {
	
	public static Stream<Arguments> createLists() {
		  return Stream.of(
		      Arguments.of(new ArrayList<Object>()),
		      Arguments.of(new LinkedList<Object>())
		  );
		}



	/**1
	 * GIVEN: una lista con un objeto 
	 * WHEN: se borra   
	 * THEN: devuelve el objeto y la lista queda vacia
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void delOnlyItem(List<String> list) {
		String x = "Junit";
		list.add(x);
		String removedItem = (String) list.remove(0);
		assertEquals(x,removedItem);
		assertEquals(0,list.size());
}


	/**2
	 * GIVEN: se da la posicion 0
	 * WHEN:  se llama al metodo con una lista no vacia
	 * THEN:  se borra esa posicion y se mueven el resto de elementos a la izquierda
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void delFirst(List<String> list) {
		list.add("Hola");
		list.add("Adios");
		
		int pos = 0;
		int expectedSize = list.size()-1;
		Object deletedObject = "Hola";
		
		assertEquals(expectedSize, list.size());
		assertEquals(deletedObject, list.remove(pos));
		assertEquals("Adios", list.get(pos));
	}
	
	/**3
     * GIVEN: Una lista con elementos
     * WHEN: Se elimina un elemento de una posición existente
     * THEN: Se devuelve el elemento eliminado, se elimina de la lista y los elementos a la derecha se desplazan a la izquierda
     */
	@ParameterizedTest@MethodSource("createLists")
	public void delLast(List<String> list) {
		 list.add("A");
	        list.add("B");
	        list.add("C");

	        Object removed = list.remove(1); 
	        
	        assertEquals("B", removed); 
	        assertFalse(list.contains("B")); 
	        assertEquals("C", list.get(1)); 
	}

	
	/**4
	 * GIVEN: a list
	 * WHEN: remove the last object
	 * THEN: return that object and borrow that element from the list
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void delMiddle(List<String> list) {
		list.add(0,"with");
		list.add(1,"JUNIT");
		list.add(2,"testing");
		
		Object object = list.remove(2);
		
		assertEquals(2, list.size());
		assertEquals("testing", object);
	}
	
	
	/**5
	 * GIVEN: 
	 * WHEN:    
	 * THEN: 
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void emptyList(List<?> list) {
		fail();
	}
	

	/**6
	 * GIVEN: Lista vacia
	 * WHEN:    remove de 0
	 * THEN: IndexOutOfBoundsException
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void upperBound(List<?> list) {
		List<?> arrayList = new ArrayList<Object>();
		List<?> linkedList = new LinkedList<Object>();
		
		
		assertThrows(IndexOutOfBoundsException.class,()->arrayList.remove(0));	
		assertThrows(IndexOutOfBoundsException.class,()->linkedList.remove(0));	
	}
	
	/** 7 (pablo f.)
	 * GIVEN: Una lista 
	 * WHEN: Se intenta eliminar un elemento de la posicion -1
	 * THEN: Salta OutOfBoundexException
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void lowerBound(List<?> list) {
		ArrayList<?> newList = new ArrayList<Object>();
		
		assertThrows(IndexOutOfBoundsException.class,() -> list.remove(-1));
		assertTrue(list.size() == newList.size());
		

		
	}
		

}
