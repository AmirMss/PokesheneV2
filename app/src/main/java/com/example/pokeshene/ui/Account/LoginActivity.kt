package com.example.pokeshene.ui.Account

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pokeshene.R
import com.example.pokeshene.Utils.Utils
import com.example.pokeshene.ui.main.MainActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.progressBar

import kotlinx.android.synthetic.main.activity_reset_password.btn_reset_password


class LoginActivity : AppCompatActivity() {


    private var auth:FirebaseAuth?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


//        if (auth!!.currentUser !=null){
//            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//            finish()
//        }



        auth = FirebaseAuth.getInstance()
//
        btn_signup.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@LoginActivity,
                SignupActivity::class.java))
        })
        btn_reset_password.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@LoginActivity,
                ResetPasswordActivity::class.java))
        })
        btn_login.setOnClickListener(View.OnClickListener {
           login()
        })
    }
    fun login(){
        val email = emailLogin.text.toString()
        val password = password.text.toString()

        if (!Utils.validateEmail(email)){
            Toast.makeText(applicationContext,"Please Entre your email.",Toast.LENGTH_SHORT).show()
            return
        }
        if (!Utils.validatePassword(password)) {
            Toast.makeText(applicationContext, "Please Enter a valid Password", Toast.LENGTH_SHORT).show()
            return
        }
        progressBar!!.visibility = View.VISIBLE

        auth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this@LoginActivity, OnCompleteListener {
                    task ->
                if (!task.isSuccessful)
                    Toast.makeText(this@LoginActivity,("Authentification failed"),Toast.LENGTH_LONG).show()
                else{
                    startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                }
                progressBar!!.visibility = View.GONE
            })
    }
}
