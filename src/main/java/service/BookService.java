package service;

import data.Book;

import java.util.List;

public interface BookService {

    void addBook(String title, String author, int year, String gener);
    void removeBook(long id);
    Book updateBook(long id, Book updatedBook);
    Book searchByTitle(String title);
    String printBooks();
    List<Book> getAllBooks();
    long getIdCounter();

    boolean idChecker(long bookId);
}
