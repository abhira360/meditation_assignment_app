package com.testingapp.abhishekmeditationapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var  viewModel: MeditationViewModel
 //   lateinit var firestore: FirebaseFirestore
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



        viewModel = this?.run {
            ViewModelProviders.of(this)[MeditationViewModel::class.java]
        }


  //      setupFireStore()
    }

//    fun setupFireStore() {
//
//        firestore = FirebaseFirestore.getInstance()
//        val collectionReference = firestore.collection("meditation")
//        collectionReference.addSnapshotListener { value, error ->
//            if (value == null || error != null) {
//                Toast.makeText(this,"Problem in fetching data", Toast.LENGTH_SHORT).show()
//                return@addSnapshotListener
//            }
////            Log.d("Data",value.toObjects(MeditationData::class.java).toString())
//
//        }
//
//    }

    override fun onClick(v: View?) {
        when(v?.id) {

            R.id.cd_meditation -> {

                val meditationActivity = Intent(this, MeditationActivity::class.java)
//                intent.putExtra("image","https://firebasestorage.googleapis.com/v0/b/blissful-ly.appspot.com/o/image%2Fphoto-1463704131914-97e5aaa0e339.jpeg?alt=media&token=65bfca8d-afe9-47f9-8dba-40250f54f118")
//                intent.putExtra("music", "https://firebasestorage.googleapis.com/v0/b/blissful-ly.appspot.com/o/audio%2FAcceptance.mp3?alt=media&token=7ff1a857-24db-4483-9f45-65b398274799")
                startActivity(meditationActivity)

            }

            R.id.cd_calm_down -> {

                val meditationActivity = Intent(this, MeditationActivity::class.java)
                startActivity(meditationActivity)

            }
            R.id.cd_destress -> {
                val meditationActivity = Intent(this, MeditationActivity::class.java)
                startActivity(meditationActivity)

            }
            R.id.cd_relax -> {
                val meditationActivity = Intent(this, MeditationActivity::class.java)
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