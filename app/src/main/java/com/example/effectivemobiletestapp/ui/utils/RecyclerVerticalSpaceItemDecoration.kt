package com.example.effectivemobiletestapp.ui.utils

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView


class RecyclerVerticalSpaceItemDecoration(@DimenRes private val verticalSpace: Int)
    : RecyclerView.ItemDecoration()
{
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.adapter == null) return
        if (parent.getChildAdapterPosition(view) < parent.adapter!!.itemCount - 1) {
            outRect.bottom = verticalSpace
        }
    }
}