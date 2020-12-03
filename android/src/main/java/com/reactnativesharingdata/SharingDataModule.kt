package com.reactnativesharingdata

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import android.content.UriMatcher
import android.net.Uri
import android.widget.Toast
import android.content.ContentValues

class SharingDataModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    var baseReactContext = reactContext

    override fun getName(): String {
        return "SharingData"
    }

    // Example method
    // See https://facebook.github.io/react-native/docs/native-modules-android
    @ReactMethod
    fun multiply(a: Int, b: Int, promise: Promise) {
      promise.resolve(a * b)
    }

    @ReactMethod
    fun checkContentExist(url: String, promise: Promise) {
      var CONTENT_URI = Uri.parse(url)
      var c = baseReactContext.getContentResolver().query(CONTENT_URI, null, null, null, Constants.Content.CONTENT_NAME)
      if (c == null) {
        promise.resolve(false)
      } else {
        c.moveToFirst()
        var value = c.getString(c.getColumnIndex( Constants.Content.CONTENT_VALUE))

        if (value == null || value == "") {
          promise.resolve(false)
        } else {
          promise.resolve(true)
        }

      }

    }

    @ReactMethod
    fun queryData(url: String, promise: Promise) {
      var CONTENT_URI = Uri.parse(url)
      var c = baseReactContext.getContentResolver().query(CONTENT_URI, null, null, null, Constants.Content.CONTENT_NAME)
      if (c.moveToFirst()) {
          do{
              Toast.makeText(baseReactContext,
                      c.getString(c.getColumnIndex(Constants.Content._ID)) +
                              ", " +  c.getString(c.getColumnIndex(Constants.Content.CONTENT_NAME)) +
                              ", " + c.getString(c.getColumnIndex(Constants.Content.CONTENT_VALUE)),
                      Toast.LENGTH_SHORT).show();
          } while (c.moveToNext());
      }
      promise.resolve("done")
    }


    @ReactMethod
    fun insertData(url: String, key: String, value: String, promise: Promise) {
      var CONTENT_URI = Uri.parse(url)
      var values = ContentValues()
      values.put(Constants.Content.CONTENT_NAME, key)
      values.put(Constants.Content.CONTENT_VALUE, value)

      baseReactContext.getContentResolver().insert(CONTENT_URI, values)

      var mUri = baseReactContext.getContentResolver().insert(CONTENT_URI, values)

      promise.resolve(mUri != null)
    }


}
