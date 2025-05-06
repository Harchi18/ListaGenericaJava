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

/*
 * ESCENARIOS
 * 	1 Set en la posición 0 de una lista con varios elementos, cambia el valor de la posición y devuelve el valor anterior de esa posición
 * 	2 Set en la posición n de una lista con varios elementos cambia el valor de la posición n y devuelve el valor anterior de esa posición 
 * 	3 Intentar realizar set en la posición -1 de una lista vacía, lanza IndexOutOfBoundsException
 * 	4 Intentar realizar set en la posición size() de una lista vacía, lanza IndexOutOfBoundsException
 * 	5 Intentar realizar set en la posición -1 de una lista con elementos, lanza IndexOutOfBoundsException
 * 	6 Intentar realizar set en la posición size() de una lista con elementos, lanza  IndexOutOfBoundsException
 */
public class SetTest {
	
	public static Stream<Arguments> createLists() {
		  return Stream.of(
		      Arguments.of(new ArrayList<Object>()),
		      Arguments.of(new LinkedList<Object>())
		  );
		}

	/**1
	 * GIVEN: a list with some elements
	 * WHEN: set an object in pos 0
	 * THEN: move the object one pos to the right
	 */
	@ParameterizedTest
	@MethodSource("createLists")
	public void setFirst(List<Object> list) {
		Object firstPos = new Object();
		Object futureFirstPos = new Object();
		list.add(0, firstPos);
		list.add(1, "with");
		list.add(2, "framework");
		int lsize = list.size();
		assertTrue(list.set(0, futureFirstPos).equals(firstPos));
		assertTrue(list.size() == lsize);
		assertTrue(list.get(0).equals(futureFirstPos));
		 
	}
	
	
	
	/**2
	 * GIVEN: a list with several objects
	 * WHEN:  set an object in a n position
	 * THEN:  returns the previous value of the position n and change it
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void setMiddle(List<String> list) {
		list.add(0, "testing");
		list.add(1, "with");
		list.add(2, "JUnit");
		list.add(3, "framework");
		
		assertEquals("with", list.set(1, "hello"));
		assertEquals("hello", list.get(1));

	}
	
	/**3  
	 * GIVEN: A list
	 * WHEN:  Set object in index -1
	 * THEN:  Throws IndexOutOfBoundsException
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void lowwerEmpty(List<String> list) {
		assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1,"object"));

	}
	
	/**4
	 * GIVEN: 
	 * WHEN:    
	 * THEN: 
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void upperEmpty(List<String> list) {
		String o = new String();
		IllegalArgumentException e = 
				assertThrows(IllegalArgumentException.class, () ->
				list.set(list.size(), o),"Exception expected");
		assertEquals("IndexOutOfBoundsException", e.getMessage());
		assertEquals(o,list.get(list.size()));
		
	}
	
	/**5
	 * GIVEN: -1
	 * WHEN: 
	 * THEN: indexOutOfBound
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void lowerNotEmpty(List<?> list) {
		IllegalArgumentException e = assertThrows(
				IllegalArgumentException.class,
				() -> new ArrayList<Object>(-1), "Exception expeceted");
		assertEquals(IndexOutOfBoundsException.class, e.getMessage());
				
		

	}
	
	/**6
	 * GIVEN: a list with some object
	 * WHEN:  list.set(list.size(),o)
	 * THEN:  return IndexOutOfBoundsException
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void upperNotEmpty(List<Object> list) {
		Object o = 5;
		list.add(o);
		list.add(o);
		list.add(o);
		list.add(o);
		
		assertThrows(IndexOutOfBoundsException.class, () -> {
           
			list.set(list.size(), o);
        });

	}



}
