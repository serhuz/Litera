/*
 *   Copyright © Sergei Munovarov. All rights reserved.
 *   See LICENCE for license details.
 */

package ua.sergeimunovarov.litera

import android.content.Context
import androidx.annotation.StringRes

class StringProvider(private val context: Context) {

    fun getString(@StringRes id: Int) = context.getString(id)
}
