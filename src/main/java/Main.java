import data.Book;
import repository.BookRepository;
import repository.BookRepositoryImplementation;
import service.BookService;
import service.BookServiceImplementation;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepositoryImplementation();
        BookService bookService = new BookServiceImplementation(bookRepository.loadFromFile());
        Scanner reader = new Scanner(System.in);

        boolean running = true;

        while (running) {
            printMenu();

            String num = reader.nextLine();

            switch (num) {
                case ("1") -> handleAddBook(bookService, reader);
                case ("2") -> handleUpdateBook(bookService, reader);
                case ("3") -> handleDeleteBook(bookService, reader);
                case ("4") -> handleFindBook(bookService, reader);
                case ("5") -> handlePrintBooks(bookService);
                case ("6") -> handleSaveToFile(bookRepository, bookService);
                case ("7") -> running = false;
            }
        }
    }

    private static void printMenu() {
        System.out.println(" Library Menu ");
        System.out.println("1. Add book");
        System.out.println("2. Update book");
        System.out.println("3. Remove book");
        System.out.println("4. Search by title");
        System.out.println("5. Print books");
        System.out.println("6. Save to file");
        System.out.println("7. Stop");
    }

    private static void handleAddBook(BookService bookService, Scanner reader) {
        System.out.println("Title:");
        String title = reader.nextLine();
        System.out.println("Author:");
        String author = reader.nextLine();
        System.out.println("Year:");
        int year = Integer.parseInt(reader.nextLine());
        System.out.println("Genre:");
        String genre = reader.nextLine();
        bookService.addBook(title, author, year, genre);
        long currentKey = bookService.getIdCounter() - 1;
        System.out.printf("You added book '%s' with ID: %d. \n", title, currentKey);
    }

    private static void handleUpdateBook(BookService bookService, Scanner reader) {
        System.out.println("Enter ID of the book for update:");
        long bookId = Long.parseLong(reader.nextLine());
        if (bookService.idChecker(bookId)) {
            System.out.println("Update title:");
            String updatedTitle = reader.nextLine();
            System.out.println("Update author:");
            String updatedAuthor = reader.nextLine();
            System.out.println("Update year:");
            int updatedYear = Integer.parseInt(reader.nextLine());
            System.out.println("Update genre:");
            String updatedGenre = reader.nextLine();
            Book updatedBook = new Book(bookId, updatedTitle, updatedAuthor, updatedYear, updatedGenre);
            if (bookService.updateBook(bookId, updatedBook) != null) {
                System.out.printf("You updated book '%s' with ID: %d. \n", updatedTitle, bookId);
            } else {
                System.out.println("There is not such an id.");
            }
        } else {
            System.out.println("There is not such an id.");
        }
    }

    private static void handleDeleteBook(BookService bookService, Scanner reader) {
        long bookId;
        System.out.println("Enter ID of the book for delete:");
        bookId = Long.parseLong(reader.nextLine());
        bookService.removeBook(bookId);
    }

    private static void handleFindBook(BookService bookService, Scanner reader) {
        String title;
        System.out.println("Enter searched book's title:");
        title = reader.nextLine();
        Book searchedBook = bookService.searchByTitle(title);
        if (searchedBook != null) {
            System.out.println(searchedBook);
        } else {
            System.out.println("There isn't any book with this title.");
        }
    }

    private static void handlePrintBooks(BookService bookService) {
        System.out.print(bookService.printBooks());
    }

    private static void handleSaveToFile(BookRepository bookRepository, BookService bookService) {
        List<Book> books = bookService.getAllBooks();
        for (Book book : books) {
            System.out.println(book.toString());
        }
        bookRepository.saveToFile(bookService.getAllBooks());
    }


}
