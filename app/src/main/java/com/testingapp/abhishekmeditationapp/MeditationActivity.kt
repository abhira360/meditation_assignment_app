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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_meditation.*


class MeditationActivity : AppCompatActivity() {

    private lateinit var viewModel: MeditationViewModel

    private lateinit var simpleExoplayer: SimpleExoPlayer
    private var playbackPosition: Long = 0
    private var dashUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
    private val urlList = listOf(dashUrl to "dash")
    var imageUrl = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fmobile.twitter.com%2Fgoogle&psig=AOvVaw1GNmkJ2-gcHLynJe7XsnZz&ust=1649491632270000&source=images&cd=vfe&ved=0CAoQjRxqFwoTCLi-gKiBhPcCFQAAAAAdAAAAABAD"
    private val dataSourceFactory: com.google.android.exoplayer2.upstream.DataSource.Factory by lazy {
        DefaultDataSourceFactory(this, "exoplayer-sample")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meditation)
        viewModel = this?.run {
            ViewModelProviders.of(this)[MeditationViewModel::class.java]
        }
//        viewModel.imageLink.value = "https://firebasestorage.googleapis.com/v0/b/blissful-ly.appspot.com/o/image%2Fphoto-1463704131914-97e5aaa0e339.jpeg?alt=media&token=65bfca8d-afe9-47f9-8dba-40250f54f118"
//        viewModel.musicLink.value = "https://firebasestorage.googleapis.com/v0/b/blissful-ly.appspot.com/o/audio%2FAcceptance.mp3?alt=media&token=7ff1a857-24db-4483-9f45-65b398274799"



//        imageUrl = viewModel.imageLink.value
        loadImage()
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


    }

    fun loadImage() {
        Picasso.get().load(imageUrl)
        iv_image.setScaleType(ImageView.ScaleType.FIT_CENTER)
    }

    fun playMusic() {

    }

    private fun initializePlayer() {
        val randomUrl = urlList.random()
        preparePlayer(randomUrl.first, randomUrl.second)
        exoplayerView.player = simpleExoplayer
        simpleExoplayer.seekTo(playbackPosition)
        simpleExoplayer.playWhenReady = true
    }

    private fun buildMediaSource(uri: Uri, type: String): MediaSource {
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(uri))

    }

    private fun preparePlayer(videoUrl: String, type: String) {
        val uri = Uri.parse(videoUrl)
        val mediaSource = buildMediaSource(uri, type)
        simpleExoplayer.prepare(mediaSource)
    }

    private fun releasePlayer() {
        playbackPosition = simpleExoplayer.currentPosition
        simpleExoplayer.release()
    }

    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

}