package com.example.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.app.entity.User
import com.example.app.widget.CodeView
import com.example.core.utils.CacheUtils
import com.example.core.utils.Utils
import com.example.lesson.LessonActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val usernameKey = "username"
    private val passwordKey = "password"

    private lateinit var et_username: EditText
    private lateinit var et_password: EditText
    private lateinit var et_code: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        et_code = findViewById(R.id.et_code)



        et_username.setText(CacheUtils.get(usernameKey))
        et_password.setText(CacheUtils.get(passwordKey))

        val btn_login = findViewById<Button>(R.id.btn_login)
        val img_code = findViewById<CodeView>(R.id.code_view)

        btn_login.setOnClickListener(this)
        img_code.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v is CodeView) {
            v.updateCode()
        } else if (v is Button) {
            login()
        }
    }

    private fun login() {
        val username = et_username.text.toString()
        val password = et_password.text.toString()
        val code = et_code.text.toString()

        val user = User(username, password, code)
        // 内部函数,每次被调用时,会产生一个函数对象
        fun verify(): Boolean {
            // 如果user.username为null,则user.username?.length这个整体也为null
            if (user.username?.length ?: 0 < 4) {
                Utils.toast("用户名不合法")
                return false
            }
            if (user.password?.length ?: 0 < 4) {
                Utils.toast("密码不合法")
                return false
            }
            return true
        }

        if (verify()) {
            CacheUtils.save(usernameKey, username)
            CacheUtils.save(passwordKey, password)

            // startActivity(Intent(this, LessonActivity::class.java))
            // startActivity(Intent(this, lessonActivity))
            myStartActivity<LessonActivity>()
        }
    }

    /**
     * 为Activity声明一个扩展的函数
     * 内联函数配合reified关键字,实现"真泛型"
     */
    private inline fun <reified T> Activity.myStartActivity() {
        startActivity(Intent(this, T::class.java))
    }

    /**
     * 为Activity声明一个扩展属性
     */
    private val Activity.lessonActivity by lazy { LessonActivity::class.java }
}