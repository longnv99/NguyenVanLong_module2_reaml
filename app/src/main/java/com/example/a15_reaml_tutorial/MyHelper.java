package com.example.a15_reaml_tutorial;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public
class MyHelper {
    Realm realm;
    RealmResults<User> users;

    public
    MyHelper(Realm realm) {
        this.realm = realm;
    }

    // select
    public void selectFromDB(){
        users = realm.where(User.class).findAll();
    }

    public
    ArrayList<User> convertList(){
        ArrayList<User> list = new ArrayList<>();
        for (User u : users)
            list.add(u);

        return list;
    }


}
