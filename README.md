# Realm
Realm is a lightweight database that can replace both SQLite and ORM libraries in your Android projects

## Install Realm as a Gradle plugin
   Step 1: Add the class path dependency to the project level build.gradle file.

```gradle
    
    dependencies {
        classpath "io.realm:realm-gradle-plugin:5.4.1"
    }

```
Step 2: Apply the realm-android plugin to the top of the application level build.gradle file.

```gradle

  apply plugin: 'realm-android'
  
  realm {
  syncEnabled = true;
}

```

## Initializing Realm
   Before you can use Realm in your app, you must initialize it. This only has to be done once.
   You must provide an Android context. A good place to initialize Realm is in onCreate on an application subclass:

```java
   
   package com.example.basicprogramming.realmdemoapp.configs;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmConfig extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().name("detailList.realm").build();
        Realm.setDefaultConfiguration(configuration);


    }
}

```
If you create your own application subclass, you must add it to the app’s AndroidManifest.xml:

```xml

<application
  android:name=".MyApplication"
  ...
/>

```
   

