package com.mieczkowskidev.instagramintegration

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val clientId = ""
    private val redirectUri = "sociallogin://redirect"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_web_view.loadUrl(
                "https://api.instagram.com/oauth/authorize/?client_id=$clientId&redirect_uri=$redirectUri&response_type=token")
    }
}
