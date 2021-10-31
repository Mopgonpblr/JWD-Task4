package by.epamtc.HacakConstantine.task4.Bean;

public class Book {
    private int id;
    private String title;
    private String author;
    private int pageNumber;
    private String status;

    public Book() {
    }

    public Book(int id, String title, String author, int pageNumber, String status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pageNumber = pageNumber;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!this.status.equals(b.status)) return false;
        return true;
    }

    @Override
    public String toString() {
        return this.id + " " + this.title + " " + this.author + " " + this.pageNumber + " " + this.status;
    }

    @Override

    public int hashCode() {
        return (int) 31 * this.id
                + (this.title == null ? 0 : this.title.hashCode())
                + (this.author == null ? 0 : this.author.hashCode())
                + (this.status == null ? 0 : this.status.hashCode()) + this.pageNumber;
    }

}
