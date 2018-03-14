Bottom Navigation bar layout
==========================

This is a bottom navigation bar layout.

## 1. Demo
![demo image](./images/demo.png)

## 2. Usage

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Step 2. Add the dependency

```gradle
dependencies {
    compile 'com.github.onlynight:BottomNavBar:1.1.0'
}
```


## 3. API

control java code:

```java
mAdapter = new NavItemAdapter();
navBar.setAdapter(mAdapter);
navBar.setOnItemSelectedListener(this);
navBar.setSelect(0);
```
