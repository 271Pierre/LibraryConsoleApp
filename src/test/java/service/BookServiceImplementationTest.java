package service;

import data.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BookServiceImplementationTest {


    @Test
    void bookListAddBookSuccessfully() {
        BookService bookService = new BookServiceImplementation();
        bookService.addBook("firstBook", "Author", 1907, "novel");
        Assertions.assertTrue(bookService.getAllBooks().size() > 0);
    }

    @Test
    void removeBook() {
        BookService bookService = new BookServiceImplementation();
        bookService.addBook("firstBook", "Author", 1907, "novel");
        bookService.addBook("secondBook", "Author1", 1910, "poem");
        bookService.removeBook(2);
        Assertions.assertTrue(bookService.getAllBooks().size() == 1);
    }

    @Test
    void updateBook() {
        BookService bookService = new BookServiceImplementation();
        bookService.addBook("firstBook", "Author", 1907, "novel");
        bookService.addBook("secondBook", "Author1", 1910, "poem");
        Book book = new Book(1, "thirdBook", "Author3", 190, "poem");
        bookService.updateBook(1,book);
        Assertions.assertTrue(bookService.getAllBooks().get(0).getTitle().equals("thirdBook"));
    }

    @Test
    void searchByTitle() {
        BookService bookService = new BookServiceImplementation();
        bookService.addBook("firstBook", "Author", 1907, "novel");
        bookService.addBook("secondBook", "Author1", 1910, "poem");
        String searchedBooksAuthor = bookService.searchByTitle("secondBook").getAuthor();
        Assertions.assertTrue("Author1".equals(searchedBooksAuthor));
    }

    @Test
    void printBooks() {
        BookService bookService = new BookServiceImplementation();
        bookService.addBook("secondBook", "Author1", 1910, "poem");
        String printSecondBook = bookService.printBooks();
        String firstString = "1 - secondBook - Author1 - 1910 - poem\r\n";
        Assertions.assertTrue(firstString.equals(printSecondBook));
//    }
    }
}