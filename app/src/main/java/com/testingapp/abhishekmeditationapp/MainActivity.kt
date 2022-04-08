package com.testingapp.abhishekmeditationapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    var userId : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cd_meditation.setOnClickListener(this)
        cd_calm_down.setOnClickListener(this)
        cd_destress.setOnClickListener(this)
        cd_relax.setOnClickListener(this)
        bt_signOut.setOnClickListener(this)

        userId = intent.getStringExtra("userId")

    }


    override fun onClick(v: View?) {
        when(v?.id) {

            R.id.cd_meditation -> {

                val meditationActivity = Intent(this, MeditationActivity::class.java)
                meditationActivity.putExtra("doc", "focus")
                meditationActivity.putExtra("sub_collection_doc","y36OYfQ0dCW0XCfwrs1e" )
                startActivity(meditationActivity)

            }

            R.id.cd_calm_down -> {

                val meditationActivity = Intent(this, MeditationActivity::class.java)
                meditationActivity.putExtra("doc", "calm_down")
                meditationActivity.putExtra("sub_collection_doc","xQf14Gr2r6HNmwp0ZGZL" )
                startActivity(meditationActivity)

            }
            R.id.cd_destress -> {
                val meditationActivity = Intent(this, MeditationActivity::class.java)
                meditationActivity.putExtra("doc", "destress")
                meditationActivity.putExtra("sub_collection_doc","fgMpR4Y1ZTsr7RnaUf6p" )
                startActivity(meditationActivity)

            }
            R.id.cd_relax -> {
                val meditationActivity = Intent(this, MeditationActivity::class.java)
                meditationActivity.putExtra("doc", "relax")
                meditationActivity.putExtra("sub_collection_doc","ABDc11SDUaNvymUKgveo" )
                startActivity(meditationActivity)

            }

            R.id.bt_signOut -> {
                userId = null
                finishAffinity()
                finishAndRemoveTask()
            }

        }
    }
}