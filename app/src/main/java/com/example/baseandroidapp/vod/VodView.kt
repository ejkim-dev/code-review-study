package com.example.baseandroidapp.vod

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VodView(val id: Int, val title: String, val imageUrl: String) : Parcelable
