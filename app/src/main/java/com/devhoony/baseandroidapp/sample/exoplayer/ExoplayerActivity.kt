package com.devhoony.baseandroidapp.sample.exoplayer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.devhoony.baseandroidapp.AndroidApplication
import com.devhoony.baseandroidapp.databinding.ActivityExoplayerBinding
import com.devhoony.baseandroidapp.util.PlayerUtil
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.cache.SimpleCache

import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

import android.view.View
import android.view.ViewGroup


class ExoplayerActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: ActivityExoplayerBinding

    private lateinit var player: ExoPlayer

    /** Player UI View */
    private lateinit var playerView: StyledPlayerView

    private val simpleCache: SimpleCache = AndroidApplication.simpleCache!!

    // fullscreen mode check
    private var isFullScreen = false

    // sensor
    private lateinit var sensorManager: SensorManager
    private lateinit var gravitySensor: Sensor

    companion object {
        fun callingIntent(context: Context) = Intent(context, ExoplayerActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExoplayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val streamUrl =
            "https://one-m-online.s3.ap-northeast-2.amazonaws.com/online-lecture/1638332287717/HLS/1638332287717_TRAILER.m3u8"
        val url =
            "https://1milliondanceplus.s3.us-west-1.amazonaws.com/tutorial/10/Tina+Boo-B-Quick+Moves-1-Trailer.mp4"

        binding.btnMp4.setOnClickListener {
            Toast.makeText(this, "play mp4", Toast.LENGTH_SHORT).show()
            releasePlayer()
            initPlayer(url = url)

        }
        binding.btnStream.setOnClickListener {
            Toast.makeText(this, "play stream", Toast.LENGTH_SHORT).show()
            releasePlayer()
            initPlayer(url = streamUrl)
        }


        binding.btnFullscreen.setOnClickListener {
            changeFullScreenMode()
        }


        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)

    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, gravitySensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }


    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onBackPressed() {
        if (isFullScreen) {
            changeFullScreenMode()
            return
        }
        super.onBackPressed()

    }

    private fun initPlayer(url: String) {
        playerView = binding.playerView

        player = ExoPlayer.Builder(this)
            .setMediaSourceFactory(PlayerUtil.getCacheMediaSourceFactory(this, simpleCache))
            .build()

        playerView.player = player

        player.setMediaSource(PlayerUtil.buildMediaSource(this, url, simpleCache))

        player.playWhenReady = true

        player.prepare()
    }

    private fun releasePlayer() {
        if (::player.isInitialized) player.release()
    }


    private fun changeFullScreenMode() {
        if (isFullScreen) {
//                    fullscreenButton.setImageDrawable(
//                        ContextCompat.getDrawable(
//                            this@MainActivity,
//                            R.drawable.ic_fullscreen_open
//                        )
//                    )
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            if (supportActionBar != null) {
                supportActionBar!!.show()
            }
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            val params = playerView.layoutParams
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height =
                (200 * applicationContext.resources.displayMetrics.density).toInt()
            playerView.layoutParams = params
            isFullScreen = false
        } else {
//                    fullscreenButton.setImageDrawable(
//                        ContextCompat.getDrawable(
//                            this@MainActivity,
//                            R.drawable.ic_fullscreen_close
//                        )
//                    )
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
            if (supportActionBar != null) {
                supportActionBar!!.hide()
            }
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            val params = playerView.layoutParams
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.MATCH_PARENT
            playerView.layoutParams = params
            isFullScreen = true
        }
    }

    var isReverse = false

    fun rotateScreen() {
        if (isFullScreen) {
            requestedOrientation =
                if (isReverse) ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE else ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
            isReverse = !isReverse
            val params = playerView.layoutParams
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.MATCH_PARENT
            playerView.layoutParams = params
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor == gravitySensor) {
            val xAxis = event.values[0]
            if ((xAxis > 9.5 && isReverse) || (xAxis < -9.5 && !isReverse)) {
//                Toast.makeText(this, "rotate screen", Toast.LENGTH_SHORT).show()
                rotateScreen()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }


}