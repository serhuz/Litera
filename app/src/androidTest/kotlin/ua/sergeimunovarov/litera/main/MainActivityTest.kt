/*
 *   Copyright © Sergei Munovarov. All rights reserved.
 *   See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.main

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.*
import org.koin.test.KoinTest
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito
import ua.sergeimunovarov.litera.BuildConfig
import ua.sergeimunovarov.litera.Events
import ua.sergeimunovarov.litera.R
import ua.sergeimunovarov.litera.translit.TransliterationActivity

@Ignore("Fix Activity not idle issue")
class MainActivityTest : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        Intents.init()
        declareMock<Events>()
    }

    @After
    fun tearDown() {
        Intents.release()
        scenario.close()
    }

    @Test
    fun openTransliterationActivity() {
        scenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withHint(R.string.hint_enter_text)).perform(click())

        intended(hasComponent(TransliterationActivity::class.java.name))
    }

    @Test
    fun openPrivacyPolicy() {
        scenario = ActivityScenario.launch(MainActivity::class.java)

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.menu_privacy_policy)).perform(click())

        intended(allOf(hasAction(Intent.ACTION_VIEW), hasData(BuildConfig.PRIVACY_POLICY_URL)))
    }

    @Test
    fun openLicences() {
        scenario = ActivityScenario.launch(MainActivity::class.java)

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.menu_oss_licenses)).perform(click())

        intended(hasComponent(OssLicensesMenuActivity::class.java.name))
    }


}
