package by.epamtc.HacakConstantine.task4.Bean;

public class Book {
    private int id;
    private String title;
    private String author;
    private int pageNumber;
    private BookStatus bookStatus;

    public Book() {
    }

    public Book(int id, String title, String author, int pageNumber, BookStatus bookStatus) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pageNumber = pageNumber;
        this.bookStatus = bookStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass())
            return false;
        Book b = (Book) obj;
        if (this.id != b.id) return false;
        if (!this.title.equals(b.title)) return false;
        if (!this.author.equals(b.author)) return false;
        if (this.pageNumber != b.pageNumber) return false;
        if (!this.bookStatus.equals(b.bookStatus)) return false;
        return true;
    }

    @Override
    public String toString() {
        return this.id + "|" + this.title + "|" + this.author + "|" + this.pageNumber + "|" + this.bookStatus;
    }

    @Override

    public int hashCode() {
        return (int) 31 * this.id
                + (this.title == null ? 0 : this.title.hashCode())
                + (this.author == null ? 0 : this.author.hashCode())
                + (this.bookStatus == null ? 0 : this.bookStatus.hashCode()) + this.pageNumber;
    }

}
