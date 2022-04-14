package com.example.baseandroidapp.util

import android.content.Context
import android.net.Uri
import com.example.baseandroidapp.R
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.source.rtsp.RtspMediaSource
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.cache.CacheDataSource
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import com.google.android.exoplayer2.util.Util

object PlayerUtil {

    /** media source code */
    fun buildMediaSource(context: Context, url: String, cache: SimpleCache? = null): MediaSource {
        val extension = url.split(".").last()
        val uri = Uri.parse(url)

        return if (cache == null) {
            val dataSourceFactory = buildDataSourceFactory(context)
            createMediaSource(uri, extension, dataSourceFactory)
        } else {
            val cacheDataSourceFactory = getCacheFactory(context, cache)
            createMediaSource(uri, extension, cacheDataSourceFactory)
        }
    }


    private fun getCacheFactory(context: Context, simpleCache: SimpleCache) =
        CacheDataSource.Factory()
            .setCache(simpleCache)
            .setUpstreamDataSourceFactory(buildDataSourceFactory(context))
            .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)

    fun getCacheMediaSourceFactory(context: Context, simpleCache: SimpleCache) =
        DefaultMediaSourceFactory(
            getCacheFactory(context, simpleCache)
        )

    private fun buildDataSourceFactory(context: Context): DataSource.Factory {
        val userAgent = Util.getUserAgent(context, context.getString(R.string.app_name))
        return DefaultHttpDataSource.Factory()
            .setAllowCrossProtocolRedirects(true)
            .setUserAgent(userAgent)
    }


    private fun createMediaSource(
        uri: Uri,
        extension: String,
        dataSourceFactory: DataSource.Factory
    ): MediaSource {
        when (@C.ContentType val type = Util.inferContentType(uri, extension)) {
            C.TYPE_DASH -> {
                return DashMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(MediaItem.fromUri(uri))
            }
            C.TYPE_HLS -> {
                return HlsMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(MediaItem.fromUri(uri))
            }
            C.TYPE_RTSP -> {
                return RtspMediaSource.Factory()
                    .createMediaSource(MediaItem.fromUri(uri))
            }
            C.TYPE_SS -> {
                return SsMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(MediaItem.fromUri(uri))
            }
            C.TYPE_OTHER -> {
                return ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(MediaItem.fromUri(uri))
            }
            else -> throw IllegalStateException("Unsupported type: $type")
        }
    }


}