#BuildNumberOverlay

#install
```
allprojects {
 repositories {
    jcenter()
    maven { url "https://jitpack.io" }
 }
}
```

and 
```
dependencies {
    compile 'com.github.tatocaster:BuildNumberOverlay:1.0'
}
```

Library uses SYSTEM_WINDOW to show a build number and name over the top of apps

supports **minSdk : 11** aka **Android 3.0**

<img src="https://raw.githubusercontent.com/tatocaster/BuildNumberOverlay/master/art/art.png" alt="All in one" width="300">

Initialize in your application class

```
NumberOverlay.initialize(this);
```

multiple times init will cause error
`NumberOverlayException` :  
`"Can not initialize multiple times!"`

`NumberOverlay` class has method `isInitialized()` for advanced login in your application
 but behind the scenes `initialize()` method use this.
 
 ***more to come***
 - change background color
 - change text color
 - change position as user configures
