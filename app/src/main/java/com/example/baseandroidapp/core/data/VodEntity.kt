package com.example.baseandroidapp.core.data

import com.example.baseandroidapp.core.domain.Vod

data class VodEntity(
    val id: Int,
    val position: Int,
    val tutorialHistoryId: Int?,
    val viewCount: Int?,
    val videoLength: Double,
    val teaserStartTime: Double,
    val teaserEndTime: Double,
    val previousRecord: Double?,
    val thumbnailUrl: String,
    val streamUrl: String,
    val videoUrl: String,
    val s3Path: String,
    val bothSides: Boolean,
    val exposure: Boolean,
    val purchase: Boolean?,
    val danceLevel: DanceLevelDto,
    val tutorialDetails: List<TutorialDetailDto>,
    //    val tutorialSections:List<TutorialSection>,
//    val tutorialPreviews:List<TutorialPreview>,
//    val tutorialSubtitles: List<TutorialSubtitleDto>,
//    val tutorialPrices: List<TutorialPriceDto>,
){
    fun toVod() = Vod(
        id = id,
        title = streamUrl,
        description = teaserStartTime.toString(),
        imageUrl = thumbnailUrl
    )
}

data class VodDetailEntity(
    val id: Int,
    val position: Int,
    val tutorialHistoryId: Int?,
    val viewCount: Int?,
    val videoLength: Double,
    val teaserStartTime: Double,
    val teaserEndTime: Double,
    val previousRecord: Double?,
    val thumbnailUrl: String,
    val streamUrl: String,
    val videoUrl: String,
    val s3Path: String,
    val bothSides: Boolean,
    val exposure: Boolean,
    val purchase: Boolean?,
) {
    companion object {
        val empty = VodDetailEntity(
            0, 0, 0, 0, 0.0, 0.0, 0.0, 0.0, "",
            "", "", "", false, false, false
        )
    }
    fun toVod() = Vod(
        id = id,
        title = streamUrl,
        description = teaserStartTime.toString(),
        imageUrl = thumbnailUrl
    )
}

data class DanceLevelDto(
    val id: Int,
    val koName: String,
    val enName: String,
    val colorCode: String
)

data class TutorialDetailDto(
    val name: String,
    val description: String,
    val languageCode: String
)