package uo.mp.util.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import uo.mp.util.collections.impl.ArrayList;
import uo.mp.util.collections.impl.LinkedList;

/*
 * ESCENARIOS
 * 	1 El index de un elemento que no existe es -1
 * 	2 El index de null es -1
 *  3 El index de un elemento localizado en la primera posición de la lista es 0
 *  4 El index de un elemento localizado en la última posición de la lista es size() -1
 *  5 El index de un elemento colocado en medio de la lista es la posición que tiene
 *  6 En una lista con elementos repetidos, el indexOf de un elemento devuelve la posición de la primera aparición * 
 */
public class IndexOfTests {
	
	public static Stream<Arguments> createLists() {
		  return Stream.of(
		      Arguments.of(new ArrayList<Object>()),
		      Arguments.of(new LinkedList<Object>())
		  );
		}

	/**1
	 * GIVEN: listas con elementos
	 * WHEN: IndexOf elemento que no existe
	 * THEN: devuelve -1
	 */
	@ParameterizedTest
	@MethodSource("createLists")
	public void nonExistElem(List<?> list) {

		String obj1 = "asdfmovies1";
		String obj2 = "asdfmovies2";
		List<String> arrayList = new ArrayList<String>();
		List<String> linkedList = new LinkedList<String>();
		
		arrayList.add(obj1);
		linkedList.add(obj1);
		
		assertEquals(-1,arrayList.indexOf(obj2));	
		assertEquals(-1,linkedList.indexOf(obj2));
		
	}
	
	/**2
	 * GIVEN: A list
	 * WHEN: indexOf is called
	 * THEN: The index of "null" has to be -1
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void nullElem(List<?> list) {
		assertEquals(-1, list.indexOf(null));
		
	}
	
		
	/**3
	 * GIVEN: lista con un objeto en la primera posicion
	 * WHEN: indexOf()
	 * THEN: devuelve cero (el indice esperado)
	 */
	@ParameterizedTest
	@MethodSource("createLists")
	public void firstInList(List<Object> list) {
		Object obj1 = new Object();
		list.add(0, obj1);
		
		int expected = 0;
		int result = list.indexOf(obj1);
		
		assertEquals(expected, result);
	}

	/**4
	 * GIVEN: A list with multiple elements
	 * WHEN: indexOf(lasElement)
	 * THEN: returns the element's position (the list's size - 1)
	 */
	@ParameterizedTest
	@MethodSource("createLists")
	public void lastInList(List<String> list) {
		list.add("Elemento 1");
		list.add("Elemento 2");
		list.add("Elemento 3");
		String elem4  = "Elemento 4";
		list.add(elem4);
		
		assertEquals(list.size()-1, list.indexOf(elem4));
	}
	
	/**5
	 * GIVEN: 
	 * WHEN:    
	 * THEN: 
	 */
	@ParameterizedTest
	@MethodSource("createLists")
	public void midleInList(List<Object> list) {
		Object obj1 = new Object();
		list.add(0, obj1);
		list.add(1, "with");
		list.add(2, "framework");
		
		assertTrue(list.indexOf(obj1) == 0);
		
	}
	
	/**6
	 * GIVEN: 
	 * WHEN:    
	 * THEN: 
	 */
	@ParameterizedTest
	@MethodSource("createLists")
	public void repeatedInList(List<String> list) {
		list.add(0, "testing");
		list.add(1, "with");
		list.add(2, "JUnit");
		list.add(3, "framework");
		list.add(4, "testing");
		list.add(5, "framework");
		
		assertEquals(0, list.indexOf("testing"));
		assertEquals(3, list.indexOf("framework"));
	}

}
