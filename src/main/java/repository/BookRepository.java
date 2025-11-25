package repository;

import data.Book;

import java.util.List;

public interface BookRepository {



boolean saveToFile(List<Book> books);
List<Book> loadFromFile();

}
