package com.mieczkowskidev.instagramintegration

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.login_dialog.*
import kotlinx.android.synthetic.main.login_dialog.view.*

/**
 * Created by Patryk Mieczkowski on 11.07.2018
 */
class LoginDialog : DialogFragment() {

    companion object {

        fun newInstance(): LoginDialog = LoginDialog()
    }

    lateinit var loginAction: LoginAction

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.login_dialog, container, false)

        dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        view.login_web_view.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                Log.d("LoginDialog started: ", url)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                hideProgressBar()

                Log.d("LoginDialog finished", url)

                val substring = url?.substringAfter("access_token=", "")

                if (substring != null && substring.isNotEmpty()) {
                    Log.d("LoginDialog finished: ", substring)
                    loginAction.loginTokenReceived(substring)
                    closeDialog()
                }
            }
        }

        view.login_web_view.settings.javaScriptEnabled = true

        view.login_web_view.loadUrl(signInInsta().toString())

        return view
    }

    private fun signInInsta(): Uri {

        val uriBuilder = Uri.Builder()

        uriBuilder.scheme("https")
                .authority("api.instagram.com")
                .appendPath("oauth")
                .appendPath("authorize")
                .appendQueryParameter("client_id", Config.clientId)
                .appendQueryParameter("redirect_uri", Config.redirectUri)
                .appendQueryParameter("response_type", "token")
                .build()

        return uriBuilder.build()
    }

    private fun hideProgressBar() {
        login_progress_bar.visibility = View.GONE
    }

    private fun closeDialog() {
        dialog.dismiss()
    }

    interface LoginAction {

        fun loginTokenReceived(token: String)
    }
}