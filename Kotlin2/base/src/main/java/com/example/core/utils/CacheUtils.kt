package com.example.core.utils

import android.content.Context
import com.example.core.BaseApplication
import com.example.core.R
import kotlin.reflect.KProperty

// 属性委托(by后面接对象,该对象需要实现getValue和setValue方法,如果是val关键字声明的,则只需实现getValue方法)
var name: String by Saver("name")
var password: String by Saver("password")

class Saver(private var key: String) {
    operator fun getValue(nothing: Nothing?, property: KProperty<*>): String {
        return CacheUtils.get(key)!!
    }

    operator fun setValue(nothing: Nothing?, property: KProperty<*>, value: String) {
        CacheUtils.save(key, value)
    }
}

object CacheUtils {
    @JvmStatic
    private var context = BaseApplication.currentApplication
    @JvmStatic
    private var SP = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    /**
     * 类型推断简化函数
     */
    @JvmStatic
    fun save(key: String, value: String) = SP.edit().putString(key, value).apply()

    /**
     * 类型推断简化函数
     */
    @JvmStatic
    fun get(key: String) = SP.getString(key, null)

    private fun test() {
        // 后面接上!!,强行将CacheUtils.get()返回值转换为非空类型(如果转换失败则抛出异常)
        val name: String = CacheUtils.get("testKey")!!
    }

}