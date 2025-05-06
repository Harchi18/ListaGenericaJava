package uo.mp.util.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import uo.mp.util.collections.impl.ArrayList;
import uo.mp.util.collections.impl.LinkedList;

/*
 * ESCENARIOS
 * 	1 Una lista recien creada esta vacía
 *  2 Una lista queda vacía después de la operación clear
 *  3 Una lista con un elemento no está vacía
 *  4 Una lista con más de un elemento no esta vacía
 */
public class IsEmptyTest {
	
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
		for(int i= 0; i<list.size();i++) {
       	     assertEquals(null,list.get(i));
		}
	}

	/**
	 * GIVEN: 
	 * WHEN:    
	 * THEN: 
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void clearedList(List<?> list) {
		fail();
	}
	
	/**
	 * GIVEN: 
	 * WHEN:    
	 * THEN: 
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void sizeOneList(List<?> list) {
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add(0, "String1");
		
		assertEquals(false, arrayList.isEmpty());
	}
	
	/**
	 * GIVEN: a list with elements
	 * WHEN:  isEmpty(list)  
	 * THEN:  return false
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void severalItemsList(List<Object> list) {
		Object o = 5;
		list.add(o);
		list.add(o);
		list.add(o);
		list.add(o);
		
		assertEquals(false,list.isEmpty());
	}
}
