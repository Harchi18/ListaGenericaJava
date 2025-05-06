package uo.mp.util.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import uo.mp.util.collections.impl.ArrayList;
import uo.mp.util.collections.impl.LinkedList;

/*
 * ESCENARIOS
 * 	1 Una lista vacía no contiene un elemento cualquiera
 * 	2 Una lista con varios elementos no contiene al elemento cualquiera 
 * 	3 Una lista con un elemento contiene al elemento
 * 	4 Una lista con varios elemento contiene al elemento
 * 	5 Una lista vacía no contienen null
 * 	6 Una lista con elementos no contiene null
 */
public class ContainsTest{
	public static Stream<Arguments> createLists() {
		  return Stream.of(
		      Arguments.of(new ArrayList<Object>()),
		      Arguments.of(new LinkedList<Object>())
		  );
		}


	/**1
	 * GIVEN: an empty list
	 * WHEN:  search if the empty list contains an element not in the list
	 * THEN:  false
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void emptyList(List<?> list) {
		assertFalse(list.contains("testing"));
	}

	/**2
	 * GIVEN: A list with several elements
	 * WHEN:  item not contained
	 * THEN: false
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void severalElementsNotInList(List<String> list) {
		list.add(0, "testing");
		list.add(1, "with");
		list.add(2, "JUnit");
		list.add(3, "framework");
		
		assertFalse(list.contains("Hellow"));
	}
	
	/**3
	 * GIVEN: 
	 * WHEN:    
	 * THEN: 
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void onlyOneElementInList(List<String> list) {
		list.add(0, "testing");
	
		assertTrue(list.contains("testing"));
	}
	
	
	/**4
	 * Lista con varios elementos que contiene el elemento
	 * GIVEN: Lista con elementos
	 * WHEN: Contiene el elemento
	 * THEN: 
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void severalElementsIsInList(List<?> list) {
		ArrayList<String> arrayListExpected= new ArrayList<String>();
		
		// OBJETOS TIPO STRING PARA METER EN LA LISTA
		arrayListExpected.add(0, "string1");
		arrayListExpected.add(1, "string2");
		arrayListExpected.add(2, "string3");
		
		assertEquals(true, arrayListExpected.contains("string2"));
	}

	/**5
	 * GIVEN: An empty list
	 * WHEN:  list.contains(null)  
	 * THEN: return fals
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void emptyListDoesNotContainNull(List<?> list) {

		assertEquals(0,list.size());
		assertEquals(false,list.contains(null));
	}
	
	/**6
	 * GIVEN: a non empty list and a  null element
	 * WHEN: 
	 * THEN: the list dont contains null
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void nullNotInList(List<String> list) {
		list.add(0, "testing");
		list.add(1, "with");
		list.add(2, "JUnit");
		list.add(3, "framework");
		
		assertFalse(list.contains(null));
	}
}
