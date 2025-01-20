## Intermediate Kotlin Programming Language

### 1) Layout And Views - Düzen ve Görünümler
-   View Binding - https://developer.android.com/topic/libraries/view-binding?hl=tr

### 2) Android Basics - Android Temelleri

![](/images/activity_lifecycle.png)

-   Android Activity Lifecycle - Yaşam Döngüsü - https://developer.android.com/guide/components/activities/activity-lifecycle?hl=tr
-   Data Transfer - Veri Transferi
-   Context
-   Android Alert Dialog - https://developer.android.com/develop/ui/views/components/dialogs?hl=tr
-   Shared Preferences

### 3) RecyclerView - Dinamik Listeler

![](/images/recyclerview_android.png)

-   RecyclerView - https://developer.android.com/develop/ui/views/layout/recyclerview?hl=tr
-   Adaptor
-   Singleton

![](/images/SuperHeroes/super_hero_app.jpg)

### 4) Fragments and Navigation - Parçalar  ve Gezinme
-   Fragments - https://developer.android.com/guide/fragments?hl=tr
-   Navigation - https://developer.android.com/guide/navigation?hl=tr
-   Navigation Graph
-   Fragment Lifecycle - Parça Yaşam Döngüsü - https://developer.android.com/guide/fragments/lifecycle?hl=tr
-   Safe Args - nav_graph -> secondFragment - https://developer.android.com/guide/navigation/use-graph/safe-args?hl=tr

```cs
    Safe Args - build.gradle.kts - project:

plugins {
    id ("androidx.navigation.safeargs.kotlin") version "2.7.7" apply false
}
```
```cs
    Safe Args - build.gradle.kts - app:

plugins {
    id ("kotlin-kapt")
    id ("androidx.navigation.safeargs.kotlin")
}

dependencies {
    val nav_version = "2.7.7"

    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
}
```

![](/images/fragment_view_lifecycle.png)

### 5) Room and Database
-   Room - https://developer.android.com/training/data-storage/room?hl=tr
-   Sqlite Online - https://sqliteonline.com/
-   Entity - Model
-   Data Access Object (DAO) - Veri Erişimi Nesnesi 
-   Database
-   Threading - RxJava - https://developer.android.com/training/data-storage/room/async-queries?hl=tr

-   Fragment
-   Navigation
-   Permissions and Controls - İzinler ve Kontroller                        <br />
    https://developer.android.com/guide/topics/permissions/overview?hl=tr   <br />
    https://developer.android.com/reference/android/Manifest.permission     <br />
-   Activity Launcher - Etkinlik Başlatıcısı

-   Basic Create-Read-Delete (CRD) Transaction
-   Recyclerview Border Row - https://stackoverflow.com/questions/44498524/android-adding-a-border-and-rounding-to-recyclerview-items
-   MVVM - Model View ViewModel

```cs
    Nav | Room - build.gradle.kts - project:

plugins {
    id ("androidx.navigation.safeargs.kotlin") version "2.7.7" apply false
    id ("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}
```

```cs
    Nav | Room - build.gradle.kts - app:

plugins {
    id ("androidx.navigation.safeargs.kotlin")
    id ("com.google.devtools.ksp")
}

dependencies {
    val nav_version = "2.7.7"
    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor ("androidx.room:room-compiler:$room_version")
    // To use Kotlin annotation processing tool (kapt)
    ksp("androidx.room:room-compiler:$room_version")

    implementation ("androidx.room:room-rxjava3:$room_version")
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.2")
}
```
```html
    manifest - permissions:

    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"></uses-permission>     //  harici depolama alanı
    <uses-permission android:name="android.permission.READ_MEDIA_IMAGES"></uses-permission>         //  resim dosyaları
```

![](/images/Foods/food_app.jpg)

### 6) Google Play
-   https://developer.android.com/distribute?hl=tr
-   Release - Yayınlama
-   Android App Icon Generator - https://www.appicon.co/
-   AAB - Android App Bundle

-   Release -> Production -> Create new release

```
    unsigned - imzasız: Build -> Build App -> Build APK(s) -- test app
```

```
App bundles:
    Upload files >

    signed - imzalı: Build -> Generate Signed App -> Next - Android App Bundle
                           -> Create new... -> !!Password!! -> release -> create :: "key.jks" - !!protect!!

    projectDic\app\release\"app-release.aab"
```

-   Privacy Policy - Gizlilik Politikası : Website URL for Declaration

```cs
    build.gradle.kts - app:

android {
    namespace = "com.atakanturgut.foodapp"

    defaultConfig {
        applicationId = "com.atakanturgut.foodapp"

        targetSdk = 35
        versionCode = 1             // change versionCode - 2 
        versionName = "1.0"         // change versionName - "2.0" ...
    }
}
```