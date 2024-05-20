package com.jave.bookStoreQuotes.factory;

import com.jave.bookStoreQuotes.entity.Book;
import com.jave.bookStoreQuotes.entity.Copy;
import com.jave.bookStoreQuotes.entity.Novel;
import com.jave.bookStoreQuotes.values.copy.*;


public class CopyFactoryImpl implements CopyFactory{
    @Override
    public Copy createCopy(String id, String type, Title title, Author author, Stock stock, PublicationYear publicationYear, Price price) {
        if ("Book".equals(type)) {
            return Book.from(id, new Type(type), title, author, stock, publicationYear, price);
        } else if ("Novel".equals(type)) {
            return Novel.from(id, new Type(type), title, author, stock, publicationYear, price);
        } else {
            throw new IllegalArgumentException("Invalid type");
        }
    }
}
