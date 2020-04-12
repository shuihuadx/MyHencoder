package com.example.core

import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder : RecyclerView.ViewHolder {
    private val viewHashMap = HashMap<Int, View>()
    constructor(itemView: View):super(itemView){

    }
    protected fun <T : View> getView(@IdRes id: Int): T {
        var view = viewHashMap[id]
        if (view == null) {
            view = itemView.findViewById(id)
            viewHashMap[id] = view
        }
        return view as T
    }

    protected fun setText(@IdRes id: Int, text: String?) {
        getView<TextView>(id).text = text
    }
}