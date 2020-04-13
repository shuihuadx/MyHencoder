package com.example.lesson

import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient
import com.example.core.utils.Utils
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class LessonPresenter constructor(private var activity: LessonActivity) {
    private val LESSON_PATH = "lessons"
    private var lessons = ArrayList<Lesson?>()
    private val type: Type? = object : TypeToken<ArrayList<Lesson?>?>() {}.type

    fun fetchData() {
        HttpClient.INSTANCE.get(LESSON_PATH, type, object : EntityCallback<ArrayList<Lesson?>> {
            override fun onSuccess(entity: ArrayList<Lesson?>) {
                this@LessonPresenter.lessons = entity

                activity.runOnUiThread {
                    activity.showResult(lessons)
                }
            }

            override fun onFailure(message: String?) {
                activity.run { Utils.toast(message) }
            }
        })
    }

    fun showPlayback() {
        /*
        不同的遍历方式
        val playbackLessons = ArrayList<Lesson?>()
        for (lesson in lessons) {
            println(lesson)
        }
        // for-i循环
        for (i in 0 until lessons.size) {
            println(lessons[i])
        }
        // 如果函数的最后⼀个参数是 lambda ，那么 lambda 表达式可以放在圆括号之外
        lessons.forEach() { lesson: Lesson? ->
            println(lesson)
        }
        // 如果你的函数传⼊参数只有⼀个 lambda 的话，那么⼩括号可以省略的
        lessons.forEach { lesson: Lesson? ->
            println(lesson)
        }
        // 如果 lambda 表达式只有⼀个参数，那么可以省略，通过隐式的 it 来访问
        lessons.forEach {
            println(it)
        }
         */

        // 使用filter过滤ArrayList,可是filter返回值为List类型的而不是MutableList
        val playbackLessonsTemp: List<Lesson?> = lessons.filter { it?.state == Lesson.State.PLAYBACK }
        val playbackLessons2 = ArrayList<Lesson?>()
        playbackLessons2.addAll(playbackLessonsTemp)
        activity.showResult(playbackLessons2)
    }
}