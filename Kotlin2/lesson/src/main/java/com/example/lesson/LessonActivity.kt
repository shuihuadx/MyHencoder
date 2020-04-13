package com.example.lesson

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.core.BaseView
import com.example.lesson.entity.Lesson

class LessonActivity : AppCompatActivity(), BaseView<LessonPresenter>, Toolbar.OnMenuItemClickListener {
    private var lessonAdapter = LessonAdapter()
    private lateinit var refreshLayout: SwipeRefreshLayout

    /**
     * 实现父类(BaseView)的抽象属性
     * lazy 延迟属性：值只在第⼀次访问的时候计算
     */
    override val presenter: LessonPresenter by lazy {
        LessonPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        /*
        -返回⾃身 -> 从 apply 和 also 中选
            *作⽤域中使⽤ this 作为参数 ----> 选择 apply
            *作⽤域中使⽤ it 作为参数 ----> 选择 also
        -不需要返回⾃身 -> 从 run 和 let 中选择
            *作⽤域中使⽤ this 作为参数 ----> 选择 run
            *作⽤域中使⽤ it 作为参数 ----> 选择 let
         */
        findViewById<Toolbar>(R.id.toolbar)?.run {
            inflateMenu(R.menu.menu_lesson)
            setOnMenuItemClickListener(this@LessonActivity)
        }

        findViewById<RecyclerView>(R.id.list)?.run {
            layoutManager = LinearLayoutManager(this@LessonActivity)
            adapter = lessonAdapter
            addItemDecoration(DividerItemDecoration(this@LessonActivity, LinearLayout.VERTICAL))
        }

        refreshLayout = findViewById(R.id.swipe_refresh_layout)
        refreshLayout.setOnRefreshListener { presenter.fetchData() }
        refreshLayout.isRefreshing = true

        presenter.fetchData()
    }

    internal fun showResult(lessons: ArrayList<Lesson?>) {
        lessonAdapter.updateAndNotify(lessons)
        refreshLayout.isRefreshing = false
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        presenter.showPlayback()
        return false
    }

}