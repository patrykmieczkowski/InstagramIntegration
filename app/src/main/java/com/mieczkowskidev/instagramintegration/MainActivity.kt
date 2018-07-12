package com.mieczkowskidev.instagramintegration

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_button.setOnClickListener { showLoginDialog() }

    }

    private fun showLoginDialog() {

        LoginDialog.newInstance().show(supportFragmentManager, LoginDialog::class.java.simpleName)

//        val intent = Intent(this, LoginActivity::class.java)
//        startActivity(intent)
    }
}
