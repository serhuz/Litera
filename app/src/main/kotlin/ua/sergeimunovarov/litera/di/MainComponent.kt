/*
 *   Copyright © Sergei Munovarov. All rights reserved.
 *   See LICENCE for license details.
 */

package ua.sergeimunovarov.litera.di

import dagger.Component
import ua.sergeimunovarov.litera.di.annotation.MainActivityScope
import ua.sergeimunovarov.litera.main.MainActivity

@MainActivityScope
@Component(
        modules = [MainModule::class],
        dependencies = [AppComponent::class]
)
interface MainComponent {

    fun inject(mainActivity: MainActivity)
}