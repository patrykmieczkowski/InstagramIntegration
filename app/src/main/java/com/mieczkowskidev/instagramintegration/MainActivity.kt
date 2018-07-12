package com.mieczkowskidev.instagramintegration

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LoginDialog.LoginAction {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_button.setOnClickListener { showLoginDialog() }

    }

    private fun showLoginDialog() {

        val loginDialog = LoginDialog.newInstance()

        loginDialog.show(supportFragmentManager, LoginDialog::class.java.simpleName)
        loginDialog.loginAction = this
    }

    override fun loginTokenReceived(token: String) {

        login_button.visibility = View.GONE
        main_text.visibility = View.VISIBLE
        main_text.text = token
    }

}
