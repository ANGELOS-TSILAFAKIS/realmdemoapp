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

