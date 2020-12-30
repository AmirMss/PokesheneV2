package com.example.pokeshene.ui.Account

import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.pokeshene.R
import com.example.pokeshene.Utils.Utils
import com.example.pokeshene.ui.main.MainActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_singup.*


class SignupActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)

        auth = Firebase.auth


        btn_reset_password!!.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@SignupActivity, ResetPasswordActivity::class.java))
        })

        sign_in_button!!.setOnClickListener(View.OnClickListener {
            finish()
        })

            sign_up_button!!.setOnClickListener(View.OnClickListener {

                signup()
            })

        }
    fun signup () {
        val email = emailSignup.text.toString().trim()
        val password = passwordSignup.text.toString().trim()

        if (!Utils.validateEmail(email)) {
            Toast.makeText(
                applicationContext,
                "Please Entre your email.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        if (!Utils.validatePassword(password)) {
            Toast.makeText(
                applicationContext,
                "Please Enter a valid Password",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        progressBar!!.setVisibility(View.VISIBLE)


        //create user
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, OnCompleteListener { task ->
                Toast.makeText(
                    this@SignupActivity,
                    "createUserWithEmail:onComplete" + task.isSuccessful,
                    Toast.LENGTH_SHORT
                ).show()
                progressBar!!.setVisibility(View.VISIBLE)

                if (!task.isSuccessful) {
                    Toast.makeText(
                        this@SignupActivity,
                        "User Not created",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@OnCompleteListener
                } else {
                    startActivity(Intent(this@SignupActivity, MainActivity::class.java))
                    finish()
                }

                progressBar!!.setVisibility(View.GONE)
            })
    }

        override fun onResume() {
            super.onResume()
            progressBar!!.setVisibility(View.GONE)
        }
    }

