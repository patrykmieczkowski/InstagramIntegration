package com.mieczkowskidev.instagramintegration

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.login_dialog.view.*

/**
 * Created by Patryk Mieczkowski on 11.07.2018
 */
class LoginDialog : DialogFragment() {

    companion object {

        fun newInstance(): LoginDialog = LoginDialog()
    }

    private val url = "https://api.instagram.com/oauth/authorize/?client_id=${Config.clientId}&redirect_uri=${Config.redirectUri}&response_type=token"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.login_dialog, container, false)

        view.login_web_view.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                Log.d("LoginDialog", url)

            }
        }

        view.login_web_view.loadUrl(url)

        return view
    }
}