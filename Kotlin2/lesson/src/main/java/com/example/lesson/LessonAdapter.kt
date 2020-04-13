package com.example.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.core.BaseViewHolder
import com.example.lesson.entity.Lesson

class LessonAdapter : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {
    private var list = ArrayList<Lesson?>()
    internal fun updateAndNotify(list: ArrayList<Lesson?>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder.onCreate(parent)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    class LessonViewHolder(itemView: View) : BaseViewHolder(itemView) {

        companion object {
            fun onCreate(parent: ViewGroup): LessonViewHolder {
                return LessonViewHolder(LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_lesson, parent, false))
            }
        }

        internal fun onBind(lesson: Lesson?) {
            // lesson为null时,直接返回
            lesson ?: return
            // lesson为null时,直接抛出异常
            // lesson ?: throw IllegalAccessException("lesson is null")

            // ?:关键字,当数据为null时,可以为其赋予默认值
            val date = lesson.date ?: "日期待定"

            setText(R.id.tv_date, date)
            setText(R.id.tv_content, lesson.content)

            // ?. 配合 let 使用,可用于替换if(xxx!=null)
            lesson.state?.let {
                setText(R.id.tv_state, it.stateName())
                // when操作可以有返回值
                val colorRes = when (it) {
                    Lesson.State.PLAYBACK -> R.color.playback
                    Lesson.State.LIVE -> R.color.live
                    Lesson.State.WAIT -> R.color.wait
                }
                /*
                when不接受参数时,所有分支条件都是布尔表达式,可用于替换if-else-if
                 */
//                val colorRes = when {
//                    state == Lesson.State.PLAYBACK -> R.color.playback
//                    state == Lesson.State.LIVE -> R.color.live
//                    state == Lesson.State.WAIT -> R.color.wait
//                    else -> R.color.playback
//                }

                val backgroundColor = itemView.context.getColor(colorRes)
                getView<TextView>(R.id.tv_state).setBackgroundColor(backgroundColor)
            }
        }
    }
}