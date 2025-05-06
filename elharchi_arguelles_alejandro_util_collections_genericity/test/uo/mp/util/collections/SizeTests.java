package uo.mp.util.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import uo.mp.util.collections.impl.ArrayList;
import uo.mp.util.collections.impl.LinkedList;

/*
 * ESCENARIOS
 *  1 Una lista vacía devueve 0
 * 	2 Una lista no vacía, devuelve el número de elementos correcto
 */
public class SizeTests {
	public static Stream<Arguments> createLists() {
		  return Stream.of(
		      Arguments.of(new ArrayList<Object>()),
		      Arguments.of(new LinkedList<Object>())
		  );
		}

	/**
	 * GIVEN: 
	 * WHEN:    
	 * THEN: 
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void emptyList(List<?> list) {
		ArrayList<?> arrayList = new ArrayList<Object>();
		assertEquals(0, arrayList.size());
	}

	/**
	 * GIVEN: a list with elements
	 * WHEN:  list.size()
	 * THEN:  return the number of elements
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void nonEmptyList(List<Object> list) {
		Object o = 5;
		list.add(o);
		list.add(o);
		list.add(o);
		list.add(o);
		
		assertEquals(4,list.size());
	}


}
