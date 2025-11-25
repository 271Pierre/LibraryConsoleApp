package service;

import data.Book;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImplementation implements BookService{


    private List<Book> books;
    private long idCounter = 1;


    public BookServiceImplementation() {
        this.books = new ArrayList<>();
    }
    public BookServiceImplementation(List<Book> books) {
        this.setBooks(books);
    }

    @Override
    public void addBook(String title, String author, int year, String genre) {
        long id = countChecker();
        Book book = new Book(id, title, author, year, genre);
        idCounter++;
        this.books.add(book);
    }


    @Override
    public void removeBook(long id) {
        if(this.books.removeIf(b -> b.getId() == id)){
            System.out.printf("Book with ID: '%d' has been deleted.\n", id);
        }
        else{
            System.out.printf("Book with ID: '%d' doesn't exist.\n", id);
        }
    }




    @Override
    public Book updateBook(long id, Book updatedBook) {
        return this.books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .map(b -> {
                    b.setAuthor(updatedBook.getAuthor());
                    b.setTitle(updatedBook.getTitle());
                    b.setYear(updatedBook.getYear());
                    b.setGenre(updatedBook.getGenre());
                    return b;
                })
                .orElse(null);
    }

    @Override
    public Book searchByTitle(String title) {
        return this.books.stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String printBooks() {
        StringBuilder allBooksForPrint = new StringBuilder();
        for (Book book : books) {
            allBooksForPrint.append(book.toString());
            allBooksForPrint.append(System.lineSeparator());
        }
        return allBooksForPrint.toString();
    }



    @Override
    public boolean idChecker(long bookId) {
        boolean thereIsId = false;
        for (int i = 0; i < this.books.size(); i++) {
            if(bookId == this.books.get(i).getId()){
                thereIsId = true;
            }
        }
        return thereIsId;
    }
    private long countChecker() {
        if(this.books.size() > 0){
            long lastId = this.books.get(this.books.size() - 1).getId();
            idCounter = lastId + 1;
        }
        return idCounter;
    }

    public long getIdCounter() {
        return idCounter;
    }
    @Override
    public List<Book> getAllBooks() {
        return this.books;
    }
    public void setBooks(List<Book> books) {
        this.books = new ArrayList<>(books);
    }

}
