package com.uillirt.projects.misc.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface BookShelfService {
    @WebMethod
    String insertBook(Book bookVO);

    @WebMethod
    Book getBook(String title);
}