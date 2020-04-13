package com.example.app

/**
 * 内联函数
 * 参数为函数类型的时候，可以减少函数对象的创建
 * 也可以减少函数调用栈(这点性能提升可以忽略)
 */
inline fun doIt(arg: String) {
    println(arg)
}

/**
 * 参数为函数类型的函数
 */
fun test(funArg: (String) -> Unit) {
    funArg("111")
}

fun main() {
    // 使用已声明好的函数作为参数,去调用参数含有函数类型的函数
    test(::doIt)
    // 使用匿名函数作为参数,去调用参数含有函数类型的函数
    test(fun(arg: String) {
        print(arg)
    })
    // 使用匿名函数作为参数,去调用参数含有函数类型的函数(lambda形式)
    test {
        print(it)
    }
}