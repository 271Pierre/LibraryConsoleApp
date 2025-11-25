package data;

public class Book {

    private long id;
    private String title;
    private String author;
    private int year;
    private String genre;



    public Book(long id, String title, String author, int year, String genre) {
        this.setId(id);
        this.setTitle(title);
        this.setAuthor(author);
        this.setYear(year);
        this.setGenre(genre);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if(id <= 0){
            throw new IllegalArgumentException("System error, incorrect id.");
        }
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        if(title.length() <= 1){
            throw new IllegalArgumentException("Too short book's title.");
        }
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        if(author.length() <= 1){
            throw new IllegalArgumentException("Incorrect author's name. Written name is too short.");
        }
        this.author = author;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        if(year <= 0 || year >= 2147483647){
            throw new IllegalArgumentException("Incorrect year is inserted.");
        }
        this.year = year;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        if(genre.length() < 1 || genre.length() > 25 ){
            throw new IllegalArgumentException("This genre doesn't exist.");
        }
        this.genre = genre;
    }



    @Override
    public String toString() {
        return String.format("%d - %s - %s - %d - %s", this.getId(), this.getTitle(), this.getAuthor(), this.getYear(), this.getGenre());
    }
}
