package com.reactnativesharingdata

import android.net.Uri
import android.widget.Toast
import android.content.ContentValues
import com.facebook.react.bridge.*

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

      if (c == null) {
        promise.resolve(null)
      } else if (c.moveToFirst()) {

          var dataArray = Arguments.createArray()
          do{
            var key = c.getString(c.getColumnIndex(Constants.Content.CONTENT_NAME))
            var value = c.getString(c.getColumnIndex(Constants.Content.CONTENT_VALUE))

            val item = Arguments.createMap();
            item.putString(key, value)
            dataArray.pushMap(item)
          } while (c.moveToNext());

        promise.resolve(dataArray)
      }

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
