package com.github.xeniale.backendexamples.misc.service;

import com.github.xeniale.service.soap.test.Book;
import com.github.xeniale.service.soap.test.BookShelfService;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;

//@WebService(endpointInterface = "com.github.xeniale.service.soap.test.BookShelfService")
public class BookShelfServiceImpl implements BookShelfService {

    @Override
    public String insertBook(Book arg0) {
        return null;
    }

    Map<String, Book> books = new HashMap<String, Book>() {{
        Book testBook = new Book();
        testBook.setAuthor("author");
        testBook.setBookId(42);
        testBook.setBookName("book_name");
        put(testBook.getBookName(), testBook);
    }};

    public Book getBook(String title) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return books.get(title);
    }
}
