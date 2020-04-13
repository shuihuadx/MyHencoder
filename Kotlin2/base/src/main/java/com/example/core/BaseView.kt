package com.example.core

interface BaseView<T> {
    /**
     * 声明抽象属性
     */
    val presenter:T
}