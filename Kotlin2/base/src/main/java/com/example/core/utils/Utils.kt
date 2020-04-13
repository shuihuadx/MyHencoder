package com.example.core.utils

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import com.example.core.BaseApplication

object Utils {
    private val displayMetrics = Resources.getSystem().displayMetrics
    fun dp2px(dp: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)

    /**
     * 函数参数默认值替换函数重载
     * 注解@JvmOverloads解决Java代码无法识别函数参数默认值的问题
     */
    @JvmOverloads
    fun toast(string: String?, duration: Int = Toast.LENGTH_SHORT) =
            Toast.makeText(BaseApplication.currentApplication, string, duration).show()
}