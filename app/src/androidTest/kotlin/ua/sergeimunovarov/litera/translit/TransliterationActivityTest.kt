/*
 *   Copyright © Sergei Munovarov. All rights reserved.
 *   See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.translit

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import ua.sergeimunovarov.litera.R

class TransliterationActivityTest {

    private lateinit var scenario: ActivityScenario<TransliterationActivity>

    @Test
    fun setProvidedInput() {
        scenario = ActivityScenario.launch(givenIntent())

        onView(withId(R.id.input)).check(matches(withText("Test")))
    }

    private fun givenIntent() =
        Intent(
                InstrumentationRegistry.getInstrumentation().targetContext,
                TransliterationActivity::class.java
        ).apply { putExtra("text", "Test") }

}
