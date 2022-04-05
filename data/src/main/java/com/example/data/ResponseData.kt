package com.example.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// response wrapper test
sealed class DefaultResult<T>(
    @SerializedName("code")
    @Expose
    var code: Int = -1,

    @SerializedName("msg")
    @Expose
    var msg: String? = null,

    @SerializedName("success")
    @Expose
    var success: Boolean = false,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("errKey")
    var errKey: String? = null
) {

    fun isSuccessful() = success

    class SingleResult<T>(
        @SerializedName("data")
        val data: T
    ) : DefaultResult<T>()

    class ListResult<T>(
        @SerializedName("list")
        val list: T
    ) : DefaultResult<T>()

    class PageResult<T>(
        @SerializedName("page")
        val page: PageObject<T>
    ) : DefaultResult<T>()

}


class PageObject<T>(
    val content: T
)



