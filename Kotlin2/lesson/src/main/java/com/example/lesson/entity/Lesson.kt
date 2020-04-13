package com.example.lesson.entity

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
internal data class Lesson constructor(var date: String?, var content: String?, var state: State?) {
    enum class State {
        PLAYBACK {
            override fun stateName(): String {
                return "有回放"
            }
        },
        LIVE {
            override fun stateName(): String {
                return "正在直播"
            }
        },
        WAIT {
            override fun stateName(): String {
                return "等待中"
            }

        };

        abstract fun stateName(): String
    }

}