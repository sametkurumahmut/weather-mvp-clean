package io.sametkurumahmut.weather.domain.device.settings

interface AppSettings {

    fun newOpenAppSettingsBuilder(): OpenAppSettings.Builder

    interface OpenAppSettings {

        fun open()

        interface Builder {

            fun build(): OpenAppSettings

            fun excludeFromRecentApps(): Builder

            fun openInNewScreen(): Builder
        }
    }
}
