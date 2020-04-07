/**
 * see https://github.com/nemanja-kovacevic/recycler-view-swipe-to-delete/blob/master/app/src/main/java/net/nemanjakovacevic/recyclerviewswipetodelete/MainActivity.java
 */
package ua.sergeimunovarov.litera.main.history

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DeletionItemDecoration : RecyclerView.ItemDecoration() {

    private val background = ColorDrawable(Color.RED)

    private var isDeleting = false

    fun observeAdapter(adapter: HistoryAdapter) {
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                isDeleting = true
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                isDeleting = false
            }
        })
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.itemAnimator?.isRunning == true && isDeleting) {
            var lastViewComingDown: View? = null
            var firstViewComingUp: View? = null

            val left = 0
            val right = parent.width

            var top = 0
            var bottom = 0

            val childCount = parent.layoutManager!!.childCount
            for (i in 0 until childCount) {
                val child: View = parent.layoutManager!!.getChildAt(i)!!

                if (child.translationY < 0) {
                    lastViewComingDown = child
                } else if (child.translationY > 0) {
                    if (firstViewComingUp == null) {
                        firstViewComingUp = child
                    }
                }
            }

            if (lastViewComingDown != null && firstViewComingUp != null) {
                top = lastViewComingDown.bottom + lastViewComingDown.translationY.toInt()
                bottom = firstViewComingUp.top + firstViewComingUp.translationY.toInt()
            } else if (lastViewComingDown != null) {
                top = lastViewComingDown.bottom + lastViewComingDown.translationY.toInt()
                bottom = lastViewComingDown.bottom
            } else if (firstViewComingUp != null) {
                top = firstViewComingUp.top
                bottom = firstViewComingUp.top + firstViewComingUp.translationY.toInt()
            }

            background.setBounds(left, top, right, bottom)
            background.draw(c)
        }

        super.onDraw(c, parent, state)
    }
}
