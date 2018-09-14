# Realm
Realm is a lightweight database that can replace both SQLite and ORM libraries in your Android projects. Compared to SQLite, Realm is faster and has lots of modern features, such as JSON support, a fluent API, data change notifications, and encryption support, all of which make life easier for Android developers

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

## App Images

<p align="left">

  <img src="https://github.com/apppath/realmdemoapp/blob/master/home-activity.png" width="350"/>
  <img src="https://github.com/apppath/realmdemoapp/blob/master/insert-activity.png" width="350"/>
 
</p>

'''
'''
<p align="left">

  <img src="https://github.com/apppath/realmdemoapp/blob/master/update-activity.png" width="400"/>
  <img src="https://github.com/apppath/realmdemoapp/blob/master/detail-list.png" width="400"/>

</p>


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
   
## Table Model
   Realm Java lets you efficiently write your app’s model layer in a safe, persistent, and fast way. Here’s what it looks like:

```java

package com.example.basicprogramming.realmdemoapp.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class LanguageList extends RealmObject {

    @PrimaryKey
    private String id;
    @Required
    private String name;
    @Required
    private String developed;
    @Required
    private String description;
    @Required
    private String latest;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeveloped() {
        return developed;
    }

    public void setDeveloped(String developed) {
        this.developed = developed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }
}


```
   
# Done All Crud Action In This App   
