package com.example.core.utils

import android.content.Context
import com.example.core.BaseApplication
import com.example.core.R

object CacheUtils {
    @JvmStatic
    private var context = BaseApplication.currentApplication()
    @JvmStatic
    private var SP = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    @JvmStatic
    public fun save(key: String, value: String) {
        SP.edit().putString(key, value).apply()
    }

    @JvmStatic
    public fun get(key: String): String? {
        return SP.getString(key, null)
    }
}