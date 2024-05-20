package com.jave.bookStoreQuotes.values.identities;


import com.jave.generic.Identity;

public class GroupId extends Identity {

    public GroupId() {
        super();
    }

    public GroupId(String id) {
        super(id);
    }
    public static GroupId of(String uuid){
        return new GroupId(uuid);
    }
}
