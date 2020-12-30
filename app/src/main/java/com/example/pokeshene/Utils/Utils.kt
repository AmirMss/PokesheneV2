package com.example.pokeshene.Utils

import android.text.TextUtils
import org.w3c.dom.Text

object Utils {
    fun validatePassword(password: String): Boolean{
        if (!TextUtils.isEmpty(password) && password.length > 6)
            return true
        return false
    }
    fun validateEmail(email: String): Boolean{
        if (!TextUtils.isEmpty(email) )
            return true
        return false
    }
}