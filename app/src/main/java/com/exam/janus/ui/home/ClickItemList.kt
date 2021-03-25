package com.exam.janus.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView

interface ClickItemList {
    fun clickButtons(view: View, position: Int)
}