package com.example.baseandroidapp.sample.pip

import android.app.PictureInPictureParams
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.baseandroidapp.AndroidApplication
import com.example.baseandroidapp.databinding.ActivityPipBinding
import com.example.baseandroidapp.util.PlayerUtil
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.cache.SimpleCache


class PipActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPipBinding

    private lateinit var player: ExoPlayer

    /** Player UI View */
    private lateinit var playerView: StyledPlayerView

    private val simpleCache: SimpleCache = AndroidApplication.simpleCache!!

    private var videoUrl = ""

    companion object {
        fun callingIntent(context: Context) = Intent(context, PipActivity::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPipBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val streamUrl =
            "https://one-m-online.s3.ap-northeast-2.amazonaws.com/online-lecture/1638332287717/HLS/1638332287717_TRAILER.m3u8"
        val url =
            "https://1milliondanceplus.s3.us-west-1.amazonaws.com/tutorial/10/Tina+Boo-B-Quick+Moves-1-Trailer.mp4"

        initPlayer(url)

        binding.btnPip.setOnClickListener {
            startPip()
        }


    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onResume() {
        super.onResume()
        if (videoUrl.isNotEmpty() && isInPictureInPictureMode) {
            initPlayer(videoUrl)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onPause() {
        super.onPause()
        if (::player.isInitialized && player.isPlaying) {
            startPip()
            Toast.makeText(this, "pip", Toast.LENGTH_SHORT).show()
        } else {
            releasePlayer()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        videoUrl = ""
    }


    private fun initPlayer(url: String) {
        playerView = binding.playerView

        videoUrl = url

        player = ExoPlayer.Builder(this)
            .setMediaSourceFactory(PlayerUtil.getCacheMediaSourceFactory(this, simpleCache))
            .build()


        val TAG = "media session"
        val mediaSessionCompat = MediaSessionCompat(this@PipActivity, TAG)

        mediaSessionCompat.isActive = true

        val connector = MediaSessionConnector(mediaSessionCompat)
        connector.setPlayer(player)

        playerView.player = player

        player.setMediaSource(PlayerUtil.buildMediaSource(this, url, simpleCache))

        player.playWhenReady = true

        player.prepare()
    }

    private fun releasePlayer() {
        if (::player.isInitialized) player.release()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startPip() {
        if (!isInPictureInPictureMode) {
            val pictureInPictureParams = PictureInPictureParams.Builder()
                .build()
            this.enterPictureInPictureMode(pictureInPictureParams)
            supportActionBar?.hide()
        } else {
            supportActionBar?.show()
        }

    }

    override fun onPictureInPictureModeChanged(
        isInPictureInPictureMode: Boolean,
        newConfig: Configuration?
    ) {
        if (isInPictureInPictureMode) {
            binding.viewContainer.isVisible = false
            playerView.useController = false
        } else {
            binding.viewContainer.isVisible = true
            playerView.useController = true
        }
    }
}