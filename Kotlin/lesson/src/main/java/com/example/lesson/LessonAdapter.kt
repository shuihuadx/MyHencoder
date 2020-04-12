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
            if (lesson == null) {
                return
            }
            var date = lesson.date
            if (date == null) {
                date = "日期待定"
            }
            setText(R.id.tv_date, date)

            setText(R.id.tv_content, lesson.content)

            val state = lesson.state
            if (state != null) {
                setText(R.id.tv_state, state.stateName())
                val colorRes = when (state) {
                    Lesson.State.PLAYBACK -> {
                        R.color.playback
                    }
                    Lesson.State.LIVE -> {
                        R.color.live
                    }
                    Lesson.State.WAIT -> {
                        R.color.wait
                    }
                }
                val backgroundColor = itemView.context.getColor(colorRes)
                getView<TextView>(R.id.tv_state).setBackgroundColor(backgroundColor)
            }
        }
    }
}