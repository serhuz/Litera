/*
 *   Copyright © Sergei Munovarov. All rights reserved.
 *   See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.translit

import android.app.Activity
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Condition
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import ua.sergeimunovarov.litera.R
import ua.sergeimunovarov.litera.translit.translator.Translator
import ua.sergeimunovarov.litera.translit.translator.TranslatorFactory

class TransliterationActivityTest : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    private lateinit var translator: Translator

    private lateinit var scenario: ActivityScenario<TransliterationActivity>

    @Before
    fun setUp() {
        translator = mock {
            on { replaceCharacters(any()) } doReturn "test"
        }
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun clearInput() {
        scenario = ActivityScenario.launch(TransliterationActivity::class.java)

        onView(withId(R.id.input)).perform(typeText("something"))
        onView(withId(R.id.clear)).perform(click())
        onView(withId(R.id.input)).check(matches(withText("")))
        onView(withId(R.id.atRomanized)).check(matches(withText("")))
    }

    @Test
    fun closeActivity() {
        scenario = ActivityScenario.launch(TransliterationActivity::class.java)

        onView(withId(R.id.clear)).perform(click())

        assertThat(scenario.result.resultCode).isEqualTo(Activity.RESULT_CANCELED)
    }

    @Test
    fun transliterateInput() {
        declareMock<TranslatorFactory> {
            given(this.createTranslator()).willReturn(translator)
        }

        scenario = ActivityScenario.launch(TransliterationActivity::class.java)

        onView(withId(R.id.input)).perform(typeText("something"))
        onView(withId(R.id.atRomanized)).check(matches(withText("test")))
        verify(translator, atLeastOnce()).replaceCharacters(any())
    }

    @Test
    fun passDataInActivityResult() {
        declareMock<TranslatorFactory> {
            given(this.createTranslator()).willReturn(translator)
        }

        scenario = ActivityScenario.launch(TransliterationActivity::class.java)

        onView(withId(R.id.input)).perform(typeText("something"))
        onView(withId(R.id.atComplete)).perform(click())

        assertThat(scenario.result.resultCode).isEqualTo(Activity.RESULT_OK)
        assertThat(scenario.result.resultData).has(object : Condition<Intent>() {
            override fun matches(value: Intent) =
                    value.getStringExtra("input") == "something"
                            && value.getStringExtra("romanized") == "test"

        })
    }

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
