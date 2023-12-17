import org.gradle.kotlin.dsl.provider.inClassPathMode

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()

    }

}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }



}


rootProject.name = "Food_App(Retrofit_MVVM_Coroutines_Dager_Hilt_Room)"
include(":app")
