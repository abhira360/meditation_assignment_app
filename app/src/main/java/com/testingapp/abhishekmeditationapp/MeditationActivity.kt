package com.testingapp.abhishekmeditationapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_meditation.*


class MeditationActivity : AppCompatActivity() {

    private lateinit var simpleExoplayer: SimpleExoPlayer
    private var playbackPosition: Long = 0
    private var dashUrl = ""
    private val dataSourceFactory: com.google.android.exoplayer2.upstream.DataSource.Factory by lazy {
        DefaultDataSourceFactory(this, "exoplayer-sample")
    }
    var folder = ""
    var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meditation)

        folder = intent.getStringExtra("doc")!!
        id = intent.getStringExtra("sub_collection_doc")!!
        simpleExoplayer = SimpleExoPlayer.Builder(this).build()
        simpleExoplayer!!.addListener(object : Player.Listener { // player listener

            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                when (playbackState) { // check player play back state
                    Player.STATE_READY -> {
                    }
                    Player.STATE_ENDED -> {
                        //your logic
                    }
                    Player.STATE_BUFFERING ->{
                        //your logic
                    }
                    Player.STATE_IDLE -> {
                        //your logic
                    }
                    else -> {
                        exoplayerView.hideController()
                    }
                }
            }
        })

        setupFireStore()
    }

    fun setupFireStore() {

        val db = FirebaseFirestore.getInstance()
        val user = db.collection("meditation").document(folder).collection("session").document(id)

        user.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val doc = task.result
                Picasso.get().load(doc.get("imageLink").toString()).into(iv_image)
                iv_image.setScaleType(ImageView.ScaleType.FIT_CENTER)

                dashUrl = doc.get("link").toString()
                initializePlayer()
            }
        }
    }

    private fun initializePlayer() {
        val uri = Uri.parse(dashUrl)
        val mediaSource = buildMediaSource(uri, "dash")
        simpleExoplayer.prepare(mediaSource)
        exoplayerView.player = simpleExoplayer
        simpleExoplayer.seekTo(playbackPosition)
        simpleExoplayer.playWhenReady = true
    }

    private fun buildMediaSource(uri: Uri, type: String): MediaSource {
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(uri))

    }

    private fun releasePlayer() {
        playbackPosition = simpleExoplayer.currentPosition
        simpleExoplayer.release()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }



}