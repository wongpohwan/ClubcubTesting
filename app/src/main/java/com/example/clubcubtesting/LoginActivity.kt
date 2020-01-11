package com.example.clubcubtesting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        login_button_login.setOnClickListener{
            performLogin()
        }

        back_to_registration_textView.setOnClickListener{
            // go to RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun performLogin() {
        val email = email_edittext_login.text.toString()
        val password = password_edittext_login.text.toString()

        Log.d("Login", "Attempt login with email/pw $email/***")

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener{
            if(it.isSuccessful) {
                Log.d("LoginActivity","Login successfully with uid: ${it.result?.user?.uid}")
                val user = FirebaseAuth.getInstance().currentUser
                updateUI(user)
            }
            else {
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                updateUI(null)
            }


        }
    }


    private fun updateUI(currentUser: FirebaseUser?) {
        if(currentUser != null) {
             //start an intent with put extra
//            val emailLogin = email_edittext_login.text.toString()
//            val intent = Intent(this, HomepageActivity::class.java).apply {
//                putExtra("Email", emailLogin)
//            }
//            startActivity(intent)

            // start an intent (homepage) without put extra
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
            finish()
        }
        else {
            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
        }
    }
}