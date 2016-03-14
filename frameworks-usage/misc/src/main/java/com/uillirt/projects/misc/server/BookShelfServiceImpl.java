package com.uillirt.projects.misc.server;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;

@WebService(endpointInterface = "com.uillirt.projects.misc.server.BookShelfService")
public class BookShelfServiceImpl implements BookShelfService {
    Map<String, Book> books = new HashMap<String, Book>() {{
        Book testBook = new Book();
        testBook.setAuthor("author");
        testBook.setBookId(42);
        testBook.setBookName("book_name");
        put(testBook.getBookName(), testBook);
    }};

    public String insertBook(Book book) {
        System.out.println("insert");
        books.put(book.getBookName(), book);
        return book.getBookName();
    }


    public Book getBook(String title) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return books.get(title);
    }
}
