package com.devhoony.data.mapper

import com.devhoony.data.GithubReposEntity
import com.devhoony.data.GithubUserEntity
import com.devhoony.domain.entity.GithubRepo
import com.devhoony.domain.entity.GithubUser
import javax.inject.Singleton

@Singleton
class GithubMapper {


    fun transform(model: GithubUserEntity): GithubUser = with(model) {
        return GithubUser(
            id = id,
            name = login,
            imageUrl = avatar_url
        )
    }

//    fun GithubUserEntity.transform(): GithubUser = with(this) {
//        return GithubUser(
//            id = id,
//            name = login,
//            imageUrl = avatar_url
//        )
//    }

    fun transform(model: GithubReposEntity) = with(model) {
        GithubRepo(
            id = id,
            title = name ?: "",
            description = description ?: "",
            starCount = stargazers_count ?: 0
        )
    }

    fun transform(model: List<GithubReposEntity>) = with(model) {
        model.map {
            this@GithubMapper.transform(it)
        }
    }


}