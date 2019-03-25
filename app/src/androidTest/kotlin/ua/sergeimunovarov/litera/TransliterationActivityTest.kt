/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import ua.sergeimunovarov.litera.translit.TransliterationActivity

class TransliterationActivityTest {

    @get:Rule
    val activityRule =
            ActivityTestRule<TransliterationActivity>(TransliterationActivity::class.java, true, false)

    @Test
    fun setProvidedInput() {
        val intent = Intent().apply { putExtra("text", "Test") }

        activityRule.launchActivity(intent)

        onView(withId(R.id.input)).check(matches(withText("Test")))
    }
}
