package com.example.newshub.activity.signinActivity.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.newshub.R
import com.example.newshub.activity.loginActivity.view.loginActivity
import com.example.newshub.databinding.ActivitySigninBinding
import com.google.firebase.auth.FirebaseAuth

class signinActivity : AppCompatActivity() {

    lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) { 
        super.onCreate(savedInstanceState)

        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signInwithEmail()

        backtoLogin()

    }

    private fun backtoLogin() {
        binding.loginRelativeLayout.setOnClickListener {

            var intent = Intent(this, loginActivity::class.java)
            startActivity(intent)

        }
    }

    //SignIn with Email
    fun signInwithEmail() {

        binding.signinButton.setOnClickListener {

            var firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.createUserWithEmailAndPassword(
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
                .addOnSuccessListener { result ->

                    Toast.makeText(this, "User Created Successfully", Toast.LENGTH_SHORT).show()

                    var intent = Intent(this, loginActivity::class.java)
                    startActivity(intent)

                }.addOnFailureListener { error ->

                    Toast.makeText(this, "Failed to Create User", Toast.LENGTH_SHORT).show()
                }

        }
    }
}
