/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.views

import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.snackbar.Snackbar

/*fun Snackbar.withTextColor(@ColorRes colorId: Int) {
    val contentLayout = (this.view as ViewGroup).getChildAt(0) as SnackbarContentLayout
    val tv = contentLayout.messageView
    tv.setTextColor(ResourcesCompat.getColor(context.resources, colorId, context.theme))
}*/

fun Snackbar.withBackgroundColor(@ColorRes colorId: Int): Snackbar {
    this.view.setBackgroundColor(ResourcesCompat.getColor(context.resources, colorId, context.theme))
    return this
}
