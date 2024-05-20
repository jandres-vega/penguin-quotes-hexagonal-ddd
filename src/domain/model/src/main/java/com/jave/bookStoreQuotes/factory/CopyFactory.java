package com.jave.bookStoreQuotes.factory;


import com.jave.bookStoreQuotes.entity.Copy;
import com.jave.bookStoreQuotes.values.copy.*;

public interface CopyFactory {
    Copy createCopy(
            String id,
            String type,
            Title title,
            Author author,
            Stock stock,
            PublicationYear publicationYear,
            Price price
    );
}
