/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera

import java.io.File

object FileUtil {

    @JvmStatic
    fun aJson(filename: String): String =
            FileUtil::class.java.classLoader!!.getResource(filename).run {
                return File(path).readText(Charsets.UTF_8).trim()
            }
}

