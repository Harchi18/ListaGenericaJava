package uo.mp.util.collections;

import static org.junit.Assert.assertTrue; 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import uo.mp.util.collections.impl.ArrayList;
import uo.mp.util.collections.impl.LinkedList;

/*
 * SCENARIOS
 * 	1 Dos listas vacías del mismo tipo  son iguales
 *  2 Dos listas vacías de ambos tipos son iguales
 *  3 Dos listas del mismo tipo  con los mismos elementos en la misma posición son iguales *  4 Dos listas de tipo ArrayList con los mismos elementos en la misma posición son iguales
 *  4 Dos listas de ambos tipos con los mismsos elementos en la misa posición son iguales
 *  5 Dos listas del mismo tipo con los mismos elementos en diferente posición no son iguales
 *  6 Dos listas de ambos tipos con los mismos elementos en diferentes posiciones no son iguales
 *  7 Dos listas del mismo tipo con diferentes elementos no son iguales
 *  8 Dos listas de ambos tipos con diferentes elementos no son iguales
 *  
 *  
 * 	
 */
public class EqualsTests {
	

	private static Stream<Arguments> create2ListsSameType() {
	    return Stream.of(
	    		Arguments.of(new ArrayList<Object>(), new ArrayList<Object>()),
	    		Arguments.of(new LinkedList<Object>(), new LinkedList<Object>())
	    );
	}
	
	private static Stream<Arguments> create2ListsDifferentType() {
	    return Stream.of(
	    		Arguments.of(new ArrayList<Object>(), new LinkedList<Object>()),
	    		Arguments.of(new LinkedList<Object>(), new ArrayList<Object>())
	    );
	}
	
	@ParameterizedTest@MethodSource("create2ListsSameType")
	
	/**1 
	 * GIVEN: dos listas vacias
	 * WHEN:  se llama al metodo
	 * THEN:  devuelve que ambas listas vacias son iguales
	 */
	public void sameTypeEmtpylist(List<?> list1, List<?> list2) {

		List<?> list = list1;
		List<?> other = list2;
		
		assertTrue(list.equals(other));

	}
	
	
	
	/**2
	 * GIVEN: Dos listas vacías de ambos de Diferentes tipos
	 * WHEN:  Llamar al
	 * THEN: 
	 */
	@ParameterizedTest
	@MethodSource("create2ListsDifferentType")
	public void bothTypeEmtpyList(List<?> list1, List<?> list2) {
		list1 = new ArrayList<Object>();
		list2 = new LinkedList<Object>();
		assertTrue(list1.equals(list2));
	}

	
	/**3
	 * GIVEN: dos listas del mismo tipo con los mismos elementos en la misma posicion
	 * WHEN: llamas a equals
	 * THEN: son iguales
	 */
	@ParameterizedTest
	@MethodSource("create2ListsSameType")
	public void sameTypeSameElements(List<String> list1, List<String> list2) {
		list1.add("Hola");
		list2.add("Hola");
		
		assertTrue(list1.equals(list2));
	
	}
	
	/**4
	 * GIVEN:  Dos listas, una de cada tipo, internamente iguales
	 * WHEN: Se comprueba que sean iguales
	 * THEN: Se verifica que ambas listas son iguales
	 */
	@ParameterizedTest
	@MethodSource("create2ListsDifferentType")
	public void differentTypeSameElementsSamePosition(List<String> list1, List<String> list2) {
		list1 = new ArrayList<String>();
		list2 = new LinkedList<String>();
		
		list1.add(2,"String");
		list2.add(2,"String");
		
		assertEquals(list1, list2);
		
	}
	
	
	
	/**5
	 * GIVEN: dos listas del mismo tipo con los mismos elementos en diferente posición
	 * WHEN:  equals
	 * THEN: devuelve falso
	 */
	@ParameterizedTest
	@MethodSource("create2ListsSameType")
	public void sameTypeSameItemsDifferentOrder(List<?> list1, List<?> list2) {
		
		String obj1 = "asdfmovies1";
		String obj2 = "asdfmovies2";
		List<String> arrayList1 = new ArrayList<String>();
		List<String> linkedList1 = new LinkedList<String>();
		List<String> arrayList2 = new ArrayList<String>();
		List<String> linkedList2 = new LinkedList<String>();
		
		arrayList1.add(obj1);
		arrayList1.add(obj2);
		arrayList2.add(obj2);
		arrayList2.add(obj1);
		linkedList1.add(obj1);
		linkedList1.add(obj2);
		linkedList2.add(obj2);
		linkedList2.add(obj1);
		
		assertFalse(arrayList1.equals(arrayList2));	
		assertFalse(linkedList1.equals(linkedList2));
		
	}

	
	
	/**6
	 * GIVEN: Two lists with the same elemnts in different order
	 * WHEN: equals is called
	 * THEN: False is returned because both lists are considered different
	 */
	@ParameterizedTest
	@MethodSource("create2ListsDifferentType")
	public void differentTypeSameElementsDifferentPositions(List<String> list1, List<String> list2) {
		list1.add(0,"PC");
		list1.add(1,"mouse");
		list1.add(2,"screen");
		
		list2.add(9, "screen");
		list1.add(1,"PC");
		list1.add(2,"mouse");
		
		assertFalse(list1.equals(list2));
	}


	
	/**7
	 * GIVEN: Dos listas del mismo tipo, pero con diferentes elementos
	 * WHEN:  equals()
	 * THEN: devuelve false
	 */
	@ParameterizedTest
	@MethodSource("create2ListsSameType")
	public void sameTypeDifferentElements(List<?> list1, List<?> list2) {
		boolean expected = false;
		boolean result = list1.equals(list2);
		
		assertEquals(expected, result);
	}

	
	/**8  
	 * GIVEN: Two different kinds of lists with different elements
	 * WHEN: equals
	 * THEN: return false
	 */
	@ParameterizedTest
	@MethodSource("create2ListsDifferentType")
	public void DifferentTypeDifferentItems(List<Object> list1, List<Object> list2) {
		list1 = new ArrayList<Object>();
		list2 = new LinkedList<Object>();

		list1.add(new Object());
		list1.add(new Object());
		list1.add(new Object());
		
		list2.add(new Object());
		list2.add(new Object());
		list2.add(new Object());
		
		assertFalse(list1.equals(list2));
		assertFalse(list2.equals(list1));
	}

}
