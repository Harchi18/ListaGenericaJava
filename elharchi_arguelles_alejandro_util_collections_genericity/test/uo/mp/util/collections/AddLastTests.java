package uo.mp.util.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import uo.mp.util.collections.impl.ArrayList;
import uo.mp.util.collections.impl.LinkedList;

public class AddLastTests {
	
	// SCENARIOS
	//1 Add en una lista vacía añade el elemento y devuelve true
	//2 Add en una lista con varios elementos, añade el elemento en la última posición y devuelve true
	//3 Se puede añadir un elemento repetido
	//4 Intentar añadir un null, se lanza IllegalArgumentException y la lista permanece igual

	public static Stream<Arguments> createLists() {
		  return Stream.of(
		      Arguments.of(new ArrayList<Object>()),
		      Arguments.of(new LinkedList<Object>())
		  );
		}
	
	/**1
	 * GIVEN: elemento a añadir
	 * WHEN: addLastTest()
	 * THEN: true si se pudo añadir o false en caso contrario
	 */
	@ParameterizedTest
	@MethodSource("createLists") 
	public void emptyList(List<Object> list) {
		Object obj1 = new Object();
		list.add(obj1);
		assertEquals(obj1, list.get((list.size() - 1)));
	}
	
	
	/**2
	 * GIVEN: A list with multiple elements
	 * WHEN: add()
	 * THEN: adds an element in the last position and returns true
	 */
	@ParameterizedTest@MethodSource("createLists") 
	public void severalElements(List<Object> list) {
		list.add(new Object());
		list.add(new Object());
		list.add(new Object());
		
		Object object = new Object();
		list.add(3, object);
		
		assertEquals(4, list.size());
		assertEquals(object, list.get(4));
	}

	/** 3
	 * GIVEN: a object that is already in the list
	 * WHEN: add the element repeated at the end
	 * THEN: add it correctly
	 */
	@ParameterizedTest@MethodSource("createLists")  
	public void notNullRepeatedElement(List<Object> list) {		
		Object obj1 = new Object();
		list.add(0, obj1);
		list.add(1, "with");
		list.add(2, "framework");
		int lsize = list.size();
		list.add(3, obj1);
		 assertTrue(list.size() == lsize + 1);
		 assertTrue(list.get(0).equals(list.get(3)));
	}

	
	/**4  
	 * GIVEN: a non empty list and a null element
	 * WHEN: trying to add the element at the end 
	 * THEN: throws an exception, the list remains unchanged
	 */
	@ParameterizedTest
	@MethodSource("createLists")  
	public void nullElement2(List<String> list) {
		list.add(0, "testing");
		list.add(1, "with");
		list.add(2, "JUnit");
		list.add(3, "framework");
		String s = list.toString();
		int lsize = list.size();
		IllegalArgumentException e = 
				assertThrows(IllegalArgumentException.class, () -> 
				list.add(null),"Exception expected");
		 assertEquals("No se admite null en una lista", e.getMessage());
		 assertTrue(list.size() == lsize);
		 assertTrue(list.toString().equals(s));		
	}


}
