package by.epamtc.HacakConstantine.task4.Bean;

import by.epamtc.HacakConstantine.task4.Bean.enums.BookStatus;

import java.io.Serializable;

public class Book implements Serializable {
    private static final char paramDelimiter = '|';
    private int id;
    private String title;
    private String author;
    private int pageNumber;
    private BookStatus bookStatus;
    private String owner = "";

    public Book() {
    }

    public Book(int id, String title, String author, int pageNumber) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pageNumber = pageNumber;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
        if (!this.owner.equals(b.owner)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(this.id).append(paramDelimiter).append(this.title).append(paramDelimiter).append(this.author).append(paramDelimiter).append(this.pageNumber).append(paramDelimiter).append(this.bookStatus).append(paramDelimiter).append(this.owner).append("\n");
        return str.toString();
    }

    @Override
    public int hashCode() {
        return (int) 31 * this.id
                + (this.title == null ? 0 : this.title.hashCode())
                + (this.author == null ? 0 : this.author.hashCode())
                + (this.bookStatus == null ? 0 : this.bookStatus.hashCode()) + this.pageNumber;
    }


}
