# Piper

Piper is an Android library to reduce the amount of fiddly boilerplate code you have to write to
use a SQLite database.

*It is a work in progress.*

## To use

In your project-level `build.gradle`:

    allprojects {
        repositories {
            maven {
                url 'https://dl.bintray.com/guardian/android'
            }
            // Others...
        }
    }

In your module-level `build.gradle`:

    dependencies {
        compile 'com.guardian.android:piper:0.1.2'
        // Others...
    }
