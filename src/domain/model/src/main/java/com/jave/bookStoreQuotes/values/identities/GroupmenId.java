package com.jave.bookStoreQuotes.values.identities;


import com.jave.generic.Identity;

public class GroupmenId extends Identity {

    public GroupmenId() {
        super();
    }

    public GroupmenId(String id) {
        super(id);
    }
    public static GroupmenId of(String uuid){
        return new GroupmenId(uuid);
    }
}
