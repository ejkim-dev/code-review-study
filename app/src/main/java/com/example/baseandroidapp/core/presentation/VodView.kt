package com.example.baseandroidapp.core.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VodView(
    val id: Int,
    val title: String,
    val imageUrl: String,
) : Parcelable


data class VodDetailView(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
)