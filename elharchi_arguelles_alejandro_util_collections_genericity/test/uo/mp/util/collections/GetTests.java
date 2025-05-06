package uo.mp.util.collections;
 
import static org.junit.Assert.assertEquals;
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
 * 	1 Intentar realizar get en la posición 0 de una lista vacía, lanza IndexOutOfBoundsException
 *  2 Intentar realizar get en la posición -1 de una lista vacía, lanza IndexOutOfBoundsException
 *  3 Intentar realizar get en la posición -1 de una lista con elementos, lanza IndexOutOfBoundsException
 *  4 Intentar realizar get en la posición size() de una lista con elementos, lanza IndexOutOfBoundsException
 *  5 Realizar get en una posición cualquiera de una lista no vacía retorna el elemento y la lista perpanece igual
 */
public class GetTests {
	
	public static Stream<Arguments> createLists() {
		  return Stream.of(
		      Arguments.of(new ArrayList<Object>()),
		      Arguments.of(new LinkedList<Object>())
		  );
		}


	/** 1
	 * GIVEN: una lista vacia
	 * WHEN: intenta ver el objeto de la posicion 0   
	 * THEN: lanza una excepcion 
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void emptyList(List<?> list) {
		IllegalArgumentException e = 
				assertThrows(IllegalArgumentException.class, () -> 
				list.get(0),"Exception expected");
		 assertEquals("No hay elemento en esa posicion", e.getMessage());
	}
	
	/** 2
	 * GIVEN: la posicion -2
	 * WHEN:  se llama al metodo con una lista vacia
	 * THEN:  salta una excpcion
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void emptyMinusOne(List<?> list) {
		int lsize = list.size();
		IllegalArgumentException e = 
				assertThrows(IllegalArgumentException.class, () -> 
				list.get(-1),"Exception expected");
		 assertEquals("No se admite un get de una posicion negativa", e.getMessage());
		 assertTrue(list.size() == lsize);	
	}
	
	/**3
     * GIVEN: Una lista con elementos
     * WHEN: Se intenta realizar get en la posición -1
     * THEN: Se lanza IndexOutOfBoundsException
     */
    @ParameterizedTest
    @MethodSource("createLists")
    public void lower(List<String> list) {
        list.add("Elemento"); 
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }
	
    /**4
	 * GIVEN: lista con elementos
	 * WHEN: intenta realizar get en la posicion size()
	 * THEN: IndexOutOfBoundsException
	 */
	@ParameterizedTest@MethodSource("createLists") 
	public void upper(List<String> list) {
		list.add("Hello1");
		list.add("Hello2");
		list.add("Hello3");
		
		assertEquals(3, list.size());
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size()));
		
	}
	
	/** 5
	 * GIVEN: Una lista no vacia de elementos
	 * WHEN: Llamamos al get de una posicion cualquiera  
	 * THEN: Devuelve el elemento y la lista permanece igual
	 */
	@ParameterizedTest@MethodSource("createLists")
	public void inList(List<String> list) {
		
		list.add(0,"objeto");
		list.add(1,"objeto");
		list.add(2,"objeto");
		
		Object object0 = list.get(0);
		Object object1 = list.get(1);
		Object object2 = list.get(2);
		
		assertEquals(object0, list.get(0));
		
		
		assertEquals(object1, list.get(1));
		
		assertEquals(object2, list.get(2));
		
	}	
}
