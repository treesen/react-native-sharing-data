# react-native-sharing-data

share data between apps, support both iOS and Android.

## Installation

```sh
npm install react-native-sharing-data
```

## Android

AndroidManifest.xml, add provider config and permission
```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
  package="com.example.reactnativesharingdata">

    // ...

    <permission android:name="com.reactnativesharingdata.ACCESS_DATABASE" android:protectionLevel="signature" /> // keep name same as "android:permission"

    <uses-permission android:name="com.reactnativesharingdata.otherapp.ACCESS_DATABASE" /> // change to the permission name of the other app you wanna get data.
  
    // ...

    <application
      android:name=".MainApplication"
      android:label="@string/app_name"
      android:icon="@mipmap/ic_launcher"
      android:roundIcon="@mipmap/ic_launcher_round"
      android:allowBackup="false"
      android:theme="@style/AppTheme">

      // ...
      
      <provider
        android:authorities="com.reactnativesharingdata" // change it  if you need.
        android:name="com.reactnativesharingdata.ShareContentProvider" // DO NOT CHANGE
        android:permission="com.reactnativesharingdata.ACCESS_DATABASE" // change it if you need, but keep the same with permission name above
        android:exported="true"/>
    </application>

</manifest>

```

android > app > build.gradle
```gradle

 // all related apps need same signature
 signingConfigs {
      debug {
      // ...
      }
      
      release {
       // ...
      }
  }

```

MainApplication.java, init uri in onCreate method.
```kotlin

import com.reactnativesharingdata.SharingDataPackage;
import com.reactnativesharingdata.Constants;

@Override
public void onCreate() {
 // ...
  Constants.initUri("com.reactnativesharingdata", "/data"); // authorities and path, change if you need. authorities must be the same with "android:authorities" in AndroidManifest.xml 
}
```

## iOS

need capbilty of the App Groups when you specialize the defaultSuitName

## Usage

```js
ios functions:

  set(defaultSuiteName: string | null, key: string, value: string): Promise<void>;
  get(defaultSuiteName: string | null, key: string): Promise<string>;
  clear(defaultSuiteName: string | null, key: string): Promise<void>;
  getMultiple(defaultSuiteName: string | null, keys: [string]): Promise<[string]>;
  setMultiple(defaultSuiteName: string | null, data: [{}]): Promise<void>;
  clearMultiple(defaultSuiteName: string | null, keys: [string]): Promise<void>;
  
  
android functions:

  checkContentExist(url: string): Promise<boolean>;
  queryAllData(url: string): Promise<any>;
  queryDataByKey(url: string, key: String): Promise<any>;
  insertData(url: string, key: string, value: string): Promise<any>;


example:

import SharingData from "react-native-sharing-data";

// ...

const urlTest = 'content://com.reactnativesharingdata/data'; // content://authorities + path

SharingData.queryData(urlTest).then(result => {
  alert(JSON.stringify(result))
});

SharingData.queryDataByKey(urlTest, "testKey").then(result => {
  alert(JSON.stringify(result))
});

SharingData.insertData(urlTest, 'test', 'xxxxxxx1').then(() => {

})

```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
