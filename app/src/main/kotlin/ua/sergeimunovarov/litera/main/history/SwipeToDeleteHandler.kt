package ua.sergeimunovarov.litera.main.history

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeToDeleteHandler<out T : RecyclerView.ViewHolder>(
        private val deleteIcon: Drawable,
        private val onDelete: (T) -> Unit
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    private val iconIntrinsicWidth = deleteIcon.intrinsicWidth
    private val iconIntrinsicHeight = deleteIcon.intrinsicHeight
    private val deleteIconMargin = (16 * Resources.getSystem().displayMetrics.density).toInt()
    private val background = ColorDrawable(Color.RED)

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
        val height = view.bottom - view.top

        // draw the red background, based on the offset of the swipe (dX)
        background.apply {
            val left = if (dX < 0) view.right + dX.toInt() else view.left - dX.toInt()
            setBounds(left, view.top, view.right, view.bottom)
            draw(c)
        }

        deleteIcon.apply {
            val deleteIconTop = view.top + (height - iconIntrinsicHeight) / 2
            val deleteIconLeft =
                    if (dX < 0) view.right - deleteIconMargin - iconIntrinsicWidth
                    else view.left + deleteIconMargin
            val deleteIconRight =
                    if (dX < 0) view.right - deleteIconMargin
                    else view.left + deleteIconMargin + iconIntrinsicHeight
            val deleteIconBottom = deleteIconTop + iconIntrinsicHeight

            setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
            draw(c)
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}
