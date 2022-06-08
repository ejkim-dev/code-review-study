package com.devhoony.baseandroidapp

import android.app.Application
import com.google.android.exoplayer2.database.StandaloneDatabaseProvider
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AndroidApplication : Application() {

    companion object {
        var simpleCache: SimpleCache? = null
        var leastRecentlyUsedCacheEvictor: LeastRecentlyUsedCacheEvictor? = null
        var exoDatabaseProvider: StandaloneDatabaseProvider? = null
        var exoPlayerCacheSize: Long = 90 * 1024 * 1024L


        // app static variable
        const val GYOSI_COUNT = 6
        const val DAY_COUNT = 5
        const val BAN_COUNT = 10
    }

    override fun onCreate() {
        super.onCreate()
        if (leastRecentlyUsedCacheEvictor == null) {
            leastRecentlyUsedCacheEvictor = LeastRecentlyUsedCacheEvictor(exoPlayerCacheSize)
        }

        if (exoDatabaseProvider == null) {
            exoDatabaseProvider = StandaloneDatabaseProvider(this)
        }

        if (simpleCache == null) {
            simpleCache =
                SimpleCache(cacheDir, leastRecentlyUsedCacheEvictor!!, exoDatabaseProvider!!)
        }
    }

}