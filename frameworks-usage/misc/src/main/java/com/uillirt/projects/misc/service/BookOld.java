package com.uillirt.projects.misc.service;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "Book")
public class BookOld implements Serializable {

    private long bookId;
    private String bookName;
    private String author;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long booktId) {
        this.bookId = booktId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
