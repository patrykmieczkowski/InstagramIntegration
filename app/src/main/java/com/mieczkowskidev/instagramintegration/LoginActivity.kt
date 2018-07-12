package com.mieczkowskidev.instagramintegration

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.login_dialog.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_dialog)

        login_web_view.webViewClient = object : WebViewClient() {


            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                Log.d("LoginDialog started: ", url)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                Log.d("LoginDialog finished", url)

                val substring = url?.substringAfter("access_token=", "")

                Log.d("LoginDialog finished: ", substring)


            }
        }

        login_web_view.settings.javaScriptEnabled = true

        login_web_view.loadUrl(signInInsta().toString())
    }

    fun signInInsta(): Uri {

        var uriBuilder = Uri.Builder()

        uriBuilder.scheme("https")
                .authority("api.instagram.com")
                .appendPath("oauth")
                .appendPath("authorize")
                .appendQueryParameter("client_id", Config.clientId)
                .appendQueryParameter("redirect_uri", Config.redirectUri)
                .appendQueryParameter("response_type", "token")

        return uriBuilder.build()
    }

}
