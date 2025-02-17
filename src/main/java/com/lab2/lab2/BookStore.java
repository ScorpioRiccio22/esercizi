package com.lab2.lab2;

import java.util.ArrayList;
import java.util.List;

public class BookStore {
    private List<Book> books;

    public BookStore() {
        this.books = new ArrayList<Book>();
    }

    public void addBook(Book book) {
        books.add(book);
    }
    
    
//    Prende un book come parametro.
//    Chiama getTitle() su book per ottenere il titolo del libro.
//    Confronta il titolo del libro (book.getTitle()) con il titolo passato come parametro (title) usando equals.
//    Ritorna true se i titoli sono uguali, false altrimenti.

    public boolean removeBook(String title) {
        return books.removeIf(book -> book.getTitle().equals(title));
    }
    
//    In sostanza, per ogni oggetto Book nello stream, Book::getPrice 
//    ottiene il prezzo di quel libro, e mapToDouble crea un nuovo DoubleStream
//    basato su questi valori.
//    sum() è una operazione terminale che calcola la somma di tutti i valori nel DoubleStream.
//    Quindi, alla fine, sum restituisce la somma di tutti i prezzi dei libri 
//	  nella collezione books.

    public double getTotalPrice() {
        return books.stream().mapToDouble(Book::getPrice).sum();
    }

    
    public int getBookCount() {
        return books.size();
    }
}