package com.github.xeniale.backendexamples.misc.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface BookShelfServiceOld {
    @WebMethod
    String insertBook(BookOld bookVO);

    @WebMethod
    BookOld getBook(String title);
}