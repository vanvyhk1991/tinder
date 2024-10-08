package com.app.tinder

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import com.app.tinder.model.Profile
import com.google.gson.GsonBuilder
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream


/**
 * Created by janisharali on 21/08/16.
 */
object Utils {
    private const val TAG = "Utils"

    fun loadProfiles(context: Context): List<Profile>? {
        try {
            val builder = GsonBuilder()
            val gson = builder.create()
            val array = JSONArray(loadJSONFromAsset(context, "profile.json"))
            val profileList: MutableList<Profile> = ArrayList()
            for (i in 0 until array.length()) {
                val profile = gson.fromJson(
                    array.getString(i),
                    Profile::class.java
                )
                profileList.add(profile)
            }
            return profileList
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    private fun loadJSONFromAsset(context: Context, jsonFileName: String): String? {
        var json: String? = null
        var `is`: InputStream? = null
        try {
            val manager = context.assets
            Log.d(TAG, "path $jsonFileName")
            `is` = manager.open(jsonFileName)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, charset("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun getDisplaySize(windowManager: WindowManager): Point {
        try {
            if (Build.VERSION.SDK_INT > 16) {
                val display = windowManager.defaultDisplay
                val displayMetrics = DisplayMetrics()
                display.getMetrics(displayMetrics)
                return Point(displayMetrics.widthPixels, displayMetrics.heightPixels)
            } else {
                return Point(0, 0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return Point(0, 0)
        }
    }

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}