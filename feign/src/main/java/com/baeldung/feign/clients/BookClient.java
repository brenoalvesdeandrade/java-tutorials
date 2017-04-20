package com.nklkarthi.feign.clients;

import com.nklkarthi.feign.models.Book;
import com.nklkarthi.feign.models.BookResource;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface BookClient {
    @RequestLine("GET /{isbn}")
    BookResource findByIsbn(@Param("isbn") String isbn);

    @RequestLine("GET")
    List<BookResource> findAll();

    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    void create(Book book);
}
