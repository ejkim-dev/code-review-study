package com.example.baseandroidapp.sample.exoplayer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.baseandroidapp.AndroidApplication
import com.example.baseandroidapp.databinding.ActivityExoplayerBinding
import com.example.baseandroidapp.util.PlayerUtil
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.cache.SimpleCache

class ExoplayerActivity : AppCompatActivity() {

    lateinit var binding: ActivityExoplayerBinding

    private lateinit var player: ExoPlayer

    /** Player UI View */
    private lateinit var playerView: StyledPlayerView

    companion object {
        fun callingIntent(context: Context) = Intent(context, ExoplayerActivity::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExoplayerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val streamUrl =
            "https://one-m-online.s3.ap-northeast-2.amazonaws.com/online-lecture/1638332287717/HLS/1638332287717_TRAILER.m3u8"
        val url = "https://1milliondanceplus.s3.us-west-1.amazonaws.com/tutorial/10/Tina+Boo-B-Quick+Moves-1-Trailer.mp4"


        binding.btnMp4.setOnClickListener {
            Toast.makeText(this, "play mp4", Toast.LENGTH_SHORT).show()
            if (::player.isInitialized) player.release()
            initPlayer(url = url)

        }
        binding.btnStream.setOnClickListener {
            Toast.makeText(this, "play stream", Toast.LENGTH_SHORT).show()
            if (::player.isInitialized) player.release()
            initPlayer(url = streamUrl)
        }


    }

    private val simpleCache: SimpleCache = AndroidApplication.simpleCache!!

    private fun initPlayer(url: String) {

        playerView = binding.playerView

        player = ExoPlayer.Builder(this)
            .setMediaSourceFactory(PlayerUtil.getCacheMediaSourceFactory(simpleCache))
            .build()

        playerView.player = player

        player.setMediaSource(PlayerUtil.buildMediaSource(url, simpleCache))

        player.playWhenReady = true

        player.prepare()
    }

    override fun onStop() {
        super.onStop()
        if (::player.isInitialized) player.release()
    }


}