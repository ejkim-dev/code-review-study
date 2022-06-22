package com.example.codereviewstudy.data.mapper

import com.example.codereviewstudy.data.entity.UserEntity
import com.example.codereviewstudy.domain.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserEntityMapper @Inject constructor() {
    fun transform(data: UserEntity): User = with(data) {
        return User(
            login,
            id,
            node_id,
            avatar_url,
            gravatar_id,
            url,
            html_url,
            followers_url,
            following_url,
            gists_url,
            starred_url,
            subscriptions_url,
            organizations_url,
            repos_url,
            events_url,
            received_events_url,
            type,
            site_admin,
            name,
            company,
            blog,
            location,
            email,
            hireable,
            bio,
            twitter_username,
            public_repos,
            public_gists,
            followers,
            following,
            created_at,
            updated_at,
        )
    }
}