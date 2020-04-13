package com.example.app.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.example.app.R
import com.example.core.utils.Utils
import java.util.*

class CodeView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : AppCompatTextView(context, attrs) {
    /**
     * Kotlin按照声明的先后顺序来初始化属性和init代码块
     * codeList放到init代码块前面来,防止init代码块中访问时codeList,抛出空指针异常
     */
    private var codeList: Array<String> = arrayOf(
            "kotlin",
            "android",
            "java",
            "http",
            "https",
            "okhttp",
            "retrofit",
            "tcp/ip"
    )

    private var paint: Paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = getContext().getColor(R.color.colorAccent)
        strokeWidth = Utils.dp2px(6f)
    }

    /**
     * 主构造函数不能有函数体
     * 函数体中的初始化逻辑可以防止init代码块中来做
     */
    init {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        gravity = Gravity.CENTER
        setBackgroundColor(getContext().getColor(R.color.colorPrimary))
        setTextColor(Color.WHITE)

        updateCode()
    }


    fun updateCode() {
        val random = Random().nextInt(codeList.size)
        val code = codeList[random]
        text = code
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawLine(0f, height.toFloat(), width.toFloat(), 0f, paint)
        super.onDraw(canvas)
    }
}