package com.lab2.lab2;

import static org.junit.jupiter.api.Assertions.*;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class BookStoreDynamicTest {

	private BookStore bookStore;

	@BeforeEach		
	public void setUp() {
		bookStore = new BookStore();
		bookStore.addBook(new Book("La divina commedia","Dante Alighieri",40.00));
		bookStore.addBook(new Book("I Promessi sposi","Alessandro Manzoni",15.00));
		bookStore.addBook(new Book("Sei personaggi in cerca d'autore","Luigi Pirandello",25.00));
	}

	//rimozione libro
	@TestFactory
	Stream<DynamicTest> dynamicTestRimozioneLibro() {
		return Stream.of(
				new Object[] {"La divina commedia", true},
				new Object[] {"I Promessi sposi",true},
				new Object[] {"La chiave di Sara", false}
				).map(data -> DynamicTest.dynamicTest("Rimozione libro(titolo: " + data[0] + ")",
					() -> {
						String titolo = (String) data[0];
						boolean expected = (boolean)data[1];
						assertEquals(expected, bookStore.removeBook(titolo));
					}
					));
	}
	
	@TestFactory
	Stream<DynamicTest> dynamicTestAggiuntaLibro() {
		return Stream.of(
				new Object[] {"Il Signore degli Anelli", "J.R.R. Tolkien", 30.00, 4},
				new Object[] {"1984","George Orwell", 20.00, 5},
				new Object[] {"Il Codice Da Vinci", "Dan Brown", 15.00, 6}
				).map(data -> DynamicTest.dynamicTest("Aggiunta libro (titolo: " +data[0]+ ", Autore:"+data[1]+ ", Prezzo:"+data[2]+")",
					() -> {
						String titolo = (String)   data[0];
						String autore = (String)   data[1];
						double prezzo = (double) data[2];
						
						int expected = (int) data[3];						
						Book libro = new Book(titolo,autore,prezzo);
						bookStore.addBook(libro);
						int count = bookStore.getBookCount();
						
						assertEquals(expected,count);
					}
					));
	}
	
	//Calcola prezzo totale
	@TestFactory
	Stream<DynamicTest> dynamicTestPrezzoTotale() {
		return Stream.of(
				new Object[] {"Il Signore degli Anelli", "J.R.R. Tolkien", 30.00, 110.00},
				new Object[] {"1984","George Orwell", 20.00, 130.00},
				new Object[] {"Il Codice Da Vinci", "Dan Brown", 15.00, 145.00}
				).map(data -> DynamicTest.dynamicTest("Prezzo totale (titolo: " +data[0]+ ", Autore:"+data[1]+ ", Prezzo:"+data[2]+")",
					() -> {
						String titolo = (String) data[0];
						String autore = (String) data[1];
						double prezzo = (double) data[2];
						double expected = (double) data[3];
						
						Book libro = new Book(titolo,autore,prezzo);
						bookStore.addBook(libro);
						double totalPrice = bookStore.getTotalPrice();
						
						assertEquals(expected,totalPrice);		
					}
					));
	}
		
}

//Aggiungi Libro
//@TestFactory
//Stream<DynamicTest> dynamicTestAggiuntaLibro() {
//	return Stream.of(
//			new Object[] {"Il Signore degli Anelli", "J.R.R. Tolkien", 30.00},
//			new Object[] {"1984","George Orwell", 20.00},
//			new Object[] {"Il Codice Da Vinci", "Dan Brown", 15.00}
//			).map(data -> DynamicTest.dynamicTest("Aggiunta libro (titolo: " +data[0]+ ", Autore:"+data[1]+ ", Prezzo:"+data[2]+")",
//				() -> {
//					String titolo = (String)   data[0];
//					String autore = (String)   data[1];
//					double prezzo = (double) data[2];
//					
//					int countBefore = bookStore.getBookCount();
//					Book libro = new Book(titolo,autore,prezzo);
//					bookStore.addBook(libro);
//					int countAfter = bookStore.getBookCount();
//					
//					assertEquals(countBefore +1,countAfter);
//				}
//				));
//}
//
////Calcola prezzo totale
//@TestFactory
//Stream<DynamicTest> dynamicTestPrezzoTotale() {
//	return Stream.of(
//			new Object[] {"Il Signore degli Anelli", "J.R.R. Tolkien", 30.00},
//			new Object[] {"1984","George Orwell", 20.00},
//			new Object[] {"Il Codice Da Vinci", "Dan Brown", 15.00}
//			).map(data -> DynamicTest.dynamicTest("Prezzo totale (titolo: " +data[0]+ ", Autore:"+data[1]+ ", Prezzo:"+data[2]+")",
//				() -> {
//					String titolo = (String) data[0];
//					String autore = (String) data[1];
//					double prezzo = (double) data[2];
//					
//					double sommaIniziale = bookStore.getTotalPrice();
//					Book libro = new Book(titolo,autore,prezzo);
//					bookStore.addBook(libro);
//					double totalPrice = bookStore.getTotalPrice();
//					
//					assertEquals(sommaIniziale+prezzo,totalPrice);		
//				}
//				));
//}