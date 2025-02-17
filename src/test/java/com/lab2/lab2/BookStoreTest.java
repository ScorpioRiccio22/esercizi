package com.lab2.lab2;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BookStoreTest {
	private BookStore bookStore;
	
	@BeforeEach		
	public void setUp() {
		bookStore = new BookStore();
		bookStore.addBook(new Book("La divina commedia","Dante Alighieri",40.00));
		bookStore.addBook(new Book("I Promessi sposi","Alessandro Manzoni",15.00));
		bookStore.addBook(new Book("Sei personaggi in cerca d'autore","Luigi Pirandello",25.00));
	}
	
	@Test
	public void testAddBook() {
		Book libro = new Book("Racconti del terrore","Edgar Allan Poe",20.00);
		bookStore.addBook(libro);
		assertEquals(4,bookStore.getBookCount());
	}
	
	@Test
	public void testRemoveBook() {
		boolean rimosso = bookStore.removeBook("I Promessi sposi");
		assertTrue(rimosso);
		assertEquals(2,bookStore.getBookCount());
	}
	
	@Test
	public void testRemoveNonExistentBook() {
		boolean rimosso = bookStore.removeBook("La chiave di Sara");
		assertFalse(rimosso);
		assertEquals(3,bookStore.getBookCount());
	}
	
	@Test
	public void testGetTotalPrice() {
		double totalPrice = bookStore.getTotalPrice();
		assertEquals(80.00,totalPrice);
	}
	
	 @ParameterizedTest
	    @CsvSource({
	        "I Promessi sposi, true",
	        "La chiave di Sara, false",
	    })
	 public void testRemovedBookParameterized(String titolo,boolean atteso) {
			boolean rimosso = bookStore.removeBook(titolo);
			assertEquals(atteso,rimosso);
		}
	
}
