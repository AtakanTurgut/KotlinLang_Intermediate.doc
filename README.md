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
Safe Args - build.gradle.kts - project
plugins {
    id ("androidx.navigation.safeargs.kotlin") version "2.7.7" apply false
}
```
```cs
Safe Args - build.gradle.kts - app
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