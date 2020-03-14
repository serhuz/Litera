/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

abstract class BaseActivity : AppCompatActivity() {

    @FlowPreview
    @ExperimentalCoroutinesApi
    protected fun app() = application as Litera
}
