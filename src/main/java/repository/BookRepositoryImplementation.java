package repository;

import data.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImplementation implements BookRepository {


    private static final String FULL_PATH = "src/main/resources/files/books.txt";

    @Override
    public boolean saveToFile(List<Book> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FULL_PATH))){
            for (Book book : books) {
                writer.write(book.toString());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error saving the text.");
            return false;
        }

    }

    @Override
    public List<Book> loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FULL_PATH))) {
            List<Book> books = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                if (line.isBlank()) {
                    line = reader.readLine();
                    continue;
                }
                String[] lineText = line.split("\\s-\\s");
                if(lineText.length< 5){
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }
                long id = Long.parseLong(lineText[0]);
                String title = lineText[1];
                String author = lineText[2];
                int year = Integer.parseInt(lineText[3]);
                String genre = lineText[4];
                books.add(new Book(id, title, author, year, genre));
                line = reader.readLine();
            }
            return books;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("The file is not found.");

        } catch (IOException e) {
            throw new RuntimeException("The file can not be read.");
        }
    }
}
