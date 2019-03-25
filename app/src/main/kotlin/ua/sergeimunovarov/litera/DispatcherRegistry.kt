/*
 *   Copyright © Sergei Munovarov. All rights reserved.
 *   See LICENCE for license details.
 */

package ua.sergeimunovarov.litera

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object DispatcherRegistry {

    @JvmStatic
    var main: CoroutineDispatcher = Dispatchers.Main

    @JvmStatic
    var io: CoroutineDispatcher = Dispatchers.IO
}
