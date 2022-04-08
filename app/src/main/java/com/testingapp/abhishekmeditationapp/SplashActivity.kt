package com.testingapp.abhishekmeditationapp

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.airbnb.lottie.LottieDrawable
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setUpAnimation()
        auth = Firebase.auth
        val updateHandler = Handler()

        val runnable = Runnable {
            auth.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(baseContext, "Authentication Success.",
                            Toast.LENGTH_SHORT).show()
                        val user = auth.currentUser
                        val mainActivity = Intent(this, MainActivity::class.java)
                        mainActivity.putExtra("userId", user)
                        startActivity(mainActivity)

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(ContentValues.TAG, "signInAnonymously:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
        updateHandler.postDelayed(runnable, 1000)
    }

    fun setUpAnimation() {
        exclamation_view.setAnimation(R.raw.lottie_animation)
        exclamation_view.repeatCount = LottieDrawable.INFINITE
        exclamation_view.playAnimation()
    }
}