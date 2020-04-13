package com.example.app.entity

/**
 * 在主构造参数前⾯加上 var/val 使构造参数同时成为成员变量
 *
 * data class(数据类)会自动⽣成以下方法
 * toString()
 * hashCode()
 * equals()
 * copy() (浅拷⻉)
 * componentN()
 */
data class User @JvmOverloads constructor(var username: String? = null, var password: String? = null, var code: String? = null) {
    private fun test() {
        val user = User()
        // 对象解构
        val (name, pwd, code) = user
        print(name)
        print(pwd)
        print(code)
    }
}