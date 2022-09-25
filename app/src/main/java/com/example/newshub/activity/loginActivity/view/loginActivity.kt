package com.example.newshub.activity.loginActivity.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.newshub.R
import com.example.newshub.activity.homescreenActivity.view.MainActivity
import com.example.newshub.activity.signinActivity.view.signinActivity
import com.example.newshub.databinding.ActivityLoginBinding
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.*
import java.util.*


class loginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var callbackManager: CallbackManager
    private val RC_SIGN_IN = 1
    val email = "email"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        loginwithEmail()

        loginwithgoogle()

        loginwithfacebook()

        backtoSignin()

    }

    private fun backtoSignin() {
        binding.signRelativeLayout.setOnClickListener {

            var intent = Intent(this, signinActivity::class.java)
            startActivity(intent)

        }
    }

    //Login with Email
    fun loginwithEmail() {

        binding.loginButton222.setOnClickListener {

            var firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.signInWithEmailAndPassword(
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString()
            ).addOnSuccessListener { result ->

                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()

                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }.addOnFailureListener { error ->
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }

        }

    }

    //Login with Google
    fun loginwithgoogle() {

        binding.googleButtonImage.setOnClickListener {

            binding.lottieAnimation.isVisible = true
            binding.lottieAnimation.playAnimation()

            Handler().postDelayed({ binding.lottieAnimation.isVisible = false }, 3000)

            var signInRequest = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id)).requestEmail()
                .build()


            var googleApiClient =
                GoogleApiClient.Builder(this).addApi(Auth.GOOGLE_SIGN_IN_API, signInRequest).build()

            var intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)

            startActivityForResult(intent, RC_SIGN_IN)


        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {

            RC_SIGN_IN -> {


                var result = Auth.GoogleSignInApi.getSignInResultFromIntent(data!!)

                var account = result?.signInAccount

                googleCredential(account?.idToken.toString())


            }

        }

    }

    private fun googleCredential(idtoken: String) {

        var credential = GoogleAuthProvider.getCredential(idtoken, null)

        var firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signInWithCredential(credential).addOnSuccessListener { result ->

            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
            .addOnFailureListener { error ->
                binding.lottieAnimation.isVisible = false
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }

    }

    //Login with Facebook
    private fun loginwithfacebook() {

        callbackManager = CallbackManager.Factory.create();

        binding.loginButton.setReadPermissions(Arrays.asList(email))

        binding.loginButton.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult?> {

                override fun onSuccess(result: LoginResult?) {

                    facebookCredential(result?.accessToken?.token.toString())
                }

                override fun onCancel() {
                    // App code
                }

                override fun onError(exception: FacebookException) {
                    // App code
                }

            })

    }

    private fun facebookCredential(idtoken: String) {

        var credential = FacebookAuthProvider.getCredential(idtoken)

        var firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signInWithCredential(credential).addOnSuccessListener {

            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }.addOnFailureListener {

            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }

}