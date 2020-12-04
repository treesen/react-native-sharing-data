package com.reactnativesharingdata

import android.net.Uri
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
      var data = this.query(url, null, null, null, Constants.Content.CONTENT_KEY)

      promise.resolve(data !== null)
    }

    @ReactMethod
    fun queryAllData(url: String, promise: Promise) {

      var data = this.query(url, null, null, null, Constants.Content.CONTENT_KEY)

      promise.resolve(data)
    }

    @ReactMethod
    fun queryDataByKey(url: String, key: String?, promise: Promise) {

      var data = this.query(url, null, Constants.Content.CONTENT_KEY + " = '$key'", null, Constants.Content.CONTENT_KEY)

      promise.resolve(data)
    }

    /*
    * return map to keep key unique
    * */
    private fun query(url: String, projection:  Array<out String>?, selection: String?, selectionArgs:  Array<out String>?, sortOrder: String ): WritableMap? {
      var uri = Uri.parse(url)
      var c = baseReactContext.contentResolver.query(uri, projection, selection, selectionArgs, sortOrder)

      if (c != null && c.count > 0 && c.moveToFirst()) {

        var data = Arguments.createMap()
        do {
          var key = c.getString(c.getColumnIndex(Constants.Content.CONTENT_KEY))
          var value = c.getString(c.getColumnIndex(Constants.Content.CONTENT_VALUE))

          data.putString(key, value)

        } while (c.moveToNext());

        return data
      }

      return null
    }


    @ReactMethod
    fun insertData(url: String, key: String, value: String, promise: Promise) {
      var CONTENT_URI = Uri.parse(url)
      var values = ContentValues()
      values.put(Constants.Content.CONTENT_KEY, key)
      values.put(Constants.Content.CONTENT_VALUE, value)

      baseReactContext.contentResolver.insert(CONTENT_URI, values)

      var mUri = baseReactContext.contentResolver.insert(CONTENT_URI, values)

      promise.resolve(mUri != null)
    }


}
