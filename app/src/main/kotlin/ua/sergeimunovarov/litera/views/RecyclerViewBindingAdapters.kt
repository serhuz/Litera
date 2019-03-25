/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

@file:Suppress("unused")

package ua.sergeimunovarov.litera.views

import android.graphics.drawable.Drawable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("dividerDecoration")
fun RecyclerView.applyDivider(divider: Drawable) {
    addItemDecoration(
            DividerItemDecoration(context, RecyclerView.VERTICAL).apply { setDrawable(divider) }
    )
}

