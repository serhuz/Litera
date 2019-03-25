/*
 * Copyright © Sergei Munovarov. All rights reserved.
 * See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.di

import dagger.Component
import ua.sergeimunovarov.litera.di.annotation.TransliterationActivityScope
import ua.sergeimunovarov.litera.translit.TransliterationActivity

@TransliterationActivityScope
@Component(
        modules = [TranslitModule::class],
        dependencies = [AppComponent::class]
)
interface TranslitComponent {

    fun inject(transliterationActivity: TransliterationActivity)
}
