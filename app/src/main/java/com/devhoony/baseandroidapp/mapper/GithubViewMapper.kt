package com.devhoony.baseandroidapp.mapper

import com.devhoony.baseandroidapp.feature.main.GithubInfoView
import com.devhoony.baseandroidapp.feature.main.GithubRepoView
import com.devhoony.baseandroidapp.feature.main.GithubUserView
import com.devhoony.domain.entity.GithubRepo
import com.devhoony.domain.entity.GithubUser
import com.devhoony.domain.usecase.github.GithubInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubViewMapper @Inject constructor() {


    fun transform(model: GithubUser): GithubUserView = with(model) {
        return GithubUserView(id = id, name = name, profileUrl = imageUrl)
    }

//    fun GithubUserEntity.transform(): GithubUser = with(this) {
//        return GithubUser(
//            id = id,
//            name = login,
//            imageUrl = avatar_url
//        )
//    }

    fun transform(model: GithubRepo) = with(model) {
        GithubRepoView(
            id = model.id,
            title = model.title,
            description = model.description,
            startCount = model.starCount
        )
    }

    fun transform(model: List<GithubInfo>) = with(model) {
        model.map {
            this@GithubViewMapper.transform(it)
        }
    }

    fun transform(model: GithubInfo) = with(model) {
        GithubInfoView(
            userView = user?.let { transform(it) },
            repoView = repo?.let { transform(it) }
        )
    }


//    fun transform(model: Flow<GithubUserEntity>): GithubUser = with(model) {
//        return GithubUser(
//            id = id,
//            name = login,
//            imageUrl = avatar_url
//        )
//    }

}