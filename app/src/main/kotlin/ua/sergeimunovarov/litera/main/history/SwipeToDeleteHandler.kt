package ua.sergeimunovarov.litera.main.history

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeToDeleteHandler<out T : RecyclerView.ViewHolder>(private val onDelete: (T) -> Unit) :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    private val background = ColorDrawable(Color.TRANSPARENT)

    override fun onMove(recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder) = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        @Suppress("UNCHECKED_CAST")
        onDelete(viewHolder as T)
    }

    override fun onChildDraw(c: Canvas,
                             recyclerView: RecyclerView,
                             viewHolder: RecyclerView.ViewHolder,
                             dX: Float,
                             dY: Float,
                             actionState: Int,
                             isCurrentlyActive: Boolean) {
        if (viewHolder.adapterPosition < 0) return

        val view = viewHolder.itemView // the view being swiped

        // draw the red background, based on the offset of the swipe (dX)
        background.apply {
            setBounds(view.right + dX.toInt(), view.top, view.right, view.bottom)
            draw(c)
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}
