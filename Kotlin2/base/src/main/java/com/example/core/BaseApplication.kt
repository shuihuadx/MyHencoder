package com.example.core

import android.app.Application
import android.content.Context

class BaseApplication : Application() {
    companion object {
        /**
         * 将自动生成的set方法标记为private
         * "@get"注解重命名get方法名,方便Java代码调用
         */
        @get:JvmName("currentApplication")
        @JvmStatic
        lateinit var currentApplication: Context
            private set
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        currentApplication = base
    }
}