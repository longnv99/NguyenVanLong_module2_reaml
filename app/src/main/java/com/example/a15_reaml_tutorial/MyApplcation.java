package com.example.a15_reaml_tutorial;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public
class MyApplcation extends Application {
    @Override
    public
    void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("user.realm")
                .build();

        Realm.setDefaultConfiguration(configuration);
    }
}
