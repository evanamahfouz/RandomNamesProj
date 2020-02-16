package com.example.randomnamesproj.dagger.component

import com.example.randomnamesproj.dagger.module.AppModule
import com.example.randomnamesproj.dagger.module.FragmentsBindings
import com.example.randomnamesproj.dagger.module.ViewModelsModule
import dagger.BindsInstance

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, AndroidSupportInjectionModule::class,
        FragmentsBindings::class, ViewModelsModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder

        fun build(): AppComponent
    }
}

}