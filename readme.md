#BuildNumberOverlay [![](https://jitpack.io/v/tatocaster/BuildNumberOverlay.svg)](https://jitpack.io/#tatocaster/BuildNumberOverlay)

*Works only in DEBUG builds. Release builds will ignore it*

#Install
```gradle
allprojects {
 repositories {
    jcenter()
    maven { url "https://jitpack.io" }
 }
}
```

and 
```groovy
dependencies {
    debugCompile 'com.github.tatocaster.BuildNumberOverlay:buildnumberoverlaylibrary:1.0.2'
    releaseCompile 'com.github.tatocaster.BuildNumberOverlay:buildnumberoverlaylibrary-no-op:1.0.2'
}
```

Library uses SYSTEM_ALERT_WINDOW permission to show a build number and name over the top of the apps

supports **minSdk : 11**

<img src="https://raw.githubusercontent.com/tatocaster/BuildNumberOverlay/master/art/art.png" alt="All in one" width="300">

Initialize in your application class

```java
NumberOverlay.initialize(this);
```
*demo* : [MyApp.java](https://github.com/tatocaster/BuildNumberOverlay/blob/master/app/src/main/java/me/tatocaster/buildversionoverlay/MyApp.java#L17)

multiple times init will cause error
`NumberOverlayException` :  
`"Can not initialize multiple times!"`

`NumberOverlay` class has method `isInitialized()` for advanced logic in your application
 but behind the scenes `initialize()` method uses this.
 
 ***more to come***
 - change background color
 - change text color
 - change position as user configures

# Quick contributing guide
 - Fork and clone locally
 - Create a topic specific branch. Add some nice feature.
 - Send a Pull Request to spread the fun!

#License
```
MIT License

Copyright (c) 2017 Merab Tato Kutalia

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```
