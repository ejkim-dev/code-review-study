package com.example.baseandroidapp.core.data

data class VodEntity (
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
)

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