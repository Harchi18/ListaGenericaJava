package uo.mp.util.collections;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import uo.mp.util.collections.impl.ArrayList;
import uo.mp.util.collections.impl.LinkedList;


//Escenarios
//1 Add en la posición 0 de una lista vacía añade el elemento
//2 Add en la posición 0 de una lista con varios elementos añade el elemento y mueve el resto una posición a la derecha
//3 Add en una posición intermedia de una lista añade el elemento y mueve el resto una posición a la derecha
//4 Add en la última posición de una lista con elementos añade el elemento y el último pasa a la derecha
//5 Add en la posición después del último de una lista con elementos añade el elemento al final
//6 Un elemento repetido PUEDE ser añadido y se almacena correctamente
//7 Intentar añadir  en una posición negativa, se lanza IndexOutOfBoundsException y la lista permanece igual
//8 Intentar añadir en una posicón > size(), se lanza IndexOutOfBoundsException y la lista permanece igual
//9 Intentar añadir un null, se lanza IllegalArgumentException y la lista permanece igual

public class AddInPositionTests {
	
	public static Stream<Arguments> createLists() {
		  return Stream.of(
		      Arguments.of(new ArrayList<Object>()),
		      Arguments.of(new LinkedList<Object>())
		  );
		}
	
	@ParameterizedTest
	@MethodSource("createLists")
	/** 1
	 * GIVEN: An empty list
	 * WHEN: add(0,number)
	 * THEN: number is added in 0 position
	 */
	 @Test
	public void emptyListAddAt0(List<Object> list) {
		
		Object number = 3;
		list.add(0,number);
		assertTrue(list.size() == 1);
		assertTrue(list.get(0) == number);
		
	}
	
	
	
	/**
	 * 2 
	 * GIVEN: a non empty list and a non null element
	 * WHEN: trying to add the element at first position
	 * THEN: the list contains element at index 0, the size is increased in 1
	 */
	@ParameterizedTest
	@MethodSource("createLists")
	public void addNonEmptyListAddAt0(List<String> list) {
		list.add(0, "with");
		list.add(1, "JUnit");
		list.add(2, "framework");
		int lsize = list.size();

		list.add(0, "testing");

		assertTrue(list.size() == lsize+1);
		assertTrue(list.get(0).equals("testing"));
		assertTrue(list.toString().equals("[testing, with, JUnit, framework]"));
	}
	
	/**3
	 * GIVEN: a non empty list and a non null element
	 * WHEN: trying to add the element at intermediate position
	 * THEN: the list contains element at index 1, the size is increased in 1
	 */
	@ParameterizedTest
	@MethodSource("createLists")
	public void addMiddle(List<String> list) {
		list.add(0, "with");
		list.add(1, "JUnit");
		list.add(2, "framework");
		int lsize = list.size();

		list.add(1, "testing");

		assertTrue(list.size() == lsize+1);
		assertTrue(list.get(1).equals("testing"));
		assertTrue(list.toString().equals("[with, testing, JUnit, framework]"));
	}

	/**4
	 * GIVEN: una lista con objetos
	 * WHEN:  llamamos al metodo con la ultima posicion
	 * THEN:  añade ese objeto en la ultima posicion y mueve una posición más al que estaba allí
	 */
	@ParameterizedTest
	@MethodSource("createLists")
	public void addLast(List<String> list) {
		list.add(0, "with");
		list.add(1, "JUnit");
		list.add(2, "framework");
		int lsize = list.size();

		list.add(2, "testing");

		assertTrue(list.size() == lsize+1);
		assertTrue(list.get(2).equals("testing"));
		assertTrue(list.toString().equals("[with, JUnit, testing, framework]"));
	}

	/**5
	 * GIVEN: Una lista con elementos 
	 * WHEN: Añadir en la siguiente posicion del ultimo objeto, el ultimo objeto
	 * THEN: Comprobar que hay cuatro objetos no son null, y que el el ultimo y penultimo son iguales
	 */
	@ParameterizedTest
	@MethodSource("createLists")
	public void addAfterLast(List<String> list) {
		list.add(0, "objeto 1");
		list.add(1, "objeto 2");
		list.add(2, "objeto 3");
		int listSize = list.size();
		
		list.add("testing");
		
		assertTrue(listSize == list.size() + 1);
		assertTrue(list.get(3).equals("testing"));
		assertTrue(list.toString().equals("[objeto 1, objeto 2, objeto 3, testing]"));
	}
	
	/**6
	 * GIVEN: a non empty list and a non null element
	 * WHEN: trying to add a repeated element correct
	 * THEN: the list contains a repeated element
	 */
	@ParameterizedTest
	@MethodSource("createLists")
	public void addRepeated(List<String> list) {
		list.add(0, "with");
		list.add(1, "JUnit");
		list.add(2, "framework");
		int lsize = list.size();

		list.add(3, "with");

		assertTrue(list.size() == lsize+1);
		assertTrue(list.get(0).equals(list.get(3)));
		assertTrue(list.toString().equals("[with, JUnit, framework, with]"));
	}
	
	/**7
	 * GIVEN: Una lista 
	 * WHEN: Se intenta introducir un una posicion negativa
	 * THEN: lanza IAE
	 */
	@ParameterizedTest 
	@MethodSource("createLists")
	public void LowerBound(List<String> list) {
		
		ArrayList<?> newList = new ArrayList<Object>();
		
		assertThrows(IndexOutOfBoundsException.class,() -> list.add(-1,"objeto"));
		assertTrue(list.size() == newList.size());
		assertEquals(list.toString(), newList.toString());
		
		
	}
	
	/**8
	 * GIVEN: lista con un objeto
	 * WHEN: Intentar añadir en una posicón > size()
	 * THEN: se lanza IndexOutOfBoundsException y la lista permanece igual
	 */
	@ParameterizedTest 
	@MethodSource("createLists")
	public void UpperBound(List<?> list) {
		
		String obj = "asdf";
	
		ArrayList<String> arraylist = new ArrayList<String>();
		 arraylist.add(0,obj);
		LinkedList<String> linkedlist = new LinkedList<String>();
		 linkedlist.add(0,obj);
		
		assertThrows(IndexOutOfBoundsException.class, () -> arraylist.add(2,obj));
		assertThrows(IndexOutOfBoundsException.class, () -> linkedlist.add(2,obj));
		assertEquals(0,arraylist.size());
		assertEquals(0,linkedlist.size());
        assertTrue(arraylist.contains(obj));
		assertTrue(linkedlist.contains(obj));
	}
	
	
	
	/**9
	 * GIVEN: A list
	 * WHEN: A null element tries to be added
	 * THEN: An IllegalArgumentException is Thrown due to the already mentioned nature of the element
	 */
	@ParameterizedTest
	@MethodSource("createLists")
	public void nullExcep(List<?> list) {
		int size = list.size();
		assertThrows(IllegalArgumentException.class, () -> list.add(null),"Exception expected");
		assertEquals(list.size(), size);
	}
	
}
