package com.keshav.internproject.utlies

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.keshav.internproject.R
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

abstract class SwipeShareCallBack(context: Context): ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
    private val deletecolor= ContextCompat.getColor(context, R.color.share)
    private  val deleteicon  = R.drawable.icon_share
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        RecyclerViewSwipeDecorator.Builder(
            c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive
        )
            .addSwipeRightActionIcon(deleteicon)
            .addSwipeRightBackgroundColor(deletecolor)
            .create()
            .decorate()
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}