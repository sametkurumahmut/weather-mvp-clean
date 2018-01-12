package io.sametkurumahmut.weather.device.mobile.android.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import io.sametkurumahmut.weather.domain.device.settings.AppSettings
import javax.inject.Inject

class AppSettingsImpl @Inject constructor(private val context: Context) : AppSettings {

    //region AppSettings Methods
    override fun newOpenAppSettingsBuilder(): AppSettings.OpenAppSettings.Builder
            = OpenAppSettings.Builder(this.context)
    //endregion

    //region Nested Types
    class OpenAppSettings(builder: Builder) : AppSettings.OpenAppSettings {

        //region Properties
        private val context: Context
        private val intent: Intent
        //endregion

        //region init
        init {
            this.context = builder.context
            this.intent = builder.intent
        }
        //endregion

        //region AppSettings.OpenAppSettings Methods
        override fun open() {
            this.context.startActivity(this.intent)
        }
        //endregion

        //region Nested Types
        class Builder(val context: Context) : AppSettings.OpenAppSettings.Builder {

            val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", this.context.packageName, null))

            override fun build(): AppSettings.OpenAppSettings {
                return OpenAppSettings(this)
            }

            override fun excludeFromRecentApps(): AppSettings.OpenAppSettings.Builder {
                this.intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)

                return this
            }

            override fun openInNewScreen(): AppSettings.OpenAppSettings.Builder {
                this.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                return this
            }
        }
        //endregion
    }
    //endregion
}
