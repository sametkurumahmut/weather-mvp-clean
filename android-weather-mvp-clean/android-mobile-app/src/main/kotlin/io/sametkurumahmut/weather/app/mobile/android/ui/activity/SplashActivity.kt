package io.sametkurumahmut.weather.app.mobile.android.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    //region Activity Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.startActivity(Intent(this, MainActivity::class.java))
        this.finish()
    }
    //endregion
}
