package com.example.pokeshene.ui.Account

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pokeshene.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPasswordActivity : AppCompatActivity() {


    private var auth:FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)



        auth = FirebaseAuth.getInstance()



        btn_back!!.setOnClickListener(View.OnClickListener {
            finish()
        })
        btn_reset_password!!.setOnClickListener(View.OnClickListener {
            val email = email!!.text.toString().trim()
            if (TextUtils.isEmpty(email)){
                Toast.makeText(applicationContext,getString(R.string.enter_your_email),Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            progressBar!!.setVisibility(View.VISIBLE)

            auth!!.sendPasswordResetEmail(email)
                    .addOnCompleteListener(OnCompleteListener {
                        task ->
                        if (task.isSuccessful){
                            Toast.makeText(this@ResetPasswordActivity,getString(R.string.reset_email_instructions),Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(this@ResetPasswordActivity,getString(R.string.failed_reset_email),Toast.LENGTH_SHORT ).show()
                        }
                        progressBar!!.setVisibility(View.GONE)
                    })
            
        })
    }
}
