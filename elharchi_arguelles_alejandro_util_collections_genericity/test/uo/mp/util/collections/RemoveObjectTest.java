package uo.mp.util.collections;

import static org.junit.jupiter.api.Assertions.assertFalse; 
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import uo.mp.util.collections.impl.ArrayList;
import uo.mp.util.collections.impl.LinkedList;

/*
 * SCENARIOS
 * 	1 Eliminar un object en una lista vacía devuelve false y deja la lista como estaba
 * 	2 Eliminar un object existente en una lista con un elemento, devuelve true y la lista queda vacía
 *  3 Eliminar un object existente en una lista con varios elementos, devuelve true y el elemento es borrado
 *  4 Eliminar un object no existente en una lista con elementos, devuelve false y la lista no cambia
 *  5 Intentar realizar remove con null lanza IllegalArgumentException
 *  
 */	
public class RemoveObjectTest {

	public static Stream<Arguments> createLists() {
		return Stream.of(Arguments.of(new ArrayList<Object>()), Arguments.of(new LinkedList<Object>()));
	}

	/**
	 * GIVEN: an empty list 
	 * WHEN:  remove an object not in the list
	 * THEN:  false
	 */
	@ParameterizedTest
	@MethodSource("createLists")
	public void emptyList(List<?> list) {
		List<?> listToCompare = list;
		list.remove("test");
		assertFalse(list.remove("test"));
		assertEquals(list, listToCompare);
	}

	/** 1
	 * GIVEN: A list with a single element
	 * WHEN: remove(theElement)
	 * THEN: removes the element from the list and the list is now empty
	 */
	@ParameterizedTest
	@MethodSource("createLists")
	public void singleItemList(List<String> list) {
		String theElement = "Single element";
		list.add(theElement);
		
		assertTrue(list.remove(theElement));
		assertEquals(0, list.size());
	}

	/**
	 * GIVEN: WHEN: THEN:
	 */
	@ParameterizedTest
	@MethodSource("createLists")
	public void notInList(List<String> list) {
		list.add(0, "testing");
		list.add(1, "with");
		list.add(2, "JUnit");
		list.add(3, "framework");
		
		String s = list.toString();
		int lsize = list.size();
	
		assertFalse(list.remove("Hellow"));
		assertTrue(list.size() == lsize);
		assertTrue(list.toString().equals(s));	
	}

	/**
	 * GIVEN: WHEN: THEN:
	 */
	@ParameterizedTest
	@MethodSource("createLists")
	public void inListFirst(List<?> list) {
		fail();

	}

	/**
	 * GIVEN: WHEN: THEN:
	 */
	@ParameterizedTest
	@MethodSource("createLists")
	public void inListLast(List<?> list) {
		IllegalArgumentException e = 
				assertThrows(IllegalArgumentException.class, () ->
				list.remove(null));
				assertEquals("No puede ser null a la hora de utilizar el remove",e.getMessage());
				

	}

	/**
	 * GIVEN: WHEN: THEN:
	 */
	@ParameterizedTest
	@MethodSource("createLists")
	public void inListOther(List<?> list) {
		fail();

	}

	/**
	 * GIVEN: WHEN: THEN:
	 */
	@ParameterizedTest
	@MethodSource("createLists")
	public void inListRepeated(List<?> list) {
		fail();
	}

	/**
	 * GIVEN: WHEN: THEN:
	 */
	@ParameterizedTest
	@MethodSource("createLists")
	public void nullObject(List<?> list) {
		fail();
	}
}
