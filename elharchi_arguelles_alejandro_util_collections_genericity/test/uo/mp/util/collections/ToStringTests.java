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
 *  2 Una lista con un elmento devuelve [elemento]
 */
public class ToStringTests {
	
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
	public void emptylist(List<?> list) {
		fail();
	}

	/**
	 * GIVEN: una lista con un elemento
	 * WHEN:  llamas el metodo con esa lista
	 * THEN:  devuelve entre corchetes el elemento
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void one(List<String> list) {
		list.add("Hola");
		
		String expected = "[Hola]";
		
		assertEquals(expected, list.toString());
	}
	
	/**
     * GIVEN: Una lista con 3 elementos
     * WHEN: Se llama al método toString()
     * THEN: Devuelve una representación en formato [elemento1, elemento2, elemento3]
     */
	@ParameterizedTest@MethodSource("createLists")
	public void some(List<String> list) {
		list.add("Elemento1");
        list.add("Elemento2");
        list.add("Elemento3");

        assertEquals("[Elemento1, Elemento2, Elemento3]", list.toString());
	}

}
